package com.jikexueyuan.notebook;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by QQQ on 2016/8/12.
 */
public class AddContent extends AppCompatActivity implements View.OnClickListener{

    private String val;
    private Button savebtn, deletebtn;
    private EditText etText;
    private ImageView c_img;
    private VideoView v_video;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);

        val = getIntent().getStringExtra("flag");

        savebtn = (Button) findViewById(R.id.id_btn_save);
        deletebtn = (Button) findViewById(R.id.id_btn_cancel);
        etText = (EditText) findViewById(R.id.id_c_ettext);
        c_img = (ImageView) findViewById(R.id.id_c_img);
        v_video = (VideoView) findViewById(R.id.id_c_video);

        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();

        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_save:
                addDB();
                finish();
                break;
            case R.id.id_btn_cancel:
                finish();
                break;
        }
    }

    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT, etText.getText().toString());
        cv.put(NotesDB.TIME,getTime());
        dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
    }

    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }
}
