package br.inf.combustivelemconta.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.application.App;

public class SettingsActivity extends AppCompatActivity {
    private ViewHolder mHolder;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        initView();
    }

    public void initView() {
        setContentView(R.layout.activity_settings);
        mHolder = new ViewHolder();

    }

    private static class ViewHolder {
    }
}
