package com.example.movieapp.presentation.home

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.domain.model.MinMovie
import com.example.movieapp.presentation.details.BASE_IMAGE_URL

class MovieAdapter(private val onClickMovie:(MinMovie)->Unit): ListAdapter<MinMovie, MovieAdapter.MovieViewHolder>(MovieDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutParams = binding.root.layoutParams
        layoutParams.width = parent.measuredWidth / 2 - convertDpToPx(parent.context, 8)
        binding.root.layoutParams = layoutParams
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.binding.tvTitle.text =it.title
            holder.binding.imgCover.load(BASE_IMAGE_URL+it.posterPath){
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_image_broken)
            }
            holder.binding.movieContainer.setOnClickListener {_ ->
                onClickMovie(it)
            }
        }

    }

    inner class MovieViewHolder(val binding : ListItemMovieBinding): RecyclerView.ViewHolder(binding.root)

}
fun convertDpToPx(context: Context, dip: Int): Int {
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        context.resources.displayMetrics
    )
    return px.toInt()
}