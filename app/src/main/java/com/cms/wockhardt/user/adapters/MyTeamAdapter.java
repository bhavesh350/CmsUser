package com.cms.wockhardt.user.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cms.wockhardt.user.MyCampsActivity;
import com.cms.wockhardt.user.MyTeamActivity;
import com.cms.wockhardt.user.R;
import com.cms.wockhardt.user.application.AppConstants;
import com.cms.wockhardt.user.models.Doctor;

import java.util.List;

/**
 * Created by Abhishek on 22-04-2017.
 */

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.MyViewHolder> {

    List<Doctor> data;
    private LayoutInflater inflater;
    private Context context;

    public MyTeamAdapter(Context context, List<Doctor> data) {
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
        View view = inflater.inflate(R.layout.item_my_team, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        String current = data.get(position).getName();
        holder.txt_name.setText("EMP-00" + (position + 1) + " ," + " Name, HQ");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                if (((MyTeamActivity) context).isGoNext) {
                    context.startActivity(new Intent(context, MyCampsActivity.class).putExtra(AppConstants.EXTRA, true));
                }
            } catch (Exception e) {
            }

        }
    }
}
