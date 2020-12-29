package com.choshinyoung.spen;

import android.view.KeyEvent;

public class SpenAirActionController {

    public SpenAirActionEventReceiver receiver;

    public static SpenAirActionController create(SpenAirActionEventReceiver rcv) {
        SpenAirActionController controller = new SpenAirActionController();
        controller.receiver = rcv;
        return controller;
    }

    public void KeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            receiver.onClick();
        }
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            receiver.onDoubleClick();
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            receiver.onSwipeRight();
            receiver.onSwipe(SpenAirActionEventReceiver.RIGHT);
        }
        else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            receiver.onSwipeLeft();
            receiver.onSwipe(SpenAirActionEventReceiver.LEFT);
        }
        else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            receiver.onSwipeUp();
            receiver.onSwipe(SpenAirActionEventReceiver.UP);
        }
        else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            receiver.onSwipeDown();
            receiver.onSwipe(SpenAirActionEventReceiver.DOWN);
        }
        else if (keyCode == KeyEvent.KEYCODE_PAGE_DOWN) {
            receiver.onCircleCw();
            receiver.onCircle(SpenAirActionEventReceiver.RIGHT);
        }
        else if (keyCode == KeyEvent.KEYCODE_PAGE_UP) {
            receiver.onCircleCcw();
            receiver.onCircle(SpenAirActionEventReceiver.LEFT);
        }
    }
}
