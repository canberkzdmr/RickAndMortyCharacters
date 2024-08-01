package com.example.rickandmortycharacters.presentation.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VB : ViewDataBinding, VH : BaseRecyclerViewAdapter.BaseViewHolder<T, VB>>(
    private val context: Context,
) : RecyclerView.Adapter<VH>() {

    protected val items: MutableList<T> = mutableListOf()

    fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(item: T) {
        val position = items.indexOf(item)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun createViewHolder(binding: VB): VH

    abstract fun bindViewHolder(binding: VB, item: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(context)
        val binding = createViewBinding(inflater, parent, viewType)
        return createViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        bindViewHolder(holder.binding, item, position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    private fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): VB {
        val layoutResId = getLayoutResId(viewType)
        return DataBindingUtil.inflate(inflater, layoutResId, parent, false)
    }

    abstract class BaseViewHolder<T, VB : ViewDataBinding>(open val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }
}