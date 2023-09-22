package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Httpsrequest extends AppCompatActivity {
    private TextView mtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpsrequest);

        mtext=findViewById(R.id.text_view);

        OkHttpClient client=new OkHttpClient();
        String url="https://reqres.in/api/users?page=2https://reqres.in/api/users?page=2";
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse=response.body().string();
                    Httpsrequest.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtext.setText(myResponse);
                        }
                    });
                }

            }
        });

    }
}