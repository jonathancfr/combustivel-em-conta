package br.inf.combustivelemconta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
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
    private GasStation gasStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);

        gasStation = EventBus.getDefault().removeStickyEvent(GasStation.class);

        if (gasStation != null) {
            setData();
        }
    }

    public void setData() {
//        if (gasStation != null) {
//            Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
//
//            if (mToolbar != null) {
//                mToolbar.setTitle(gasStation.getName());
//            }
//        }
    }

    @Subscribe
    public void onAnything(NoSubscriberEvent randomEvent) {}

}
