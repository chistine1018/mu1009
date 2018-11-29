package edu.imac.nutc.tensorflowtest.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;

import edu.imac.nutc.tensorflowtest.R;
import edu.imac.nutc.tensorflowtest.ui.book.BookFragment;
import edu.imac.nutc.tensorflowtest.ui.card.CardFlipFragment;
import edu.imac.nutc.tensorflowtest.ui.photo.InceptionV3Activity;
import info.hoang8f.widget.FButton;

/**
 * Created by user on 2018/9/17.
 */

public class MainActivity extends AppCompatActivity {

    //    private TextView startCamera;
//    private TextView startFlipCard;
    private info.hoang8f.widget.FButton startBook;
    private info.hoang8f.widget.FButton startCamera;
    private info.hoang8f.widget.FButton startFlipCard;
    private ImageView foundDevice;
    RippleBackground rippleBackground;
    boolean judge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCamera = findViewById(R.id.startCamera);
        startFlipCard = findViewById(R.id.startFlipCard);
        startBook = findViewById(R.id.startBook);

//        startBook.setButtonColor(getResources().getColor(R.color.fbutton_color_concrete));
//        startBook.setShadowColor(getResources().getColor(R.color.fbutton_color_asbestos));
//        startBook.setShadowEnabled(true);
//        startBook.setShadowHeight(5);
//        startBook.setCornerRadius(5);
        rippleBackground = (RippleBackground) findViewById(R.id.content);
        foundDevice = (ImageView) findViewById(R.id.foundDevice);
        judge = true;
        final Handler handler = new Handler();
        if (judge) {
            rippleBackground.startRippleAnimation();
//                stop
//                rippleBackground.stopRippleAnimation();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    foundDevice();
                    judge = false;

                }
            }, 3000);
        }


        ImageView imageView = (ImageView) findViewById(R.id.centerImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    private void foundDevice() {
        final Handler handler = new Handler();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

//        scale
//        ArrayList<Animator> animatorList = new ArrayList<Animator>();
//        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleX", 0f, 1.2f, 1f);
//        animatorList.add(scaleXAnimator);
//        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleY", 0f, 1.2f, 1f);
//        animatorList.add(scaleYAnimator);
//        animatorSet.playTogether(animatorList);
        startCamera.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startFlipCard.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startBook.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rippleBackground.stopRippleAnimation();

                            }
                        }, 1500);
                    }
                }, 1500);
            }
        }, 1500);

        animatorSet.start();

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
