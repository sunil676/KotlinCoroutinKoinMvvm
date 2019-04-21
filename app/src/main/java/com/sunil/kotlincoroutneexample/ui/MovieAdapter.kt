package com.sunil.kotlincoroutneexample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunil.kotlincoroutneexample.R
import com.sunil.kotlincoroutneexample.data.MovieEntity
import kotlinx.android.synthetic.main.row_movie.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sunil.kotlincoroutneexample.utility.AppConstants


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.RecyclerViewAdapterViewHolder>() {

    var dataList: List<MovieEntity> = emptyList<MovieEntity>().toMutableList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)
        return RecyclerViewAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterViewHolder, position: Int) {
        val data = dataList[position]

        loadImageUrl(holder.imageView, data.posterPath)
    }

    override fun getItemCount(): Int = dataList.size

    inner class RecyclerViewAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.imageViewCover
    }

    fun loadImageUrl(view: ImageView, url: String?) {
        if (url != null && url != "")
            Glide.with(view.getContext())
                .load(AppConstants.IMAGE_ENDPOINT_PREFIX + url)
                .into(view)
    }

}