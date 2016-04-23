package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.R;

/**
 * Created by cwenhui on 2016.02.23
 */
public class FriendMsgFragment extends Fragment {
    private static final String TAG = "FriendMsgFragment";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend_msg, container, false);
        return view;
    }
}
