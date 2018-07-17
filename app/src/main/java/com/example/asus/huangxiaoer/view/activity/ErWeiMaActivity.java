package com.example.asus.huangxiaoer.view.activity;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.view.custom.AutoScannerView;
import com.google.zxing.Result;
import com.google.zxing.client.android.BaseCaptureActivity;

public class ErWeiMaActivity extends BaseCaptureActivity {
    private static final String TAG = ErWeiMaActivity.class.getSimpleName();
    private SurfaceView surfaceView;
    private AutoScannerView autoScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_er_wei_ma );
        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        autoScannerView = (AutoScannerView) findViewById(R.id.autoscanner_view);
    }
    @Override
    protected void onResume() {
        super.onResume();
        autoScannerView.setCameraManager(cameraManager);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return (surfaceView == null) ? (SurfaceView) findViewById(R.id.preview_view) : surfaceView;
    }

    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        //    Log.i(TAG, "dealDecode ~~~~~ " + rawResult.getText() + " " + barcode + " " + scaleFactor);
        playBeepSoundAndVibrate(true, false);
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
    }
}
