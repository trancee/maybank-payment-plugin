/**
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) Matt Kane 2010
 * Copyright (c) 2011, IBM Corporation
 * Copyright (c) 2013, Maciej Nux Jaros
 */
package com.airasia.plugins.m2u;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

/**
 * This calls out to the Maybank Payment plugin and returns the result.
 *
 * @sa https://github.com/apache/cordova-android/blob/master/framework/src/org/apache/cordova/CordovaPlugin.java
 */
public class MaybankPayment extends CordovaPlugin {
	public static final int REQUEST_CODE = 0x0ba7c0de;

	private static final String START = "start";
	private static final String START_INTENT = "com.airasia.plugins.m2u.START";

	private static final String LOG_TAG = "MaybankPayment";

	private CallbackContext callbackContext;

	/**
	 * Constructor.
	 */
	public MaybankPayment() {
	}

	/**
	 * Executes the request.
	 *
	 * This method is called from the WebView thread. To do a non-trivial amount of work, use:
	 *	 cordova.getThreadPool().execute(runnable);
	 *
	 * To run on the UI thread, use:
	 *	 cordova.getActivity().runOnUiThread(runnable);
	 *
	 * @param action		  The action to execute.
	 * @param args			The exec() arguments.
	 * @param callbackContext The callback context used when calling back into JavaScript.
	 * @return				Whether the action was valid.
	 *
	 * @sa https://github.com/apache/cordova-android/blob/master/framework/src/org/apache/cordova/CordovaPlugin.java
	 */
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		this.callbackContext = callbackContext;

		if (action.equals(START)) {
			start();
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Starts an intent to start the payment.
	 */
	public void start() {
		Intent intentStart = new Intent(START_INTENT);
		intentStart.addCategory(Intent.CATEGORY_DEFAULT);
		// avoid calling other phonegap apps
		intentStart.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());

		this.cordova.startActivityForResult((CordovaPlugin) this, intentStart, REQUEST_CODE);
	}

	/**
	 * Called when the payment intent completes.
	 *
	 * @param requestCode The request code originally supplied to startActivityForResult(),
	 *					   allowing you to identify who this result came from.
	 * @param resultCode  The integer result code returned by the child activity through its setResult().
	 * @param intent	  An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("returnKey", intent.getStringExtra("returnKey"));
					Log.v(LOG_TAG, "RETURN STATUS: " + intent.getStringExtra("returnKey"));
				} catch (JSONException e) {
					Log.d(LOG_TAG, "This should never happen");
				}
				//this.success(new PluginResult(PluginResult.Status.OK, obj), this.callback);
				this.callbackContext.success(obj);
			} else if (resultCode == Activity.RESULT_CANCELED) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("returnKey", "");
				} catch (JSONException e) {
					Log.d(LOG_TAG, "This should never happen");
				}
				//this.success(new PluginResult(PluginResult.Status.OK, obj), this.callback);
				this.callbackContext.success(obj);
			} else {
				//this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
				this.callbackContext.error("Unexpected error");
			}
		}
	}
}
