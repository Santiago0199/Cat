package com.santiagoperdomo.cat.ui.breed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.santiagoperdomo.cat.R

class BreedFragment : Fragment() {

    private lateinit var breedViewModel: BreedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        breedViewModel =
            ViewModelProviders.of(this).get(BreedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_breed, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        breedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}