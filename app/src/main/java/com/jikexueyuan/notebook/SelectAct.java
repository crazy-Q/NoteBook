package com.jikexueyuan.notebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by QQQ on 2016/8/13.
 */
public class SelectAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        Log.d("TAG", getIntent().getIntExtra(NotesDB.ID, 0) + "");
    }
}
