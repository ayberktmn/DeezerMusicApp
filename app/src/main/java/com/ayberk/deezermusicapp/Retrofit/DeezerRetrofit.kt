package com.ayberk.deezermusicapp.Retrofit

import android.widget.Toast
import androidx.compose.ui.unit.IntSize
import androidx.core.util.rangeTo
import androidx.lifecycle.MutableLiveData
import com.ayberk.deezermusicapp.models.*
import com.ayberk.deezermusicapp.models.album.Album
import okhttp3.internal.toHexString
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class DeezerRetrofit @Inject constructor(private val retroService: Retrofit) {

    fun getCategory(liveData: MutableLiveData<Category>){
        retroService.getCategory().enqueue(object : retrofit2.Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getDetails(id:Int,liveData: MutableLiveData<Details>){
        retroService.getDetails(id).enqueue(object : retrofit2.Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getArtist(idArtist:Int,liveData: MutableLiveData<artistX>){
        retroService.getArtist(idArtist).enqueue(object : retrofit2.Callback<artistX>{

            override fun onResponse(call: Call<artistX>, response: Response<artistX>) {
                liveData.postValue(response.body())

            }

            override fun onFailure(call: Call<artistX>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getAlbum(idAlbums: Int,liveData: MutableLiveData<com.ayberk.deezermusicapp.models.album.Album>){
        retroService.getAlbum(idAlbums).enqueue(object : retrofit2.Callback<com.ayberk.deezermusicapp.models.album.Album>{
            override fun onResponse(call: Call<com.ayberk.deezermusicapp.models.album.Album>, response: Response<com.ayberk.deezermusicapp.models.album.Album>) {

                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                    println("retrofit ${liveData.value}")
                } else  {
                    liveData.postValue(null)
                    println("retrofit fail ${response.code()}")
                }
            }

            override fun onFailure(call: Call<com.ayberk.deezermusicapp.models.album.Album>, t: Throwable) {
                liveData.postValue(null)
                println("retrofit faill ${t.message}")
            }
        })
    }

    fun getMusic(idMusic:Int,liveData: MutableLiveData<com.ayberk.deezermusicapp.models.album.Album>){
        retroService.getMusic(idMusic).enqueue(object : retrofit2.Callback<com.ayberk.deezermusicapp.models.album.Album>{
            override fun onResponse(call: Call<com.ayberk.deezermusicapp.models.album.Album>, response: Response<com.ayberk.deezermusicapp.models.album.Album>) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<com.ayberk.deezermusicapp.models.album.Album>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}