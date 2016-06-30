package br.inf.combustivelemconta.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.models.GasStation;
import br.inf.combustivelemconta.models.GasStationAdapter;

public class GasStationListActivity extends AppCompatActivity {

    private static final String TAG = "GasStationListActivity";
    private ViewHolder mHolder;
    private ArrayList<GasStation> gasStationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    public void initData() {
        GasStation gasStation = new GasStation();
        gasStation.setName("Hello You");
        gasStation.setAddress("Addreeeeessssss");
        gasStationList.add(gasStation);

        gasStation = new GasStation();
        gasStation.setName("Hello You");
        gasStation.setAddress("Addreeeeessssss");
        gasStationList.add(gasStation);

        gasStation = new GasStation();
        gasStation.setName("Hello You");
        gasStation.setAddress("Addreeeeessssss");
        gasStationList.add(gasStation);
    }

    public void initView() {
        setContentView(R.layout.activity_gas_station_list);

        mHolder = new ViewHolder();

        // Recycler View
        mHolder.mRecyclerView = (RecyclerView) findViewById(R.id.gas_stations_recycler_view);
        mHolder.mAdapter = new GasStationAdapter(gasStationList);
        mHolder.mRecyclerView.setHasFixedSize(true);
        mHolder.mLayoutManager = new LinearLayoutManager(this);
        mHolder.mRecyclerView.setLayoutManager(mHolder.mLayoutManager);
        mHolder.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHolder.mRecyclerView.setAdapter(mHolder.mAdapter);
    }

    private static class ViewHolder {
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
    }
}
