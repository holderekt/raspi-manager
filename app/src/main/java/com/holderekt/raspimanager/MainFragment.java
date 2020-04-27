package com.holderekt.raspimanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View thisfragment = inflater.inflate(R.layout.main_fragment, container, false);
        System.out.println("mamasmama");
        TextView a = thisfragment.findViewById(R.id.textView);
        a.setText("a");
        return thisfragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.shutdown).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Cliccato pulsante", Toast.LENGTH_LONG);
                myToast.show();
            }
        });
    }

}
