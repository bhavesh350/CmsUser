package com.cms.wockhardt.user.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cms.wockhardt.user.CampExecutionClickActivity;
import com.cms.wockhardt.user.R;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.application.SingleInstance;
import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;

import java.util.Date;
import java.util.List;

/**
 * Created by Abhishek on 22-04-2017.
 */

public class ExecuteCampsAdapter extends RecyclerView.Adapter<ExecuteCampsAdapter.MyViewHolder> {

    private List<Camp.Data> data;
    private LayoutInflater inflater;
    private Context context;

    public ExecuteCampsAdapter(Context context, List<Camp.Data> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_my_camps, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Camp.Data d = data.get(position);
        holder.txt_name.setText(d.getDoctor().getName() + " " + "(" + d.getDoctor().getMsl_code() + ")");
        holder.txt_count.setText(d.getPatient_count() + " Patients");
        holder.txt_date.setText(MyApp.parseDateFullMonth(d.getCamp_date().split(" ")[0]));

        Date campDate = MyApp.getDate(d.getCamp_date().split(" ")[0]);
        boolean isPast = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000)).after(campDate);


        if (d.getStatus() == 0) {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_red));
        } else if (d.getStatus() == 2) {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_green));
        } else if (isPast) {
            data.get(position).setPast(true);
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_gray));
        } else {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_yellow));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;
        TextView txt_count;
        TextView txt_date;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            card_view = itemView.findViewById(R.id.card_view);
            txt_count = itemView.findViewById(R.id.txt_count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (data.get(getLayoutPosition()).getStatus() == 2 &&
                    MyApp.getTodayDate(System.currentTimeMillis()).equals(MyApp.parseDateFullMonth(
                            data.get(getLayoutPosition()).getCamp_date().split(" ")[0]))) {
                SingleInstance.getInstance().setSelectedCamp(data.get(getLayoutPosition()));
                context.startActivity(new Intent(context, CampExecutionClickActivity.class));
            } else {
                Date campDate = MyApp.getDate(data.get(getLayoutPosition()).getCamp_date().split(" ")[0]);
                boolean isPast = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000)).after(campDate);

                if (data.get(getLayoutPosition()).getStatus() == 1) {
                    MyApp.popMessage("Alert", "Your camp is not approved yet.", context);
                } else if (data.get(getLayoutPosition()).getStatus() == 0) {
                    MyApp.popMessage("Alert", "Your camp has been rejected by your RM.", context);
                } else if (isPast) {
                    MyApp.showMassage(context, "It's past date camp.");
                } else {
                    MyApp.popMessage("Alert", "It's future camp date, please wait for the date to come.", context);
                }
            }

        }
    }
}
