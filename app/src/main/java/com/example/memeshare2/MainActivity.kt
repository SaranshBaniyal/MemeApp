package com.example.memeshare2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.memeshare2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://meme-api.herokuapp.com"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener{
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(RetrofitApi::class.java)

            val retrofitData = retrofitBuilder.getMeme()

            retrofitData.enqueue(object : Callback<Meme?> {
                override fun onResponse(call: Call<Meme?>, response: Response<Meme?>) {

                    val responseBody = response.body()!!

                    var img_url:String = responseBody.url

                    Glide.with(this@MainActivity)
                        .load(img_url)
                        .fitCenter()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.placeholder)
                        .into(binding.imageView)

                }

                override fun onFailure(call: Call<Meme?>, t: Throwable) {

                }
            })
        }
    }
}