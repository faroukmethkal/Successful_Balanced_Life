package com.example.successful_balanced_life.view.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.successful_balanced_life.R;
import com.example.successful_balanced_life.model.Category;
import com.example.successful_balanced_life.model.Goal;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;
    private OnClickListener listener;

    public CategoryAdapter(List<Category> categories){
        this.categories = categories;
    }


    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
      //  private final ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        //    icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(v->{
                listener.onClick(categories.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener{
        void onClick(Category category);


    }
}
