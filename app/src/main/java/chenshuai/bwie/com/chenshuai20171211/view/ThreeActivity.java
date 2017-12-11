package chenshuai.bwie.com.chenshuai20171211.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chenshuai.bwie.com.chenshuai20171211.R;
import chenshuai.bwie.com.chenshuai20171211.adapter.MyAdapter;
import chenshuai.bwie.com.chenshuai20171211.bean.PrBean;
import chenshuai.bwie.com.chenshuai20171211.persenter.PrPeresenter;
import chenshuai.bwie.com.chenshuai20171211.view.iview.vvm;

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener, vvm {

    private ImageView mIv;
    /**
     * 商品列表
     */
    private TextView mTextView;
    private RecyclerView mRv;
    int pscid = 39;
    int page = 1;
    private List<PrBean.DataBean> list = new ArrayList<>();
    private SwipeRefreshLayout mSl;
    private PrPeresenter prPeresenter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        prPeresenter = new PrPeresenter(this);

        prPeresenter.getson(pscid, page);
        initView();
        //上啦刷新
        mSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                page = 1;
                prPeresenter.getson(pscid, page);
                mSl.setRefreshing(false);
                Toast.makeText(ThreeActivity.this, "数据刷新完成!!!", Toast.LENGTH_SHORT).show();
            }
        });
/**
 * 加载更多
 */
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager im = (LinearLayoutManager) mRv.getLayoutManager();
                    if (im.findLastVisibleItemPosition() == list.size() - 1) {
                        List<String> dataNew = new ArrayList<String>();
                        page++;
                        prPeresenter.getson(pscid, page);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ThreeActivity.this, "数据加载完成!!!", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTextView = (TextView) findViewById(R.id.textView);
        mRv = (RecyclerView) findViewById(R.id.rv);

        mRv.setOnClickListener(this);

        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mSl = (SwipeRefreshLayout) findViewById(R.id.sl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv:


                break;
        }
    }

    @Override
    public void showGoods(PrBean goodsBean) {
        List<PrBean.DataBean> data = goodsBean.getData();
        if (data.size()==0){
            Toast.makeText(ThreeActivity.this,"没有可以加载的数据了!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        list.addAll(data);
        adapter = new MyAdapter(list, this);
        mRv.setAdapter(adapter);
    }
}
