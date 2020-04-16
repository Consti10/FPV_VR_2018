package constantin.fpv_vr.AConnect;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import constantin.fpv_vr.XDJI.DJIApplication;
import constantin.fpv_vr.databinding.ConnectDjiFragmentBinding;

public class FConnectDJI extends Fragment implements View.OnClickListener{
    private ConnectDjiFragmentBinding binding;
    private Context mContext;

    @Override
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=getActivity();
        binding=ConnectDjiFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        final Application application=getActivity().getApplication();
        //((DJIApplication)application).initAppIfNeeded();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

    }
}
