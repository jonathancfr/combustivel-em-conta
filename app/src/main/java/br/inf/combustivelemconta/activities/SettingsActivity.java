package br.inf.combustivelemconta.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import br.inf.combustivelemconta.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        setContentView(R.layout.activity_settings);
        mHolder = new ViewHolder();


        mHolder.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mHolder.toolbar);
        mHolder.toolbar.setTitle(getString(R.string.configuracoes));
        mHolder.toolbar.setTitleTextColor(Color.WHITE);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mHolder.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mHolder.rateLayout = findViewById(R.id.rate);
        mHolder.rateLayout.setOnClickListener(this);

        mHolder.removeFavorites = findViewById(R.id.remove_favorites);
        mHolder.removeFavorites.setOnClickListener(this);

        mHolder.contactUs = findViewById(R.id.contact_us);
        mHolder.contactUs.setOnClickListener(this);

        mHolder.info = findViewById(R.id.info);
        mHolder.info.setOnClickListener(this);

        mHolder.closeSession = findViewById(R.id.close_session);
        mHolder.closeSession.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rate: {
                onClickRate();
                break;
            }
            case R.id.remove_favorites: {
                onClickRemove();
                break;
            }
            case R.id.contact_us: {
                onClickContactUs();
                break;
            }
            case R.id.info: {
                onClickInfo();
                break;
            }
            case R.id.close_session: {
                onClickClose();
                break;
            }
        }
    }

    private void onClickRate() {
        System.out.println("on rate");
    }

    private void onClickRemove() {
        System.out.println("remove");
    }

    private void onClickContactUs() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    private void onClickInfo() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.infos))
                .setMessage( getString(R.string.info_text) + "\n\n"
                        +"Desenvolvido por:\nAna Paula de Sousa\nEduardo de Souza\nJonathan Christian")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void onClickClose() {
        System.out.println("close");
    }

    private static class ViewHolder {
        View rateLayout;
        View removeFavorites;
        View contactUs;
        View info;
        View closeSession;
        Toolbar toolbar;
    }

}
