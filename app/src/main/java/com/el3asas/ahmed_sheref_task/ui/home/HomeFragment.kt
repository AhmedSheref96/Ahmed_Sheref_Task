package com.el3asas.ahmed_sheref_task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.el3asas.ahmed_sheref_task.databinding.FragmentHomeBinding
import com.el3asas.utils.binding.FragmentBinding
import com.el3asas.utils.utils.customSnackBar
import com.el3asas.utils.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentHomeBinding::inflate) :
    FragmentBinding<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = this@HomeFragment
            executePendingBindings()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.isError.collect {
                it?.let { it1 ->
                    customSnackBar(
                        requireView(),
                        it1,
                        com.el3asas.utils.R.drawable.ic_outline_error_outline_24
                    ) {}
                }
            }
        }

        setupRecyclerView()
        openWelcomeDialog()
    }

    private fun setupRecyclerView() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.insertToDB(viewHolder.absoluteAdapterPosition)
                    Toast.makeText(requireContext(), "Item Inserted", Toast.LENGTH_LONG).show()
                }

                override fun onMoved(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    fromPos: Int,
                    target: RecyclerView.ViewHolder,
                    toPos: Int,
                    x: Int,
                    y: Int
                ) {
                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun openWelcomeDialog() {
        if (viewModel.welocomeScreenShowed.not()) {
            viewModel.welocomeScreenShowed = true
            val dir = HomeFragmentDirections.actionHomeFragmentToWelcomeFragment()
            navigate(findNavController(), dir)
        }
    }

}