package br.inf.combustivelemconta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.models.GasStation;

public class GasStationActivity extends AppCompatActivity {

    private static final String TAG = "GasStationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        EventBus.getDefault().getStickyEvent(GasStation.class);
    }

    public void eventBusRegister() {
        if ( ! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void eventBusUnregister() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onStart() {
        super.onStart();

        eventBusRegister();
    }

    public void onResume() {
        super.onResume();

        eventBusRegister();
    }

    public void onDestroy() {
        eventBusUnregister();
        super.onDestroy();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GasStation gasStation) {
        Log.d(TAG, "Received gasStation");
    };

    @Subscribe
    public void onAnything(NoSubscriberEvent randomEvent) {}

}
