package com.jikexueyuan.notebook;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by QQQ on 2016/8/13.
 */
public class SelectAct extends AppCompatActivity implements View.OnClickListener{

    private Button s_delete, s_back;
    private ImageView s_img;
    private VideoView s_video;
    private TextView s_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        s_delete = (Button) findViewById(R.id.id_s_btndelete);
        s_back = (Button) findViewById(R.id.id_s_btnback);
        s_img = (ImageView) findViewById(R.id.id_s_img);
        s_video = (VideoView) findViewById(R.id.id_s_video);
        s_tv = (TextView) findViewById(R.id.id_s_tv);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();

        s_delete.setOnClickListener(this);
        s_back.setOnClickListener(this);

        if (getIntent().getStringExtra(NotesDB.PATH).equals(null)) {
            s_img.setVisibility(View.GONE);
        }else {
            s_img.setVisibility(View.VISIBLE);
        }
        if (getIntent().getStringExtra(NotesDB.VIDEO).equals(null)) {
            s_video.setVisibility(View.GONE);
        }else {
            s_video.setVisibility(View.VISIBLE);
        }
        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(NotesDB.PATH));
        s_img.setImageBitmap(bitmap);
        s_video.setVideoURI(Uri.parse(getIntent().getStringExtra(NotesDB.VIDEO)));
        s_video.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_s_btndelete:
                deleteData();
                finish();
                break;
            case R.id.id_s_btnback:
                finish();
                break;
        }
    }

    public void deleteData(){
        dbWriter.delete(NotesDB.TABLE_NAME, "_id=" + getIntent().getIntExtra(NotesDB.ID,0), null);
    }
}
