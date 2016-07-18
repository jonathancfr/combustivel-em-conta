package br.inf.combustivelemconta.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.AppCompatSpinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.models.GasStation;
import br.inf.combustivelemconta.adapters.GasStationAdapter;

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

        mHolder.settings = (ImageButton) findViewById(R.id.settings);
        mHolder.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });

        mHolder.selectOrder = (AppCompatSpinner) findViewById(R.id.gas_stations_select_order);
        addListenerOnSpinnerItemSelection();
    }

    public void onStart() {
        super.onStart();

        if ( ! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onResume() {
        super.onResume();

        if ( ! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(GasStation gasStation) {
        EventBus.getDefault().unregister(this);

        EventBus.getDefault().postSticky(gasStation);
        Intent intent = new Intent(this, GasStationActivity.class);
        startActivity(intent);
    };

    @Subscribe
    public void onAnything(NoSubscriberEvent randomEvent) {}

    public void addListenerOnSpinnerItemSelection() {
        mHolder.selectOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SPINNER", "SELECTED");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
        ImageButton settings;
        AppCompatSpinner selectOrder;
    }
}
