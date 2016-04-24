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

import java.io.File;
import java.util.List;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.activities.DetailActivity;
import cn.edu.hebiace.javajpk.adapters.FragListAdapter;
import cn.edu.hebiace.javajpk.beans.BaseBean;
import cn.edu.hebiace.javajpk.utils.Utils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * 显示影院情况的Fragment，通过静态方法获取实例。
 */
public class FragKe extends Fragment {
    private static final String TAG = "FragKe";

    private View view;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FragListAdapter adapter;
    private List<BaseBean> datas;

    public FragKe() {
        // Required empty public constructor
    }

    public static FragKe newInstance() {
        FragKe fragment = new FragKe();
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

        view = inflater.inflate(R.layout.frag_ke, container, false);
        initViews();
        initDatas();
        initListener();
        return view;
    }

    private void initViews() {
        showLoading();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frag_cinema_swipe);
        listView = (ListView) view.findViewById(R.id.frag_ke_list);
        adapter = new FragListAdapter(getContext());
        listView.setAdapter(adapter);
    }

    private void showLoading() {
    }

    private void initDatas() {
        Log.e(TAG, "FragKe.initDatas.......");
        datas = Utils.getLocalFile(getActivity(), Utils.KEJIANS);
        if (datas != null) {
            adapter.setItems(datas);
        }

    }

    private void initListener() {
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "正在刷新");
                if (adapter != null) {
                    refreshData();
                    listView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = datas.get(position).getPath();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(getString(R.string.docpath),path);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                View itemView = listView.getAdapter().getView(position, view, parent);
                TextView bt = (TextView) itemView.findViewById(R.id.frag_list_item_bt);
                if (bt.getVisibility() == View.VISIBLE) {
                    return false;
                }
                showDialog(position);
                return true;
            }
        });
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
        et.setTextSize(24);
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
                        refreshData();
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

    private void openKejian(int position) {
        File file = new File(datas.get(position).getPath());
        String mimeType = Utils.getMIMEType(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), mimeType);
        startActivity(intent);
    }

    private void refreshData() {
        initDatas();
//        AppAction appAction = ((LApplication) getActivity().getApplication()).getAppAction();
//        appAction.getCinemaAll(new ActionCallBackListener<List<Cinema>>() {
//
//            @Override
//            public void onSuccess(List<Cinema> data) {
//                if (data != null) {
//                    Log.e(TAG, data.toString());
//                    cinemas = data;
////                    cinemaAdapter.setItems(cinemas);
//                }
//            }
//
//            @Override
//            public void onFailure(String failure) {
//                Utils.log(getActivity(),failure);
//            }
//        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
