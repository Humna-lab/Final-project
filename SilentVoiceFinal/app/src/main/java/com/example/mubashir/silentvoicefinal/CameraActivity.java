package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class CameraActivity extends AppCompatActivity {

    private Camera_surfaceview cameraSurfaceView;
    private Camera camera;
    private FrameLayout cameraPreviewLayout;
    private ImageView capturedImageviewer;
    static Camera.Parameters param;
    private Timer _timer;
    TextView tv;
    Button stopVideo;
    Button capture_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        cameraPreviewLayout = (FrameLayout) findViewById(R.id.camera_preview);
        //  capturedImageviewer = (ImageView) findViewById(R.id.image_view);
        camera = check_camera_application();
        tv=(TextView)findViewById(R.id.textView2);
         tv.setText("Interpreter's Output");
         tv.setBackgroundColor(Color.rgb(165,195,249));
         tv.setTextColor(Color.BLACK);
         tv.setEnabled(false);
        cameraSurfaceView = new Camera_surfaceview(CameraActivity.this, camera);
        cameraPreviewLayout.addView(cameraSurfaceView);

        stopVideo=(Button)findViewById(R.id.button2);
        stopVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(_timer == null)
                {
                    Toast.makeText(CameraActivity.this, "Not capturing, Click on capture to start",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    _timer.cancel();
                    _timer = null;
                    capture_image.setBackgroundColor(Color.parseColor("#2c7ce2"));
                }
            }
        });
        capture_image = (Button) findViewById(R.id.button);
        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        param=camera.getParameters();
        cameraSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"AutoFocus",Toast.LENGTH_SHORT).show();
                param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(param);
                camera.startPreview();

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            if(_timer != null) {
                _timer.cancel();

            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void takePicture() {
        if (_timer != null)
        {
            _timer.cancel();
            _timer = null;
            capture_image.setBackgroundColor(Color.parseColor("#2c7ce2"));
            return;
    }
        tv.setText("");
//        capture_image = (Button) findViewById(R.id.button);
        capture_image.setBackgroundColor(Color.parseColor("#32CD32"));

//        Toast.makeText(CameraActivity.this, "Capturing...",
//                Toast.LENGTH_SHORT).show();

        _timer = new Timer();
        _timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // get an image from the camera
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override

                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap image = BitmapFactory.decodeByteArray(data, 0, data.length);
                            serverWorks sw = new serverWorks(getApplication());
                            sw.image = data;
                            sw.execute();
                            //for(;sw.flagsss==true;){}
                            String stuff = tv.getText().toString();
                            if (sw.resulting != null) {
                                if (sw.resulting.equals("delete"))
                                {
                                    if (stuff.equals(""))
                                    {

                                    }
                                    else {
                                        tv.setText(stuff.substring(0, stuff.length() - 1));
                                    }
                                }
                                else if (sw.resulting.equals("space")) {
                                    tv.setText(stuff + " ");

                                }
                                else if (sw.resulting.equals("nothing")) {
                                    tv.setText(stuff + "");
                                }
                                else
                                    {
                                    tv.setText(stuff + sw.resulting);
                                }
                            }
                            if (image != null) {
                                //    ImageView view = (ImageView) findViewById(R.id.image_view);
                                //   view.setImageBitmap(image);
                                //  view.setRotation(90);
                            }
                        }catch (Exception e){
                            Log.d("ErrorProblemO", "onPictureTaken: "+e);
                        }}
                });
            }
        }, 5000, 5000);
    }

    private Camera check_camera_application() {
        Camera mycamera = null;
        try {
            mycamera = Camera.open();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mycamera;
    }
    public void onBackPressed()
    {
        Intent myIntent = getIntent();
        String previousActivity= myIntent.getStringExtra("key");
        if (previousActivity.equals("MainTestActivity")) {
            myIntent = new Intent( this, MainTestActivity.class );
            startActivity( myIntent );
            finish();
        }
        else
        {
            myIntent = new Intent( this, MainTestActivity.class );
            startActivity( myIntent );
            finish();
        }

    }
}
