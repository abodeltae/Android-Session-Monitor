# Android-Session-Monitor
Android Session Monitor is a simple library to log the start/end time and duration of how long a screen has been visible in your application 

## How to use 

First init the library by calling init from your **`application`** class 

```java 
SessionMonitorManager.init(this);
```
### Monitor Activities And fragments 

For Activities you can just extend ***`MonitoredActivity`*** 

 Or if you wish you can create an instance of  **`SessionHelper`** in your activity and pass onPause and onResume Events like this 
```java 
SessionHelper session=new SessionHelper(this);

    @Override
    protected void onResume() {
        super.onResume();
        session.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        session.onStop();
    }
```
For Fragments you can just extend ***`MonitoredFragment`*** or ***`MonitoredSupportFragment`***

 Or if you wish you can  create an instance of  **`SessionHelper`** in your Fragment and pass onPause and onResume Events like this 
```java 
    SessionHelper session=new SessionHelper(this);
    @Override
    public void onResume() {
        super.onResume();
        session.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        session.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        session.onHiddenChanged(hidden);
    }
```
### Custom Views Monitoring 
In case you need to monitor something other than Fragments or Activities 
you need to create a **`SessionHelper`** and specify the name and type of screen yourself 
call onStop and onResume according to your need 
```java 
    SessionHelper session=new SessionHelper( name, type);
```

### Get saved and logged data 
  ***`SessionMonitorManager`*** contains all the methods you will need to get reports and information about the saved data 

First get an instance of  ***`SessionMonitorManager`*** by calling 
```java
 sessionMonitor=SessionMonitorManager.getInstance();

```
### public methods SessionMonitorManager 

```java 
ArrayList<SessionEntry> getEntries()
```
Returns an array list with all the logged entries 
and entry contains :

name : the name of the screen 

type : the type of the screen 

startTimeMillis  - unix time

endTimeMillis - unix time

durationMillis
```java 
ArrayList<SessionEntry> getEntries(String screenName)
```
Returns an array list with all the logged entries for the given screenName 
```java 
ScreenReportItem getScreenReport(String screenName)
```
Return report for the given screen 
ScreenReportItem contains : 
name : name of screen 
totalDuration: total duration the screen has been visible for 
ArrayList<SessionEntry> sessionEntries list on entries for this screen 

```java 
ArrayList<ScreenReportItem> getReportForScreens(ArrayList<String>screenNames)
```
Return report for the given screens 
```java 
ArrayList<ScreenReportItem> getReportForAllScreens()
```
Return report for all logged screens

```java 
 ArrayList<ScreenReportItem>getReportForActivities()
```
Return report for all logged activities


```java 
ArrayList<ScreenReportItem>getReportForFragments()
```
Return report for all logged fragments

```java 
ArrayList<ScreenReportItem> getReportForType(String type)
```
If you used custom types 
Return report for all logged screens of the given type 


```java 
ArrayList<String> getScreenNamesForType(String type)
```
get list of screen names for the given type 
Example of type : `activity` , `fragment` or if you used a custom type then give the name of that type 

```java 
long getTotalDurationForActivities()
```
get the sume of visible durations for actvities in milliseconds 

```java 
long getTotalDurationForFragments()
```
get the sume of visible durations for fragments in milliseconds

```java 
long getTotalDurationForType(String type)
```
get the sum of visible durations for the given type  in milliseconds



```java 
void reset()
```
clear all saved Data








