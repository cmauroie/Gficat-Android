package com.cmauroie.gficatandroid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;

public class DetailsActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView textViewTitle;
    private TextView textViewDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        videoView = (VideoView)findViewById(R.id.id_video);
        textViewTitle = (TextView)findViewById(R.id.id_title_album);
        textViewDescription = (TextView)findViewById(R.id.id_deatils_album);
        initial();

    }

    private void initial(){
        String titleStr = getIntent().getStringExtra("title");
        String urlVideoStr = getIntent().getStringExtra("url_video");
        String descriptionStr = getIntent().getStringExtra("description");

        textViewTitle.setText(titleStr);
        textViewDescription.setText(descriptionStr);

        playVideo(urlVideoStr);
    }

    private void playVideo(String videoUrl){
        if (videoUrl != null) {
            Uri vidUri = Uri.parse(videoUrl);
            videoView.setVideoURI(vidUri);
            videoView.start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
