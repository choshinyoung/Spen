package com.choshinyoung.spen;

import android.view.KeyEvent;

public class SpenAirActionController {

    public SpenAirActionEventReceiver receiver;

    private boolean isClick = false;
    public boolean getIsClick() {
        boolean b = isClick;
        isClick = false;

        return b;
    }

    private boolean isDoubleClick = false;
    public boolean getIsDoubleClick() {
        boolean b = isDoubleClick;
        isDoubleClick = false;

        return b;
    }

    private boolean isSwipeRight = false;
    public boolean getIsSwipeRight() {
        boolean b = isSwipeRight;
        isSwipeRight = false;

        return b;
    }

    private boolean isSwipeLeft = false;
    public boolean getIsSwipeLeft() {
        boolean b = isSwipeLeft;
        isSwipeLeft = false;

        return b;
    }

    private boolean isSwipeUp = false;
    public boolean getIsSwipeUp() {
        boolean b = isSwipeUp;
        isSwipeUp = false;

        return b;
    }

    private boolean isSwipeDown = false;
    public boolean getIsSwipeDown() {
        boolean b = isSwipeDown;
        isSwipeDown = false;

        return b;
    }

    private boolean isCircleCw = false;
    public boolean getIsCircleCw() {
        boolean b = isCircleCw;
        isCircleCw = false;

        return b;
    }

    private boolean isCircleCcw = false;
    public boolean getIsCircleCcw() {
        boolean b = isCircleCcw;
        isCircleCcw = false;

        return b;
    }

    private int isSwipe = -1;
    public int getIsSwipe() {
        int i = isSwipe;
        isSwipe = -1;

        return i;
    }

    private int isCircle = -1;
    public int getIsCircle() {
        int i = isCircle;
        isCircle = -1;

        return i;
    }

    public static SpenAirActionController create(SpenAirActionEventReceiver rcv) {
        SpenAirActionController controller = new SpenAirActionController();
        controller.receiver = rcv;
        return controller;
    }

    public static SpenAirActionController create() {
        SpenAirActionController controller = new SpenAirActionController();
        controller.receiver = null;
        return controller;
    }

    public void KeyDown(int keyCode, boolean isUnity) {
        if (keyCode == (isUnity ? 32 : KeyEvent.KEYCODE_SPACE)) {
            isClick = true;

            if (receiver != null)
                receiver.onClick();
        }
        else if (keyCode == (isUnity ? 13 : KeyEvent.KEYCODE_ENTER)) {
            isDoubleClick = true;

            if (receiver != null)
                receiver.onDoubleClick();
        }
        else if (keyCode == (isUnity ? 275 : KeyEvent.KEYCODE_DPAD_RIGHT)) {
            isSwipeRight = true;
            isSwipe = SpenAirActionEventReceiver.RIGHT;

            if (receiver != null) {
                receiver.onSwipeRight();
                receiver.onSwipe(SpenAirActionEventReceiver.RIGHT);
            }
        }
        else if (keyCode == (isUnity ? 276 : KeyEvent.KEYCODE_DPAD_LEFT)) {
            isSwipeLeft = true;
            isSwipe = SpenAirActionEventReceiver.LEFT;

            if (receiver != null) {
                receiver.onSwipeLeft();
                receiver.onSwipe(SpenAirActionEventReceiver.LEFT);
            }
        }
        else if (keyCode == (isUnity ? 273 : KeyEvent.KEYCODE_DPAD_UP)) {
            isSwipeUp = true;
            isSwipe = SpenAirActionEventReceiver.UP;

            if (receiver != null) {
                receiver.onSwipeUp();
                receiver.onSwipe(SpenAirActionEventReceiver.UP);
            }
        }
        else if (keyCode == (isUnity ? 274 : KeyEvent.KEYCODE_DPAD_DOWN)) {
            isSwipeDown = true;
            isSwipe = SpenAirActionEventReceiver.DOWN;

            if (receiver != null) {
                receiver.onSwipeDown();
                receiver.onSwipe(SpenAirActionEventReceiver.DOWN);
            }
        }
        else if (keyCode == (isUnity ? 43 : KeyEvent.KEYCODE_PLUS)) {
            isCircleCw = true;
            isCircle = SpenAirActionEventReceiver.RIGHT;

            if (receiver != null) {
                receiver.onCircleCw();
                receiver.onCircle(SpenAirActionEventReceiver.RIGHT);
            }
        }
        else if (keyCode == (isUnity ? 45 : KeyEvent.KEYCODE_MINUS)) {
            isCircleCcw = true;
            isCircle = SpenAirActionEventReceiver.LEFT;

            if (receiver != null) {
                receiver.onCircleCcw();
                receiver.onCircle(SpenAirActionEventReceiver.LEFT);
            }
        }
    }
}
