package mobile.umn.mobileapp.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.umn.mobileapp.HomeActivity;
import mobile.umn.mobileapp.R;
import mobile.umn.mobileapp.model.RequestHeader;

/**
 * Created by User on 24/05/2018.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder>{
    private ArrayList<RequestHeader> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView request_id;
        public TextView request_type;
        public TextView request_name;
        public TextView request_date;
        public TextView request_total;
        public Button button_approve;
        public Button button_reject;

        public EditText text_details;
        public Button button_confirm;
        public Button button_cancel;

        public ViewHolder(CardView v) {
            super(v);
            request_id = (TextView)v.findViewById(R.id.text_request_id);
            request_type = (TextView)v.findViewById(R.id.text_request_type);
            request_name = (TextView)v.findViewById(R.id.text_request_name);
            request_date = (TextView)v.findViewById(R.id.text_request_date);
            request_total = (TextView)v.findViewById(R.id.text_request_total);
            button_approve = (Button)v.findViewById(R.id.button_approve);
            button_reject = (Button)v.findViewById(R.id.button_reject);

            button_confirm = (Button)v.findViewById(R.id.button_confirm);
            button_cancel = (Button)v.findViewById(R.id.button_cancel);
            text_details = (EditText)v.findViewById(R.id.text_details);

            button_confirm.setVisibility(View.GONE);
            button_cancel.setVisibility(View.GONE);
            text_details.setVisibility(View.GONE);

            button_approve.setOnClickListener((view -> {
                int position = getAdapterPosition();
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Approved", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }));

            button_reject.setOnClickListener((view -> {
                /*int position = getAdapterPosition();
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Rejected", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();*/
                button_confirm.setVisibility(View.VISIBLE);
                button_cancel.setVisibility(View.VISIBLE);
                text_details.setVisibility(View.VISIBLE);
            }));

            button_cancel.setOnClickListener((view -> {
                button_confirm.setVisibility(View.GONE);
                button_cancel.setVisibility(View.GONE);
                text_details.setVisibility(View.GONE);
            }));

            button_confirm.setOnClickListener((view -> {
                if(TextUtils.isEmpty(text_details.getText())){
                    text_details.setError("Please state why this request is rejected");
                }
                else{
                    int position = getAdapterPosition();
                    mDataset.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mDataset.size());
                    Snackbar.make(view, "Request Rejected: " + text_details.getText().toString(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }));
        }
    }

    public HomeListAdapter(ArrayList<RequestHeader> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_home, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.request_id.setText(mDataset.get(position).getRequest_header_id());
        holder.request_name.setText(mDataset.get(position).getName());
        holder.request_type.setText(mDataset.get(position).getType());
        holder.request_total.setText(mDataset.get(position).getTotal());
        holder.request_date.setText(mDataset.get(position).getDate());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
