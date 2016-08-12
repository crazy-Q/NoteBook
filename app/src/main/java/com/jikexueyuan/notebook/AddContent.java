package com.jikexueyuan.notebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by QQQ on 2016/8/12.
 */
public class AddContent extends AppCompatActivity {

    private String val;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);

        val = getIntent().getStringExtra("flag");

    }
}
