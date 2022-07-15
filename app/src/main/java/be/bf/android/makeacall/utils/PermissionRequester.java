package be.bf.android.makeacall.utils;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;

import java.util.function.Consumer;

public abstract class PermissionRequester {

    public static boolean needRequest(Activity activity, String... permissions) {
        boolean toRequest = false;

        for(String permission: permissions) {
            if (!toRequest && ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                toRequest = true;
            }
        }

        return toRequest;
    }
}
