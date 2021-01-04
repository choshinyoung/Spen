Spen
====

<p align="center">
    <a href="README.md">한국어</a> |
    <span>English</span>
</p>

<br>

An android library that makes it easy to use Air Action and S pen Remote SDK   
Also usable for Unity3D

- Available since Note 10 or Tab S6 that running at least Android 9.0 or higher.
- Cannot use Air Action and Spen Remote at the same time.

<br>

## 목차
- Android Studio
    1. [Setup](#Setup)
    2. [Spen Remote](#Spen-Remote)
    3. [Air Action](#Air-Action)
- Unity
    1. [Use in Unity](#Use-in-Unity)
    2. [Unity Spen Remote](#Unity-Spen-Remote)
    3. [Unity Air Action](#Unity-Air-Action)


<br>


# Setup

1. Create a new project.

2. Open `Project` view in the `Project` window.

3. Copy and paste [`Spen-v1.1.arr`](https://github.com/choshinyoung/Spen/releases/tag/v1.1) to `app/libs` folder.

4. Go to `Android` view again, and open `build.gradle (Module: ~.app)` in `Gradle Scripts`.

5. Add 
    ```gradle
    repositories {
        flatDir {
            dirs 'libs'
        }
    } 
    ``` 
    at the very bottom.

6. Inside a `dependencies`, Add
    ```gradle
    implementation name: 'Spen-v1.0', ext: 'aar'
    ```
    and click `Sync Now`.
 
![SpenRemote_build.gradle](imgs/SpenRemote_build.gradle.png)


<br>


## Spen Remote

1. Implement `SpenRemoteEventReceiver` to MainActivity(or another class).

2. Make the `SpenUnitManager` type variable named "spen" and initialize in the `onCreate` method with ```SpenRemoteController.create(this, this)```
    - The first parameter is the `Context` type, and the second is the `SpenRemoteEventReceiver` type.

3. Override `onResume` method and connect to S pen with ```spen.initSpenRemote();```.

4. Override `onPause` and add ```spen.releaseSpenRemote();```.

5. Now you can use events by overriding `SpenButtonEvent` and `SpenAirMotionEvent` method.
    - `SpenButtonEvent` is called when button down or up. `SpenAirMotionEvent` is called whenever the S pen moved.
    - You can check if the button is down with ```spen.getButtonPressed()```. You can get gyroscope senser value with ```spen.getX()``` and ```spen.getY()``` from `SpenAirMotionEvent`.

<br>

- You don't have to use the events. You can use `spen.getButtonPressed()`, `spen.getX()` and `spen.getY()` without implementing `SpenRemoteEventReceiver` and setting second paramter of ```SpenRemoteController.create```.

[Read Full Code](Examples/RemoteExample.java)


<br>


## Air Action

1. Open `AndroidManifest.xml` and write 
    `<action android:name="com.samsung.android.support.REMOTE_ACTION" />` inside a `<intent-filter>`.

2. Down the `<intent-filter>`, Add
    ```xml
    <meta-data
        android:name="com.samsung.android.support.REMOTE_ACTION"
        android:resource="@xml/remote_action_sample"/>
    ``` 

![AirAction_AndroidManifest](imgs/AirAction_AndroidManifest.png)

3. Implement the `SpenAirActionEventReceiver`.

4. Create `SpenAirActionController` type variable named `spen`, and initialize by ```SpenAirActionController.create(this);``` in `onCreate`.
    - A type of the parameter is `SpenAirActionEventReceiver`.

5. Override `onKeyDown` and add ```spen.KeyDown(keyCode, false);```.

6. Now you can use events by overriding methods you need in `onClick`, `onDoubleClick`, `onSwipeRight`, `onSwipeLeft`, `onSwipeDown`, `onSwipeUp`, `onCircleCw`, `onCircleCcw` from `SpenAirActionEventReceiver`.

7. You can also use the `onSwipe` and `onCircle` method instead of the indivisual methods. In `onSwipe`, the parameter is 0 if right, 1 if left, 2 if up, 3 if down. In `onCircle`, In `onCircle`, the parameter is 0 for clickwise direction, and 1 for counter-clickwise direction. (`UP`, `DOW N`, `RIGHT`, `LEFT` is pre decalered as static variables in `SpenAirActionEventReceiver`)

8. If you use your Air Action app the first time, you need to enable this application from `Air Action` in S pen settings.

<br>

- You don't have to use events of Air Action too. You can use following methods without implementing `SpenAirActionEventReceiver` and writing a parameter of `SpenAirActionController.create` (also available with the events): `getIsClick`, `getIsDoubleClick`, `getIsSwipeRight`, `getIsSwipeLeft`, `getIsSwipeUp`, `getIsSwipeDown`, `getIsCircleCw`, `getIsCircleCcw`(return value is boolean type), `getIsSwipe`, `getIsCircle`(return value is int type).

[Read Full Code](Examples/AirActionExample.java)


<br>


# Use in Unity


1. Open your unity project.

2. Create `Assets/Plugins/Android` Folder, paste [`Spen-v1.1.arr`](https://github.com/choshinyoung/Spen/releases/tag/v1.1) on it.

3. Set platform to Android in the `Build Settings`, goto `Player Settings...` -> `Other Settings` -> `Identification` and set `Minimum API LEVEl` to `Android 9.0 'Pie'` or higher.

<br>

- **You have to test your game on your phone since Android library is unable for Unity editor.**


<br>


## Unity Spen Remote


1. Create a new Script and open it. Create an `AndroidJavaObject` type variable named spen.

2. In `Start` Method, Write 
    ```cs
    AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
    AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
    ```
    to get activity, and write
    ```cs
    AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenRemoteController");
    spen = sPenClass.CallStatic<AndroidJavaObject>("create", activity);
    spen.Call("initSpenRemote");
    ```
    to create SpenRemoteController object and initialize spen.

3. Now you can use `spen.Call<bool>("getButtonPressed")`, `spen.Call<float>("getX")` and `spen.Call<float>("getY")` methods.

[Read Full Code](Examples/UnityRemoteExample.cs)


<br>


## Unity Air Action


1. Build your project as android platform.

2. Copy `Temp/StaginArea/UnityManifest.xml` and paste to `Assets/Plugins/Android`, and change name to `AndroidManifest.xml`.

3. Now open the file, Write 
    `<action android:name="com.samsung.android.support.REMOTE_ACTION" />` inside the `<intent-filter>`, and write 
    ```xml
    <meta-data
        android:name="com.samsung.android.support.REMOTE_ACTION"
        android:resource="@xml/remote_action_sample"/>
    ``` 
    under it as we did in [here](#Air-Action).

4. Create `AndroidJavaObject` type variable spen in new C# script.

5. Create SpenAirActionController object in `Start` method by writing 
    ```cs
    AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenAirActionController");
    spen = sPenClass.CallStatic<AndroidJavaObject>("create");
    ```

6. 
    ```cs
    void OnGUI()
    {
        Event e = Event.current;
        if (e.isKey)
        {
            spen.Call("KeyDown", (int)e.keyCode, true);
        }
    }
    ```
    Send an event to `spen` when key event received on `OnGUI`.

7. Now you can use methods such as `spen.Call<bool>("getIsClick")`, `spen.Call<bool>("getIsDoubleClick")`, `spen.Call<bool>("getIsSwipeUp")`, and etc. (You can use every methods as same as with Java)

8. You need to enable the Air action setting when you use your game for the first time.

[Read Full Code](Examples/UnityAirActionExample.cs)


***


[Galaxy S Pen](https://developer.samsung.com/galaxy-spen-remote/)   
