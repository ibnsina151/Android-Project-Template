package com.example.ibnsina.arraylist_value_pass;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ibnsina on 6/6/17.
 */

public class Qabir {
    public int age;
    public String name;
    public int price;

    Qabir(){
    }

    Qabir(int age, String name,int price){
        this.age=age; this.name=name;this.price=price;
    }

    // method for sending object
    public String toJSON(){
        return "{age:" + age + ",name:\"" +name +"\",price:\"" +price +"\"}";
    }

    // method for get back original object
    public void initilizeWithJSONString(String jsonString){
        JSONObject json;
        try {
            json =new JSONObject(jsonString );
            age=json.getInt("age");
            name=json.getString("name1");
            price=json.getInt("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
