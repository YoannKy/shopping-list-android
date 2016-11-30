package com.esgi.ykeoxay.shopping.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esgi.ykeoxay.shopping.Fragment.LoginFragment;
import com.esgi.ykeoxay.shopping.R;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, new LoginFragment());
        fragmentTransaction.commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }
}
