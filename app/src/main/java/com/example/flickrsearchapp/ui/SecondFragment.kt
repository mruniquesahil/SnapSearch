package com.example.flickrsearchapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flickrsearchapp.databinding.FragmentSecondBinding
import com.example.flickrsearchapp.network.PhotosRepository
import com.example.flickrsearchapp.network.RetrofitService
import com.example.flickrsearchapp.viewModel.FlickrViewModel
import com.example.flickrsearchapp.viewModel.FlickrViewModelFactory
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var viewModel: FlickrViewModel
    private val retrofitService = RetrofitService.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().run {
            ViewModelProvider(
                this,
                FlickrViewModelFactory(PhotosRepository(retrofitService))
            ).get(FlickrViewModel::class.java)
        }
        viewModel.imageUrl.observe(viewLifecycleOwner, Observer {
            it.let {
                Picasso.get().load(it).into(binding.imageView)
            }

        })

        binding.close.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}