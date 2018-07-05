package mobile.umn.mobileapp.adapter;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import entity.MasterCard;
import lombok.NonNull;
import mobile.umn.mobileapp.R;

/**
 * Created by Heri on 7/5/2018.
 */

public class PurchasingListAdapter extends RecyclerView.Adapter<PurchasingListAdapter.ViewHolder>{
    private List<MasterCard> ongoingmasterCards;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView request_id;
        public TextView request_type;
        public TextView request_name;
        public TextView request_date;
        public TextView request_price;

        public Button button_approve;
        public Button button_reject;
        public TextView approval_box1,approval_box2,approval_box3,approval_box4;

        public ViewHolder(CardView v) {
            super(v);
            request_id = (TextView)v.findViewById(R.id.text_ongoingrequest_id);
            request_type = (TextView)v.findViewById(R.id.text_ongoingrequest_type);
            request_name = (TextView)v.findViewById(R.id.text_ongoingrequest_name);
            request_date = (TextView)v.findViewById(R.id.text_ongoingrequest_date);
            request_price = (TextView)v.findViewById(R.id.text_ongoingrequest_price);
            button_approve = (Button)v.findViewById(R.id.button_approve);
            button_reject = (Button)v.findViewById(R.id.button_reject);
            approval_box1 = (TextView) v.findViewById(R.id.text_approval_box_1);
            approval_box2 = (TextView) v.findViewById(R.id.text_approval_box_2);
            approval_box3 = (TextView) v.findViewById(R.id.text_approval_box_3);
            approval_box4 = (TextView) v.findViewById(R.id.text_approval_box_4);

            button_approve.setOnClickListener((view -> {
                int position = getAdapterPosition();
                // mDataset.remove(position);
                notifyItemRemoved(position);
                // notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Approved", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }));

            button_reject.setOnClickListener((view -> {
                int position = getAdapterPosition();
                //mDataset.remove(position);
                notifyItemRemoved(position);
                //notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Rejected", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }));
        }
    }

    public PurchasingListAdapter(List<MasterCard> masterCards) {
        ongoingmasterCards = masterCards;
//        System.out.println("Price:"+this.ongoingmasterCards.get(0).);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PurchasingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ongoingcardview_home, parent, false);
        return new PurchasingListAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @NonNull
    @Override
    public void onBindViewHolder(PurchasingListAdapter.ViewHolder holder, int position) {
        //holder.request_date.setText(masterCards.get(position).getDate());
        System.out.println("POSITION:"+position);
        holder.request_id.setText(Integer.toString(ongoingmasterCards.get(position).getRequest_header_id()));
        holder.request_price.setText(Integer.toString(ongoingmasterCards.get(position).getGrand_total()));
        holder.request_name.setText(ongoingmasterCards.get(position).getRequested_by());
        holder.request_date.setText(ongoingmasterCards.get(position).getRequest_date());


       /*

        */

        if(ongoingmasterCards.get(position).getApp_by1().equals("ACCEPTED")) holder.approval_box1.setBackgroundColor(Color.parseColor("#adff2f"));
        else if(ongoingmasterCards.get(position).getApp_by1().equals("REJECTED")) holder.approval_box1.setBackgroundColor(Color.parseColor("#FF0000"));
        else holder.approval_box1.setBackgroundColor(Color.parseColor("#cedbef"));

        if(ongoingmasterCards.get(position).getApp_by2().equals("ACCEPTED")) holder.approval_box2.setBackgroundColor(Color.parseColor("#7fFF00"));
        else if(ongoingmasterCards.get(position).getApp_by2().equals("REJECTED")) holder.approval_box2.setBackgroundColor(Color.parseColor("#FF0000"));
        else holder.approval_box2.setBackgroundColor(Color.parseColor("#cedbef"));

        if(ongoingmasterCards.get(position).getApp_by3().equals("ACCEPTED")) holder.approval_box3.setBackgroundColor(Color.parseColor("#41FF30"));
        else if(ongoingmasterCards.get(position).getApp_by3().equals("REJECTED")) holder.approval_box3.setBackgroundColor(Color.parseColor("#FF0000"));
        else holder.approval_box3.setBackgroundColor(Color.parseColor("#cedbef"));

        if(ongoingmasterCards.get(position).getApp_by4().equals("ACCEPTED")) holder.approval_box4.setBackgroundColor(Color.parseColor("#00ff00"));
        else if(ongoingmasterCards.get(position).getApp_by4().equals("REJECTED")) holder.approval_box4.setBackgroundColor(Color.parseColor("#FF0000"));
        else holder.approval_box4.setBackgroundColor(Color.parseColor("#cedbef"));



       /*disini set if buat mati nyalain
        holder.approval_box1.setVisibility(View.GONE);
        holder.approval_box2.setVisibility(View.GONE);
        holder.approval_box3.setVisibility(View.GONE);
        holder.approval_box4.setVisibility(View.GONE);
        */
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ongoingmasterCards.size();
        //return 0;
    }
}
