package mobile.umn.mobileapp.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import entity.MasterCard;
import mobile.umn.mobileapp.HomeActivity;
import mobile.umn.mobileapp.R;
import mobile.umn.mobileapp.model.RequestHeader;

/**
 * Created by User on 24/05/2018.
 */

public class OngoingRequestListAdapter extends RecyclerView.Adapter<OngoingRequestListAdapter.ViewHolder>{
    private List<MasterCard> masterCards;


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

    public OngoingRequestListAdapter(List<MasterCard> masterCards) {
        this.masterCards = masterCards;
      //  System.out.println(this.masterCards.get(1).getDate());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OngoingRequestListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ongoingcardview_home, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.request_date.setText(masterCards.get(position).getDate());
        holder.request_price.setText(masterCards.get(position).getPrice());
       /*

        */
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
        return masterCards.size();

          //return 0;
    }
}
