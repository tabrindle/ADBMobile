package com.adobe.PhoneGapPlugin;

import android.location.Location;
import android.util.Log;
import android.widget.Switch;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.adobe.mobile.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hpeterso
 * Date: 10/2/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ADBMobile_PhoneGap extends CordovaPlugin {

    // =====================
    // public Method - all calls filter through this
    // =====================
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Config.setContext(cordova.getActivity());
        if (action.equals("getVersion")) {
            this.getVersion(callbackContext);
            return true;
        } else if (action.equals("getPrivacyStatus")) {
            this.getPrivacyStatus(callbackContext);
            return true;
        } else if (action.equals("setPrivacyStatus")) {
            this.setPrivacyStatus(args, callbackContext);
            return true;
        } else if (action.equals("getLifetimeValue")) {
            this.getLifetimeValue(callbackContext);
            return true;
        } else if (action.equals("getUserIdentifier")) {
            this.getUserIdentifier(callbackContext);
            return true;
        } else if (action.equals("setUserIdentifier")) {
            this.setUserIdentifier(args, callbackContext);
            return true;
        } else if (action.equals("getDebugLogging")) {
            this.getDebugLogging(callbackContext);
            return true;
        } else if (action.equals("setDebugLogging")) {
            this.setDebugLogging(args, callbackContext);
            return true;
        } else if (action.equals("trackState")) {
            this.trackState(args, callbackContext);
            return true;
        } else if (action.equals("trackAction")) {
            this.trackAction(args, callbackContext);
            return true;
        } else if (action.equals("trackLocation")) {
            this.trackLocation(args, callbackContext);
            return true;
        } else if (action.equals("trackLifetimeValueIncrease")) {
            this.trackLifetimeValueIncrease(args, callbackContext);
            return true;
        } else if (action.equals("trackTimedActionStart")) {
            this.trackTimedActionStart(args, callbackContext);
            return true;
        } else if (action.equals("trackTimedActionUpdate")) {
            this.trackTimedActionUpdate(args, callbackContext);
            return true;
        } else if (action.equals("trackTimedActionEnd")) {
            this.trackTimedActionEnd(args, callbackContext);
            return true;
        } else if (action.equals("trackingTimedActionExists")) {
            this.trackingTimedActionExists(args, callbackContext);
            return true;
        } else if (action.equals("trackingIdentifier")) {
            this.trackingIdentifier(callbackContext);
            return true;
        } else if (action.equals("trackingClearQueue")) {
            this.trackingClearQueue(callbackContext);
            return true;
        } else if (action.equals("trackingGetQueueSize")) {
            this.trackingGetQueueSize(callbackContext);
            return true;
        }
        return false;
    }

    // =====================
    // Analytics/Config Methods
    // =====================
    private void getVersion(CallbackContext callbackContext) {
            String version = Config.getVersion();
            callbackContext.success(version);
    }

    private void getPrivacyStatus(CallbackContext callbackContext) {
        switch (Config.getPrivacyStatus()) {
            case MOBILE_PRIVACY_STATUS_OPT_IN:
                callbackContext.success("Opted In");
                break;
            case MOBILE_PRIVACY_STATUS_OPT_OUT:
                callbackContext.success("Opted Out");
                break;
            case MOBILE_PRIVACY_STATUS_UNKNOWN:
                callbackContext.success("Opt Unknown");
                break;
            default:
                callbackContext.error("Privacy Status was an unknown value");
        }
    }

    private void setPrivacyStatus(JSONArray args, CallbackContext callbackContext) {
        int status = 0;
        try {
            status = args.getInt(0);
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }

        switch (status) {
            case 1:
                Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN);
                callbackContext.success("Status set to opted in");
                break;
            case 2:
                Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT);
                callbackContext.success("Status set to opted out");
                break;
            case 3:
                Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_UNKNOWN);
                callbackContext.success("Status set to unknown");
                break;
            default:
                callbackContext.error("Privacy Status was an unknown value");
        }
    }

    private void getLifetimeValue(CallbackContext callbackContext) {
        BigDecimal ltValue = Config.getLifetimeValue();
        callbackContext.success(ltValue.toString());
    }

    private void getUserIdentifier(CallbackContext callbackContext) {
        String UserIdentifier = Config.getUserIdentifier();
        callbackContext.success(UserIdentifier);
    }

    private void setUserIdentifier(JSONArray args, CallbackContext callbackContext) {
        try {
            String userIdentifier = args.getString(0);
            Config.setUserIdentifier(userIdentifier);
            callbackContext.success("Set User Identifier");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void getDebugLogging(CallbackContext callbackContext) {
        boolean debugLogging = Config.getDebugLogging();
        callbackContext.success(debugLogging ? "true" : "false");
    }

    private void setDebugLogging(JSONArray args, CallbackContext callbackContext) {
        try {
            boolean status = args.getBoolean(0);
            Config.setDebugLogging(status);
            callbackContext.success("Set DebugLogging");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }

    }

    private void trackState(JSONArray args, CallbackContext callbackContext) {
        try {
            String state = null;
            HashMap<String, Object> cData = null;
            if (args.get(0) != JSONObject.NULL && args.get(0).getClass() == String.class) {
                state = args.getString(0);
            } else if (args.get(0) != JSONObject.NULL) {
                JSONObject cDataJSON = args.getJSONObject(0);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            if (args.get(1) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(1);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackState(state, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackAction(JSONArray args, CallbackContext callbackContext) {
        try {
            String action = null;
            HashMap<String, Object> cData = null;
            // set appState if passed in
            if (args.get(0) != JSONObject.NULL && args.get(0).getClass() == String.class) {
                action = args.getString(0);
            } else if (args.get(0) != JSONObject.NULL) {
                // else set cData if it is passed in alone
                JSONObject cDataJSON = args.getJSONObject(0);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            // set cData if it is passed in along with action
            if (args.get(1) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(1);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackAction(action, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackLocation(JSONArray args, CallbackContext callbackContext) {
        try {
            Location location = new Location("New Location");
            location.setLatitude(Double.parseDouble(args.getString(0)));
            location.setLongitude(Double.parseDouble(args.getString(1)));

            HashMap<String, Object> cData = null;

            // set cData if it is passed in along with action
            if (args.get(2) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(2);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackLocation(location, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackLifetimeValueIncrease(JSONArray args, CallbackContext callbackContext) {
        try {
            BigDecimal amount = new BigDecimal(args.getString(0));
            HashMap<String, Object> cData = null;

            // set cData
            if (args.get(1) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(1);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackLifetimeValueIncrease(amount, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackTimedActionStart(JSONArray args, CallbackContext callbackContext) {
        try {
            String action = null;
            HashMap<String, Object> cData = null;
            // set appState if passed in
            if (args.get(0) != JSONObject.NULL && args.get(0).getClass() == String.class) {
                action = args.getString(0);
            } else if (args.get(0) != JSONObject.NULL) {
                // else set cData if it is passed in alone
                JSONObject cDataJSON = args.getJSONObject(0);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            // set cData if it is passed in along with action
            if (args.get(1) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(1);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackTimedActionStart(action, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackTimedActionUpdate(JSONArray args, CallbackContext callbackContext) {
        try {
            String action = null;
            HashMap<String, Object> cData = null;
            // set appState if passed in
            if (args.get(0) != JSONObject.NULL && args.get(0).getClass() == String.class) {
                action = args.getString(0);
            } else if (args.get(0) != JSONObject.NULL) {
                // else set cData if it is passed in alone
                JSONObject cDataJSON = args.getJSONObject(0);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            // set cData if it is passed in along with action
            if (args.get(1) != JSONObject.NULL)
            {
                JSONObject cDataJSON = args.getJSONObject(1);
                if (cDataJSON != null && cDataJSON.length() > 0)
                    cData = GetHashMapFromJSON(cDataJSON);
            }
            Analytics.trackTimedActionUpdate(action, cData);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackingTimedActionExists(JSONArray args, CallbackContext callbackContext) {
        try {
            String action = args.getString(0);

            boolean exists = Analytics.trackingTimedActionExists(action);
            callbackContext.success(exists ? "true" : "false");
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackTimedActionEnd(JSONArray args, CallbackContext callbackContext) {
        try {
            String action = args.getString(0);

            Analytics.trackTimedActionEnd(action, null);
            callbackContext.success();
        }
        catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }

    private void trackingIdentifier(CallbackContext callbackContext) {
        String trackingIdentifier = Analytics.getTrackingIdentifier();
        callbackContext.success(trackingIdentifier);
    }

    private void trackingClearQueue(CallbackContext callbackContext) {
        Analytics.clearQueue();
        callbackContext.success();
    }

    private void trackingGetQueueSize(CallbackContext callbackContext) {
        long size = Analytics.getQueueSize();
        callbackContext.success(String.valueOf(size));
    }

    // =====================
    // Helpers
    // =====================
    private HashMap<String, Object> GetHashMapFromJSON(JSONObject data) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        @SuppressWarnings("rawtypes")
        Iterator it = data.keys();
        while (it.hasNext()) {
            String n = (String)it.next();
            try {
                map.put(n, data.getString(n));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        HashMap<String, Object> table = new HashMap<String, Object>();
        table.putAll(map);
        return table;
    }
}
