package com.example.a26792.helloworld;
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
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {
    private final String image_type = "image/*";
    private final int IMAGE_CODE = 0;
    private Button choose = null;
    private Button my_button3 = null;
    private ImageView imgShow;
//        记录点击次数
    static int myClickCount = 0;
//        點擊放大或者縮小
    Bitmap bp = null;
    ImageView imageview;
    float scaleWidth;
    float scaleHeight;
    int h;
    boolean num = false;
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        init();
//        tianjia
        DisplayMetrics dm = new DisplayMetrics();//创建矩阵
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        my_button3 = (Button) findViewById(R.id.button3);
        my_button3.setOnClickListener(new MainActivity.MyButtonListener());
        imageview = (ImageView) findViewById(R.id.imgShow);
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.hello);
        int width = bp.getWidth();
        int height = bp.getHeight();
        int w = dm.widthPixels; //得到屏幕的宽度
        int h = dm.heightPixels; //得到屏幕的高度
        scaleWidth = ((float) w) / width;
        scaleHeight = ((float) h) / height;
        imageview.setImageBitmap(bp);
    }
        private void init() {
            choose = (Button) findViewById(R.id.button1);
            choose.setOnClickListener(listener);
//        返回键代码
            my_button3 = (Button) findViewById(R.id.button3);
            my_button3.setOnClickListener(new MyButtonListener());
        }

        private OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                countClick();
                switch (myClickCount) {
                    case 1: {
                        imgShow = (ImageView) findViewById(R.id.imgShow);
                        break;
                    }
                    case 2: {
                        imgShow = (ImageView) findViewById(R.id.imgShow1);
                        break;
                    }
                    case 3: {
                        imgShow = (ImageView) findViewById(R.id.imgShow2);
                        break;
                    }
                    case 4: {
                        imgShow = (ImageView) findViewById(R.id.imgShow3);
                        myClickCount = 0;
                        break;
                    }
                }
                setImage();
            }

            private void countClick() {
                myClickCount++;
            }

        };

        private void setImage() {
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType(image_type);
            startActivityForResult(getAlbum, IMAGE_CODE);
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Bitmap bm = null;
            ContentResolver resolver = getContentResolver();
            if (requestCode == IMAGE_CODE) {
//如果没有try，出现异常会导致程序崩溃，如果try里面有错误，就会返回所写异常的处理（即catch内的东西）
                try {
                    Uri originalUri = data.getData();
                    bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                    imgShow.setImageBitmap(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //    返回按钮代码
        private class MyButtonListener implements OnClickListener {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                myClickCount = 0;
                intent.setClass(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }

        //    點擊放大或者縮小
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:  //当屏幕检测到第一个触点按下之后就会触发到这个事件。
                    if (num == true) {
                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleHeight);

                        Bitmap newBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                        imageview.setImageBitmap(newBitmap);
                        num = false;
                    } else {
                        Matrix matrix = new Matrix();
                        matrix.postScale(1.0f, 1.0f);
                        Bitmap newBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                        imageview.setImageBitmap(newBitmap);
                        num = true;
                    }
                    break;
            }
            return super.onTouchEvent(event);
        }
    }
