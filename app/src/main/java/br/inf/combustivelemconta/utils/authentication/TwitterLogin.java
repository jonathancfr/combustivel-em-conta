package br.inf.combustivelemconta.utils.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.utils.DialogUtils;
import br.inf.combustivelemconta.utils.SnackbarUtils;

public class TwitterLogin {

    private static final String TAG = "FacebookLogin";

    private Context mContext;
    private FirebaseAuth mAuth;
    private Activity mActivity;

    private TwitterAuthClient mTwitterAuthClient;

    public TwitterLogin(Context context, FirebaseAuth auth) {
        mAuth = auth;
        mContext = context;
        mActivity = (Activity) mContext;

        mTwitterAuthClient = new TwitterAuthClient();
    }

    public void signIn() {
        DialogUtils.showLoadingDialog(mActivity);
        mTwitterAuthClient.authorize(mActivity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                DialogUtils.dismissLoadingDialog();
                SnackbarUtils.showLoggingError(mContext, "Twitter");
            }
        });
    }

    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

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

    public void onTwitterSignInResult(int requestCode, int resultCode, Intent data) {
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    public void signOut() {
        mAuth.signOut();
        Twitter.logOut();
    }
}
