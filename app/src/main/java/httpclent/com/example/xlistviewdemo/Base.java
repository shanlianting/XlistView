package httpclent.com.example.xlistviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 嘿 on 2017/6/28.
 */

public class Base extends BaseAdapter {
    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;
    private List<Data.ListBean> list;
    private Context mContext;

    public Base(List<Data.ListBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
      /*  if (position % 2 == 0) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }*/
        if(list.get(position).getType()==1){
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    /**
     * 指定你要加载的条目类型
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;
        int type = getItemViewType(i);
        switch (type) {
            case TYPE_1: {
                if (view == null) {
//                    /iew = View.inflate(mContext, R.layout.activitytwo, null);
                    viewHolder = new ViewHolder();
                    view = view.inflate(mContext, R.layout.item, null);
                    viewHolder.image1 = (ImageView) view.findViewById(R.id.image1);
                    viewHolder.image2 = (ImageView) view.findViewById(R.id.image2);
                    viewHolder.tvContent1 = (TextView) view.findViewById(R.id.text1);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.tvContent1.setText(list.get(i).getTitle());
                Glide.with(mContext).load(list.get(i).getPic()).into(viewHolder.image1);
                Glide.with(mContext).load(list.get(i).getPic()).into(viewHolder.image2);
            }
            break;
            case TYPE_2: {
                if (view == null) {
                    // view = View.inflate(mContext, R.layout.activitytwo, null);
                    viewHolder2 = new ViewHolder2();
                    view = view.inflate(mContext, R.layout.item2, null);
                    viewHolder2.image3 = (ImageView) view.findViewById(R.id.image3);
                    viewHolder2.tvContent2 = (TextView) view.findViewById(R.id.text2);
                    viewHolder2.tvContent3 = (TextView) view.findViewById(R.id.text3);
                    view.setTag(viewHolder2);
                } else {
                    viewHolder2 = (ViewHolder2) view.getTag();
                }
                viewHolder2.tvContent2.setText(list.get(i).getTitle());
                viewHolder2.tvContent3.setText(list.get(i).getId()+"");
                Glide.with(mContext).load(list.get(i).getPic()).into(viewHolder2.image3);
            }
            break;
        }
        return view;
    }

    static class ViewHolder {
        TextView tvContent1;
        ImageView image1, image2;

    }

    static class ViewHolder2 {
        TextView tvContent2, tvContent3;
        ImageView image3;
    }
}
