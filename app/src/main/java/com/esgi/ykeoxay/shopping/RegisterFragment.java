package com.esgi.ykeoxay.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.esgi.ykeoxay.shopping.Interface.TokenParserResponse;
import com.esgi.ykeoxay.shopping.Parser.AuthenticationParser;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Webservice.Webservice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import static android.content.Context.MODE_PRIVATE;


public class RegisterFragment extends Fragment implements TokenParserResponse{

    public RegisterFragment() {
        // Required empty public constructor
    }

    public RegisterFragment getCurrentFragment()
    {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onResume() {
        Button submit = (Button) getActivity().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText email = (EditText) getActivity().findViewById(R.id.email);
                EditText firstname = (EditText) getActivity().findViewById(R.id.firstname);
                EditText password = (EditText) getActivity().findViewById(R.id.password);
                RequestParams params = new RequestParams();
                params.put("email", email.getText().toString());
                params.put("firstname", firstname.getText().toString());
                params.put("password", password.getText().toString());
                Webservice ws = new Webservice();
                ws.register(new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        if (Config.DISPLAY_LOG) {
                            Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                        }
                        AuthenticationParser authenticationParser = new AuthenticationParser(getCurrentFragment());
                        authenticationParser.execute(new String(bytes));
                    }
                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        String failedReponse = "";
                        if (bytes != null) {
                            failedReponse = new String(bytes);
                        }
                        Log.i(Config.LOG_PREFIX, "Failure! WS Response :" + failedReponse);
                    }
                }, params);

            }
        });

        CheckBox switchAuth = (CheckBox) getActivity().findViewById(R.id.checkbox_auth);
        switchAuth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    LoginFragment login = new LoginFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, login);
                    fragmentTransaction.commit();
                }
            }
        });
        super.onResume();
    }

    @Override
    public void responseParsed(String token) {
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!token.equals("")) {
            editor.putString("token", token);
            Intent myIntent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
            startActivity(myIntent);
        }

    }
}
