package com.example.android3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android3.databinding.CardBinding
import java.util.*

interface LikeListener {
    fun onPressedLike(user: Card)
}

class CustomRecyclerAdapter(private val context: Context, private val likeListener: LikeListener) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(), View.OnClickListener {
    private var cards = emptyList<Card>()

    override fun onClick(v: View) {
        val user = v.tag as Card
        when (v.id) {
            R.id.like_btn -> {
                likeListener.onPressedLike(user)
            }
        }
    }

    class MyViewHolder(val binding: CardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(inflater, parent, false)
        binding.likeBtn.setOnClickListener(this)
        binding.root.setOnClickListener(this)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = cards[position]

        with(holder.binding) {
            likeBtn.tag = user
            img.setImageResource(user.id)
            name.text = user.name
            date.text = user.date
            gender.text = user.gender
            description.text = user.description

            holder.itemView.setOnClickListener {
                val getImage: Int = user.id
                val getName: String = user.name
                val getDate: String = user.date
                val getGender: String = user.gender
                val getDesc: String = user.description

                val intent = Intent(context, CardActivity::class.java)

                intent.putExtra("putImage", getImage)
                intent.putExtra("putName", getName)
                intent.putExtra("putDate", getDate)
                intent.putExtra("putGender", getGender)
                intent.putExtra("putDesc", getDesc)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<Card>) {
        cards = list
        notifyDataSetChanged()
    }
}