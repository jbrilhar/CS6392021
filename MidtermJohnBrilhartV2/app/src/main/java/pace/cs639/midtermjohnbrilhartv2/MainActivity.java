package pace.cs639.midtermjohnbrilhartv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText1);
        mTextView = (TextView) findViewById(R.id.textView);

    }

    public void convert(View view) {
        String liters = mEditText.getText().toString();
        try{double num= (double)Integer.parseInt(liters);
            double result = num * .219;
            String answer = Double.toString(result);
            mTextView.setText(answer + " gallons");

        }
        catch(Exception e){

            mTextView.setText("Please only enter numbers!");

        }

    }
}