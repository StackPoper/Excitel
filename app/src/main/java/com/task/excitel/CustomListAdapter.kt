package com.task.excitel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.excitel.data.ItemInfo
import com.task.excitel.databinding.InfoItemBinding
import com.task.excitel.ui.IOnItemInfo

class CustomListAdapter(private val onItemInfo: IOnItemInfo):
    ListAdapter<ItemInfo, CustomListAdapter.ShowViewHolder>(DiffCallback), Filterable {

    var itemsList: ArrayList<ItemInfo> = ArrayList()
    var itemsListFiltered: ArrayList<ItemInfo> = ArrayList()

    /**
     * The ShowViewHolder constructor takes the binding variable from the associated
     * ShowInfoItem, which gives it access to the [ItemInfo] information.
     */
    class ShowViewHolder(private var binding: InfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(showInfo: ItemInfo) {
            binding.item = showInfo
            // Forces the data binding to execute immediately, which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ItemInfo] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ItemInfo>() {
        override fun areItemsTheSame(oldItem: ItemInfo, newItem: ItemInfo): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: ItemInfo, newItem: ItemInfo): Boolean {
            return oldItem.name == newItem.name
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            InfoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = itemsListFiltered[position]
        holder.itemView.setOnClickListener {
            onItemInfo.click(holder.itemView, show)
        }
        holder.bind(show)
    }

    override fun getItemCount(): Int =
        itemsListFiltered.size

    fun addData(list: List<ItemInfo>) {
        val sortedList = list.sortedByDescending {
            it.population
        }
        itemsList = ArrayList(sortedList)
        itemsListFiltered = itemsList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                itemsListFiltered = if (charString.isEmpty())
                    itemsList
                else {
                    val filteredList = ArrayList<ItemInfo>()
                    itemsList
                        .filter {
                            it.name!!.contains(constraint!!, true)
                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply {
                    values = itemsListFiltered
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                itemsListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<ItemInfo>
                notifyDataSetChanged()
            }
        }
    }
}