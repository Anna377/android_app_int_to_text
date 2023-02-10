package com.example.integer_to_text_app;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumber;
    private TextView resultText;
    private Button convertButton;
    
    private static final String[] unitsArr = { "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen" };
    private static final String[] tensArr = { "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        inputNumber = findViewById(R.id.input_number);
        resultText = findViewById(R.id.result_text);
        convertButton = findViewById(R.id.convert_button);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(inputNumber.getText().toString().trim());
                if (number < 0 || number > 999_999_999) {
                    resultText.setText("Number must be between 0 and 999,999,999");
                } else {
                    resultText.setText(intToText(number));
                }
            }
        });
    }

    public String intToText(int number) {
        if (number == 0) {
            return "zero";
        }

        if (number < 0) {
            return "minus " + intToText(-1 * number);
        }

        String words = "";

        if ((number / 1000000000) > 0) {
            words += intToText(number / 1000000000) + " billion ";
            number %= 1000000000;
        }

        if ((number / 1000000) > 0) {
            words += intToText(number / 1000000) + " million ";
            number %= 1000000;
        }

        if ((number / 1000) > 0) {
            words += intToText(number / 1000) + " thousand ";
            number %= 1000;
        }

        if ((number / 100) > 0) {
            words += intToText(number / 100) + " hundred ";
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                words += unitsArr[number];
            } else {
                words += tensArr[number/ 10] + " ";
                words += unitsArr[number % 10];
            }
        }

        return words.trim();
    }

}
