
package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static android.graphics.Bitmap.createBitmap;


public class CameraOpen extends AppCompatActivity {

    private static final String TAG = "CameraOpen";

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    Bitmap photo;
    Bitmap imgBitmap;
    private Mat inputMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameraopen);

        this.imageView = (ImageView) this.findViewById(R.id.imagePreview);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");

            Bitmap biImg = canny(photo);

            imageView.setImageBitmap(biImg);
        }
    }

    protected Bitmap canny(Bitmap img) {

        inputMat = new Mat(photo.getWidth(), photo.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(photo, inputMat);

        Mat BilateralFilterImg = applyBilateralFilter(inputMat);
        Imgproc.cvtColor(inputMat, BilateralFilterImg, Imgproc.COLOR_RGB2GRAY);
        Imgproc.adaptiveThreshold(BilateralFilterImg, inputMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 5);
        Utils.matToBitmap(inputMat, imgBitmap);

        imgBitmap = Bitmap.createBitmap(inputMat.cols(), inputMat.rows(), Bitmap.Config.ARGB_8888);
        return imgBitmap;
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

    }
}