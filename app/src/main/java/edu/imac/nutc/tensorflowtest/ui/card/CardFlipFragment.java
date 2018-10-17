package edu.imac.nutc.tensorflowtest.ui.card;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.imac.nutc.tensorflowtest.R;


import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by user on 2018/7/12.
 */

public class CardFlipFragment extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private MyRecyclerViewAdapter adapter;
    private TextView passTextView;

    private ArrayList<String> randomList;
    private ArrayList<String> reList;
    private ArrayList<String> randomImgList;
    private int r1;
    private int r2;
    private int RandomSize;

    private ArrayList<String> setBitmapPath;
    private String bitmapPath;
    private ArrayList<String> selectBmp;

    private ImageView backCf;
    private int useTime;
    private Timer timer;
    private TextView t;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardflip);
        passTextView = findViewById(R.id.pass_tv);
        backCf = findViewById(R.id.backCf);

        randomList = new ArrayList<>();
        reList = new ArrayList<>();
        randomImgList = new ArrayList<>();
        Random random = new Random();
        r1 = random.nextInt(8 - 2) + 2;//1~7

        //產生亂數表元素
        int reItemSize = 2; //圖片重複數
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= reItemSize; j++) {
                randomList.add(String.valueOf(i));
            }
        }

        //產生隨機亂數表_data
        RandomSize = randomList.size();
        for (int i = 1; i <= RandomSize; i++) {
            r2 = random.nextInt(randomList.size());
            reList.add(String.valueOf(randomList.get(r2)));
            randomList.remove(r2);
        }
        Object list[] = reList.toArray();
        String[] data = Arrays.copyOf(list, list.length, String[].class);

        //產生選擇狀態表_selectList
        int[] selectRegister = new int[RandomSize];
        for (int i = 0; i <= RandomSize - 1; i++) {
            selectRegister[i] = 1;
        }

        //imgSelect
        setBitmapPath = new ArrayList<>();
        selectBmp = new ArrayList<>();
        String[] imgType = {"Mammals", "Amphibian", "Bird", "Fish", "Reptile", "Other"};
        for (int i = 0; i <= 5; i++) {
            bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/" + imgType[i];
            File directory = new File(bitmapPath);
            File[] imgAmount = directory.listFiles();
            if (imgAmount != null) {
                for (int j = 0; j < imgAmount.length; j++) {
                    setBitmapPath.add(imgAmount[j].getPath());
                }
            }
        }
        if (setBitmapPath.size() >= 7) {
            selcetImgType();
        }

        RecyclerView recyclerView = findViewById(R.id.my_recycler);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        //Log.e("sele", "selcetImgType: "+selectBmp.toString() );

        adapter = new MyRecyclerViewAdapter(this, data, selectRegister, selectBmp);
        adapter.setClickListener(this);
        recyclerView.setItemViewCacheSize(6 * reItemSize - 15);
        recyclerView.setAdapter(adapter);

        t = findViewById(R.id.userTimerText);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                handler.postDelayed(this, 1000);
                useTime = useTime + 1;
            }
        };
        runnable.run();
    }


    @Override
    public void onItemClick(View view, int position, int score) {
        passTextView.setVisibility(View.VISIBLE);
        t.setVisibility(View.VISIBLE);
        t.setText("使用了" + useTime + "秒");
    }

    private void selcetImgType() {
        ArrayList a = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int t = random.nextInt(setBitmapPath.size());
            a.add(t);
        }
        noRecycle(a);
        while (a.size() < 7) {
            int t = random.nextInt(setBitmapPath.size());
            a.add(t);
            noRecycle(a);
        }
        for (int i = 0; i < a.size(); i++) {
            Log.e("a", "selcetImgType: " + a.toString());
            selectBmp.add(setBitmapPath.get((Integer) a.get(i)));
            setBitmapPath.remove(a.get(i));
        }
    }

    private void noRecycle(ArrayList a) {
        int number = 0;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                if (a.get(i) == a.get(j)) {
                    number = number + 1;
                }

                if (number == 2) {
                    a.remove(a.get(j));
                    number = 0;
                }

            }
            number = 0;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backCf:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
