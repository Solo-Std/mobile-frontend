package mobile.umn.mobileapp.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.umn.mobileapp.R;
import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.RequestDetail;
import mobile.umn.mobileapp.model.RequestHeader;

/**
 * Created by User on 24/05/2018.
 */

public class AddPurchaseRequestAdapter extends RecyclerView.Adapter<AddPurchaseRequestAdapter.ViewHolder>{
    private ArrayList<RequestDetail> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_name;
        public TextView item_qty;
        public TextView item_price;
        public TextView item_total;
        public ViewHolder(CardView v) {
            super(v);
            item_name = (TextView)v.findViewById(R.id.text_item_name);
            item_qty = (TextView)v.findViewById(R.id.text_qty);
            item_price = (TextView)v.findViewById(R.id.text_price);
            item_total = (TextView)v.findViewById(R.id.text_total);
        }
    }

    public AddPurchaseRequestAdapter(ArrayList<RequestDetail> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AddPurchaseRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_pr_add, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.item_name.setText(mDataset.get(position).getItem().getItem_name());
        holder.item_qty.setText(String.valueOf(mDataset.get(position).getItem_qty()));
        holder.item_price.setText(String.valueOf(mDataset.get(position).getItem().getItem_price()));
        holder.item_total.setText(String.valueOf(mDataset.get(position).getTotal_price()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
