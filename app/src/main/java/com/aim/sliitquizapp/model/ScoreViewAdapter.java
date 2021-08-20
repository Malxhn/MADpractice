package com.aim.sliitquizapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.aim.sliitquizapp.R;
import com.aim.sliitquizapp.StatisticsActivity;

import java.util.List;

public class ScoreViewAdapter extends RecyclerView.Adapter<ScoreViewAdapter.ViewHolder>{
    StatisticsActivity context;
    List<Statistics> stList;

    public ScoreViewAdapter(StatisticsActivity context, List<Statistics> TempList) {

        this.stList = TempList;

        this.context = context;
    }

    @Override
    public ScoreViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_scores, parent, false);

        ScoreViewAdapter.ViewHolder viewHolder = new ScoreViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreViewAdapter.ViewHolder holder, int position) {

        Statistics st = stList.get(position);

        holder.txCrt.setText(String.valueOf(st.getCorrect()));

        holder.txWrg.setText(String.valueOf(st.getWrong()));

        holder.txSkp.setText(String.valueOf(st.getSkip()));

        holder.txScr.setText(String.valueOf(st.getScore())+" %");

        holder.txSub.setText(st.getSubject());

        holder.txDate.setText(st.getDate());

    }

    @Override
    public int getItemCount() {

        return stList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txCrt;
        public TextView txWrg;
        public TextView txSkp;
        public TextView txScr;
        public TextView txDate;
        public TextView txSub;

        public ViewHolder(View itemView) {

            super(itemView);

            txCrt = itemView.findViewById(R.id.txCrt);

            txWrg = itemView.findViewById(R.id.txWrg);

            txSkp = itemView.findViewById(R.id.txSkp);

            txScr = itemView.findViewById(R.id.txScr);

            txDate = itemView.findViewById(R.id.txDate);

            txSub = itemView.findViewById(R.id.txSub);

        }

    }
}
