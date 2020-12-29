import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.choshinyoung.spen.SpenRemoteController;
import com.choshinyoung.spen.SpenRemoteEventReceiver;
import com.samsung.android.sdk.penremote.AirMotionEvent;
import com.samsung.android.sdk.penremote.ButtonEvent;

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
    public void SpenButtonEvent(ButtonEvent buttonEvent) {
        Log.v("Spen Button", Boolean.toString(spen.buttonPressed));
    }

    @Override
    public void SpenAirMotionEvent(AirMotionEvent airMotionEvent) {
        Log.v("Spen AirMotion", "x: " + airMotionEvent.getDeltaX() + ", y: " + airMotionEvent.getDeltaY());
    }
}