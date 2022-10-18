package com.task.excitel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.task.excitel.R
import com.task.excitel.databinding.FragmentCountryInfoBinding


class DetailsFragment: Fragment() {

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the ListFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCountryInfoBinding.inflate(inflater, container, false)

        val imgView = binding.root.findViewById<AppCompatImageView>(R.id.btn_close)
        imgView.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.remove(this)
            transaction?.commit()
        }
        binding.detail = (activity as MainActivity).viewModel.details.value
        return binding.root
    }
}