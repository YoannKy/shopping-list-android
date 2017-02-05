package com.esgi.ykeoxay.shopping.Fragment;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.esgi.ykeoxay.shopping.Model.ShoppingList;
import com.esgi.ykeoxay.shopping.R;
import com.esgi.ykeoxay.shopping.Util.Config;
import com.esgi.ykeoxay.shopping.Validation.ShoppingListValidation;
import com.esgi.ykeoxay.shopping.Webservice.ShoppingListService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

public class EditShoppingListFragment extends Fragment {

    private  Bundle editShoppingList;
    private ShoppingList shoppingList;

    public EditShoppingListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_shopping_list, container, false);
    }

    @Override
    public void onResume() {
        this.editShoppingList = getArguments();
        shoppingList = new ShoppingList();
        shoppingList.setId(Integer.valueOf(this.editShoppingList.getString("id")));
        shoppingList.setName(this.editShoppingList.getString("name"));
        shoppingList.setCompleted(Boolean.valueOf(editShoppingList.getString("completed")));
        EditText name = (EditText) getActivity().findViewById(R.id.edit_shopping_list_name);
        CheckBox completed = (CheckBox) getActivity().findViewById(R.id.edit_shopping_list_completed);
        name.setText(shoppingList.getName());
        completed.setChecked(shoppingList.getCompleted());

        Button submit = (Button) getActivity().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) getActivity().findViewById(R.id.edit_shopping_list_name);
                CheckBox completed = (CheckBox) getActivity().findViewById(R.id.edit_shopping_list_completed);
                if(ShoppingListValidation.isFormValid(name)) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    String token = sharedPreferences.getString("token", "");
                    Integer completedValue = (completed.isChecked()) ? 1 : 0;
                    Log.i(Config.LOG_PREFIX, String.valueOf(completed.isChecked()));
                    RequestParams params = new RequestParams();
                    params.put("token", token);
                    params.put("name", name.getText().toString());
                    params.put("id", shoppingList.getId());
                    params.put("completed", completedValue);
                    updateData(params);
                }
            }
        });
        super.onResume();
    }

    protected void updateData(RequestParams params) {
        ShoppingListService.editShoppingList(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (Config.DISPLAY_LOG) {
                    Log.i(Config.LOG_PREFIX, "Success! WS Response :" + new String(bytes));
                }
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
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


}
