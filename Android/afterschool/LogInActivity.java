package com.example.leejaejun.afterschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Server.LogInServerJun;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button logInButton = (Button)findViewById(R.id.loginButton);
        final EditText idEdit = (EditText)findViewById(R.id.idEdit);
        final EditText psEdit = (EditText)findViewById(R.id.psEdit);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEdit.getText().toString().trim();
                String ps = psEdit.getText().toString().trim();
                new LogInServerJun(LogInActivity.this, getApplicationContext().getString(R.string.junYoungURL)+"Login", id, ps).run();

            }
        });
    }
}
