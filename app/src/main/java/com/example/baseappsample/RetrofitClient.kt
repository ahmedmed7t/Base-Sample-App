package com.example.baseappsample

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class RetrofitClient {
    var BASE_URL: String = "https://api.github.com/"

    private var instance: Api? = null

    private fun getOkHttpClient(context: Context) : OkHttpClient{

        var caFileInputStream = context.resources.openRawResource(R.raw.wikipedia_mid)
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null, null)

        // CertificateFactory
        val cfcaCert = CertificateFactory.getInstance("X.509")
        val potentialCA: Certificate = try {
            cfcaCert.generateCertificate(caFileInputStream)
        } finally {
            caFileInputStream.close()
        }
        keyStore.setCertificateEntry("av-ca", potentialCA)

        // Create a TrustManager that trusts the CAs in our KeyStore
        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        // Create an SSLContext that uses our TrustManager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.trustManagers, null)

        val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm()
        )

        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> = trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            ("Unexpected default trust managers:"
                    + trustManagers.contentToString())
        }
        val trustManager = trustManagers[0] as X509TrustManager

        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustManager)
            .build()
    }

    private fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getOkHttpClient(context))
            .build()
    }

    @Synchronized
    fun getInstance(context: Context): Api? {
        if (instance == null) {
            instance = provideRetrofit(context).create(Api::class.java)
        }
        return instance
    }
}