package com.bisector.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.*;
import static android.R.layout.simple_spinner_dropdown_item;

public class RegSignSPlash extends AppCompatActivity {
    private Spinner spinner;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                setContentView(R.layout.activity_reg_sign_s_plash);
                spinner = findViewById(R.id.spinnerCountries);
                spinner.setAdapter(new ArrayAdapter<String>(RegSignSPlash.this, simple_spinner_dropdown_item, CountryData.countryNames));

                editText = findViewById(R.id.editTextPhone);

                findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                        String number = editText.getText().toString().trim();

                        if (number.isEmpty() || number.length() < 10) {
                            editText.setError("Valid number is required");
                            editText.requestFocus();
                            return;
                        }

                        String phoneNumber = "+" + code + number;

                        Intent intent = new Intent(RegSignSPlash.this, VerifyPhoneActivity.class);
                        intent.putExtra("phonenumber", phoneNumber);
                        startActivity(intent);

                    }
                });
            }
        }.start();

    }




    @Override
    public void onBackPressed() {
        androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(RegSignSPlash.this);
        dialog.setCancelable(true);
        dialog.setTitle("Exit the App?");
        dialog.setMessage("Do You Want to Leave The App?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);}
        });
        final androidx.appcompat.app.AlertDialog alert = dialog.create();
        alert.show();
    }

    public void Register(View view) {
    }
}