package cn.edu.hebiace.javajpk.activities.frags;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.activities.VideoActivity;
import cn.edu.hebiace.javajpk.adapters.FragListAdapter;
import cn.edu.hebiace.javajpk.beans.BaseBean;
import cn.edu.hebiace.javajpk.utils.Utils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class FragVideos extends Fragment {
    private static final String TAG = "FragVideos = ";

    private FragListAdapter adapter;
    private List<BaseBean> datas;
    private OnFragSalesListener mListener;
    private View view;
    private ListView listView;

    public FragVideos() {
        // Required empty public constructor
    }

    public static FragVideos newInstance() {
        FragVideos fragment = new FragVideos();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_videos, container, false);
        listView = (ListView) view.findViewById(R.id.frag_videos_list);

        adapter = new FragListAdapter(getContext());
        listView.setAdapter(adapter);
        initData();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frag_videos_swipe);
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "正在刷新");
                if (adapter != null) {
                    listView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra(getString(R.string.videopath), datas.get(position).getPath());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                View itemView = listView.getAdapter().getView(position,view,parent);
                TextView bt  = (TextView) itemView.findViewById(R.id.frag_list_item_bt);
                if(bt.getVisibility() == View.VISIBLE){
                    return false;
                }
                showDialog(position);
                return true;
            }
        });
        return view;
    }
    private void initData(){
        datas = Utils.getLocalFile(getActivity(),Utils.VIDEOS);
        if(datas !=null){
            adapter.setItems(datas);
        }
    }


    private void showDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.renamedialog));
        final File file = new File(datas.get(position).getPath());
        final EditText et = new EditText(getActivity());
        final String oldname = file.getName();
        et.setText(oldname);
        if (oldname.contains(".")) {
            et.setSelection(0, oldname.lastIndexOf("."));
        } else {
            et.setSelection(0, oldname.length() - 1);
        }
        et.setTextSize(20);
        et.setLayoutParams(new DrawerLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));

        builder.setView(et);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newname = et.getText().toString();
                        if (newname.equals("")) {
                            Utils.log(getActivity(), getString(R.string.nostr));
                            dialog.cancel();
                        }
                        if (newname.equals(oldname)) {
                            Utils.log(getActivity(), getString(R.string.samename));
                            dialog.cancel();
                        }

                        if (!file.canWrite()) file.setWritable(true);
                        File newfile = new File(file.getParent() + File.separator + newname);
                        // Utils.loge(TAG,newfile.getPath());
                        if (file.renameTo(newfile)) {
                            Utils.loge("FragKe rename file:", newfile.getName());
                            Utils.log(getActivity(), getString(R.string.renamesuccess));
                        }
//
//                        if (newname.contains(".")) {
//                            datas.get(position).setName(newname.substring(0, newname.lastIndexOf(".")));
//                        } else {
//                            datas.get(position).setName(newname);
//                        }
//                        datas.get(position).setPath(newfile.getPath());
                        initData();
                        listView.setAdapter(adapter);

                    }
                }
        );
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSales(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragSalesListener) {
            mListener = (OnFragSalesListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragSalesListener {
        // TODO: Update argument type and name
        void onSales(Uri uri);
    }
}
