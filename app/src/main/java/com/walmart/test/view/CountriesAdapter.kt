package com.walmart.test.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walmart.test.databinding.CountryItemBinding
import com.walmart.test.model.data.Country

class CountriesAdapter(
    val countries: List<Country>
): RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    inner class CountryViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                tvCountryNameRegion.text = "${country.name}, ${country.region}"
                tvCountryCode.text = country.code
                tvCountryCapital.text = country.capital
            }
        }
    }
}