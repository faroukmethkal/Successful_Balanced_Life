package com.example.successful_balanced_life.view.goal;

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
import com.example.successful_balanced_life.databinding.FragmentLongGoalBinding;
import com.example.successful_balanced_life.databinding.FragmentShortGoalBinding;
import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.view.category_details.GoalAdapter;
import com.example.successful_balanced_life.viewmodel.GoalViewModel;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ShortGoalFragment extends Fragment {
    FragmentShortGoalBinding binding;
    private GoalAdapter goalAdapter;
    private GoalViewModel goalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        binding = FragmentShortGoalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView goalList = binding.rv;
        goalList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);



        goalViewModel.getAllGoal().observe(getViewLifecycleOwner(), goals -> {
            if (!goals.isEmpty()){
                List<Goal> longGoal = new ArrayList<>();
                for (Goal goal: goals){
                    if (getPeriod(goal.getStartDate(), goal.getDeadline()) <= 5){
                        longGoal.add(goal);
                    }
                }
                goalAdapter = new GoalAdapter(longGoal);
                goalList.setAdapter(goalAdapter);

                goalAdapter.setOnClickListener(goal->{
                    Bundle bundle = new Bundle();
                    bundle.putInt("goal_name",goal.getId());
                    Navigation.findNavController(root).navigate(R.id.edit_goal_fragment, bundle);
                });
            }
        });



        return root;
    }


    private static int getPeriod(LocalDateTime start, LocalDateTime end) {
        int t = 0;
        if (start != null && end != null){
            t =  Period.between(start.toLocalDate(), end.toLocalDate()).getDays();
            System.out.println("period in days----------->>>>>> "+t);
        }

        return  t;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
