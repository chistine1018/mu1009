package edu.imac.nutc.tensorflowtest.ui.photo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.imac.nutc.tensorflowtest.R;
import edu.imac.nutc.tensorflowtest.RecognitionUnit;
import edu.imac.nutc.tensorflowtest.ui.book.DataSort;
import edu.imac.nutc.tensorflowtest.ui.main.MainActivity;

/**
 * Created by cheng on 2018/2/19.
 */

public class InceptionV3Activity extends AppCompatActivity {
    private static final String TAG = InceptionV3Activity.class.getName();
    private static final String MODEL_FILE = "file:///android_asset/inception_v3_2016_08_28_frozen.pb";
    private static final String LABEL_PATH = "imagenet_slim_labels.txt";
    private static final String INPUT_NODE = "input";
    private static final String OUTPUT_NODE = "InceptionV3/Predictions/Reshape_1";
    private static int INPUT_SIZE = 299;
    private TensorFlowInferenceInterface inferenceInterface;
    private int[] intValues;
    private float[] inputFloat;
    private ArrayList<RecognitionUnit> result;
    private LinearLayout backBtn;

    //camera
    private ImageView mImg;
    private TextView mImagText;
    private TextView resolution;
    private DisplayMetrics mPhone;
    private final static int CAMERA = 66;
    private final static int PHOTO = 99;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inception);

        //讀取手機解析度
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        mImg = findViewById(R.id.showImg);
        mImagText = findViewById(R.id.showText);
        resolution = findViewById(R.id.resolution);
        backBtn = findViewById(R.id.back);

        //start camera
        ContentValues value = new ContentValues();
//        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                value);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        //init();
    }
