package ro.pub.cs.systems.eim.practicaltest01var01;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {
    private Button northButton = null;
    private Button southButton = null;
    private Button eastButton = null;
    private Button westButton = null;
    private TextView counterText = null;
    private int numclicks = 0;
    private boolean started = false;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        String str;

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.North_Button:
                    str = counterText.getText().toString();
                    if (str.isEmpty())
                        str = "North";
                    else
                        str += ", North";

                    numclicks++;
                    counterText.setText(str.toCharArray(), 0, str.length());
                    break;

                case R.id.South_Button:
                    str = counterText.getText().toString();
                    if (str.isEmpty())
                        str = "South";
                    else
                        str += ", South";

                    numclicks++;
                    counterText.setText(str.toCharArray(), 0, str.length());
                    break;

                case R.id.East_Button:
                    str = counterText.getText().toString();
                    if (str.isEmpty())
                        str = "East";
                    else
                        str += ", East";

                    numclicks++;
                    counterText.setText(str.toCharArray(), 0, str.length());
                    break;

                case R.id.West_Button:
                    str = counterText.getText().toString();
                    if (str.isEmpty())
                        str = "West";
                    else
                        str += ", West";

                    numclicks++;
                    counterText.setText(str.toCharArray(), 0, str.length());
                    break;

                case R.id.Navigate_Button:
                    counterText.setText("".toCharArray(), 0 , 0);
                    numclicks = 0;
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);

                    startActivityForResult(intent, 1);
                    break;
            }

            if (numclicks > 3 && !started) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra("instruction", str);
                getApplicationContext().startService(intent);
                started = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        northButton = (Button)findViewById(R.id.North_Button);
        southButton = (Button)findViewById(R.id.South_Button);
        eastButton = (Button) findViewById(R.id.East_Button);
        westButton = (Button)findViewById(R.id.West_Button);
        counterText = (TextView)findViewById(R.id.Counter_TextView);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null)
            numclicks = Integer.valueOf(savedInstanceState.getString("numclicks"));

        Log.d("", "Numarul de clickuri" + String.valueOf(numclicks) + "\n");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("numclicks", String.valueOf(numclicks));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, "S-a apasat REGISTER." + resultCode, Toast.LENGTH_LONG).show();
            else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "S-a apasat Cancel." + resultCode, Toast.LENGTH_LONG).show();
        }
    }

}
