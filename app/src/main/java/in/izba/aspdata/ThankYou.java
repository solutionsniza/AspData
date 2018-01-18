package in.izba.aspdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        TextView payNow = (TextView) findViewById(R.id.textClick);
        payNow.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
