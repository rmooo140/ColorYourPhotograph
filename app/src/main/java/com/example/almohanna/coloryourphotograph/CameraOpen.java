
package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static android.graphics.Bitmap.createBitmap;

/**
 * Created by user on 07/10/17.
 */

public class CameraOpen extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {
    //Camera.PictureCallback, Camera.PreviewCallback

    private static final String TAG = "CameraOpen";
    private JavaCameraView mOpenCvCameraView;
    private Mat mRgba;
    private Mat mByte;
    private Handler mHandler;
    private int CountImage;
    private ToggleButton capture;
    private ImageView Iv;
    private Bitmap mBitmap;

    CameraBridgeViewBase.CvCameraViewFrame inputFrame;

    BaseLoaderCallback mBaseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);
            switch (status) {
                case BaseLoaderCallback.SUCCESS: {
                    mOpenCvCameraView.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.cameraopen);
        mOpenCvCameraView = (JavaCameraView) findViewById(R.id.textdetetion_view);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);

        Iv = (ImageView) findViewById(R.id.imagePreview);
        capture = (ToggleButton) findViewById(R.id.capture);
        capture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                captureOnClick();
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj=="IMG")
                {
                    Canvas canvas = new Canvas();
                    canvas.setBitmap(mBitmap);
                    Iv.setImageBitmap(mBitmap);
                    if (CountImage>=-1)
                    {
                        capture.setChecked(false);
                        captureOnClick();
                    }
                }
            }
        };

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug() == true) {
            Log.i(TAG, "opencv loaded");
            mBaseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.i(TAG, "opencn not loade");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, mBaseLoaderCallback);
        }
    }

    public void  captureOnClick()
    {
        if (capture.isChecked())
            //faceState=TRAINING;
            Toast.makeText(this,"if part",Toast.LENGTH_SHORT).show();

        else {
            Toast.makeText(this,"Captured",Toast.LENGTH_SHORT).show();
                CountImage=0;
            //FaceState=IDLE;
           // IV.setImageResource(R.drawable.user_image);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mByte = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Mat BilateralFilterImg = applyBilateralFilter(mRgba);
        Imgproc.cvtColor(mRgba, BilateralFilterImg, Imgproc.COLOR_RGB2GRAY);
        Imgproc.adaptiveThreshold(BilateralFilterImg, mByte, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 5);
        return mByte; // this is m Binary image

       // mBitmap = createBitmap(mByte.width(),mByte.height(), Bitmap.Config.ARGB_8888);
        //Utils.matToBitmap(mByte, mBitmap);
    }

    public Mat applyBilateralFilter(Mat mRgba) {
        Mat result;
        // convert 4 channel Mat to 3 channel Mat
        Imgproc.cvtColor(mRgba, mRgba, Imgproc.COLOR_BGRA2BGR);

        // create dest Mat
        Mat dstMat = mRgba.clone();

        // apply bilateral filter
        Imgproc.bilateralFilter(mRgba, dstMat, 10, 250, 50);

        // convert to 4 channels Mat back
        Imgproc.cvtColor(dstMat, dstMat, Imgproc.COLOR_RGB2RGBA);

        // create result bitmap and convert Mat to it
        Bitmap bm = createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);
        Utils.bitmapToMat(bm, dstMat);

        return dstMat;
        // show bitmap on ImageView
        //javaCameraView.setImageBitmap(bm);
    }
}

