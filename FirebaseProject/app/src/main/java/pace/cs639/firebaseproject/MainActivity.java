package pace.cs639.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextLast;
    private EditText mEditTextFirst;
    private TextView tv;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        mEditTextLast = (EditText) findViewById(R.id.editTextLastName);
        mEditTextFirst = (EditText) findViewById(R.id.editTextTextFirstName);

        tv = (TextView) findViewById(R.id.tv);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("employees");
        Log.i("MAINACTIVITY", myRef.toString());

        //myRef.setValue("YO!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Employee employ;
            int counter = 0;
            String tv_string="Employees: \n";
            for (DataSnapshot ds: dataSnapshot.getChildren()){
                employ = (Employee)ds.getValue(Employee.class);
                Log.i("MAINACTIVITY", counter + " - First Name: " + employ.getFirstName()+ " Last Name: " + employ.getLastName());
                tv_string = tv_string + counter + " - First Name: " +  employ.getFirstName()+ " Last Name: " + employ.getLastName()+ "\n";
                counter++;
            }
            tv.setText(tv_string);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MAINACTIVITY", "Failed to read value.", error.toException());
            }
        });

    }

    public void addEmployee(View view) {
        myRef = database.getReference("employees");
        String firstName = mEditTextFirst.getText().toString();
        String lastName = mEditTextLast.getText().toString();
        if (firstName.length() > 0 && lastName.length() > 0) {
            Employee emp = new Employee(lastName, firstName);
            myRef.push().setValue(emp);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "You must enter both a First Name and Last Name";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}