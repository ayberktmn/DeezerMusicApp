package com.ayberk.deezermusicapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.deezermusicapp.Retrofit.DeezerRetrofit
import com.ayberk.deezermusicapp.models.*
import com.ayberk.deezermusicapp.models.album.Album

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeezerViewModel@Inject constructor(private val repo: DeezerRetrofit) : ViewModel() {

    var CategoryList: MutableLiveData<Category>
    var DetailsList: MutableLiveData<Details>
    var ArtistList: MutableLiveData<artistX>
    var AlbumList : MutableLiveData<Album>
    var MusicList : MutableLiveData<com.ayberk.deezermusicapp.models.album.Album>


    init {
        CategoryList = MutableLiveData()
        DetailsList = MutableLiveData()
        ArtistList = MutableLiveData()
        AlbumList = MutableLiveData()
        MusicList = MutableLiveData()

    }

    fun getObserverLiveData(): MutableLiveData<Category>{
        return CategoryList
    }
    fun  loadCategory(){
        repo.getCategory(CategoryList)
    }
    fun getDetailsData(): MutableLiveData<Details>{
        return DetailsList
    }
    fun  loadDetails(id:Int){
        repo.getDetails(id,DetailsList)
    }
    fun getArtistData(): MutableLiveData<artistX>{
        return ArtistList
    }
    fun  loadArtist(idArtist:Int){
        repo.getArtist(idArtist,ArtistList)
    }
    fun getAlbumLiveData(): MutableLiveData<Album>{
        return AlbumList
        println("albumlistesiii ${AlbumList}")
    }
    fun  loadAlbum(idAlbums:Int){
        repo.getAlbum(idAlbums,AlbumList)
        println("viewmodel ${AlbumList}")
    }
    fun getMusicLiveData(): MutableLiveData<com.ayberk.deezermusicapp.models.album.Album>{
        return MusicList
    }
    fun  loadMusic(idMusic:Int){
        repo.getMusic(idMusic,MusicList)
    }

}