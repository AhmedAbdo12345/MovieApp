package com.example.movieapp.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/"


class DetailsFragment : Fragment() {
    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = DetailsFragmentArgs.fromBundle(requireArguments()).movie

        binding.tvMovieTitle.text = movie.title
        binding.tvOverview.text = movie.overview
        binding.tvPopularity.text = movie.popularity.toString()
        binding.tvReleaseDate.text = movie.releaseDate
        binding.tvVoteAverage.text = movie.voteAverage.toString()
        binding.tvVoteCount.text = movie.voteCount.toString()
        binding.imgMoviePoster.load(BASE_IMAGE_URL+movie.posterPath){
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_image_broken)
        }

    }
}