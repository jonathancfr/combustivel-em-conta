package br.inf.combustivelemconta.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.models.GasStation;
import br.inf.combustivelemconta.models.GasStationAdapter;

public class GasStationListActivity extends AppCompatActivity {

    private static final String TAG = "GasStationListActivity";
    private ViewHolder mHolder;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
        mHolder.recyclerView = (RecyclerView) findViewById(R.id.gas_stations_recycler_view);
        mAdapter = new GasStationAdapter(gasStationList);
        mHolder.recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mHolder.recyclerView.setLayoutManager(mLayoutManager);
        mHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mHolder.recyclerView.setAdapter(mAdapter);

//        mHolder.settings = (ImageButton) findViewById(R.id.settings);
//        if (mHolder.settings != null) {
//            mHolder.settings.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startSettingsActivity();
//                }
//            });
//        }
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
        ImageButton settings;
    }
}
