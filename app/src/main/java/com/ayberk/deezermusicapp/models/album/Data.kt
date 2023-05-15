package com.ayberk.deezermusicapp.models.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class Data(

    @ColumnInfo(name = "album")
    val album: AlbumX,
    @ColumnInfo(name = "artist")
    val artist: Artist,
    @ColumnInfo(name = "contributors")
    val contributors: List<Contributor>,
    @ColumnInfo(name = "duration")
    val duration: Int,
    @ColumnInfo(name = "explicit_content_cover")
    val explicit_content_cover: Int,
    @ColumnInfo(name = "explicit_content_lyrics")
    val explicit_content_lyrics: Int,
    @ColumnInfo(name = "explicit_lyrics")
    val explicit_lyrics: Boolean,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "md5_image")
    val md5_image: String,
    @ColumnInfo(name = "preview")
    val preview: String,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "readable")
    val readable: Boolean,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "title_short")
    val title_short: String,
    @ColumnInfo(name = "title_version")
    val title_version: String,
    @ColumnInfo(name = "type")
    val type: String
)