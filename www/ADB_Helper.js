var ADB = (function () {
		var ADB = {
		};
	
	ADB.doNothing = function () {};
		
	var PLUGIN_NAME = "ADBMobile_PhoneGap";
	var fTRACK_APP_STATE_WITH_CDATA = "myMethod";
	
	ADB.optedIn = 1;
	ADB.optedOut = 2;
	ADB.optUnknown = 3;

	ADB.trackAppState = function (stateName) {
	   return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "myMethod", [stateName]);
	};
	
	ADB.getVersion = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "getVersion", [null]);
	}
	
	ADB.getPrivacyStatus = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "getPrivacyStatus", [null]);
	}
	
	ADB.setPrivacyStatus = function (privacyStatus) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "setPrivacyStatus", [privacyStatus]);
	};
	
	ADB.getLifetimeValue = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "getLifetimeValue", [null]);
	}

	ADB.getUserIdentifier = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "getUserIdentifier", [null]);
	}
	
	ADB.setUserIdentifier = function (userIdentifier) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "setUserIdentifier", [userIdentifier]);
	};

	ADB.getDebugLogging = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "getDebugLogging", [null]);
	}

	ADB.setDebugLogging = function(debugLogging) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "setDebugLogging", [debugLogging]);
	}

	ADB.keepLifecycleSessionAlive = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "keepLifecycleSessionAlive", [null]);
	}

	ADB.collectLifecycleData = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "collectLifecycleData", [null]);
	}
	
	ADB.trackState = function(stateName, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackState", [stateName, cData]);
	}

	ADB.trackAction = function(action, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackAction", [action, cData]);
	}
	
	ADB.trackActionFromBackground = function(action, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackActionFromBackground", [action, cData]);
	}

	ADB.trackLocation = function(lat, long, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackLocation", [lat, long, cData]);
	}
	
	ADB.trackLifetimeValueIncrease = function(amount, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackLifetimeValueIncrease", [amount, cData]);
	}

	ADB.trackTimedActionStart = function(action, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackTimedActionStart", [action, cData]);
	}

	ADB.trackTimedActionUpdate = function(action, cData) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackTimedActionUpdate", [action, cData]);
	}

	ADB.trackTimedActionEnd = function(action) {
		return cordova.exec(ADB.doNothing, ADB.doNothing, "ADBMobile_PhoneGap", "trackTimedActionEnd", [action]);
	}

	ADB.trackingTimedActionExists = function(success, fail, action) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "trackingTimedActionExists", [action]);
	}

	ADB.trackingIdentifier = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "trackingIdentifier", []);
	}

	ADB.trackingClearQueue = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "trackingClearQueue", []);
	}

	ADB.trackingGetQueueSize = function(success, fail) {
		return cordova.exec(success, fail, "ADBMobile_PhoneGap", "trackingGetQueueSize", []);
	}

		   return ADB;
}());