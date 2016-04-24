package cn.edu.hebiace.javajpk.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.beans.BaseBean;
import cn.edu.hebiace.javajpk.utils.Utils;

/**
 * Created by duke on 16-3-14.
 */
public class FragListAdapter extends LBaseAdapter<BaseBean> {

    public FragListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHolder holder = null;
        if (v == null) {
            v = inflater.inflate(R.layout.frag_list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) v.findViewById(R.id.frag_list_item_text);
            holder.downBt = (TextView) v.findViewById(R.id.frag_list_item_bt);
            v.setTag(holder);
        }
        holder = (ViewHolder) v.getTag();
        final BaseBean bean = itemList.get(position);
        holder.textView.setText(bean.getName());
        if (bean.getPath().contains("http://")) {
//        if (position == 0) {
            holder.downBt.setVisibility(View.VISIBLE);
            holder.downBt.setClickable(true);
            holder.downBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 下载附件
                    Utils.log(context, bean.getPath());
                }
            });
        } else {
            holder.downBt.setVisibility(View.GONE);
        }
        return v;
    }

    public static class ViewHolder {
        TextView textView;
        TextView downBt;
    }
}
