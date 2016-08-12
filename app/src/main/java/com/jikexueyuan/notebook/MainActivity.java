package com.jikexueyuan.notebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button textbtn,imgbtn, videobtn;
    private ListView lv;
    private Intent i;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init(){
        lv = (ListView) findViewById(R.id.id_list);
        textbtn = (Button) findViewById(R.id.id_text);
        imgbtn = (Button) findViewById(R.id.id_img);
        videobtn = (Button) findViewById(R.id.id_video);

        textbtn.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
        videobtn.setOnClickListener(this);
        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();
    }

    @Override
    public void onClick(View view) {
        i = new Intent(this, AddContent.class);
        switch (view.getId()) {
            case R.id.id_text:
                i.putExtra("flag", "1");
                startActivity(i);
                break;
            case R.id.id_img:
                i.putExtra("flag", "2");
                startActivity(i);
                break;
            case R.id.id_video:
                i.putExtra("flag", "3");
                startActivity(i);
                break;
        }
    }

    public void selectDB(){
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null, null, null, null);
        adapter = new MyAdapter(this, cursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }
}
