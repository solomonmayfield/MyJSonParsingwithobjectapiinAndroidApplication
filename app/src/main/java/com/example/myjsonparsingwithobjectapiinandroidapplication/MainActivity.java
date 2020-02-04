package com.example.myjsonparsingwithobjectapiinandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    //json file in app/src/main/my.json
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringBuilder builder = new StringBuilder();
        try {
           InputStream is =  getAssets().open("my.json");
            while (true){

                int ch = is.read();
                if(ch == -1) break;
                else builder.append((char)ch);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        parseUsingJsonObjectApi(builder.toString());

    }

    private void parseUsingJsonObjectApi(String json){


        StringBuilder builder = new StringBuilder();
        try {
            JSONObject rootObject = new JSONObject(json);//call JSONObject
            String name = rootObject.getString("first_name");
            int age = rootObject.getInt("age");
            String email = rootObject.getString("email");

            builder.append("\n Name ").append(name);
            builder.append("\n Age ").append(age);
            builder.append("\n Email ").append(email);
            //to get object inside of object
            //uncomment
            JSONObject innerObj = rootObject.getJSONObject("friends" );
            String male = innerObj.getString("male");
            String female = innerObj.getString("female");
            //to get the list inside of object
            //uncomment
            JSONArray car = rootObject.getJSONArray("car" );
            for (int i=0; i<car.length(); i++){
                JSONObject arrObj = car.getJSONObject(i);
                String carMake = arrObj.getString("make");
                String carModel = arrObj.getString("model");
            }
            Log.i("@Parsed", builder.toString());
        }catch(Exception e) {

            e.printStackTrace();
        }



    }
}
