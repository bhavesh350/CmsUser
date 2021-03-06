package com.cms.wockhardt.user.adapters;

import android.content.Context;
import android.icu.util.CurrencyAmount;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cms.wockhardt.user.R;
import com.cms.wockhardt.user.application.MyApp;
import com.cms.wockhardt.user.models.Camp;
import com.cms.wockhardt.user.models.Doctor;
import com.cms.wockhardt.user.models.MyTeam;
import com.cms.wockhardt.user.models.Patient;

import java.util.List;

/**
 * Created by Abhishek on 22-04-2017.
 */

public class CampHistoryDetailsAdapter extends RecyclerView.Adapter<CampHistoryDetailsAdapter.MyViewHolder> {

    List<Camp.Data> data;
    private LayoutInflater inflater;
    private Context context;

    public CampHistoryDetailsAdapter(Context context, List<Camp.Data> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_camp_history_details, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Camp.Data d = data.get(position);

        holder.txt_name.setText(d.getDoctor().getName() + " ( MSL code - " + d.getDoctor().getMsl_code() + ")");
        if (d.getStatus() == 0) {
            holder.txt_approval.setText("Not approved");
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_red));
        } else if (d.getStatus() == 1) {
            holder.txt_approval.setText("Approval pending");
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_yellow));
        } else {
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.card_green));
            holder.txt_approval.setText("Approved");
        }
        holder.txt_patient_screened.setText("Patients screened : " + d.getPatients().size() + "\n"
                + "Expected patients : " + d.getPatient_count());
        holder.txt_date.setText(MyApp.parseDateSortDate(d.getCamp_date()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txt_patient_screened, txt_date, txt_approval;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_patient_screened = itemView.findViewById(R.id.txt_patient_screened);
            txt_approval = itemView.findViewById(R.id.txt_approval);
            txt_name = itemView.findViewById(R.id.txt_name);
            card_view = itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
