package chenshuai.bwie.com.chenshuai20171211.model;

import chenshuai.bwie.com.chenshuai20171211.bean.PrBean;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;

/**
 * Created by 不将就 on 2017/12/11.
 */

public interface IPrModel {
    public void getPr(int pscid, int page, final OnNetListener<PrBean> onNetListener);
}
