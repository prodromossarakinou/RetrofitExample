package gr.uom.gnp.retrofitexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

class CustomAdapter(context:Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){
    var photosList : List<RetroPhoto>? = null
    val context = context
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.titleView)
        val photoView = itemView.findViewById<ImageView>(R.id.photoView)
    }
    fun setPhotos(incomingPhotos: List<RetroPhoto>) {
        photosList = incomingPhotos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.custom_row,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(photosList==null) 0 else photosList!!.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        val photo = photosList!![position]

        holder.titleView.text = photo.title

        Picasso.with(context)
            .load(photo.thumbnailUrl)
            .into(holder.photoView)

    }

}