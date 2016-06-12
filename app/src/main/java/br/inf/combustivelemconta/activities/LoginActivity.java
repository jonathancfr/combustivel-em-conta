package br.inf.combustivelemconta.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import br.inf.combustivelemconta.R;
import br.inf.combustivelemconta.application.Constants;
import br.inf.combustivelemconta.utils.SnackbarUtils;
import br.inf.combustivelemconta.utils.authentication.FacebookLogin;
import br.inf.combustivelemconta.utils.authentication.GoogleLogin;
import br.inf.combustivelemconta.utils.authentication.TwitterLogin;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private ViewHolder mHolder;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleLogin mGoogleLogin;
    private FacebookLogin mFacebookLogin;
    private TwitterLogin mTwitterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        super.onStop();
    }

    private void initData() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                checkUserExistence();
            }
        };
    }

    private void initView() {
        setContentView(R.layout.activity_login);
        mHolder = new ViewHolder();
        mHolder.facebook = (Button) findViewById(R.id.login_facebook);
        mHolder.google = (Button) findViewById(R.id.login_google);
        mHolder.twitter = (Button) findViewById(R.id.login_twitter);

        mHolder.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFacebook();
            }
        });

        mHolder.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });

        mHolder.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginTwitter();
            }
        });
    }

    private void checkUserExistence() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }

    private void loginFacebook() {
        mFacebookLogin = new FacebookLogin(this, mAuth);
        mFacebookLogin.signIn();
    }

    private void loginGoogle() {
        mGoogleLogin = new GoogleLogin(this, mAuth);
        mGoogleLogin.signIn();
    }

    private void loginTwitter() {
        mTwitterLogin = new TwitterLogin(this, mAuth);
        mTwitterLogin.signIn();
    }

    private static class ViewHolder {
        Button facebook;
        Button google;
        Button twitter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constants.GOOGLE_SIGN_IN_REQUEST_CODE:
                mGoogleLogin.onGoogleSignInResult(requestCode, resultCode, data);
                break;
            case TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE:
                mTwitterLogin.onTwitterSignInResult(requestCode, resultCode, data);
                break;
            default:
                mFacebookLogin.onFacebookSignInResult(requestCode, resultCode, data);
        }
    }
}
