package com.example.practicemvvm.config

import android.app.Application
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.practicemvvm.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class GlobalApplication: Application() {
    companion object {
        //Retrofit2
        lateinit var baseService: Retrofit
            private set

        //Glide URL -> ImageView 데이터바인딩에서 사용하기 위한 메서드
        @BindingAdapter("imageFromUrl")
        @JvmStatic
        fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
            if(!imageUrl.isNullOrEmpty()) {
                Glide.with(view.context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
            } else {
                view.setImageDrawable(ContextCompat.getDrawable(view.context,
                    R.drawable.ic_no_image
                ))
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        baseService = initRetrofitBuilder()
    }

    private fun initRetrofitBuilder(): Retrofit {
        val BASE_URL = "https://solved.ac/"
        // 기본 URL에는 기본 주소만 넣어주도록 한다. /api/v3/도 넣어주면 URL에서 인식이 불가능하다.

        //OKHttp 클라이언트
        //private val okHttpClient = OkHttpClient.Builder() 만일 SSL인증이 필요없다면 이렇게 구현.
        val okHttpClient = getUnsafeOkHttpClient() // SSL인증서 허용을 위한 OkHttpClient.Builder
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            //.writeTimeout(20, TimeUnit.SECONDS) //쓰는 기능은 현재 프로젝트에 없음.
            //.addNetworkInterceptor(InterceptorForHeader()) 헤더가 필요 없음.
            .build()

        //리턴하는 레트로핏
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // SSL을 우회하는 OKHttpClient Builder를 생성한다. 모든 SSL 인증서를 허용하게 된다.
    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }

        return builder
    }
}