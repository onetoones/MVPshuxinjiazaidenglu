package chenshuai.bwie.com.chenshuai20171211.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.chenshuai20171211.bean.beas;
import chenshuai.bwie.com.chenshuai20171211.net.Api;
import chenshuai.bwie.com.chenshuai20171211.net.OkHttpUtils;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class LoginModel implements ILoginrModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getRegis(String mobile, String password, final OnNetListener<beas> onNetListener) {
        String format = String.format(Api.LOGIN, mobile, password);

        OkHttpUtils.getHttpUtils().doGet(format, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final beas b = new Gson().fromJson(string, beas.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(b);
                    }
                });
            }
        });
    }

}
