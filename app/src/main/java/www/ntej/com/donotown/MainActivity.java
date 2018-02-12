package www.ntej.com.donotown;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText person1, person2, person3, person5_0,person5_1,person5_2,person5_3, person6, personsOwe;
    private TextView total;
    private Button totalButton;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidgets();
        loadDataFromSharedPrefs();
    }

    public void initializeWidgets() {
        person1 = (EditText) findViewById(R.id.person1ET);
        person2 = (EditText) findViewById(R.id.person2ET);
        person3 = (EditText) findViewById(R.id.person3ET);
        person5_0 = (EditText) findViewById(R.id.person5_0ET);
        person5_1 = (EditText) findViewById(R.id.person5_1ET);
        person5_2 = (EditText) findViewById(R.id.person5_2ET);
        person5_3 = (EditText) findViewById(R.id.person5_3ET);
        person6 = (EditText) findViewById(R.id.person6ET);
        personsOwe = (EditText) findViewById(R.id.personOweET);
        total = (TextView) findViewById(R.id.netTV);
        totalButton = (Button) findViewById(R.id.totalButton);
        totalButton.setOnClickListener(this);
    }

    public void loadDataFromSharedPrefs() {
        sharedPrefs = this.getPreferences(Context.MODE_PRIVATE);
        person1.setText(sharedPrefs.getString(getString(R.string.person1),"0"));
        person2.setText(sharedPrefs.getString(getString(R.string.person2),"0"));
        person3.setText(sharedPrefs.getString(getString(R.string.person3),"0"));
        person5_0.setText(sharedPrefs.getString(getString(R.string.person5_0),"0"));
        person5_1.setText(sharedPrefs.getString(getString(R.string.person5_1),"0"));
        person5_2.setText(sharedPrefs.getString(getString(R.string.person5_2),"0"));
        person5_3.setText(sharedPrefs.getString(getString(R.string.person5_3),"0"));
        person6.setText(sharedPrefs.getString(getString(R.string.person6),"0"));
        personsOwe.setText(sharedPrefs.getString(getString(R.string.person_owe),"0"));
    }

    public void displayFuckingTotal() {
        int totalFromEditText = Integer.parseInt(person1.getText().toString())
                + Integer.parseInt(person2.getText().toString())
                + Integer.parseInt(person3.getText().toString())
                + Integer.parseInt(person5_0.getText().toString())
                + Integer.parseInt(person5_1.getText().toString())
                + Integer.parseInt(person5_2.getText().toString())
                + Integer.parseInt(person5_3.getText().toString())
                + Integer.parseInt(person6.getText().toString())
                - Integer.parseInt(personsOwe.getText().toString());
        Log.i(TAG, "displayFuckingTotal:" + totalFromEditText);
        total.setText("$" + totalFromEditText);
    }

    @Override
    public void onClick(View view) {
        checkForEmptyStrings();
        displayFuckingTotal();

    }

    public void checkForEmptyStrings() {
        if (person1.getText().toString().isEmpty()) {
            person1.setText("0");
        }
        if (person2.getText().toString().isEmpty()) {
            person2.setText("0");
        }
        if (person3.getText().toString().isEmpty()) {
            person3.setText("0");
        }
        if (person5_0.getText().toString().isEmpty()) {
            person5_0.setText("0");
        }
        if (person5_1.getText().toString().isEmpty()) {
            person5_1.setText("0");
        }
        if (person5_2.getText().toString().isEmpty()) {
            person5_2.setText("0");
        }
        if (person5_3.getText().toString().isEmpty()) {
            person5_3.setText("0");
        }
        if (person6.getText().toString().isEmpty()) {
            person6.setText("0");
        }
        if (personsOwe.getText().toString().isEmpty()) {
            personsOwe.setText("0");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkForEmptyStrings();
        sharedPrefs = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(getString(R.string.person1), person1.getText().toString());
        editor.putString(getString(R.string.person2), person2.getText().toString());
        editor.putString(getString(R.string.person3), person3.getText().toString());
        editor.putString(getString(R.string.person5_0), person5_0.getText().toString());
        editor.putString(getString(R.string.person5_1), person5_1.getText().toString());
        editor.putString(getString(R.string.person5_2), person5_2.getText().toString());
        editor.putString(getString(R.string.person5_3), person5_3.getText().toString());
        editor.putString(getString(R.string.person6), person6.getText().toString());
        editor.putString(getString(R.string.person_owe), personsOwe.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPrefs = null;
    }
}
