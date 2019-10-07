package com.example.imageviewunderstanding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
ImageButton imageButton;
Intent intent;
Bitmap bitmap;
Button b1;
final static int clickcode=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream=getResources().openRawResource(R.drawable.img);
        bitmap= BitmapFactory.decodeStream(inputStream);
        imageButton=findViewById(R.id.b1);
        imageView= findViewById(R.id.img);
        b1=findViewById(R.id.wall);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Camera Button Click", Toast.LENGTH_SHORT).show();
                intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,clickcode);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle bundle_ashwini =data.getExtras();//pass the value
            bitmap=(Bitmap)bundle_ashwini.get("data");
            imageView.setImageBitmap(bitmap);

        }

    }
}
