package com.islam.pureApp.presentation.view

import android.app.SearchManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.islam.pureApp.R
import com.islam.pureApp.common.PureApp
import com.islam.pureApp.common.gone
import com.islam.pureApp.common.visible
import com.islam.pureApp.databinding.ActivityMainBinding
import com.islam.pureApp.di.AppContainer
import com.islam.pureApp.presentation.view.adapter.WordListAdapter
import com.islam.pureApp.presentation.viewmodel.MainViewModel
import com.islam.pureApp.presentation.viewmodel.SortType

private const val TAG = "MainActivity"

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private lateinit var appContainer: AppContainer
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: WordListAdapter

    override fun setupOnCreate() {
        appContainer = (application as PureApp).appContainer
        val viewModelFactory = appContainer.viewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        initRecyclerView()
        observeData()
    }

    private fun observeData() {
        binding.loading.visible()
        viewModel.wordsList.observe(this) {
            it?.let {
                adapter.submitList(it)
                scrollToTop()
                binding.loading.gone()
            }
        }
    }

    private fun scrollToTop() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.wordsList.scrollToPosition(0)
        }, 500)
    }

    private fun initRecyclerView() {
        binding.wordsList.let {
            adapter = WordListAdapter()
            it.adapter = adapter
        }
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
                val sortType =
                    if (viewModel.currentSortType == SortType.ASCEND) SortType.DESCEND else SortType.ASCEND
                viewModel.sortList(sortType)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


