package com.example.paging2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android.codelabs.paging.ui.Repoadapter
import com.example.paging2.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.net.ssl.SSLSessionBindingEvent
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = Repoadapter()
    private val viewModel by viewModel<PagingViewModel>()
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)
        initAdapter()
        job = lifecycleScope.launch {
            viewModel.searchRepo(4).collectLatest {
                adapter.submitData(it)
            }
        }

    }

    private fun initAdapter() {
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
                binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
               binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

}