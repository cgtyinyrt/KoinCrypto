package com.example.koincrypto.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koincrypto.databinding.RecyclerRowBinding
import com.example.koincrypto.model.CryptoModel

class CryptoAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<CryptoAdapter.CryptoRowHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors: Array<String> = arrayOf(
        "#13bd27",
        "#29c1e1",
        "#b129e1",
        "#d3df13",
        "#f6bd0c",
        "#a1fb93",
        "#0d9de3",
        "#ffe48f"
    )

    class CryptoRowHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoRowHolder {
        val itemBinding =
            RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoRowHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CryptoRowHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClick(cryptoList[position])
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
        holder.binding.cryptoNameText.text = cryptoList[position].currency
        holder.binding.cryptoPriceText.text = cryptoList[position].price
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}