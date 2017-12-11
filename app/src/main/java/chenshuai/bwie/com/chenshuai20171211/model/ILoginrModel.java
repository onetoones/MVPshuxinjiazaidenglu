package chenshuai.bwie.com.chenshuai20171211.model;

import chenshuai.bwie.com.chenshuai20171211.bean.beas;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;

/**
 * Created by 不将就 on 2017/12/11.
 */

public interface ILoginrModel {
    public void getRegis(String mobile, String password, OnNetListener<beas> onNetListener);
}
