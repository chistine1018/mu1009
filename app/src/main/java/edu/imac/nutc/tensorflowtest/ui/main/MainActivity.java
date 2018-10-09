package edu.imac.nutc.tensorflowtest.ui.main;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.imac.nutc.tensorflowtest.R;
import edu.imac.nutc.tensorflowtest.ui.book.BookFragment;
import edu.imac.nutc.tensorflowtest.ui.card.CardFlipFragment;
import edu.imac.nutc.tensorflowtest.ui.photo.InceptionV3Activity;

/**
 * Created by user on 2018/9/17.
 */

public class MainActivity extends AppCompatActivity {

    private TextView startCamera;
    private TextView startFlipCard;
    private TextView startBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCamera = findViewById(R.id.startCamera);
        startFlipCard = findViewById(R.id.startFlipCard);
        startBook = findViewById(R.id.startBook);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startCamera:
                Intent startCamera = new Intent();
                startCamera.setClass(MainActivity.this, InceptionV3Activity.class);
                startActivity(startCamera);
                break;
            case R.id.startFlipCard:
                Intent startFlipCard = new Intent();
                startFlipCard.setClass(MainActivity.this, CardFlipFragment.class);
                startActivity(startFlipCard);
                break;
            case R.id.startBook:
                Intent startBook = new Intent();
                startBook.setClass(MainActivity.this, BookFragment.class);
                startActivity(startBook);
                break;
        }
    }
}
