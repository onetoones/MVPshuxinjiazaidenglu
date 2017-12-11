package chenshuai.bwie.com.chenshuai20171211.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import chenshuai.bwie.com.chenshuai20171211.R;
import chenshuai.bwie.com.chenshuai20171211.bean.PrBean;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<PrBean.DataBean> list;
    private Context context;

    public MyAdapter(List<PrBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHodel(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PrBean.DataBean dataBean = list.get(position);
        MyViewHodel hs = (MyViewHodel) holder;
        hs.price.setText("原价：￥"+dataBean.getSalenum());
        hs.pricess.setText("优惠价：￥"+dataBean.getPrice());
        hs.title.setText(dataBean.getTitle());
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(hs.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHodel extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView price;
        private final TextView pricess;
        private final TextView title;

        public MyViewHodel(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.it_iv);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
            pricess = itemView.findViewById(R.id.pricesss);
        }
    }

}
