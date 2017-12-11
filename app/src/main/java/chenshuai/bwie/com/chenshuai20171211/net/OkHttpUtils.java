package chenshuai.bwie.com.chenshuai20171211.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class OkHttpUtils {
    private static OkHttpUtils httpUtils;
    private OkHttpUtils okHttpUtils;
    private final OkHttpClient client;

    private OkHttpUtils(){

        client = new OkHttpClient.Builder().build();

    }
    public static OkHttpUtils getHttpUtils(){
        if (httpUtils==null){
            synchronized (OkHttpUtils.class){
                if (httpUtils==null){
                    httpUtils = new OkHttpUtils();
                }
            }

        }
        return httpUtils;
    }
    //get请求
    public void doGet(String url, Callback callback){

        Request build = new Request.Builder().url(url).build();
        client.newCall(build).enqueue(callback);
    }
    //get请求
    public void doPost(String url, Map<String,String> params,Callback callback){

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry: params.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());

        }
        FormBody formBody = builder.build();

        Request build = new Request.Builder().url(url).post(formBody).build();
        client.newCall(build).enqueue(callback);

    }
}
