package mobile.umn.mobileapp.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.umn.mobileapp.R;
import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.RequestDetail;

/**
 * Created by User on 24/05/2018.
 */

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.ViewHolder>{
    private ArrayList<MasterItem> mDataset;
    private ArrayList<RequestDetail> requests;
    private Dialog dialog;
    private Context context;
    private Activity activity;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_name;
        public TextView item_price;
        public TextView item_code;
        public ViewHolder(CardView v) {
            super(v);
            item_name = (TextView)v.findViewById(R.id.text_item_name);
            item_code = (TextView)v.findViewById(R.id.text_item_code);
            item_price = (TextView)v.findViewById(R.id.text_item_price);
        }
    }

    public AddItemAdapter(ArrayList<MasterItem> myDataset,
                          ArrayList<RequestDetail> requests,
                          Dialog dialog, Context context, Activity activity) {
        mDataset = myDataset;
        this.requests = requests;
        this.dialog = dialog;
        this.context = context;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AddItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_pr_add_item, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Integer price = mDataset.get(position).getItem_price();
        holder.item_name.setText(mDataset.get(position).getItem_name());
        holder.item_code.setText(mDataset.get(position).getItem_code());
        holder.item_price.setText(String.valueOf(price));


        holder.itemView.setOnClickListener(param0->{
            EditText qty = dialog.findViewById(R.id.text_qty_item);
            if(qty.getText().toString().equals("")){
                qty.setError("Quantity cannot be empty");
            }
            else{
                Integer _qty = Integer.valueOf(qty.getText().toString());
                Integer total = price * _qty;
                requests.add(new RequestDetail(0,mDataset.get(position),total,_qty));
                dialog.hide();

                AddPurchaseRequestAdapter adapter = new AddPurchaseRequestAdapter(requests);
                RecyclerView myView = (RecyclerView) activity.findViewById(R.id.pr_recycler_view);
                myView.setHasFixedSize(true);
                myView.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(context);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                myView.setLayoutManager(llm);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
