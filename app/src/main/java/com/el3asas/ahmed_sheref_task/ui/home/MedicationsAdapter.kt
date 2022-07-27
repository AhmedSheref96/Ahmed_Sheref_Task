package com.el3asas.ahmed_sheref_task.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.ItemProblemHomeBinding
import com.el3asas.ahmed_sheref_task.models.AssociatedDrugItem
import com.el3asas.utils.binding.RecyclerAdapterBinding

class MedicationsAdapter(
    private val itemClickListener: MedicationItemClickListener,
    override val bindingInflater: (LayoutInflater) -> ViewBinding = ItemProblemHomeBinding::inflate
) : RecyclerAdapterBinding<ItemProblemHomeBinding>() {

    private lateinit var list: List<AssociatedDrugItem>

    override fun getItemCount() = if (this::list.isInitialized) list.size else 0

    override fun onBindViewHolder(holder: MainViewHolder<ItemProblemHomeBinding>, position: Int) {
        val item = list[position]
        holder.binding.apply {
            viewModel = item
            saveBtn.setOnClickListener {
                itemClickListener.onInsertItemClick(it, position)
            }
            root.setOnClickListener {
                itemClickListener.onItemClick(it, position)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<AssociatedDrugItem>) {
        list = data
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = list[position]

    interface MedicationItemClickListener {
        fun onItemClick(v: View, position: Int)
        fun onInsertItemClick(v: View, pos: Int)
    }

}