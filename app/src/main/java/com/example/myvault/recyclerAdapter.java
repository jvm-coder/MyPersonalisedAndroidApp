package com.example.myvault;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.myViewHolder> {

    private ArrayList<DistrictData> districtList;

    public recyclerAdapter(ArrayList<DistrictData> districtList) {
        this.districtList = districtList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDistName, textViewConfirmed, textViewActive, textViewRecovered, textViewDeceased, textViewdConfirmed, textViewdRecovered, textViewdDeceased;


        public myViewHolder(final View view) {
            super(view);
            textViewDistName = view.findViewById(R.id.textViewDistName);
            textViewConfirmed = view.findViewById(R.id.textViewConfirmedData);
            textViewActive = view.findViewById(R.id.textViewActiveData);
            textViewRecovered = view.findViewById(R.id.textViewRecoveredData);
            textViewDeceased = view.findViewById(R.id.textViewDeceasedData);
            textViewdConfirmed = view.findViewById(R.id.textViewDeltaConfirmed);
            textViewdRecovered = view.findViewById(R.id.textViewDeltaRecovered);
            textViewdDeceased = view.findViewById(R.id.textViewDeltaDeceased);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_data, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.myViewHolder holder, int position) {
        String distName = districtList.get(position).getDistrictName();
        int confirmed = districtList.get(position).getConfirmed();
        int active = districtList.get(position).getActive();
        int recovered = districtList.get(position).getRecovered();
        int deceased = districtList.get(position).getDeceased();
        int dConfirmed = districtList.get(position).getdConfirmed();
        int dRecovered = districtList.get(position).getdRecovered();
        int dDeceased = districtList.get(position).getdDeceased();
        holder.textViewDistName.setText(distName);
        holder.textViewConfirmed.setText(Integer.toString(confirmed));
        holder.textViewActive.setText(Integer.toString(active));
        holder.textViewRecovered.setText(Integer.toString(recovered));
        holder.textViewDeceased.setText(Integer.toString(deceased));
        holder.textViewdConfirmed.setText(Integer.toString(dConfirmed));
        holder.textViewdRecovered.setText(Integer.toString(dRecovered));
        holder.textViewdDeceased.setText(Integer.toString(dDeceased));

    }

    @Override
    public int getItemCount() {
        return districtList.size();
    }
}
