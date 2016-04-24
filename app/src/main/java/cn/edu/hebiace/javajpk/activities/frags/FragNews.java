package cn.edu.hebiace.javajpk.activities.frags;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.activities.DetailActivity;
import cn.edu.hebiace.javajpk.adapters.FragListAdapter;
import cn.edu.hebiace.javajpk.beans.BaseBean;
import cn.edu.hebiace.javajpk.utils.Utils;


/**
 * 显示最新消息的Fragment。
 * <p>
 * 通过静态方法获得该Fragment的对象
 */
public class FragNews extends Fragment {

    private static final String TAG = "FragNews======";


    public FragNews() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p>
     * 使用这个工厂方法根据参数创建一个新的实例。
     *
     * @return A new instance of fragment FragNews.
     */
    public static FragNews newInstance() {
        FragNews fragment = new FragNews();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //这里获取从Activity传入的参数
        }
    }
    private  WebView webView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.frag_news, container, false);
        webView = (WebView) view.findViewById(R.id.frag_new_webview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://java.haoyue66.com/");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        super.onDetach();
//        webView.destroy();
    }

    /**
     * 跳转页面方法
     *
     * @param clazz
     */
    private void go(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }


}
