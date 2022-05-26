package com.islam.pureApp.presentation.view

import android.app.SearchManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.islam.pureApp.R
import com.islam.pureApp.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupOnCreate() {
        Log.d(TAG, "setupOnCreate")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView =
            menu?.findItem(R.id.startSearch)?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                Log.d(TAG, "onQueryTextSubmit: ")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                Log.d(TAG, "onQueryTextChange: ")
                return true
            }
        })

        searchView.setOnCloseListener {
            searchView.onActionViewCollapsed()
            true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.startSearch -> {
                Log.d(TAG, "start Search")
            }
            R.id.sortList -> {
                Log.d(TAG, "Sort List")

            }
        }
        return super.onOptionsItemSelected(item)
    }

}