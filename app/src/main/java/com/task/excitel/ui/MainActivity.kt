package com.task.excitel.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.task.excitel.CustomListAdapter
import com.task.excitel.InfoViewModel
import com.task.excitel.R

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    val viewModel: InfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchViewItem = menu.findItem(R.id.search_item)
        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setIconifiedByDefault(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        filterAdapter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filterAdapter(newText)
        return false
    }

    private fun filterAdapter(query: String?) {
        supportFragmentManager.findFragmentById(R.id.infoFragment)?.let {
            if (it is ListFragment) {
                val adapter = it.getBindingAdapter()
                if (adapter is CustomListAdapter)
                    adapter.filter.filter(query)
            }
        }
    }
}