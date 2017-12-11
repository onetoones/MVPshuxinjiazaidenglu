package chenshuai.bwie.com.chenshuai20171211.persenter;

import chenshuai.bwie.com.chenshuai20171211.bean.PrBean;
import chenshuai.bwie.com.chenshuai20171211.model.IPrModel;
import chenshuai.bwie.com.chenshuai20171211.model.tProductsModel;
import chenshuai.bwie.com.chenshuai20171211.net.OnNetListener;
import chenshuai.bwie.com.chenshuai20171211.view.iview.vvm;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class PrPeresenter {
    private vvm v;
    private final IPrModel iPrModel;

    public PrPeresenter(vvm v) {
        this.v = v;
        iPrModel = new tProductsModel();
    }

    public void getson(int pscid, int page) {


        iPrModel.getPr(pscid, page, new OnNetListener<PrBean>() {
            @Override
            public void onSuccess(PrBean prBean) {
                v.showGoods(prBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
