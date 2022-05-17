package com.example.successful_balanced_life.view.category_details;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.model.Goal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {
    private List<Goal> goalList;
    private OnClickListener listener;

    public GoalAdapter(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public GoalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.goal_list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAdapter.ViewHolder holder, int position) {
        holder.name.setText(goalList.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        //  private final ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.goalName);
            //    icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(v->{
                listener.onClick(goalList.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener{
        void onClick(Goal goal);
    }
}
