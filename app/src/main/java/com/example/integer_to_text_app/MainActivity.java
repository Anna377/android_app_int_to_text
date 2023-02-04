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
    
    private static final String[] units_arr = { "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen" };
    private static final String[] tens_arr = { "", "", "twenty", "thirty", "forty",
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
                    resultText.setText(int_to_text(number));
                }
            }
        });
    }

    public String int_to_text(int number) {
        if (number == 0) {
            return "zero";
        }

        if (number < 0) {
            return "minus " + int_to_text(-1 * number);
        }

        String words = "";

        if ((number / 1000000000) > 0) {
            words += int_to_text(number / 1000000000) + " billion ";
            number %= 1000000000;
        }

        if ((number / 1000000) > 0) {
            words += int_to_text(number / 1000000) + " million ";
            number %= 1000000;
        }

        if ((number / 1000) > 0) {
            words += int_to_text(number / 1000) + " thousand ";
            number %= 1000;
        }

        if ((number / 100) > 0) {
            words += int_to_text(number / 100) + " hundred ";
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                words += units_arr[number];
            } else {
                words += tens_arr[number/ 10] + " ";
                words += units_arr[number % 10];
            }
        }

        return words.trim();
    }

}
