package com.cms.wockhardt.user.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cms.wockhardt.user.R;
import com.cms.wockhardt.user.models.Doctor;

import java.util.List;

/**
 * Created by Abhishek on 22-04-2017.
 */

public class CampHistoryDetailsAdapter extends RecyclerView.Adapter<CampHistoryDetailsAdapter.MyViewHolder> {

    List<Doctor> data;
    private LayoutInflater inflater;
    private Context context;

    public CampHistoryDetailsAdapter(Context context, List<Doctor> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_camp_history_details, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        String current = data.get(position).getName();
//        holder.txt_name.setText("Doctor " + (position + 1));
        if (position == 4) {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_red));
        } else if (position == 6) {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_yellow));
        } else {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_green));
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            card_view = itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
