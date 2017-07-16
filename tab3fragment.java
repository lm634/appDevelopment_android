package uk.co.lm634.nordfeldstefn;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright Lluis Mather 2017.
 */

public class tab3fragment extends Fragment {

    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3fragment,container,false);

        textView = (TextView)view.findViewById(R.id.biogText);
        textView.setText(R.string.biogString);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
