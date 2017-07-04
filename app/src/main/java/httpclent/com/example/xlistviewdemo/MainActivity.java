package httpclent.com.example.xlistviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
 private String s;
    private XListView xlv;
    private Base adapter;
    private ArrayList<Data.ListBean> list;
    private String path="http://qhb.2dyt.com/Bwei/news?page=11&postkey=1503d&type=";
    private int type=1;
    private Handler handler =new Handler(){
     @Override
     public void handleMessage(Message msg) {
         s = msg.obj.toString();
         Gson gson = new Gson();
         Data data = gson.fromJson(s, Data.class);
         list.addAll(data.getList());
         adapter.notifyDataSetChanged();

     }
 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        xlv = (XListView) findViewById(R.id.xlv);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(this);
        loard();
        adapter = new Base(list,this);
        xlv.setAdapter(adapter);

    }

    private void loard() {
        new Thread(){
            @Override
            public void run() {
                String s = Utils.parmrs(path+type);
                Message msg = Message.obtain();
                msg.obj =s;
                handler.sendMessage(msg);
            }
        }.start();


    }

    @Override
    public void onRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Data data = gson.fromJson(s,Data.class);
                list.addAll(0,data.getList());
                adapter.notifyDataSetChanged();

                stoploder();

            }
        },2000);


    }



    @Override
    public void onLoadMore() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Data data = gson.fromJson(s,Data.class);
                list.addAll(data.getList());
                adapter.notifyDataSetChanged();

                stoploder();

            }
        },2000);

    }
    private void stoploder() {

        xlv.stopRefresh();
        xlv.stopLoadMore();
    }


    }




