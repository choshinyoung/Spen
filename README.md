Spen
====

<p align="center">
    <span>한국어</span> |
    <a href="README_en.md">English</a>
</p>

<br>

복잡한 Air Action과 S pen Remote SDK를 간단하게 사용할 수 있는 안드로이드 라이브러리입니다.   
유니티에서도 사용할 수 있습니다.

- Android 9.0 이상의 Note10 또는 Tab S6부터 사용할 수 있습니다.
- Remote와 Air Action은 동시에 사용할 수 없습니다.

<br>

## 목차
- Android Studio
    1. [기본 설정](#기본-설정)
    2. [Spen Remote](#Spen-Remote)
    3. [Air Action](#Air-Action)
- Unity
    1. [유니티에서 사용하기](#유니티에서-사용하기)
    2. [Unity Spen Remote](#Unity-Spen-Remote)
    3. [Unity Air Action](#Unity-Air-Action)


<br>


# 기본 설정


1. 새 프로젝트를 만드세요.

2. `Project` 창에서 `Project` 뷰를 여세요.

3. `app/libs` 폴더에 [`Spen-v1.1.arr`](https://github.com/choshinyoung/Spen/releases/tag/v1.1)을 복사하세요.

4. 다시 `Android` 뷰로 가서, `Gradle Scripts` 안의 `build.gradle (Module: ~.app)`을 여세요.

5. 파일의 맨 아래  
    ```gradle
    repositories {
        flatDir {
            dirs 'libs'
        }
    } 
    ``` 
를 추가하세요.

6. `dependencies` 안에 
    ```gradle
    implementation name: 'Spen-v1.0', ext: 'aar'
    ```
    을 추가하고, `Sync Now`를 누르세요.
 
![SpenRemote_build.gradle](imgs/SpenRemote_build.gradle.png)


<br>


## Spen Remote


1. MainActivity(또는 다른 클래스)에서 `SpenRemoteEventReceiver`를 implements하세요.

2. `spen`이라는 이름의 `SpenUnitManager` 타입의 변수를 만들고 `onCreate` 메서드에서 ```SpenRemoteController.create(this, this)``` 로 초기화하세요.
    - 첫번째 매개변수는 `Context`이고, 두번째 매개변수는 `SpenRemoteEventReceiver`입니다.

3. `onResume` 메서드를 오버라이드하고 ```spen.initSpenRemote();```를 사용해 s펜에 연결하세요.

4. `onPause`를 오버라이드하고 ```spen.releaseSpenRemote();```를 추가하세요.

5. 이제 `SpenButtonEvent`와 `SpenAirMotionEvent`메서드를 오버라이드해 이벤트를 사용할 수 있습니다.
    - `SpenButtonEvent`는 S펜의 버튼을 누르거나 땔 때 호출됩니다. `SpenAirMotionEvent`는 S펜이 움직일 때마다 호출됩니다.
    - `spen.getButtonPressed()`로 버튼이 눌려있는지 확인할 수 있습니다. ```spen.getX()``` 와 ```spen.getY()``` 로 자이로 센서값을 얻을 수 있습니다.

<br>

- 이벤트를 사용하지 않아도 됩니다. `SpenRemoteEventReceiver`를 implements 하지 않고 ```SpenRemoteController.create``` 메서드에서 두번째 매개변수를 제거하면 `spen.getButtonPressed()`와 `spen.getX()`, `spen.getY()`만을 사용할 수 있습니다.

[전체 코드 보기](Examples/RemoteExample.java)


<br>


## Air Action


1. `AndroidManifest.xml`를 열고, `<intent-filter>` 안에   
    `<action android:name="com.samsung.android.support.REMOTE_ACTION" />`를 적으세요.

2. `<intent-filter>` 아래에는
    ```xml
    <meta-data
        android:name="com.samsung.android.support.REMOTE_ACTION"
        android:resource="@xml/remote_action_sample"/>
    ``` 
    를 추가하세요.

![AirAction_AndroidManifest](imgs/AirAction_AndroidManifest.png)

3. `SpenAirActionEventReceiver`를 implements하세요.

4. `SpenAirActionController` 타입의 `spen` 변수를 만들고, `onCreate`에서 ```SpenAirActionController.create(this);``` 로 초기화하세요
    - 매개변수는 `SpenAirActionEventReceiver` 타입입니다.

5. `onKeyDown`을 오버라이드하고 ```spen.KeyDown(keyCode, false);```를 추가하세요.

6. 이제 `SpenAirActionEventReceiver`의 메서드 `onClick`, `onDoubleClick`, `onSwipeRight`, `onSwipeLeft`, `onSwipeDown`, `onSwipeUp`, `onCircleCw`, `onCircleCcw` 중 필요한 메서드를 오버라이드해 이벤트를 사용할 수 있습니다.

7. 개별 메서드 대신 `onSwipe`, `onCircle`를 사용할 수 있습니다. `onSwipe`에서는 매개변수 direction이 0이면 오른쪽, 1이면 왼쪽, 2는 위, 3은 아래입니다. `onCircle`에서는 direction이 0이면 시계방향, 1이면 반대방향입니다 (`SpenAirActionEventReceiver`에 `UP`, `DOWN`, `RIGHT`, `LEFT`가 static 변수로 미리 선언돼있습니다).

8. Air Action이 적용된 앱을 처음 사용할 때는 S펜의 `에어 엑션` 설정에서 해당 앱을 활성화해야 합니다.

<br>

- Air Action 또한 이벤트를 사용하지 않아도 됩니다. `SpenAirActionEventReceiver`를 implements하지 않고, `SpenAirActionController.create`함수의 매개변수를 제거하면 이벤트 없이 `getIsClick`, `getIsDoubleClick`, `getIsSwipeRight`, `getIsSwipeLeft`, `getIsSwipeUp`, `getIsSwipeDown`, `getIsCircleCw`, `getIsCircleCcw`(리턴값은 boolean), `getIsSwipe`, `getIsCircle`(리턴값은 int)를 사용할 수 있습니다. (당연히 이벤트를 사용할 때도 쓸 수 있습니다.)

[전체 코드 보기](Examples/AirActionExample.java)


<br>


# 유니티에서 사용하기


1. 유니티 프로젝트를 여세요.

2. `Assets/Plugins/Android` 폴더를 만들고, 안에 [`Spen-v1.1.arr`](https://github.com/choshinyoung/Spen/releases/tag/v1.1)를 복사하세요.

3. `Build Settings`에서 플랫폼을 Android로 변경하고, `Player Settings...` -> `Other Settings` -> `Identification` -> `Minimum API LEVEl`을 `Android 9.0 'Pie'` 이상으로 바꾸세요.

<br>

- **안드로이드 라이브러리는 유니티 에디터에서 사용할 수 없기 때문에 핸드폰에서 테스트해야 합니다.**


<br>


## Unity Spen Remote


1. 새 스크립트를 만들고 여세요. `AndroidJavaObject` 타입의 변수 spen을 만드세요.

2. `Start` 메서드에서, 
    ```cs
    AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
    AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
    ```
    를 적어서 activity를 구하고,
    ```cs
    AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenRemoteController");
    spen = sPenClass.CallStatic<AndroidJavaObject>("create", activity);
    spen.Call("initSpenRemote");
    ```
    로 SpenRemoteController 객체를 생성하고 spen을 초기화하세요.

3. 이제 `spen.Call<bool>("getButtonPressed")`와 `spen.Call<float>("getX")`, `spen.Call<float>("getY")` 메서드를 사용할 수 있습니다.

[전체 코드 보기](Examples/UnityRemoteExample.cs)


<br>


## Unity Air Action


1. 프로젝트를 안드로이드 플랫폼으로 빌드하세요.

2. `Temp/StaginArea/UnityManifest.xml`을 `Assets/Plugins/Android`에 복사하고 이름을 `AndroidManifest.xml`로 바꾸세요.

3. 이제 그 파일을 열고, [위에서](#Air-Action) 한 것 처럼 `<intent-filter>` 안에   
    `<action android:name="com.samsung.android.support.REMOTE_ACTION" />`를 적고, 그 아래는 
    ```xml
    <meta-data
        android:name="com.samsung.android.support.REMOTE_ACTION"
        android:resource="@xml/remote_action_sample"/>
    ``` 
    를 추가하세요.

4. 새 C# 스크립트에서, `AndroidJavaObject` 타입의 변수 spen를 만드세요.

5. `Start` 메서드에서, 
    ```cs
    AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenAirActionController");
    spen = sPenClass.CallStatic<AndroidJavaObject>("create");
    ```
    를 적어서 SpenAirActionController 객체를 생성하세요.

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
    `OnGUI` 메서드에서 키 입력 이벤트를 받았을 때 `spen`에 이벤트를 전달하세요.

7. 이제 `spen.Call<bool>("getIsClick")`, `spen.Call<bool>("getIsDoubleClick")`, `spen.Call<bool>("getIsSwipeUp")` 등의 메서드를 사용할 수 있습니다. (모든 메서드는 Java에서와 똑같이 사용할 수 있습니다.)

8. 앱을 처음 사용할 때는 에어 엑션 설정을 해야 합니다.

[전체 코드 보기](Examples/UnityAirActionExample.cs)


***


[Galaxy S Pen](https://developer.samsung.com/galaxy-spen-remote/)   
