package com.example.movieapp

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.movieapp.data.database.MovieDataBase
import com.example.movieapp.data.database.entity.MovieGenreRef
import com.example.movieapp.data.database.entity.asGenreEntity
import com.example.movieapp.data.database.entity.asMovieEntity
import com.example.movieapp.data.service.MovieServices
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltWorker
class SyncDataWorker  @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    var dataBase: MovieDataBase,
    var service: MovieServices
) : Worker(appContext, workerParams) {

    private lateinit var job: Job


    override fun doWork(): Result {
        return try {
            job =   GlobalScope.launch(Dispatchers.IO) {
                dataBase.getMovieDao().deleteMovies()
                dataBase.getMovieDao().deleteMovieGenreRef()
                dataBase.getGenresDao().deleteGenres()

                val genres = service.getMovieGenres()


                genres.genres?.forEach { genre ->
                    val move = service.getDiscoverMovies(genresID = genre.id.toString())
                    move.results?.forEach {
                        dataBase.getMovieDao().insert(it.asMovieEntity())

                        if (genre.id != null && it.id != null) {
                            dataBase.getMovieDao().insertRef(MovieGenreRef(genre.id, it.id))
                        }

                    }

                    dataBase.getGenresDao().insert(genre.asGenreEntity())
                }

            }
            Result.success()
        } catch (e: Exception) {
            Log.d("WorkerAA", "doWork: ${e.message}")
            Result.failure()
        }
    }
    override fun onStopped() {
        super.onStopped()
        job.cancel()
    }

}


