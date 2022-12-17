package com.example.android3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.android3.databinding.CardBinding
import java.util.*
import kotlin.collections.ArrayList

class CustomRecyclerAdapter(private val context: ArrayList<Card>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    var cards = emptyArray<Card>()
    private var valueFilter: ValueFilter? = null
    private val inflater: LayoutInflater? = null

    class MyViewHolder(val binding: CardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = cards[position]

        with(holder.binding) {
            name.text = user.name
            description.text = user.description
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun setList(list: Array<Card>) {
        cards = list
        notifyDataSetChanged()
    }

    fun getFilter(): Filter? {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }

        return valueFilter
    }

    class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            val filterList: MutableList<String> = ArrayList()
            val mStringFilterList: List<String>? = null

            if (constraint != null && constraint.isNotEmpty()) {
                if (mStringFilterList != null) {
                    for (i in 0 until mStringFilterList.size) {
                        if (mStringFilterList != null) {
                            if (mStringFilterList[i].uppercase(Locale.ROOT).contains(
                                    constraint.toString().uppercase(
                                        Locale.getDefault()
                                    )
                                )
                            ) {
                                filterList.add(mStringFilterList.get(i))
                            }
                        }
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                if (mStringFilterList != null) {
                    results.count = mStringFilterList.size
                }
                results.values = mStringFilterList
            }

            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
        }
    }
}