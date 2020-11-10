package samir.alakbarov.crbn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import samir.alakbarov.crbn.R;


public class QRActivity extends AppCompatActivity {
    
    private TextView typeTV, dataTV;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        
        Button scanBT = findViewById(R.id.scanBT);
        typeTV = findViewById(R.id.typeTV);
        dataTV = findViewById(R.id.dataTV);
        
        scanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start scanner activity
                Intent scannerIntent = new Intent(QRActivity.this, QRScannerActivity.class);
                startActivityForResult(scannerIntent, 50);
            }
        });
        
    }
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // setting returned data to views
        if (data != null) {
            typeTV.setText(data.getStringExtra("type"));
            dataTV.setText(data.getStringExtra("data"));
        }
    }
}