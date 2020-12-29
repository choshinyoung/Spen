import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.choshinyoung.spen.SpenAirActionController;
import com.choshinyoung.spen.SpenAirActionEventReceiver;

public class MainActivity extends AppCompatActivity implements SpenAirActionEventReceiver {

    SpenAirActionController spen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spen = SpenAirActionController.create(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        spen.KeyDown(keyCode, event);

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick() {
        Log.v("Spen Air Action", "Click");
    }

    @Override
    public void onDoubleClick() {
        Log.v("Spen Air Action", "Double Click");
    }

    @Override
    public void onSwipeRight() {
        Log.v("Spen Air Action", "Swipe Right");
    }

    @Override
    public void onSwipeLeft() {
        Log.v("Spen Air Action", "Swipe Left");
    }

    @Override
    public void onSwipeDown() {
        Log.v("Spen Air Action", "Swipe Down");
    }

    @Override
    public void onSwipeUp() {
        Log.v("Spen Air Action", "Swipe Up");
    }

    @Override
    public void onCircleCw() {
        Log.v("Spen Air Action", "Circle Cw");
    }

    @Override
    public void onCircleCcw() {
        Log.v("Spen Air Action", "Circle Ccw");
    }

    /*
    @Override
    public void onSwipe(int direction) {
        switch (direction) {
            case UP:
                Log.v("Spen Air Action", "Swipe Up");
                break;
            case DOWN:
                Log.v("Spen Air Action", "Swipe Down");
                break;
            case RIGHT:
                Log.v("Spen Air Action", "Swipe Right");
                break;
            case LEFT:
                Log.v("Spen Air Action", "Swipe Left");
                break;
        }
    }

    @Override
    public void onCircle(int direction) {
        switch (direction) {
            case RIGHT:
                Log.v("Spen Air Action", "Circle Cw");
                break;
            case LEFT:
                Log.v("Spen Air Action", "Circle Ccw");
                break;
        }
    }
    */
}