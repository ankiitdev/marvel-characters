package com.ankiitdev.marvelcharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ankiitdev.marvelcharacters.core.models.CharacterEntity
import com.ankiitdev.marvelcharacters.databinding.ItemMarvelCharacterBinding
import com.bumptech.glide.Glide

class MarvelCharactersAdapter constructor(
    private val onClick: (CharacterEntity) -> Unit
) : ListAdapter<CharacterEntity, MarvelCharactersAdapter.SentMessagesViewHolder>(
    object : DiffUtil.ItemCallback<CharacterEntity>() {
        override fun areItemsTheSame(
            oldItem: CharacterEntity,
            newItem: CharacterEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterEntity,
            newItem: CharacterEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SentMessagesViewHolder {
        val binding = ItemMarvelCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SentMessagesViewHolder(binding, onClick = {
            if (it != -1) {
                getItem(it)?.let { character ->
                    onClick(character)
                }
            }
        })
    }

    override fun onBindViewHolder(holder: SentMessagesViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class SentMessagesViewHolder(
        private val binding: ItemMarvelCharacterBinding,
        onClick: ((Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick?.invoke(absoluteAdapterPosition)
            }
        }

        fun setData(characterEntity: CharacterEntity?) {
            binding.characterName.text = characterEntity?.name
            val url = "${characterEntity?.thumbnail?.path}.${characterEntity?.thumbnail?.extension}"
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.imageCharacter)
        }
    }
}
