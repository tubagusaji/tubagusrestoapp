package com.tubagusapp.favorite.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tubagusapp.core.data.Resource
import com.tubagusapp.core.domain.model.Food
import com.tubagusapp.core.ui.OnItemClick
import com.tubagusapp.core.ui.adapter.ItemFoodAdapter
import com.tubagusapp.core.utils.Constants
import com.tubagusapp.favorite.databinding.FragmentFavoriteBinding
import com.tubagusapp.favorite.di.DaggerFavoriteComponent
import com.tubagusapp.shirohigefood.di.FavoriteModuleDependencies
import com.tubagusapp.shirohigefood.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: FavoriteViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val itemFoodAdapter = ItemFoodAdapter(
                object : OnItemClick<Food> {
                    override fun onClick(data: Food) {
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(Constants.INTENT_ITEM_FOOD_TO_DETAIL, data.idMeal)

                        startActivity(intent)
                    }
                }, object : OnItemClick<Food> {
                    override fun onClick(data: Food) {
                        if (data.isOnFavorite) {
                            viewModel.removeFavorite(data)
                        } else {
                            viewModel.addFavorite(data)
                        }
                    }
                }
            )

            binding.apply {
                fragmentFavoriteRv.apply {
                    adapter = itemFoodAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }
            }

            viewModel.getFavoriteList().observe(viewLifecycleOwner) { foodResource ->
                if (foodResource is Resource.Loading) {
                    binding.fragmentFavoriteListLottieLoading.visibility = View.VISIBLE
                    binding.fragmentFavoriteListLottieNoItem.visibility = View.GONE
                    binding.fragmentFavoriteListTvNoItem.visibility = View.GONE
                } else {
                    binding.fragmentFavoriteListLottieLoading.visibility = View.GONE
                }

                if (foodResource is Resource.Success) {
                    binding.fragmentFavoriteListLottieNoItem.visibility =
                        if (foodResource.data?.isEmpty() == false) View.GONE else View.VISIBLE
                    binding.fragmentFavoriteListTvNoItem.visibility =
                        if (foodResource.data?.isEmpty() == false) View.GONE else View.VISIBLE

                    itemFoodAdapter.submitList(foodResource.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}