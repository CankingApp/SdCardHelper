package com.canking.sdcardhelper;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("has SdCard:" + sdCardExist + " \n getExternalStorageState:" + Environment.getExternalStorageState() + "\n不需要任何权限");
        test();
    }

    public void test(TextView... v) {
        Log.d("changxing", "V:" + v.length);
    }

    static HashMap<Integer, String> m;

    @TargetApi(23)
    public void onClick(View v) {
//        String tb = "/gangyun/tmp";
//        int testH = test.hashCode();
//        int tbH = tb.hashCode();
//        TextView textView = (TextView) findViewById(R.id.text1);
//        String testa = new String("/gangyun/.themes");
//
//        long now = System.currentTimeMillis();
//        for (int i = 0; i <= 100000; i++) {
//            DigestEncodingUtils.computeMd5HexString(testa);
//        }
//        long now1 = System.currentTimeMillis() - now;
//        now = System.currentTimeMillis();
//        for (int i = 0; i <= 100000; i++) {
//            String test = new String("/gangyun/.themes");
//            test.hashCode();
//        }
//        long now2 = System.currentTimeMillis() - now;
//
//
//        textView.setText("testH:" + now1 +
//                "\ntbH:" + now2 + 100 / 200);
        File sd = Environment.getExternalStorageDirectory();
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                File sdcard = Environment.getExternalStorageDirectory();
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);


                TextView textViewx = (TextView) findViewById(R.id.text1);
                textViewx.setText("getExternalStorageDirectory:" + sdcard +
                        "\ngetAbsolutePath:" + sdcard.getAbsolutePath() +
                        "\ncanRead:" + sdcard.canRead() + " canWrite:" + sdcard.canWrite() +
                        "\n不需要任何权限 \nSDstate:" +
                        Environment.getExternalStorageState());
                break;
            case R.id.btn2:
                TextView textView2 = (TextView) findViewById(R.id.text2);
                StringBuilder stringBuilder = new StringBuilder();
                if (sd.isDirectory()) {
                    File[] list = sd.listFiles();
                    if (list != null) {
                        for (File l : list) {
                            stringBuilder.append("\nF:" + l.toString());
                        }
                    } else {
                        stringBuilder.append("sd.listFiles() = null");
                    }
                } else {
                    stringBuilder.append("SD card is not directory!");
                }
                textView2.setText(stringBuilder);
                break;
            case R.id.btn3:
                StringBuffer buffer = new StringBuffer();
                String filenameSD = sd + "/testSD";


                String filename = this.getExternalCacheDir() + "/testa";
                File fileC = this.getExternalFilesDir(android.os.Environment.DIRECTORY_MUSIC);
                File fileD = this.getExternalFilesDir(android.os.Environment.DIRECTORY_ALARMS);

                buffer.append("fileMusic:" + fileC.toString() + " read:" + fileC.canRead() + " write:" + fileC.canWrite() + "\n");
                buffer.append("fileAlarms:" + fileD.toString() + " read:" + fileD.canRead() + " fileD:" + fileC.canWrite() + "\n");

                File newFile = new File(filename);
                if (newFile.exists()) {
                    boolean d = newFile.delete();
                    buffer.append("newFile exists, and Delete:" + d);
                } else {
                    buffer.append("newFile not exists:+ \n" + newFile.getAbsolutePath() + "  path:" + newFile.getPath());
                    try {
                        //boolean t = newFile.createNewFile();
                        boolean t = newFile.mkdirs();
                        buffer.append("\nnewFile create:" + t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        buffer.append("\nException catch:" + e.getMessage());
                    }
                }

                File filenameSDF = new File(filenameSD);

                if (filenameSDF.exists()) {
                    boolean r = filenameSDF.renameTo(newFile);
                    buffer.append("\nfilenameSD: renameTo" + r);
                    buffer.append("\nfilenameSD: isDirectory " + filenameSDF.isFile());

                    try {
                        FileInputStream s = new FileInputStream(filenameSDF);
                        //int t = s.read();
                        buffer.append("\nfilenameSD: FileInputStream:error" );
                    } catch (Exception e) {
                        e.printStackTrace();
                        buffer.append( "\nException catch:" + e.getMessage());
                    }
                    boolean d = filenameSDF.delete();
                    buffer.append("\nfilenameSD: newFile exists, and Delete:" + d);
                } else {
                    buffer.append("\nfilenameSD: newFile not exists");
                    try {
                        buffer.append("\nfilenameSD lastModified:" + filenameSDF.lastModified());
                        boolean t = filenameSDF.createNewFile();
                        buffer.append("\nfilenameSD  newFile create:" + t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        buffer.append(e.getStackTrace() + "\nException catch:" + e.getMessage());
                    }
                }
                TextView textView3 = (TextView) findViewById(R.id.text3);
                textView3.setText(buffer);
                break;
            case R.id.btn4:
                boolean read = PermissionUtils.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                boolean write = PermissionUtils.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                TextView textView4 = (TextView) findViewById(R.id.text4);
                textView4.setText("App Read:" + read + " Write:" + write);
                break;
            case R.id.btn5:
                boolean check = PermissionUtils.checkPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE, 1);
                TextView textView5 = (TextView) findViewById(R.id.text5);
                textView5.setText("check read:" + check);
                break;
            case R.id.btn6:
                StringBuffer bufferd = new StringBuffer();


                String filed = sd + "/xxx.pdf";
                File newFiled = new File(filed);
                bufferd.append("permission:" + (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED));

                if (newFiled.exists()) {
                    bufferd.append("\nnewFile delete:" + newFiled.length());
                    boolean d = newFiled.delete();
                    bufferd.append("\nnewFile delete:" + d);
                } else {
                    try {
                        boolean b = newFiled.createNewFile();
                        bufferd.append("\n/testDelete not exists, newFile create:" + b);
                    } catch (IOException e) {
                        bufferd.append("\nIO exception" + e.getMessage());
                    } catch (Exception e) {
                        bufferd.append("\nif file exists, will returen false, otherwise will Permission. But can know exists and the size of the file" + e.getMessage());
                        e.printStackTrace();
                        bufferd.append("\nException catch:" + e.getMessage());
                    }
                }

                TextView textView6 = (TextView) findViewById(R.id.text6);
                textView6.setText("delete read:\n" + bufferd);

                break;
            case R.id.btn7:
                Intent i = new Intent(this, ActivityA.class);
                startActivity(i);
                break;
            case R.id.btn8:
                Intent b = new Intent(this, ActivityB.class);
                startActivity(b);
                break;
        }
    }
}
