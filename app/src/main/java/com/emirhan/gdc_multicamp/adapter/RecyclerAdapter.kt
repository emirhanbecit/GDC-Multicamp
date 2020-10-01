package com.emirhan.gdc_multicamp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import com.emirhan.gdc_multicamp.R
import com.emirhan.gdc_multicamp.model.Post
import kotlinx.android.synthetic.main.item_post.view.*


class RecyclerAdapter(private val postList : MutableList<Post>, private val  onClick : (Post) -> Unit )

    : RecyclerView.Adapter<PostViewHolder>() {

    override fun getItemCount(): Int {
        return postList.size // Kaç tane layout oluşturulacağını, listemizin boyutunu atayarak belirtiyoruz.
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        // Verileri bağlama işlemini burada yapıyoruz ->

        val post = postList[position]
        holder.bind(post,onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
        // Gösterilecek olan layoutu gönderiyoruz
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(post : Post , onClick : (Post) -> Unit ){
        itemView.banner.load(post.banner){
            placeholder(R.drawable.google_logo)
        }

        // Layouta tıklandığında olacak işlemi onClick ile belirtiyoruz.
        itemView.setOnClickListener {
            onClick(post)
        }
    }


}


