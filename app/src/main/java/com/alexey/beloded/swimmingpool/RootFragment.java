package com.alexey.beloded.swimmingpool;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RootFragment extends Fragment {
    public static final String MyPrefs = "MYPREFS";
    private int day;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.root_fragment, container, false);
        day = context.getSharedPreferences(MyPrefs, Context.MODE_PRIVATE).getInt("DAY", 1);
        Log.d("TAG", "Root day: " + String.valueOf(day));
        getFragmentManager().beginTransaction().replace(R.id.root, PlaceholderFragment.newInstance(day)).commit();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
