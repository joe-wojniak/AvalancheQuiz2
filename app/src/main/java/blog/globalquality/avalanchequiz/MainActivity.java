package blog.globalquality.avalanchequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    double quizScore;
    double avalanche;

    /**
     * This method is called when the score quiz button is clicked
     */

    public void scoreQuiz(View view) {
        /**
         * Checks the answer to the first question: Number of avalanches in Colorado: 276
         */

        quizScore = 0;

        EditText numberOfAvalanches = (EditText) findViewById(R.id.numberOfAvalanches);

        if (!numberOfAvalanches.getText().toString().isEmpty())
            avalanche = Integer.valueOf(numberOfAvalanches.getText().toString());
        else {
            Toast.makeText(this, "Please Answer the first Question", Toast.LENGTH_LONG).show();
            return;
        }

        if (avalanche >= 276) {
            if (avalanche <= 300) {
                quizScore = 50;
            }
        }

        /**
         * Checks the Avalanche triangle question: Terrain, Snowpack, Weather are correct.
         */
        CheckBox terrain = (CheckBox) findViewById(R.id.terrainCheckBox);
        CheckBox trees = (CheckBox) findViewById(R.id.treesAndRocksCheckBox);
        CheckBox snowpack = (CheckBox) findViewById(R.id.snowpackCheckBox);
        CheckBox weather = (CheckBox) findViewById(R.id.weatherCheckBox);

        if (terrain.isChecked() && !trees.isChecked() && snowpack.isChecked() && weather.isChecked())
            quizScore = quizScore + 30;

        RadioButton Avvy = (RadioButton) findViewById(R.id.AvvyYes);
        if (Avvy.isChecked()) {
            quizScore = quizScore + 10;
        }
        Avvy = (RadioButton) findViewById(R.id.AvvyNo);
        if (Avvy.isChecked()) {
            quizScore = quizScore - 20;
        }

        RadioButton forecast = (RadioButton) findViewById(R.id.forecastYes);
        if (forecast.isChecked()) {
            quizScore = quizScore + 10;
        }
        forecast = (RadioButton) findViewById(R.id.forecastNo);
        if (forecast.isChecked()) {
            quizScore = quizScore - 20;
        }

        /**
         * Check the Danger Scale answers. 5 need to be picked.
         */

        CheckBox miniscule = (CheckBox) findViewById(R.id.minusculeCheckBox);
        CheckBox moderate = (CheckBox) findViewById(R.id.moderateCheckBox);
        CheckBox extreme = (CheckBox) findViewById(R.id.extremeCheckBox);
        CheckBox low = (CheckBox) findViewById(R.id.lowCheckBox);
        CheckBox gargantuan = (CheckBox) findViewById(R.id.gargantuanCheckBox);
        CheckBox medium = (CheckBox) findViewById(R.id.mediumCheckBox);
        CheckBox considerable = (CheckBox) findViewById(R.id.considerableCheckBox);
        CheckBox high = (CheckBox) findViewById(R.id.highCheckBox);

        if (!miniscule.isChecked() && moderate.isChecked() && extreme.isChecked() && low.isChecked()
                && !gargantuan.isChecked() && !medium.isChecked() && considerable.isChecked() && high.isChecked())
            quizScore = quizScore + 50;

        if (!miniscule.isChecked() && !moderate.isChecked() && !extreme.isChecked() && !low.isChecked()
                && !gargantuan.isChecked() && !medium.isChecked() && !considerable.isChecked() && !high.isChecked())
            Toast.makeText(this, "Please choose at least 5 Danger Scale check boxes.", Toast.LENGTH_LONG).show();

        if (miniscule.isChecked() && moderate.isChecked() && extreme.isChecked() && low.isChecked()
                && gargantuan.isChecked() && medium.isChecked() && considerable.isChecked() && high.isChecked())
            Toast.makeText(this, "Please choose no more than 5 Danger Scale check boxes.", Toast.LENGTH_LONG).show();

        /**
         * Check Question: Accidents only happen Danger scale 3-Considerable or higher
         */
        ToggleButton considerableToggle = (ToggleButton) findViewById(R.id.considerableToggleButton);
        if (considerableToggle.isChecked()) {
            quizScore = quizScore - 30;
        } else {
            quizScore = quizScore + 10;
        }

        /**
         * Show Quiz score, format to 2 decimal places
         */

        DecimalFormat df = new DecimalFormat("###.##");
        df.setRoundingMode(RoundingMode.DOWN);

        if (quizScore < 50) {
            Toast.makeText(this, ("The avalanche won this time! Your score: " + df.format(quizScore)), Toast.LENGTH_LONG).show();
        }
        if (quizScore < 140) {
            if (quizScore > 50) {
                Toast.makeText(this, ("Close one. Study more! Your score: " + df.format(quizScore)), Toast.LENGTH_LONG).show();
            }
        }
        if (quizScore > 140) {
            Toast.makeText(this, ("Good Job! Your score: " + df.format(quizScore)), Toast.LENGTH_LONG).show();
        }
    }
}
