using UnityEngine;
using UnityEngine.UI;

public class Spen : MonoBehaviour
{
    AndroidJavaObject spen;

    public Text txt;

    // Start is called before the first frame update
    void Start()
    {
        AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenAirActionController");
        spen = sPenClass.CallStatic<AndroidJavaObject>("create");
    }

    // Update is called once per frame
    void Update()
    {
        if (spen.Call<bool>("getIsClick")) {
            txt.text += "Click\n";
        }
        if (spen.Call<bool>("getIsDoubleClick"))
        {
            txt.text += "Double click\n";
        }
        if (spen.Call<bool>("getIsSwipeRight"))
        {
            txt.text += "Swipe right\n";
        }
        if (spen.Call<bool>("getIsSwipeLeft"))
        {
            txt.text += "Swipe left\n";
        }
        if (spen.Call<bool>("getIsSwipeUp"))
        {
            txt.text += "Swipe up\n";
        }
        if (spen.Call<bool>("getIsSwipeDown"))
        {
            txt.text += "Swipe down\n";
        }
        if (spen.Call<bool>("getIsCircleCw"))
        {
            txt.text += "Circle cw\n";
        }
        if (spen.Call<bool>("getIsCircleCcw"))
        {
            txt.text += "Circle ccw\n";
        }

        int swipe = spen.Call<int>("getIsSwipe");
        if (swipe != -1)
        {
            txt.text += $"Swipe: {swipe}";
        }

        int circle = spen.Call<int>("getIsCircle");
        if (circle != -1)
        {
            txt.text += $"Circle {circle}";
        }
    }

    void OnGUI()
    {
        Event e = Event.current;
        if (e.isKey)
        {
            spen.Call("KeyDown", (int)e.keyCode, true);
        }
    }
}
