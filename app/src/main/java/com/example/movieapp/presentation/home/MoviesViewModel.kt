package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.model.MinMovie
import com.example.movieapp.domain.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MovieRepo): ViewModel() {
    private val _moviesGenres = MutableStateFlow<GenresUiState>(GenresUiState.Started)
    val moviesGenres = _moviesGenres.asStateFlow()

    private val _moviesDiscover = MutableStateFlow<MovieUiState>(MovieUiState.Started)
    val moviesDiscover = _moviesDiscover.asStateFlow()

    fun getMoviesDiscover(genresID:String =""){
        viewModelScope.launch {
            _moviesDiscover.value = MovieUiState.Loading
            try {
                _moviesDiscover.value = MovieUiState.Success(moviesRepo.getMoviesByGenresID(id = genresID))
            }catch(e: Exception){
                _moviesDiscover.value = MovieUiState.Failed(e.message.toString())
            }
        }

    }

    fun getMovieGenres(){
        viewModelScope.launch {
            _moviesGenres.value = GenresUiState.Loading
            try {
                _moviesGenres.value = GenresUiState.Success(moviesRepo.getMovieGenres())
            }catch(e: Exception){
                _moviesGenres.value = GenresUiState.Failed(e.message.toString())
            }
        }

    }
}

sealed interface GenresUiState{
    data object Started : GenresUiState
    data object Loading : GenresUiState
    data class Success(val response: List<Genre>): GenresUiState
    data class Failed(val message:String): GenresUiState

}
sealed interface MovieUiState{

    data object Started : MovieUiState
    data object Loading : MovieUiState
    data class Success(val response: List<MinMovie>): MovieUiState
    data class Failed(val message:String): MovieUiState
}
