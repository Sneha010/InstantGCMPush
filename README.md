Allows app to receive the push notifications from Google cloud messaging server.
There are two library project in this project. Project contains demo app to illustrate the uses of the libraries.

#### InstantGCM
Allows app to register on Google Cloud Messaging server and receive GCM registration Id with new implementation of InstantId API method 
over deprecated register() method of gcm instance. 
App developer can store this registration Id at middleware or their own server for further authentication with GCM

##### Uses
1. Call ``` InstantGCMClient.initialise(Context context , GCMDataInterface dataInterface , final GCMRegListener gcmRegListener) ```
  - ```GCMDataInterface``` delivers project Id or # for request
  - ```GCMRegListener``` provides result callback after attempting to achieve Registration Id to GCM server

2. Implement ``` GcmListenerService ``` . Message from GCM will received in ``` onMessageReceived ``` callback.
   Don't forget to mention this service in ``` Application ``` tag of manifest file.

    ```
    <service android:name="com.yourpackagename.MyGcmListenerService"
            android:exported="false" >
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
        </service>
    ```

#### Connecting to Application Middleware
Enable app to Register and Deregister on its middleware so that app can subscribe and unsubcribe anytime on app user request for receiving GCM message.

##### Uses
It has two main methods 
  1. ```GCMMiddlewareClient.register(Context context , MiddlewareDataInterface middlewareDataInterface, InstantGCMRegistrationListener instantGCMRegistrationListener)```
      - ```MiddlewareDataInterface``` passes correspoding Base url , project Id or # and device unique Id such as IMEI #.
      - ```InstantGCMRegistrationListener``` provides result callback of this register method.
      - Before attempting to register at middleware, this method first establishes a connection between GCM server and gains the GCM Registration Id.
        Only After successful connection we proceed with the middleware registration.
      
  2. ``` GCMMiddlewareClient.deRegister(Context context , MiddlewareDataInterface middlewareDataInterface, InstantGCMDeregistrationListener instantGCMRegistrationListener)```
      - ```MiddlewareDataInterface``` passes correspoding Base url , project Id or # and device unique Id such as IMEI #.
      - ```InstantGCMDeregistrationListener``` provides result callback of this register method. 
      
  3. Use ``` GCMRegisterUtils.isRegisteredOnServer(context)``` and ``` GCMRegisterUtils.setRegisteredOnServer(context ,             "true/false")``` for communication regarding successful transfer of GCMRegId to the app middleware.

##### Add following Permissions and Receiver in your app manifest

```
  <uses-permission android:name="android.permission.INTERNET" />
   
    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />

```
```
    <receiver
        android:name="com.google.android.gms.gcm.GcmReceiver"
        android:exported="true"
        android:permission="com.google.android.c2dm.permission.SEND" >
        
        <intent-filter>
            <action android:name="com.google.android.c2dm.intent.RECEIVE"/>
            <category android:name="com.yourpackagename" />
        </intent-filter>
        
     </receiver>
```
 



#### Add dependency of libraries
 
  Add following in your build.gradle :
      
  1. For instantgcm (Available on jcenter):
      
      ``` compile 'com.instant.instantgcm:instantgcm:1.0.1' ``` 
      
      or you can copy the module in project and use the following
      
      ``` compile project(':instantgcm') ```
      
  2. For middlewarelib :
  
      ``` compile project(':middlewarelib') ```


### License

```
Copyright 2016 Sneha Khadatare

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
