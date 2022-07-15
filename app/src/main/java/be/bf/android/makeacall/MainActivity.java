package be.bf.android.makeacall;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import be.bf.android.makeacall.utils.PermissionRequester;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CALL = 1;
    public Button callBtn;
    public EditText phoneNr;

    private ActivityResultLauncher<String> requestPermissionLauncher;

    private void isGrantedCallPhone(boolean isGranted) {
        if (isGranted) {
            makeCall();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callBtn = findViewById(R.id.callBtn);
        phoneNr = findViewById(R.id.phoneNr);

        callBtn.setOnClickListener(this::makeCallAction);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), this::isGrantedCallPhone);
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNr.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void makeCallAction(View v) {
        if (PermissionRequester.needRequest(this, Manifest.permission.CALL_PHONE)) {
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        } else {
            makeCall();
        }
    }
}