package id.dev.merapitech.afterworld.translateaw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.dev.merapitech.afterworld.translateaw.EditActivity;
import id.dev.merapitech.afterworld.translateaw.R;
import id.dev.merapitech.afterworld.translateaw.response.ResponseBahasa;
import okhttp3.ResponseBody;
import retrofit2.Callback;

public class BahasaAdapter extends RecyclerView.Adapter<BahasaAdapter.MyViewHolder> {

    private Context mContext;
    private List<ResponseBahasa> bahasaList;
    private List<ResponseBahasa> itemsCopy;
    private Callback<ResponseBody> cBack;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBahasa, tvVariable, tvTitle;

        public MyViewHolder(View view) {
            super(view);
            tvBahasa = view.findViewById(R.id.tv_bahasa);
            tvVariable = (TextView) view.findViewById(R.id.tv_variabel);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }

    public BahasaAdapter(Context mContext, List<ResponseBahasa> bahasaList) {
        this.mContext = mContext;
        this.bahasaList = bahasaList;
    }

    @Override
    public BahasaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_bahasa, parent, false);


        return new BahasaAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final BahasaAdapter.MyViewHolder holder, final int position) {
        final ResponseBahasa bahasa = bahasaList.get(position);
        holder.tvTitle.setText(bahasa.getTitle());
        holder.tvVariable.setText(bahasa.getLanguage_variable());
        holder.tvBahasa.setText(bahasa.getLanguage_jenis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("JENIS", bahasa.getLanguage_jenis());
                intent.putExtra("VARIABEL", bahasa.getLanguage_variable());
                intent.putExtra("TITLE", bahasa.getTitle());
                intent.putExtra("ID", bahasa.getIdlanguage());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return bahasaList.size();
    }

    public void setFilter(List<ResponseBahasa> newList){
        bahasaList=new ArrayList<>();
        bahasaList.addAll(newList);
        notifyDataSetChanged();
    }
}