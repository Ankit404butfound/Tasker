package com.ankit.tasker;

public class Globals {
    public static String SERVER_POINT = "http://192.168.0.156:8000/";
    public static String TOKEN = MainActivity.db.get_token();
    public static String RESULT_FROM_ASYNC_TASK;

    public static String register_device_url(String token){
        return String.format("%s/register/%s", SERVER_POINT, token);
    }

    public static String schedule_notification_url(long time, String title, String description){
        return String.format("%s/schedule?token=%s&time=%s&title=%s&description=%s", SERVER_POINT, TOKEN, time, title, description);
    }
}
