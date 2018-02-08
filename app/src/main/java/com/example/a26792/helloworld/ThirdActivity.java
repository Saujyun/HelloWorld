package com.example.a26792.helloworld;

/**
 * Created by 26792 on 2017/10/9.
 */
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.security.PublicKey;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
public class ThirdActivity extends AppCompatActivity {
    private Button my_button3 = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        my_button3 = (Button) findViewById(R.id.button3);
        my_button3.setOnClickListener(new ThirdActivity.MyButtonListener3());
    }
    class MyButtonListener3 implements OnClickListener{
        public void onClick(View v) {
            Intent intent = new Intent();
        }
    }
}