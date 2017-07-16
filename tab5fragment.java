package uk.co.lm634.nordfeldstefn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright Lluis Mather 2017.
 */

public class tab5fragment extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab5fragment,container,false);

        textView = (TextView) view.findViewById(R.id.assistText);
        textView.setText(R.string.assistString);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
