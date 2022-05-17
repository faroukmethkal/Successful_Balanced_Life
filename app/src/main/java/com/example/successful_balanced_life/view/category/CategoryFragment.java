package com.example.successful_balanced_life.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.databinding.FragmentCategoryBinding;
import com.example.successful_balanced_life.model.Category;
import com.example.successful_balanced_life.view.category_details.CategoryDetailsFragment;
import com.example.successful_balanced_life.viewmodel.CategoryViewModel;

import java.util.Arrays;
import java.util.List;


public class CategoryFragment extends Fragment {


    private FragmentCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private CategoryAdapter categoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        final RecyclerView categoryList = binding.rv;

        categoryList.setLayoutManager(new LinearLayoutManager(root.getContext()));


        categoryViewModel.getAllCategory().observe(getViewLifecycleOwner(),categories -> {

            if(categories.isEmpty()){
                for (Category category: init()){
                    categoryViewModel.insert(category);
                }
           }else {
                categoryAdapter = new CategoryAdapter(categories);
                categoryList.setAdapter(categoryAdapter);

                categoryAdapter.setOnClickListener(category -> {

                    Bundle bundle = new Bundle();
                    bundle.putString("categoryName", category.getName());
                    Navigation.findNavController(root).navigate(R.id.category_details_fragment, bundle);
                });
            }
        });

        return root;
    }


    private List<Category> init() {
        Category[] categories = {
                new Category("Education"),
                new Category("Healthy life style"),
                new Category("Job"),
                new Category("Entertainment"),
                new Category("Free time"),
                new Category("Social life")
        };

        return Arrays.asList(categories);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}