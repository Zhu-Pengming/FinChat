package com.example.financialmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FundAdapter extends RecyclerView.Adapter<FundAdapter.FundViewHolder> {

    private List<Fund> fundList;
    private Context context;

    public FundAdapter(List<Fund> fundList, Context context) {
        this.fundList = fundList;
        this.context = context;
    }

    @NonNull
    @Override
    public FundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fund, parent, false);
        return new FundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FundViewHolder holder, int position) {
        Fund fund = fundList.get(position);
        holder.fundName.setText(fund.getFundName());
        holder.fundAnnualReturn.setText(String.format("%.2f%%", fund.getAnnualReturn()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FundDetailActivity.class);
            intent.putExtra("fund", fund);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fundList.size();
    }

    static class FundViewHolder extends RecyclerView.ViewHolder {
        TextView fundName;
        TextView fundAnnualReturn;

        FundViewHolder(View itemView) {
            super(itemView);
            fundName = itemView.findViewById(R.id.fund_name);
            fundAnnualReturn = itemView.findViewById(R.id.fund_annual_return);
        }
    }
}
