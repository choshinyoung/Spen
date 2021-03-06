import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.choshinyoung.spen.SpenRemoteController;
import com.choshinyoung.spen.SpenRemoteEventReceiver;

public class MainActivity extends AppCompatActivity implements SpenRemoteEventReceiver {

    SpenRemoteController spen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spen = SpenRemoteController.create(this, this);
    }

    @Override
    protected void onResume() {
        spen.initSpenRemote();

        super.onResume();
    }

    @Override
    protected void onPause() {
        spen.releaseSpenRemote();

        super.onPause();
    }

    @Override
    public void SpenButtonEvent() {
        Log.v("Spen Button", Boolean.toString(spen.getButtonPressed()));
    }

    @Override
    public void SpenAirMotionEvent() {
        Log.v("Spen AirMotion", "x: " + spen.getX() + ", y: " + spen.getY());
    }
}