//    public void camaraBtnOnclick() {
//
//        String fileName = "testphoto.jpg";
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, fileName);
//        values.put(MediaStore.Images.Media.DESCRIPTION,
//                "Image capture by camera");
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        imageUri = getActivity().getContentResolver().insert(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        startActivityForResult(intent, CAMERA);
//    }
    //拍照完畢或選取圖片後呼叫此函式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("urii", "onActivityResult: "+data );
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if ((requestCode == REQUEST_IMAGE_CAPTURE || requestCode == PHOTO)&&data!=null) {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver contentResolver = this.getContentResolver();

//            try {
                Log.e("uri", "onActivityResult: "+uri );
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                mImg.setImageBitmap(changeBitmapSize(imageBitmap));
                mImg.setRotation(90);
                //init(changeBitmapSize(bitmap));
//            } catch (FileNotFoundException e) {
//            }
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    }

    private Bitmap changeBitmapSize(Bitmap bitmap) {

        Log.e("old", bitmap.getWidth() + " " + bitmap.getHeight());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int crop = 0;
        int startX = 0;
        int startY = 0;
        int endWidth = width;
        int endHeight = height;

        if (width > height) {
            crop = width - height;
            startX = crop / 2;
            endWidth -= crop;
        } else if (height > width) {
            crop = height - width;
            startY = crop / 2;
            endHeight -= crop;
        }
        Bitmap bitmapChangeSize = Bitmap.createBitmap(bitmap, startX, startY, endWidth, endHeight, null, true);
        int width2 = bitmapChangeSize.getWidth();
        int height2 = bitmapChangeSize.getHeight();

        //設置大小
        int changeSizeW = 299;
        int changeSizeH = 299;

        //計算壓縮大小
        float scaleWidth = (float) changeSizeW / (float) width2;
        float scaleHeight = (float) changeSizeH / (float) height2;
        Log.e("old", scaleWidth + " " + scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmapChangeSize, 0, 0, width2, height2, matrix, true);

        Log.e("new", bitmap.getWidth() + " " + bitmap.getHeight());
        init(bitmap);
        saveBitmap(bitmap);
        return bitmap;
    }

    private void saveBitmap(Bitmap bmp) {
        String dataName = result.get(0).getLabel();
        Log.e("data", "saveBitmap: "+dataName );
        DataSort dataSort = new DataSort();
        Boolean other = true;
        for (String s : dataSort.mammalsArray) {
            if (s.trim().equals(dataName)){
                Log.e("1", "saveBitmap: " );
                creatAlbum(bmp,"Mammals");
                other = false;
            }
        }
        for (String s : dataSort.amphibianArray) {
            if (s.trim().equals(dataName)){
                Log.e("2", "saveBitmap: " );
                creatAlbum(bmp,"Amphibian");
                other = false;
            }
        }
        for (String s : dataSort.birdArray) {
            if (s.trim().equals(dataName)){
                Log.e("3", "saveBitmap: " );
                creatAlbum(bmp,"Bird");
                other = false;
            }
        }
        for (String s : dataSort.fishArray) {
            if (s.trim().equals(dataName)){
                Log.e("4", "saveBitmap: " );
                creatAlbum(bmp,"Fish");
                other = false;
            }
        }
        for (String s : dataSort.reptileArray) {
            if (s.trim().equals(dataName)){
                Log.e("5", "saveBitmap: " );
                creatAlbum(bmp,"Reptile");
                other = false;
            }
        }
        for (String s : dataSort.otherArray) {
            if (other){
                Log.e("6", "saveBitmap: " );
                creatAlbum(bmp,"Other");
            }
        }

    }

    private void creatAlbum(Bitmap bmp,String name){

        String bitmapPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/"+name;
        String bitmapName =result.get(0).getLabel()+ ".jpg";
        File myBitmapDir = new File(bitmapPath);
        if (!myBitmapDir.exists()) {
            myBitmapDir.mkdirs();
        }
        File file = new File(myBitmapDir, bitmapName);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init(Bitmap bitmap) {
        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);
        intValues = new int[INPUT_SIZE * INPUT_SIZE];
        inputFloat = new float[INPUT_SIZE * INPUT_SIZE * 3];
        result = new ArrayList<>();
        loadLabelModel();
        //convertBitmapToByteBuffer(getBitmapFromAsset(this, "bird299.png"));
        convertBitmapToByteBuffer(bitmap);

    }

    private void loadLabelModel() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(LABEL_PATH)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                RecognitionUnit recognitionUnit = new RecognitionUnit();
                recognitionUnit.setLabel(mLine);
                result.add(recognitionUnit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        Log.d(TAG, "convertBitmapToByteBuffer: " + bitmap.getWidth());
        Log.d(TAG, "convertBitmapToByteBuffer: " + bitmap.getHeight());
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // Convert the image to floating point.
        for (int i = 0; i < intValues.length; ++i) {
            final float r = ((intValues[i] >> 16) & 0xFF) / 255.0f;
            final float g = ((intValues[i] >> 8) & 0xFF) / 255.0f;
            final float b = (intValues[i] & 0xFF) / 255.0f;
            inputFloat[i * 3 + 0] = r;
            inputFloat[i * 3 + 1] = g;
            inputFloat[i * 3 + 2] = b;
            if (i == intValues.length - 1) {
                startProcess();
            }
        }
    }

    private void startProcess() {
        inferenceInterface.feed(INPUT_NODE, inputFloat, 1, INPUT_SIZE, INPUT_SIZE, 3);
        final float[] resu = new float[1001];
        inferenceInterface.run(new String[]{OUTPUT_NODE});
        inferenceInterface.fetch(OUTPUT_NODE, resu);
        for (int i = 0; i < 1001; i++) {
            result.get(i).setValue(resu[i]);
        }
        sortArray();
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "label : " + result.get(i).getLabel() + "value : " + result.get(i).getValue());
        }
        mImagText.setText(result.get(0).getLabel());
        resolution.setText("辨識度：" + result.get(0).getValue());
    }

    private void sortArray() {
        for (int i = 0; i < result.size(); i++) {
            for (int j = i; j < result.size(); j++) {
                if (result.get(j).getValue() > result.get(i).getValue()) {
                    String label = result.get(i).getLabel();
                    float value = result.get(i).getValue();
                    result.get(i).setLabel(result.get(j).getLabel());
                    result.get(i).setValue(result.get(j).getValue());
                    result.get(j).setLabel(label);
                    result.get(j).setValue(value);
                }
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent backActivity = new Intent();
                backActivity.setClass(InceptionV3Activity.this, MainActivity.class);
                startActivity(backActivity);
                break;
        }
    }

}
