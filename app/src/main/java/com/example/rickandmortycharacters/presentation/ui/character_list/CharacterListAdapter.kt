package com.example.rickandmortycharacters.presentation.ui.character_list

import android.content.Context
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.CharacterListItemBinding
import com.example.rickandmortycharacters.network.model.characters.Character
import com.example.rickandmortycharacters.presentation.ui.base.BaseRecyclerViewAdapter

class CharacterListAdapter(context: Context) : BaseRecyclerViewAdapter<Character, CharacterListItemBinding, CharacterListAdapter.ViewHolder>(
    context,
) {
    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.character_list_item
    }

    override fun bindViewHolder(
        binding: CharacterListItemBinding,
        item: Character,
        position: Int,
    ) {
        binding.character = item
        binding.root.setOnClickListener {
            val navController = Navigation.findNavController(binding.root)
            val bundle =
                Bundle().apply {
                    putInt("characterId", item.id ?: 1)
                }
            navController.navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle)
        }
    }

    override fun createViewHolder(binding: CharacterListItemBinding): ViewHolder {
        return ViewHolder(binding)
    }

    inner class ViewHolder(override val binding: CharacterListItemBinding): BaseViewHolder<Character, CharacterListItemBinding>(binding) {
        override fun bind(item: Character) {}
    }
}
