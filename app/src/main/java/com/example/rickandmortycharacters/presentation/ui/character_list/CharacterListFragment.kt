package com.example.rickandmortycharacters.presentation.ui.character_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortycharacters.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: FragmentCharacterListBinding

    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.characters.value.isNullOrEmpty()) {
            viewModel.loadMoreCharacters()
        }
        setRecyclerView()
        setObservable()
    }

    private fun setObservable() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            characterAdapter.setItems(characters)
        }
    }

    private fun setRecyclerView() {
        characterRecyclerView = binding.characterListRecyclerView
        characterAdapter = CharacterListAdapter(requireContext())
        characterRecyclerView.adapter = characterAdapter
        val orientation = resources.configuration.orientation
        setRecyclerViewOrientation(orientation)
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        characterRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                ) {
                    val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPositions(null)[0]

                    if (!viewModel.isLoading.value!! && !viewModel.isLastPage.value!! && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        viewModel.loadMoreCharacters()
                    }
                }
            },
        )
    }

    private fun setRecyclerViewOrientation(orientation: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            characterRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        } else {
            characterRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setRecyclerViewOrientation(newConfig.orientation)
    }
}
