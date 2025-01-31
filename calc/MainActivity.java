package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    EditText etInput;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, 
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnAdd, R.id.btnSub, R.id.btnMul, 
                R.id.btnDiv, R.id.btnEqual, R.id.btnClear
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String value = button.getText().toString();

        if (value.equals("=")) {
            evaluateExpression();
        } else if (value.equals("C")) {
            input = "";
            etInput.setText("");
        } else {
            input += value;
            etInput.setText(input);
        }
    }

    private void evaluateExpression() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            Object result = engine.eval(input);
            etInput.setText(result.toString());
            input = result.toString();
        } catch (ScriptException e) {
            etInput.setText("Error");
        }
    }
}
