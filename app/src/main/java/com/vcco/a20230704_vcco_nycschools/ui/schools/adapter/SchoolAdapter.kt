package com.vcco.a20230704_vcco_nycschools.ui.schools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vcco.a20230704_vcco_nycschools.databinding.SchoolsAdapterBinding
import com.vcco.a20230704_vcco_nycschools.model.School

/**
 * Adapter for List of School
 */
class SchoolAdapter(private val clickListener: SchoolAdapterListener) :
    ListAdapter<School, SchoolAdapter.SchoolAdapterViewHolder>(SchoolDiffCallBack()) {
    /**
     * Create view holder in schools Adapter calling SchoolAdapterViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SchoolAdapterViewHolder.from(parent)

    /**
     * Bind school data in the giving position
     */
    override fun onBindViewHolder(holder: SchoolAdapterViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class SchoolAdapterViewHolder private constructor(private val binding: SchoolsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Bind school data in the giving position
         * @param item type School
         * @param clickListener type SchoolAdapterListener
         */
        fun bind(item: School, clickListener: SchoolAdapterListener) {
            binding.school = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            /**
             * Inflate View Holder with parent ViewGroup
             * @param parent ViewGroup
             */
            fun from(parent: ViewGroup): SchoolAdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SchoolsAdapterBinding.inflate(layoutInflater, parent, false)
                return SchoolAdapterViewHolder(binding)
            }
        }
    }
}

/**
 * Create the DiffCallBack to avoid no repeated
 */
class SchoolDiffCallBack : DiffUtil.ItemCallback<School>() {
    override fun areItemsTheSame(oldItem: School, newItem: School) = oldItem.dbn == newItem.dbn

    override fun areContentsTheSame(oldItem: School, newItem: School) = oldItem == newItem
}


/**
 * Listener for Adapter, when click the selected position retrieve the data of the position
 */
class SchoolAdapterListener(val clickListener: (schoool: String) -> Unit) {
    fun onClick(item: School) = clickListener(item.dbn)
}