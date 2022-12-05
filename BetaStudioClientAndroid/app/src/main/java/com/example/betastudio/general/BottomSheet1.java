package com.example.betastudio.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.betastudio.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet1 extends BottomSheetDialogFragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_1,
                container, false);
        textView = v.findViewById(R.id.mypatinfo);
        Button cancel = v.findViewById(R.id.cancelButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        return v;
    }

    public void setInfo(String info) {
        this.textView.setText(info);
    }

}