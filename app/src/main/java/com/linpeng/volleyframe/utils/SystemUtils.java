package com.linpeng.volleyframe.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.linpeng.volleyframe.base.KMApplication;

import java.util.List;

public class SystemUtils {
	
	 public static String getVersoinName(Context context) {
		  
	        String pn = context.getPackageName();
	        try {
	            return context.getPackageManager().getPackageInfo(pn, 0).versionName;
	        } catch (NameNotFoundException e) {
	            e.printStackTrace();
	        }
	        return "1.0.0";
	    }
	 
	 public static int getVersionCode(Context context) {
	        try {
	            String pn = context.getPackageName();
	            return context.getPackageManager().getPackageInfo(pn, 0).versionCode;
	        } catch (NameNotFoundException e) {
	            return 1;
	        }
	    }

	public static String getImei() {
		TelephonyManager tm = (TelephonyManager) KMApplication.getInstance().getSystemService(
				Context.TELEPHONY_SERVICE);
		if (tm == null) {
			return "";
		}
		return TextUtils.isEmpty(tm.getDeviceId()) ? "" : tm
				.getDeviceId();
	}

	public static String getAndroidId() {
		String id = Settings.Secure.getString(KMApplication.getInstance().getContentResolver(),
				Settings.Secure.ANDROID_ID);
		if (TextUtils.isEmpty(id)) {
			return "";
		}
		return id;
	}

	@SuppressWarnings("unused")
	public static boolean isAppForground(Context mContext) {
		ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(mContext.getPackageName())) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static int getAndroidSdkVersionInt() {
		return Build.VERSION.SDK_INT;
	}

	public static boolean isNetworkAvailable() {
		NetworkInfo info = getActiveNetworkInfo();
		return info != null && info.isAvailable();
	}
	@SuppressWarnings("unused")
	public static boolean isWifiAvailable() {
		NetworkInfo info = getActiveNetworkInfo();
		return info != null && info.isAvailable()
				&& info.getType() == ConnectivityManager.TYPE_WIFI;
	}

	public static boolean hasExternalStorage() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public static Point getDeviceSize(Context ctx) {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		Point size = new Point();
		size.x = dm.widthPixels;
		size.y = dm.heightPixels;
		return size;
	}

	private static NetworkInfo getActiveNetworkInfo() {
		ConnectivityManager cm = (ConnectivityManager) KMApplication.getInstance()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	public static int getVersionCode() {
		try {
			String pn = KMApplication.getInstance().getPackageName();
			return KMApplication.getInstance().getPackageManager().getPackageInfo(pn, 0).versionCode;
		} catch (NameNotFoundException e) {
			return 1;
		}
	}

	public static String getVersoinName() {
		String pn = KMApplication.getInstance().getPackageName();
		try {
			return KMApplication.getInstance().getPackageManager().getPackageInfo(pn, 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1.0";
	}

	public static String getLocalMacAddress() {
		WifiManager wifi = (WifiManager) KMApplication.getInstance().getSystemService(
				Context.WIFI_SERVICE);
		WifiInfo info = null;
		try {
			info = wifi.getConnectionInfo();
		}catch (Exception ignored){}
		if (info == null) {
			return "";
		}
		if (TextUtils.isEmpty(info.getMacAddress())) {
			return "";
		}
		return info.getMacAddress();
	}

	public static String getDeviceType() {
		return TextUtils.isEmpty(Build.MODEL) ? ""
				: Build.MODEL;
	}

}
