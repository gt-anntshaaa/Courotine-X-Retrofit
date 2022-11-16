package com.example.courotinexretrofit.ui.PopularMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.courotinexretrofit.R
import com.example.courotinexretrofit.data.model.Movie
import com.example.courotinexretrofit.data.remote.APIClient
import com.example.courotinexretrofit.databinding.FragmentPopularMovieBinding
import com.example.courotinexretrofit.ui.adapter.AdapterMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopularMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentPopularMovieBinding? = null
    private val binding: FragmentPopularMovieBinding get() = _binding!!

    private lateinit var adapterMovie: AdapterMovie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setup adapter movie
        adapterMovie = AdapterMovie()
        binding.rvMovie.adapter = adapterMovie

        GlobalScope.launch(Dispatchers.IO) {
            val response = APIClient.service.getPopularMovie("2")

            val data = response.body()

            val listMovie = data?.results?.map {
                Movie(
                    ID = it.id.toString(),
                    poster = it.posterPath,
                    title = it.title,
                    rating = it.voteCount,
                    backdrop = it.backdropPath,
                    overview = it.overview
                )
            }

            withContext(Dispatchers.Main){
                // ui update
                if (listMovie != null) {
                    binding.progressBar.visibility = View.GONE
                    adapterMovie.addItemMovie(listMovie)
                }
            }
        }
    }

}