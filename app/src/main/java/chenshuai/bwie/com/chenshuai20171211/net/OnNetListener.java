package chenshuai.bwie.com.chenshuai20171211.net;

/**
 * Created by 不将就 on 2017/12/11.
 */

public interface OnNetListener<T> {
    public void onSuccess(T t);
    public void onFailure(Exception e);
}
