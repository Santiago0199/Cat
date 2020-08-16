package com.santiagoperdomo.cat.ui.vote_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.santiagoperdomo.cat.R

class VoteImageFragment : Fragment() {

    private lateinit var voteImageViewModel: VoteImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        voteImageViewModel =
            ViewModelProviders.of(this).get(VoteImageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vote_image, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        voteImageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}