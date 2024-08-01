package com.example.rickandmortycharacters.presentation.ui.character_detail

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharacters.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailBinding

    private lateinit var episodeRecyclerView: RecyclerView
    private lateinit var episodeAdapter: EpisodeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterDetailLayout.visibility = View.INVISIBLE
        binding.progressIndicator.visibility = View.VISIBLE
        viewModel.fetchCharacterDetail(requireArguments().getInt("characterId"))
        setRecyclerView()
        setObservables()
    }

    private fun setObservables() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            binding.character = character
        }
        viewModel.episodes.observe(viewLifecycleOwner) { episodes ->
            episodeAdapter.setItems(episodes)
            binding.characterDetailLayout.visibility = View.VISIBLE
            binding.progressIndicator.visibility = View.GONE
        }
    }

    private fun setRecyclerView() {
        episodeRecyclerView = binding.episodesRecyclerView
        episodeAdapter = EpisodeListAdapter(requireContext())
        episodeRecyclerView.adapter = episodeAdapter
        episodeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}