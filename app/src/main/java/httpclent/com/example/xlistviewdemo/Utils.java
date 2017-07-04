package httpclent.com.example.xlistviewdemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 嘿 on 2017/6/26.
 */

public class Utils {
    public  static  String  parmrs(String path){
            try{
                URL url = new URL(path);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode()==200){
                    InputStream in = httpURLConnection.getInputStream();
                    StringBuffer sb = new StringBuffer();
                    int len = -1;
                    byte[] by = new byte[1024*4];
                    while((len=in.read(by))!=-1){
                        sb.append(new String(by,0,len,"utf-8"));
                    }
                    in.close();
                    httpURLConnection.disconnect();
                    return  sb.toString();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return  null;
        }
}
