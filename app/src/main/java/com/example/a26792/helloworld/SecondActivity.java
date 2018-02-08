package com.example.a26792.helloworld;

/**
 * Created by 26792 on 2017/9/30.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;//注意view的大小写
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
public class SecondActivity extends AppCompatActivity {
    private Button my_button2 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_button2 = (Button)findViewById(R.id.button2);
        my_button2.setText( "Star" );
        my_button2.setOnClickListener(new MyButtonListener());
    }
    class MyButtonListener implements OnClickListener{
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SecondActivity.this, MainActivity.class);
            SecondActivity.this.startActivity(intent);
        }
    }

    /**
     * 如果下面的语句不要，那么系统运行的时候会直接进入本程序中，而不是先进入主菜单
     * 再进入选择应用程序界面进入本程序
     * 为了方便调试，这里就不进入主菜单界面了*/
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
 getMenuInflater().inflate(R.menu.activity_second, menu);
 return true;
    }*/
}


