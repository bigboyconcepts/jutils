package com.af.androidutility.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network
{
    public static boolean isNetworkAvailable(Context context)
    {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            return activeNetworkInfo.isConnected();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean isWiFiConnected(Context context)
    {
        try
        {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            return mWifi.isConnected();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static NETWORK_STATE getNetworkState(Context context)
    {
        if(Network.isWiFiConnected(context))
        {
            return NETWORK_STATE.online_wifi;
        }
        else if(Network.isNetworkAvailable(context))
        {
            return NETWORK_STATE.online_3g;
        }
        else
        {
            return NETWORK_STATE.offline;
        }
    }

    public enum NETWORK_STATE
    {
        online_3g,
        online_wifi,
        offline
    }
}
