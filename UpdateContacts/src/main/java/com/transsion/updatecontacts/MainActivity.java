package com.transsion.updatecontacts;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 检查并申请READ_CONTACTS权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            updataCotact();
        }
    }

    /**
     * 更新联系人
     */
    public void updataCotact() {

        ContentValues values = new ContentValues();

        values.put(ContactsContract.Contacts.DISPLAY_NAME, "Tom");

        String where = ContactsContract.Contacts._ID + "=?";

        String[] selectionArgs = new String[]{String.valueOf(1)};

        getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, where, selectionArgs);

    }

}
