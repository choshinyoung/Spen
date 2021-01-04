package com.choshinyoung.spen;

import com.samsung.android.sdk.penremote.AirMotionEvent;
import com.samsung.android.sdk.penremote.ButtonEvent;

public interface SpenRemoteEventReceiver {

    default void SpenButtonEvent() { }

    default void SpenAirMotionEvent() { }

}
