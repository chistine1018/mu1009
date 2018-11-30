package edu.imac.nutc.tensorflowtest.ui.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

import edu.imac.nutc.tensorflowtest.R;
import edu.imac.nutc.tensorflowtest.ui.card.CardFlipFragment;
import edu.imac.nutc.tensorflowtest.ui.card.MyRecyclerViewAdapter;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class ContentFragment extends Fragment implements ScreenShotable, MyRecyclerViewAdapter.ItemClickListener {
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
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    RecyclerView recyclerView;

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        //Log.e("sele", "selcetImgType: "+selectBmp.toString() );

        adapter = new MyRecyclerViewAdapter(getContext(), data, selectRegister, selectBmp);
        adapter.setClickListener(this);
        recyclerView.setItemViewCacheSize(6 * reItemSize - 15);
        recyclerView.setAdapter(adapter);


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());

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


    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aaa, container, false);
//        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);
        this.containerView = view.findViewById(R.id.containers);


        passTextView = view.findViewById(R.id.pass_tv);
        backCf = view.findViewById(R.id.backCf);
        recyclerView = view.findViewById(R.id.my_recycler);
        t = view.findViewById(R.id.userTimerText);
        return view;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }


}
