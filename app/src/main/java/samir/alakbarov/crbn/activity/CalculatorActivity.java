package samir.alakbarov.crbn.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import samir.alakbarov.crbn.R;


public class CalculatorActivity extends AppCompatActivity {
    
    private TextView resultTV, historyTV;
    private String result = "";
    private String history = "";
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        
        resultTV = findViewById(R.id.resultTV);
        historyTV = findViewById(R.id.historyTV);
        
        //clear all and add to history on long click
        (findViewById(R.id.bDelete)).setOnLongClickListener(v -> {
            String resultText = resultTV.getText() + "";
            if (resultText.contains("=") && resultText.lastIndexOf('=') + 1 < resultText.length()) {
                history += resultText + "\n";
                historyTV.setText(history);
            }
            result = "";
            resultTV.setText("");
            return false;
        });
        
        
    }
    
    
    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onBtClick(View v) {
        try {
            switch (v.getId()) {
                
                case R.id.b0:
                    if (canZero()) {
                        result += "0";
                    }
                    break;
                
                case R.id.b1:
                    result += "1";
                    break;
                
                case R.id.b2:
                    result += "2";
                    break;
                
                case R.id.b3:
                    result += "3";
                    break;
                
                case R.id.b4:
                    result += "4";
                    break;
                
                case R.id.b5:
                    result += "5";
                    break;
                
                case R.id.b6:
                    result += "6";
                    break;
                
                case R.id.b7:
                    result += "7";
                    break;
                
                case R.id.b8:
                    result += "8";
                    break;
                
                case R.id.b9:
                    result += "9";
                    break;
                
                case R.id.bPlus:
                    if (canAdd()) {
                        result += "+";
                    }
                    break;
                
                case R.id.bResult:
                    // Calculating sum
                    
                    String[] split = result.split("\\+");
                    if (split.length == 0 || result.length() == 0 || !result.contains("+")) {
                        break;
                    }
                    
                    BigDecimal sum = new BigDecimal("0.0");
                    
                    for (String db : split) {
                        sum = sum.add(new BigDecimal(db));
                    }
                    
                    if (result.charAt(result.length() - 1) == '+') {
                        result = result.substring(0, result.length() - 1);
                    }
                    
                    // Removing redundant fraction of double
                    String sumStr;
                    long sumInt = sum.longValue();
                    BigDecimal compare = new BigDecimal(sumInt);
                    
                    if (compare.compareTo(sum) == 0) {
                        sumStr = sumInt + "";
                    } else {
                        sumStr = sum.toString();
                    }
                    
                    resultTV.setText(result + "=" + sumStr);
                    break;
                
                case R.id.bDelete:
                    String tvValue = String.valueOf(resultTV.getText());
                    if (tvValue.contains("=")) {
                        tvValue = tvValue.substring(0, tvValue.indexOf('='));
                        resultTV.setText(tvValue);
                        break;
                    }
                    
                    if (result.length() > 0) {
                        result = result.substring(0, result.length() - 1);
                    }
                    
                    break;
                
                case R.id.bDot:
                    if (canDot()) {
                        if (result.length() == 0 || isLastPlus()) {
                            result += "0";
                        }
                        
                        result += ".";
                    }
                    break;
                
            }
            if (v.getId() != R.id.bResult) {
                resultTV.setText(result);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EXCEPTION", e.getMessage() + "");
        }
    }
    
    
    private boolean canDot() {
        //check if dot can be put
        if (result.length() == 0) {
            return true;
        }
        String lastDouble;
        if (result.contains("+")) {
            int lastPlusPos = result.lastIndexOf('+');
            lastDouble = result.substring(lastPlusPos);
        } else {
            lastDouble = result;
        }
        return result.length() != 0 && !lastDouble.contains(".");
        
    }
    
    
    private boolean canZero() {
        //check if can put zero
        String lastDouble;
        if (result.contains("+")) {
            int lastPlusPos = result.lastIndexOf('+');
            lastDouble = result.substring(lastPlusPos);
        } else {
            lastDouble = result;
        }
        
        return lastDouble.length() == 0 || lastDouble.charAt(0) != '0';
    }
    
    
    private boolean isLastPlus() {
        // check if last character of result is '+'
        if (result.length() == 0) {
            return false;
        }
        return result.charAt(result.length() - 1) == '+';
    }
    
    
    private boolean canAdd() {
        // check if can add another value
        return result.length() != 0 && result.charAt(result.length() - 1) != '+' && result.charAt(result.length() - 1) != '.';
    }
    
}