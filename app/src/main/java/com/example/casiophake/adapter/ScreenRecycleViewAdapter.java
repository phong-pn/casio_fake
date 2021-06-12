package com.example.casiophake.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casiophake.Model.Model;
import com.example.casiophake.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScreenRecycleViewAdapter extends RecyclerView.Adapter<ScreenRecycleViewAdapter.ViewHolder> {
    private List<Model> modelList;
    private OnClickViewHolder onClickViewHolder;

    public List<Model> getModelList() {
        return modelList;
    }

    public OnClickViewHolder getOnClickViewHolder() {
        return onClickViewHolder;
    }

    public ScreenRecycleViewAdapter(List<Model> modelList, OnClickViewHolder onClickViewHolder) {
        this.modelList = modelList;
        this.onClickViewHolder = onClickViewHolder;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScreenRecycleViewAdapter.ViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.inputText.setText(model.getInput());
        holder.outputText.setText(Float.toString(model.getOutput()));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText inputText;
        private TextView outputText;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            inputText = itemView.findViewById(R.id.input_text);
            outputText = itemView.findViewById(R.id.output_text);
        }

        @Override
        public void onClick(View v) {
            onClickViewHolder.onClick(getAdapterPosition());
        }
    }

    public interface OnClickViewHolder{
        void onClick(int position);
    }

}
