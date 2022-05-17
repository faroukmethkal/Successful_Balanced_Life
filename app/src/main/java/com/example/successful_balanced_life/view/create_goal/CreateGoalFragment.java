package com.example.successful_balanced_life.view.create_goal;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.widget.DatePicker;

import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.databinding.FragmentCreateGoalBinding;
import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.view.category_details.GoalAdapter;
import com.example.successful_balanced_life.viewmodel.GoalViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;


public class CreateGoalFragment extends Fragment {

    private FragmentCreateGoalBinding binding;
    private GoalAdapter goalAdapter;
    private GoalViewModel goalViewModel;
    private LocalDateTime pickedStartDate;
    private LocalDateTime pickedDeadLine;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         goalViewModel =
               new ViewModelProvider(this).get(GoalViewModel.class);

        binding = FragmentCreateGoalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView categoryNametextView = binding.categoryName;
        final TextView goalName = binding.goalName;
        final EditText startDate = binding.startDate;
        final EditText endDate = binding.endDate;
        final Button createNewGoal = binding.createButton;


         String categoryName = getArguments().getString("categoryName");
        categoryNametextView.setText(categoryName);


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

         createNewGoal.setOnClickListener(view -> {
             System.out.println("Start time created ---->>>"+ pickedStartDate);
             System.out.println("End time created ---->>>"+ pickedDeadLine);
             Goal goal = new Goal(goalName.getText().toString(), categoryName,0,0,pickedStartDate, pickedDeadLine);
             goalViewModel.insert(goal);
             Bundle bundle = new Bundle();
             bundle.putString("categoryName", categoryName);
             Navigation.findNavController(root).navigate(R.id.category_details_fragment, bundle);

         });




        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}