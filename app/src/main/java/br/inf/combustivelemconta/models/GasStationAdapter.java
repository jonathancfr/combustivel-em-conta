package br.inf.combustivelemconta.models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.inf.combustivelemconta.R;

public class GasStationAdapter extends RecyclerView.Adapter<GasStationAdapter.ViewHolder> {

    private ArrayList<GasStation> gasStationList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView address;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.gas_station_name);
            address = (TextView) view.findViewById(R.id.gas_station_address);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("CLICKEDDDD", "CARD");
        }
    }

    public GasStationAdapter(ArrayList<GasStation> gasStationList) {
        this.gasStationList = gasStationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gas_station, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GasStation gasStation = gasStationList.get(position);

        holder.name.setText(gasStation.getName());
        holder.address.setText(gasStation.getAddress());
    }

    @Override
    public int getItemCount() {
        return gasStationList.size();
    }

}
