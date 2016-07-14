package br.inf.combustivelemconta.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.models.GasStation;

public class GasStationAdapter extends RecyclerView.Adapter<GasStationAdapter.ViewHolder> {

    private ArrayList<GasStation> gasStationList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView address;
        public ImageButton button;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.gas_station_name);
            address = (TextView) view.findViewById(R.id.gas_station_address);
            button = (ImageButton) view.findViewById(R.id.gas_station_add_price_button);

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

        String presentationName = (position + 1) + ". " + gasStation.getName();

        holder.name.setText(presentationName);
        holder.address.setText(gasStation.getAddress());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKED", "BUTTON");
            }
        });
    }

    @Override
    public int getItemCount() {
        return gasStationList.size();
    }

}
