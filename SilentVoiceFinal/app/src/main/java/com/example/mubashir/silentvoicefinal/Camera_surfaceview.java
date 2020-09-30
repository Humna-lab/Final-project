package com.example.mubashir.silentvoicefinal;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Camera_surfaceview extends SurfaceView implements SurfaceHolder.Callback{
 private Camera camera;
 private SurfaceHolder surfaceHolder;
    private Object mPreviewSize;

    public Camera_surfaceview(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            this.camera.setPreviewDisplay(holder);
            this.camera.startPreview();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (surfaceHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.stopPreview();
            camera.setDisplayOrientation(90);
            Camera.Parameters params = camera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            if (sizes == null || sizes.size() <= 0) {
                return;
            }

            Camera.Size bestSize = null;

            for (Camera.Size tmpSize : sizes) {
                if(tmpSize.width == tmpSize.height) {
                    bestSize = tmpSize;
                    break;
                }
            }

            if(bestSize != null) {
                params.setPreviewSize(bestSize.width, bestSize.height);
                camera.setParameters(params);
            }

            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }







    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.camera.stopPreview();
        this.camera.release();
    }

}

