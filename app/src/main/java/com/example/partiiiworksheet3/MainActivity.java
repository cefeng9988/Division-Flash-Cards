package com.example.partiiiworksheet3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUser.getText().toString().equals("johnsmith") && edtPass.getText().toString().equals("cs501")){
                    Intent intent = new Intent(MainActivity.this, flashCardsActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Welcome JohnSmith!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//public class MainActivity extends AppCompatActivity {
//    private Button btn1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //change btn name
//        btn1 = (Button)findViewById(R.id.btn1);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newActivity();
//            }
//        });
//    }
//
//    public void newActivity(){
//        Intent intent = new Intent(getApplicationContext(), flashCardsActivity.class);
//        startActivity(intent);
//    }
//}