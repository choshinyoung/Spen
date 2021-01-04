package com.choshinyoung.spen;

import android.content.Context;

import com.samsung.android.sdk.penremote.AirMotionEvent;
import com.samsung.android.sdk.penremote.ButtonEvent;
import com.samsung.android.sdk.penremote.SpenRemote;
import com.samsung.android.sdk.penremote.SpenUnit;
import com.samsung.android.sdk.penremote.SpenUnitManager;

public class SpenRemoteController {

    private SpenUnitManager sPenManager;

    private boolean buttonPressed = false;
    public boolean getButtonPressed() {
        return buttonPressed;
    }

    private float x;
    public float getX() {
        return x;
    }

    private float y;
    public float getY() {
        return y;
    }

    private final Context context;
    private final SpenRemoteEventReceiver receiver;

    public static SpenRemoteController create(Context ctx, SpenRemoteEventReceiver rcv) {
        return new SpenRemoteController(ctx, rcv);
    }

    public static SpenRemoteController create(Context ctx) {
        return new SpenRemoteController(ctx, null);
    }

    SpenRemoteController(Context ctx, SpenRemoteEventReceiver rcv) {
        context = ctx;
        receiver = rcv;
    }

    public void initSpenRemote() {
        SpenRemote spenRemote = SpenRemote.getInstance();
        if (!spenRemote.isFeatureEnabled(SpenRemote.FEATURE_TYPE_AIR_MOTION))
            return;
        if (!spenRemote.isFeatureEnabled(SpenRemote.FEATURE_TYPE_BUTTON))
            return;
        if (spenRemote.isConnected())
            return;

        sPenManager = null;
        spenRemote.connect(context, new SpenRemote.ConnectionResultCallback() {
            @Override
            public void onSuccess(SpenUnitManager spenUnitManager) {
                sPenManager = spenUnitManager;
                initSpenEventListener();
            }

            @Override
            public void onFailure(int i) {

            }
        });
    }

    public void releaseSpenRemote() {
        SpenRemote spenRemote = SpenRemote.getInstance();
        if (spenRemote.isConnected())
            spenRemote.disconnect(context);

        sPenManager = null;
    }

    private void initSpenEventListener() {
        SpenUnit buttonUnit = sPenManager.getUnit(SpenUnit.TYPE_BUTTON);
        if (buttonUnit != null) {
            sPenManager.registerSpenEventListener(spenEvent -> {
                ButtonEvent event = new ButtonEvent(spenEvent);

                buttonPressed = event.getAction() == ButtonEvent.ACTION_DOWN;

                if (receiver != null)
                    receiver.SpenButtonEvent();
            }, buttonUnit);
        }

        SpenUnit airMotionUnit = sPenManager.getUnit(SpenUnit.TYPE_AIR_MOTION);
        if (airMotionUnit != null)
            sPenManager.registerSpenEventListener(spenEvent -> {
                    AirMotionEvent event = new AirMotionEvent(spenEvent);

                    x = event.getDeltaX();
                    y = event.getDeltaY();

                    if (receiver != null)
                        receiver.SpenAirMotionEvent();
            }, airMotionUnit);
    }
}
