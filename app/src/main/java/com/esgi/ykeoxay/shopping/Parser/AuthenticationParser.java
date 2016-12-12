package com.esgi.ykeoxay.shopping.Parser;

import android.os.AsyncTask;
import com.esgi.ykeoxay.shopping.Interface.TokenParserResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationParser extends AsyncTask<String,Void,String> {

    private TokenParserResponse listener;

    public AuthenticationParser(TokenParserResponse listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String...params) {
        String token  = new String();
        try {
            token = this.parse(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    protected  void onPostExecute(String token) {

        this.listener.responseParsed(token);
    }

    public String parse(String response) throws JSONException {
        JSONObject contentJSON = new JSONObject(response);
        if(contentJSON.getInt("code") == 0) {
            return contentJSON.getJSONObject("result").getString("token");
        } else {
            return "";
        }
    }
}
