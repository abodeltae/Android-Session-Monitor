# Android-Session-Monitor
Android Session Monitor is a simple library to log the start/end time and duration of how long a screen has been visible in android 

## How to use 

First init the library by calling init from your application class 

```java 
SessionMonitorManager.init(this);
```
### Monitor Activities And fragments 

For Activities you can just extend ***`MonitoredActivity`*** 

 or if you wish you can just create an instance of  SessionHelper in your activity and pass onPause and onResume Events like this 
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

 or if you wish you can just create an instance of  SessionHelper in your Fragment and pass onPause and onResume Events like this 
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

### Get saved and logged data 
First get an instance of  ***`SessionMonitorManager`*** by calling 
```java
      sessionMonitor=SessionMonitorManager.getInstance();

```






