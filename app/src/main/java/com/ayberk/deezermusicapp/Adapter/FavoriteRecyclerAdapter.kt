package com.ayberk.deezermusicapp.Adapter
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ayberk.deezermusicapp.Room.DataDao
import com.ayberk.deezermusicapp.R
import com.ayberk.deezermusicapp.Room.RoomDataBase
import com.ayberk.deezermusicapp.databinding.FavoriteItemBinding
import com.ayberk.deezermusicapp.models.album.favoriMusic
import com.bumptech.glide.Glide


class FavoriteRecyclerAdapter(private val FavoriteMusicList:ArrayList<favoriMusic>) : RecyclerView.Adapter<FavoriteRecyclerAdapter.MusicFavoriHolder>() {
        private lateinit var db : RoomDataBase
        private lateinit var adventDao: DataDao

        class MusicFavoriHolder (val binding : FavoriteItemBinding): RecyclerView.ViewHolder(binding.root) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicFavoriHolder {
            val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
            return MusicFavoriHolder(binding)

        }

        override fun getItemCount(): Int {
            return FavoriteMusicList.size
        }

        override fun onBindViewHolder(holder: MusicFavoriHolder, position: Int) {
            holder.binding.textView2.text = FavoriteMusicList[position].title
            val minutes = FavoriteMusicList[position].duration / 60
            val seconds = FavoriteMusicList[position].duration % 60
            val durationText = String.format("%d:%02d", minutes, seconds)
            holder.binding.textView3.text = "Süre :" + durationText

            var mediaPlayer: MediaPlayer? = null
            var currentMedia: String? = null

            holder.binding.imgPlayMusic.setOnClickListener {
                val mediaList = FavoriteMusicList[position].preview

                if (mediaPlayer?.isPlaying == true && currentMedia == mediaList) {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                    currentMedia = null
                } else {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                    mediaPlayer?.release() // önceki şarkıyı durdurmak için
                    mediaPlayer = MediaPlayer()
                    mediaPlayer?.setOnCompletionListener {
                        currentMedia = null
                        mediaPlayer = null
                    }
                    mediaPlayer?.setDataSource(mediaList)
                    mediaPlayer?.prepare()
                    mediaPlayer?.start()
                    currentMedia = mediaList
                }
            }

            Glide.with(holder.binding.imageView)
                .load("https://e-cdns-images.dzcdn.net/images/cover/" + FavoriteMusicList[position].md5_image + "/500x500-000000-80-0-0.jpg")
                .into(holder.binding.imageView)
            db =
                Room.databaseBuilder(holder.binding.root.context, RoomDataBase::class.java, "Music")
                    .allowMainThreadQueries()
                    .build()
            adventDao = db.dataDao()

            holder.binding.deleteMusic.setOnClickListener {
                val favoriMusic = FavoriteMusicList[position]
                adventDao.delete(favoriMusic)
                Toast.makeText(holder.itemView.context, "Favorilerden Silindi", Toast.LENGTH_SHORT)
                    .show()

                // Favori müzik öğesini listenizden kaldırın ve RecyclerView'i güncelleyin
                FavoriteMusicList.removeAt(position)
                notifyItemRemoved(position)
                holder.itemView.findNavController().navigate(R.id.favoriFragment)
            }
        }
}