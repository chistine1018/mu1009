package edu.imac.nutc.tensorflowtest.ui.main;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import edu.imac.nutc.tensorflowtest.R;
import ru.github.igla.ferriswheel.FerrisWheelView;

public class AnimationActivity extends AppCompatActivity {
    FerrisWheelView ferrisWheelView;
    private TransitionManager manager;
    private Scene scene1;
    private Button button, buttonAbout;

    final Handler handler = new Handler();
    int countSpeed = 10;
    int countCabins = 5;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            countSpeed += 10;
            countCabins++;
            if (countSpeed <= 100) {
                ferrisWheelView.setNumberOfCabins(countCabins);
                ferrisWheelView.setRotateDegreeSpeedInSec(countSpeed);
                ferrisWheelView.resumeAnimation();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
//        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
//        animationView.setAnimation("snowman.json");
//        animationView.loop(true);


        buttonAbout = findViewById(R.id.btn_about);
        button = findViewById(R.id.firstSharedView);
//        ferrisWheelView = findViewById(R.id.ferrisWheelView);
//        ferrisWheelView.setRotateDegreeSpeedInSec(countSpeed);
//        ferrisWheelView.startAnimation();
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
                aboutDialogFragment.show(getFragmentManager(), "ddd");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
//                handler.postDelayed(runnable, 1000);

            }

        });
//        ferrisWheelView.setNumberOfCabins(14);
//        ferrisWheelView.setRotateDegreeSpeedInSec(100);


    }


    public void go() {
//        if (countCabins == 14 || countSpeed == 100) {
//            ferrisWheelView.stopAnimation();

//            Intent intent = new Intent(this, MainActivity.class);

//            manager.transitionTo(scene1);
//            startActivity(intent);
//        }
        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, findViewById(R.id.firstSharedView), "sharedView").toBundle());
    }
}
