package samir.alakbarov.crbn.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class QRScannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    
    private final int CAMERA_REQUEST_CODE = 100;
    private final int SCANNER_RESULT_CODE = 10;
    private ZBarScannerView mScannerView;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting ZBarScannerView as ContentView
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        
        // requesting camera permission for Android M and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        }
        
    }
    
    
    @Override
    public void onResume() {
        super.onResume();
        // setting current activity as ResultHandler and starting camera
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    
    
    @Override
    public void onPause() {
        super.onPause();
        // stopping camera on pause
        mScannerView.stopCamera();
    }
    
    
    @Override
    public void handleResult(Result rawResult) {
        // handling result and return it to previous activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("data", rawResult.getContents());
        resultIntent.putExtra("type", rawResult.getBarcodeFormat().getName());
        setResult(SCANNER_RESULT_CODE, resultIntent);
        finish();
        
        //mScannerView.resumeCameraPreview(this);
    }
    
    
    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // handling permissions
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        }
        
    }
    
}