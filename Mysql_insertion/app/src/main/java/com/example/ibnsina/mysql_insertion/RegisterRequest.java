package com.example.ibnsina.mysql_insertion;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ibnsina on 1/7/17.
 */

public class RegisterRequest extends StringRequest {

    private static final String Required_Register_URL = "http://127.0.0.1/login.php";

    private Map<String,String> params;

    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String>listener){
        super(Method.POST,Required_Register_URL,listener,null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String,String> getParams() {
        return params;
    }
}
