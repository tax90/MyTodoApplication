package com.example.mytodo.ui;


import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytodo.R;
import com.example.mytodo.di.Injectable;
import com.google.zxing.Result;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment implements Injectable, ZXingScannerView.ResultHandler {



    private static final int REQUEST_CAMERA = 1;

    @Inject
    public ZXingScannerView mScannerView;

    public ScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // mScannerView = new ZXingScannerView(getActivity());
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        if (!checkPermission()){
            requestPermission();
        }
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(Result rawResult) {

        Bundle bundle = new Bundle();
        bundle.putString("barcode", rawResult.getText());
        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        Navigation.findNavController(getView()).navigate(R.id.action_scannerFragment_to_addFragment,bundle);


    }

    private boolean checkPermission(){
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("PERMISSION GRANTED", "OKEJ");
                    } else {
                        requestPermission();
                    }

                }
                break;
        }
    }
}
