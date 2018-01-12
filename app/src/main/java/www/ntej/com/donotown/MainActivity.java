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

    private EditText person1, person2, person3, person4, person5, person6, personsOwe;
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
        person4 = (EditText) findViewById(R.id.person4ET);
        person5 = (EditText) findViewById(R.id.person5ET);
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
        person4.setText(sharedPrefs.getString(getString(R.string.person4),"0"));
        person5.setText(sharedPrefs.getString(getString(R.string.person5),"0"));
        person6.setText(sharedPrefs.getString(getString(R.string.person6),"0"));
        personsOwe.setText(sharedPrefs.getString(getString(R.string.person_owe),"0"));
    }

    public void displayFuckingTotal() {
        int totalFromEditText = Integer.parseInt(person1.getText().toString())
                + Integer.parseInt(person2.getText().toString())
                + Integer.parseInt(person3.getText().toString())
                + Integer.parseInt(person4.getText().toString())
                + Integer.parseInt(person5.getText().toString())
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
        if (person4.getText().toString().isEmpty()) {
            person4.setText("0");
        }
        if (person5.getText().toString().isEmpty()) {
            person5.setText("0");
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
        editor.putString(getString(R.string.person4), person4.getText().toString());
        editor.putString(getString(R.string.person5), person5.getText().toString());
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
