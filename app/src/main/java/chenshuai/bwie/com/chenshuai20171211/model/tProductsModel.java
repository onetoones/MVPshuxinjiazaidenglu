package chenshuai.bwie.com.chenshuai20171211.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.chenshuai20171211.bean.PrBean;
import chenshuai.bwie.com.chenshuai20171211.net.OkHttpUtils;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class tProductsModel implements IPrModel{
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getPr(int pscid, int page, final OnNetListener<PrBean> onNetListener) {

        String url = "http://120.27.23.105/product/getProducts?pscid="+pscid+"&page="+page+"";
       // String format = String.format(Api.PRODUCTS, pscid, page);
        OkHttpUtils.getHttpUtils().doGet(url, new Callback() {
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
                final PrBean bean = new Gson().fromJson(string, PrBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(bean);
                    }
                });
            }
        });

    }
}
