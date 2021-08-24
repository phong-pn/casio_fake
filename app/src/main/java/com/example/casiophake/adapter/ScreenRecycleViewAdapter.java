package com.example.casiophake.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casiophake.Model.Expression;
import com.example.casiophake.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ScreenRecycleViewAdapter extends RecyclerView.Adapter<ScreenRecycleViewAdapter.ViewHolder> {

    private List<Expression> modelList;
    private ViewCallBack ViewCallBack;

    public List<Expression> getModelList() {
        return modelList;
    }

    public ViewCallBack getViewCallBack() {
        return ViewCallBack;
    }

    public ScreenRecycleViewAdapter(List<Expression> modelList, ViewCallBack ViewCallBack) {
        this.modelList = modelList;
        this.ViewCallBack = ViewCallBack;
    }

    public void setModelList(List<Expression> modelList) {
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
        Expression model = modelList.get(position);
        holder.inputText.setText(model.getInput());
        holder.outputText.setText(Float.toString(model.getOutput()));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public EditText inputText;
        public TextView outputText;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            inputText = itemView.findViewById(R.id.input_text);
            outputText = itemView.findViewById(R.id.output_text);
            itemView.setOnClickListener(v -> ViewCallBack.onClick(getAdapterPosition(), ViewHolder.this));

            // make soft keyboard don't show when inputText is focused
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inputText.setShowSoftInputOnFocus(false);
            }

            inputText.setOnClickListener(v -> ViewCallBack.onClick(getAdapterPosition(), ViewHolder.this));
        }

        @Override
        public void onClick(View v) {
            ViewCallBack.onClick(getAdapterPosition(), ViewHolder.this);
        }
    }

    /** Callback to view that hold adapter's recycleView*/
    public interface ViewCallBack{
        void onClick(int position, ViewHolder viewHolder);
    }

}
