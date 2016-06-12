package br.inf.combustivelemconta.utils.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.utils.DialogUtils;
import br.inf.combustivelemconta.utils.SnackbarUtils;

public class FacebookLogin {

    private static final String TAG = "FacebookLogin";

    private Context mContext;
    private FirebaseAuth mAuth;
    private Activity mActivity;

    private CallbackManager mCallbackManager;

    public FacebookLogin(Context context, FirebaseAuth auth) {
        mAuth = auth;
        mContext = context;
        mActivity = (Activity) mContext;

        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                DialogUtils.dismissLoadingDialog();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onFailure:" + error.toString());
                DialogUtils.dismissLoadingDialog();
                SnackbarUtils.showLoggingError(mContext, "Facebook");
            }
        });
    }

    public void signIn() {
        DialogUtils.showLoadingDialog(mContext);
        LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList("email", "public_profile"));
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
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

    public void onFacebookSignInResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        SnackbarUtils.showMessage(mContext, mActivity.getString(R.string.logged_out));
    }
}
