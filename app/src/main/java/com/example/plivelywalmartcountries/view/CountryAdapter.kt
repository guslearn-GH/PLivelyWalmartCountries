package com.example.plivelywalmartcountries.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plivelywalmartcountries.databinding.CountryListItemBinding
import com.example.plivelywalmartcountries.model.CountryResponse

class CountryAdapter(private val list: List<CountryResponse>): RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryViewHolder(
            CountryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
}

class CountryViewHolder(private val binding: CountryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(country: CountryResponse) {
        binding.run {
            tvName.text = country.name
            tvRegion.text = country.region
            tvCode.text = country.code
            tvCapital.text = country.capital
        }
    }
}