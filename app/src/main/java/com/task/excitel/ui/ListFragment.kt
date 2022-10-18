package com.task.excitel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.task.excitel.CustomListAdapter
import com.task.excitel.R
import com.task.excitel.data.ItemInfo
import com.task.excitel.databinding.FragmentCountryBinding

class ListFragment: Fragment(), IOnItemInfo {
    lateinit var binding: ViewDataBinding

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the ListFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the ListFragment
        (binding as FragmentCountryBinding).viewModel = (activity as MainActivity).viewModel

        // Sets the adapter of the ShowsListAdapter RecyclerView
        (binding as FragmentCountryBinding).showsList.adapter = CustomListAdapter(this)

        return binding.root
    }

    fun getBindingAdapter(): CustomListAdapter? {
        if (binding is FragmentCountryBinding)
            return (binding as FragmentCountryBinding).showsList.adapter as CustomListAdapter
        return null
    }

    override fun click(item: View, info: ItemInfo) {
        (activity as MainActivity).viewModel.setDetailsInfo(info)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.add(R.id.infoFragment, DetailsFragment())
        transaction?.commit()
    }
}