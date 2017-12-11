package chenshuai.bwie.com.chenshuai20171211.persenter;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chenshuai.bwie.com.chenshuai20171211.bean.beas;
import chenshuai.bwie.com.chenshuai20171211.model.IRegisterModel;
import chenshuai.bwie.com.chenshuai20171211.model.RegisterModel;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;
import chenshuai.bwie.com.chenshuai20171211.view.iview.IActivity;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class RegisterPersenter {
    private IActivity iActivity;
    private final IRegisterModel iRegisterModel;

    public RegisterPersenter(IActivity iActivity) {
        this.iActivity = iActivity;
        iRegisterModel = new RegisterModel();

    }

    public void regis() {
        String name = iActivity.getName();
        String pwd = iActivity.getPwd();
        if (checkAccount(name)&&checkPwd(pwd)){
            iRegisterModel.getRegis(name, pwd, new OnNetListener<beas>() {
                @Override
                public void onSuccess(beas beas) {
                    iActivity.tz();
                }

                @Override
                public void onFailure(Exception e) {

                }
            });

        }


    }

    private boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            //给用户提示，输入的账号不能为空
            iActivity.show("请输入密码");
            return false;
        }


        if (pwd.length() != 6) {
            iActivity.show("请输入6位密码");
            return false;
        }
        return true;
    }




    /**
     * 验证手机号是否正确
     *
     * @param account
     */
    private boolean checkAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            //给用户提示，输入的账号不能为空
            iActivity.show("请输入账号");
            return false;
        }
        if (!isMobileNO(account)) {
            iActivity.show("请输入正确的手机号");
            return false;
        }
        return true;
    }




    /*
    判断是否是手机号
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
