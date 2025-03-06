package com.gomez.herlin.logindemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gomez.herlin.logindemo.R;
import com.gomez.herlin.logindemo.dto.DonutsDto;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<DonutsDto> donutsDtoList;
    private LayoutInflater minflater;
    private Context context;

    final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DonutsDto item);
    }

    public ListAdapter(List<DonutsDto> donutsDtoList, Context context, OnItemClickListener listener) {
        this.donutsDtoList = donutsDtoList;
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return donutsDtoList.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.bindData(donutsDtoList.get(position));
    }

    public void setItems(List<DonutsDto> items){
        donutsDtoList = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, type, name;
        CardView cv;

        ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idTextView);
            type = itemView.findViewById(R.id.typeTextView);
            name = itemView.findViewById(R.id.nameTextView);
            cv = itemView.findViewById(R.id.cv);
        }

        void bindData(final DonutsDto donutsDto) {
            id.setText(donutsDto.getId());
            type.setText(donutsDto.getType());
            name.setText(donutsDto.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(donutsDto);
                }
            });
        }
    }
}