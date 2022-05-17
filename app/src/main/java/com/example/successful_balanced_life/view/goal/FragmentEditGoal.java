package com.example.successful_balanced_life.view.goal;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.databinding.FragmentEditGoalBinding;
import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.view.category_details.GoalAdapter;
import com.example.successful_balanced_life.viewmodel.GoalViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


public class FragmentEditGoal extends Fragment {
    private FragmentEditGoalBinding binding;
    private GoalAdapter goalAdapter;
    private GoalViewModel goalViewModel;
    private LocalDateTime pickedStartDate;
    private LocalDateTime pickedDeadLine;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentEditGoalBinding.inflate(inflater, container, false);

        final EditText startDate = binding.startDate;
        final EditText endDate = binding.endDate;


        View root = binding.getRoot();
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);

         int goalId = getArguments().getInt("id");

        goalViewModel.getGoalByName(goalId).observe(getViewLifecycleOwner(), goals -> {
            if (!goals.isEmpty()){
                for (Goal goal: goals){
                    binding.categoryName.setText(goal.getCategoryName());
                    binding.goalName.setText(goal.getName());
                    if (goal.getStartDate() != null) {
                        binding.startDate.setText(goal.getStartDate().getDayOfMonth()+"-"+goal.getStartDate().getMonth()+"-"+goal.getStartDate().getYear());
                    }
                    if (goal.getDeadline() != null) {
                        binding.endDate.setText(goal.getDeadline().getDayOfMonth()+"-"+goal.getDeadline().getMonth()+"-"+goal.getDeadline().getYear());
                    }

                    binding.progressHorizontal.setProgress(goal.getProgress());
                    int left = goal.getDeadline().getDayOfMonth() - goal.getStartDate().getDayOfMonth();
                    binding.periodLeft.setText("Days Left: "+ left);
                }
            }
            else {
                System.out.println("NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  NULL  ");
            }
        });




        final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog[0] = new DatePickerDialog(root.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                LocalDate date = LocalDate.of(year,monthOfYear,dayOfMonth);
                                LocalTime time = LocalTime.now();
                                pickedStartDate = LocalDateTime.of(date,time);

                                startDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog[0].show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog[0] = new DatePickerDialog(root.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                LocalDate date = LocalDate.of(year,monthOfYear,dayOfMonth);
                                LocalTime time = LocalTime.now();
                                pickedDeadLine = LocalDateTime.of(date,time);


                                endDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);


                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog[0].show();


            }


        });

        binding.increment.setOnClickListener(view -> {
            binding.progressHorizontal.incrementProgressBy(1);
        });

        binding.decrement.setOnClickListener(view -> {
            if (binding.progressHorizontal.getProgress() >= 1) {
                binding.progressHorizontal.incrementProgressBy(-1);
            }
        });

        binding.editGoal.setOnClickListener(view -> {
         /*   Goal goal1 = new Goal(binding.goalName.getText().toString(), binding.categoryName.getText().toString(),
                    0,binding.progressHorizontal.getProgress(),pickedStartDate, pickedDeadLine);*/
          //  goalViewModel.update(goal1);
            goalViewModel.updateProgress(goalId, binding.progressHorizontal.getProgress());
            Navigation.findNavController(root).navigate(R.id.nav_home);

        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
