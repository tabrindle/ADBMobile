//
//  ADBMobile_PhoneGap.h
//  HelloWorld
//
//  Created by Hunter Peterson on 9/30/13.
//
//

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@interface ADBMobile_PhoneGap : CDVPlugin

- (void)getVersion:(CDVInvokedUrlCommand*)command;
- (void)getPrivacyStatus:(CDVInvokedUrlCommand*)command;
- (void)setPrivacyStatus:(CDVInvokedUrlCommand*)command;
- (void)getLifetimeValue:(CDVInvokedUrlCommand*)command;
- (void)getUserIdentifier:(CDVInvokedUrlCommand*)command;
- (void)setUserIdentifier:(CDVInvokedUrlCommand*)command;
- (void)getDebugLogging:(CDVInvokedUrlCommand*)command;
- (void)setDebugLogging:(CDVInvokedUrlCommand*)command;
- (void)keepLifecycleSessionAlive:(CDVInvokedUrlCommand*)command;
- (void)collectLifecycleData:(CDVInvokedUrlCommand*)command;
- (void)trackState:(CDVInvokedUrlCommand*)command;
- (void)trackAction:(CDVInvokedUrlCommand*)command;
- (void)trackActionFromBackground:(CDVInvokedUrlCommand*)command;
- (void)trackLocation:(CDVInvokedUrlCommand*)command;
- (void)trackLifetimeValueIncrease:(CDVInvokedUrlCommand*)command;
- (void)trackTimedActionStart:(CDVInvokedUrlCommand*)command;
- (void)trackTimedActionUpdate:(CDVInvokedUrlCommand*)command;
- (void)trackingTimedActionExists:(CDVInvokedUrlCommand*)command;
- (void)trackTimedActionEnd:(CDVInvokedUrlCommand*)command;
- (void)trackingIdentifier:(CDVInvokedUrlCommand*)command;
- (void)trackingClearQueue:(CDVInvokedUrlCommand*)command;
- (void)trackingGetQueueSize:(CDVInvokedUrlCommand*)command;

@end
