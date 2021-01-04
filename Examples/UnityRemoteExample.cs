using UnityEngine;
using UnityEngine.UI;

public class Spen : MonoBehaviour
{
    public Text txt;

    AndroidJavaObject spen;

    // Start is called before the first frame update
    void Start()
    {
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

        AndroidJavaClass sPenClass = new AndroidJavaClass("com.choshinyoung.spen.SpenRemoteController");
        spen = sPenClass.CallStatic<AndroidJavaObject>("create", activity);
        spen.Call("initSpenRemote");
    }

    // Update is called once per frame
    void Update()
    {
        txt.text = $"Button pressed: {spen.Call<bool>("getButtonPressed")}\nx: {spen.Call<float>("getX")}, y: {spen.Call<float>("getY")}";
    }
}
