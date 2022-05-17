package com.example.successful_balanced_life.view.category_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.databinding.FragmentCategoryDetailsBinding;
import com.example.successful_balanced_life.viewmodel.GoalViewModel;

public class CategoryDetailsFragment extends Fragment {
    private FragmentCategoryDetailsBinding binding;
    private GoalAdapter goalAdapter;
    private GoalViewModel goalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        binding = FragmentCategoryDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String categoryName = getArguments().getString("categoryName");
        getActivity().setTitle(categoryName);
        final RecyclerView goalList = binding.rv;
        goalList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        final Button addNewGoal = binding.addNewGoal;
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        goalViewModel.getAllGoalWhereCategoryNameIs(categoryName).observe(getViewLifecycleOwner(), goals -> {
            if (!goals.isEmpty()){
                goalAdapter = new GoalAdapter(goals);
                goalList.setAdapter(goalAdapter);

                goalAdapter.setOnClickListener(goal->{
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",goal.getId());
                    System.out.println("goal name been selected---->>>>>>"+goal.getName());
                    Navigation.findNavController(root).navigate(R.id.edit_goal_fragment, bundle);
                });

            }
        });


        addNewGoal.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("categoryName", categoryName);
            Navigation.findNavController(root).navigate(R.id.create_goal_fragment, bundle);
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
