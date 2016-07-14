package br.inf.combustivelemconta.server;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import br.inf.combustivelemconta.application.Constants;
import br.inf.combustivelemconta.enums.FuelType;
import br.inf.combustivelemconta.models.GasStation;
import br.inf.combustivelemconta.models.Price;

public class Server {
    private static Server sInstance;
    private DatabaseReference mFirebaseDatabase;

    private Server() {
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static Server getInstance() {
        if (sInstance == null) {
            sInstance = new Server();
        }
        return sInstance;
    }

    public void getNearGasStations() {

    }

    public void insertPriceToGasStation(final Price price, final GasStation gasStation) {
        mFirebaseDatabase.child(Constants.DATABASE_GAS_STATIONS).child(gasStation.getMapsId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    insertPriceToExistingGasStation(price, gasStation);
                } else {
                    insertPriceToNonexistentGasStation(price, gasStation);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void insertPriceToExistingGasStation(Price price, final GasStation gasStation) {
        mFirebaseDatabase.child(Constants.DATABASE_GAS_STATIONS + "/" + gasStation.getMapsId() + "/prices")
                .push().setValue(price.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w(TAG, "Insert price success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Insert price failure");
            }
        });
    }

    private void insertPriceToNonexistentGasStation(final Price price, final GasStation gasStation) {
        GasStation station = new GasStation(gasStation.getName(), gasStation.getAddress(), gasStation.getLatitude(), gasStation.getLongitude(), gasStation.getGeofenceRadius());
        mFirebaseDatabase.child(Constants.DATABASE_GAS_STATIONS).child(gasStation.getMapsId()).setValue(station).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w(TAG, "Insert gas station success");
                insertPriceToExistingGasStation(price, gasStation);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Insert gas station failure");
            }
        });
    }

//    public void isGasStationFavorite(GasStation gasStation, String userId, ValueEventListener listener) {
//        mFirebaseDatabase.child(Constants.DATABASE_FAVORITES).child(userId).child(gasStation.getMapsId()).addListenerForSingleValueEvent(listener);
//    }

    public void defineGasStationAsFavorite(GasStation gasStation, String userId) {
        mFirebaseDatabase.child(Constants.DATABASE_FAVORITES).child(userId).push().setValue(gasStation.getMapsId()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w(TAG, "Insert favorite success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Insert favorite failure");
            }
        });
    }

//    public void getFavoritesGasStations(String userId) {
//        mFirebaseDatabase.child(Constants.DATABASE_FAVORITES).child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void test() {
        GasStation gasStation1 = new GasStation("0", "Posto do Boi", "Av. Independência, 2908, Setor Central", -16.6598440, -49.2472810, 10);
        GasStation gasStation2 = new GasStation("1", "Posto Ipiranga", "Avenida Goiás, 1570, Setor Central", -16.6666820, -49.2605770, 30);
        GasStation gasStation3 = new GasStation("2", "Posto Jardim Botânico", "Av. Contorno, Jardim St Antonio", -16.6638424, -49.2539620, 20);
        GasStation gasStation4 = new GasStation("3", "Auto-Posto Talismã", "R. 68, 137 - St. Central", -16.6689120, -49.2579300, 20);

//        Server.getInstance().insertPriceToGasStation(new Price(3.79, Calendar.getInstance().getTime(), FuelType.GASOLINE), gasStation1);
//        Server.getInstance().insertPriceToGasStation(new Price(3.80, Calendar.getInstance().getTime(), FuelType.DIESEL), gasStation2);
//        Server.getInstance().insertPriceToGasStation(new Price(3.81, Calendar.getInstance().getTime(), FuelType.ETHANOL), gasStation3);
//        Server.getInstance().insertPriceToGasStation(new Price(3.82, Calendar.getInstance().getTime(), FuelType.ETHANOL), gasStation4);
        defineGasStationAsFavorite(gasStation1, "0");
        defineGasStationAsFavorite(gasStation2, "0");
        defineGasStationAsFavorite(gasStation2, "1");
    }

    private String TAG = "Server";
}
