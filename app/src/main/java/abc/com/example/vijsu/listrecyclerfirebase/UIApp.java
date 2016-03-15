package abc.com.example.vijsu.listrecyclerfirebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by vijsu on 15-03-2016.
 */
public class UIApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
