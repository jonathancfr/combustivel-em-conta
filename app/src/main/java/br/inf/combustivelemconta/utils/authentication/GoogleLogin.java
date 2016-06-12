package br.inf.combustivelemconta.utils.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.application.Constants;
import br.inf.combustivelemconta.utils.DialogUtils;
import br.inf.combustivelemconta.utils.SnackbarUtils;

public class GoogleLogin implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "GoogleLogin";

    private Context mContext;
    private FirebaseAuth mAuth;
    private Activity mActivity;
    private GoogleApiClient mGoogleApiClient;

    public GoogleLogin(Context context, FirebaseAuth auth) {
        mAuth = auth;
        mContext = context;
        mActivity = (Activity) mContext;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(Constants.GOOGLE_OAUTH_CLIENT_ID)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onAuthStateChanged:signed_out");
    }

    public void signIn() {
        DialogUtils.showLoadingDialog(mContext);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, Constants.GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    public void onGoogleSignInResult(int requestCode, int resultCode, Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            SnackbarUtils.showLoggingError(mContext, "Google");
            DialogUtils.dismissLoadingDialog();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());

                            DialogUtils.dismissLoadingDialog();
                            SnackbarUtils.showLoggingError(mActivity, "Server");
                        }
                        DialogUtils.dismissLoadingDialog();
                        SnackbarUtils.showMessage(mContext, mActivity.getString(R.string.logged_in));
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                });
    }
}
