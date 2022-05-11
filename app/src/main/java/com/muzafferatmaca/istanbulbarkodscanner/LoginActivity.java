package com.muzafferatmaca.istanbulbarkodscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final int requestCall = 1;

    ImageView callImage, webImage, locationImage, mailImage, whatssappImage;
    Button scanBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        define();
        action();

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void define() {

        scanBtn = findViewById(R.id.scanBtn);
        callImage = findViewById(R.id.callImage);
        webImage = findViewById(R.id.webImage);
        locationImage = findViewById(R.id.localImage);
        mailImage = findViewById(R.id.mailImage);
        whatssappImage = findViewById(R.id.whatsappImage);


    }




    public void callAction() {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+998994334996"));
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.CALL_PHONE}, requestCall);
        } else {
            try {
                startActivity(callIntent);
            } catch (Exception e) {
                /*  Toast.makeText(LoginActivity.this, "Aramak için izin vermelisiniz", Toast.LENGTH_LONG).show();*/
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == requestCall) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAction();
            } else {
                Toast.makeText(LoginActivity.this, "\n" +"Вы должны позволить позвонить", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void action() {

        callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAction();
            }
        });

        webImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //web view düzgün olanlar için
               /* Intent intent = new Intent(LoginActivity.this,WebActivity.class);
                startActivity(intent);*/

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://istanbulbarkod.uz"));
                startActivity(intent);
            }
        });

        locationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://goo.gl/maps/fqHETF47LLNE5Ro87"));
                startActivity(intent);
            }
        });

        mailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("mailto:info@istanbulbarkod.uz"));
                startActivity(intent);
            }
        });


        //for whatsapp


        /*whatssappImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = "Hello !";
                    String toNumber = "905327326074";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });*/

       whatssappImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               try {
                   Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
                   telegramIntent.setData(Uri.parse("https://t.me/i_barkod"));
                   startActivity(telegramIntent);
               } catch (Exception e) {
                   // show error message
               }


           }
       });




    }



}

