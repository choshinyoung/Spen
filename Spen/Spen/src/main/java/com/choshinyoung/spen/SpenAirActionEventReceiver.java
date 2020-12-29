package com.choshinyoung.spen;

public interface SpenAirActionEventReceiver {

    default void onClick() { }

    default void onDoubleClick() { }

    default void onSwipeRight() { }

    default void onSwipeLeft() { }

    default void onSwipeDown() { }

    default void onSwipeUp() { }

    default void onCircleCw() { }

    default void onCircleCcw() { }


    final int RIGHT = 0;
    final int LEFT = 1;
    final int UP = 2;
    final int DOWN = 3;

    default void onSwipe(int direction) { }

    default void onCircle(int direction) { }

}
