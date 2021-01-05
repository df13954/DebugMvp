package com.debug.framework.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.debug.framework.R;



/**
 * @author dr
 * @date 2020-05-22
 * @description fragment待扩展, 扩展和act同理
 */
public abstract class BaseCompatFragment extends Fragment {

    protected abstract void initView();

    protected abstract void onRefreshClick();

    protected abstract void onNetworkClick();


}
