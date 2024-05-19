package com.example.movieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.domain.model.MinMovie
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabLayout: TabLayout

    private lateinit var navControl: NavController

    private val viewModel by viewModels<MoviesViewModel>()

    private lateinit var movieDiscoverAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navControl = findNavController()
        viewModel.getMovieGenres()

        movieDiscoverAdapter = MovieAdapter(::onClickMovie)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewMovies.apply {
            adapter = movieDiscoverAdapter
            layoutManager = gridLayoutManager
        }

        tabLayout = binding.tablayoutGenres

        observeMoviesGenresUiState()
        getMoviesForEachTab()
        observeMoviesDiscoverUiState()

    }

    private fun observeMoviesGenresUiState() {
        viewModel.moviesGenres.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is GenresUiState.Failed -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }

                is GenresUiState.Loading -> {}
                is GenresUiState.Started -> {}
                is GenresUiState.Success -> {
                    tabLayout.removeAllTabs()
                    if (it.response.isNotEmpty()) {
                        viewModel.getMoviesDiscover(it.response[0].id.toString())
                    }

                    it.response.let { genres ->
                        for (genre in genres) {
                            val myTab = genre.id?.let { it1 ->
                                tabLayout.newTab()
                                    .setText(genre.name).setId(it1.toInt())
                            }

                            myTab?.let { tab ->
                                tabLayout.addTab(tab)
                            }
                        }

                    }
                    viewModel.getMoviesDiscover()
                }

            }

        }.launchIn(lifecycleScope)
    }

    private fun getMoviesForEachTab() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                val genreId = tab.id
                viewModel.getMoviesDiscover(genreId.toString())

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun observeMoviesDiscoverUiState() {
        viewModel.moviesDiscover.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is MovieUiState.Failed -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                }

                is MovieUiState.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is MovieUiState.Started -> {
                    binding.progressbar.visibility = View.GONE
                }

                is MovieUiState.Success -> {
                    binding.progressbar.visibility = View.GONE
                    movieDiscoverAdapter.submitList(null)
                    movieDiscoverAdapter.submitList(it.response)
                }
            }

        }.launchIn(lifecycleScope)
    }


    private fun onClickMovie(movie: MinMovie) {
          val action: HomeFragmentDirections.ActionHomeFragmentToDetailsFragment =
               HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
           findNavController().navigate(action)
    }
}