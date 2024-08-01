package com.example.rickandmortycharacters.presentation.ui.character_detail

import android.content.Context
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.EpisodeListItemBinding
import com.example.rickandmortycharacters.network.model.character_detail.Episode
import com.example.rickandmortycharacters.presentation.ui.base.BaseRecyclerViewAdapter

class EpisodeListAdapter(context: Context): BaseRecyclerViewAdapter<Episode, EpisodeListItemBinding, EpisodeListAdapter.ViewHolder>(context) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.episode_list_item
    }

    override fun bindViewHolder(binding: EpisodeListItemBinding, item: Episode, position: Int) {
        binding.episode = item
    }

    override fun createViewHolder(binding: EpisodeListItemBinding): ViewHolder {
        return ViewHolder(binding)
    }

    inner class ViewHolder(override val binding: EpisodeListItemBinding): BaseViewHolder<Episode, EpisodeListItemBinding>(binding) {
        override fun bind(item: Episode) {}
    }
}