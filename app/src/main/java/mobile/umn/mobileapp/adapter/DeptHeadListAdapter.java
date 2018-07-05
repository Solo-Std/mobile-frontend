package mobile.umn.mobileapp.adapter;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import entity.MasterCard;
import lombok.NonNull;
import mobile.umn.mobileapp.R;
import mobile.umn.mobileapp.model.RequestHeaderRestClient;

/**
 * Created by User on 24/05/2018.
 */

public class DeptHeadListAdapter extends RecyclerView.Adapter<DeptHeadListAdapter.ViewHolder> {
    private List<MasterCard> ongoingmasterCards;
    String position;
    View.OnClickListener onClickListener;
    View view;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView request_id;
        public TextView request_type;
        public TextView request_name;
        public TextView request_date;
        public TextView request_price;

        public Button button_approve;
        public Button button_reject;

        public ViewHolder(CardView v) {
            super(v);
            request_id = (TextView) v.findViewById(R.id.text_ongoingrequest_id);
            request_type = (TextView) v.findViewById(R.id.text_ongoingrequest_type);
            request_name = (TextView) v.findViewById(R.id.text_ongoingrequest_name);
            request_date = (TextView) v.findViewById(R.id.text_ongoingrequest_date);
            request_price = (TextView) v.findViewById(R.id.text_ongoingrequest_price);
            button_approve = (Button) v.findViewById(R.id.button_approve);
            button_reject = (Button) v.findViewById(R.id.button_reject);

            button_approve.setOnClickListener((view -> {
                int position = getAdapterPosition();
                // mDataset.remove(position);
                notifyItemRemoved(position);
                // notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Approved", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                new HttpRequestPut(Long.valueOf(request_id.getText().toString()),"dh",true);
            }));

            button_reject.setOnClickListener((view -> {
                int position = getAdapterPosition();
                //mDataset.remove(position);
                notifyItemRemoved(position);
                //notifyItemRangeChanged(position, mDataset.size());
                Snackbar.make(view, "Request Rejected", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                new HttpRequestPut(Long.valueOf(request_id.getText().toString()),"dh",false);
            }));

            if (position.equals("Department Head")) {
                button_approve.setVisibility(View.VISIBLE);
                button_reject.setVisibility(View.VISIBLE);
            } else {
                button_approve.setVisibility(View.INVISIBLE);
                button_reject.setVisibility(View.INVISIBLE);
            }
        }
    }

    public DeptHeadListAdapter(List<MasterCard> masterCards, String position, View.OnClickListener onClickListener) {
        ongoingmasterCards = masterCards;
        this.position = position;
        this.onClickListener = onClickListener;
//        System.out.println("Price:"+this.ongoingmasterCards.get(0).);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DeptHeadListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        view = parent;
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dh_card_view_home, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.request_date.setText(masterCards.get(position).getDate());
        System.out.println("POSITION:" + position);
        holder.request_id.setText(Integer.toString(ongoingmasterCards.get(position).getRequest_header_id()));
        holder.request_price.setText(Integer.toString(ongoingmasterCards.get(position).getGrand_total()));
        holder.request_name.setText(ongoingmasterCards.get(position).getRequested_by());
        holder.request_date.setText(ongoingmasterCards.get(position).getRequest_date());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ongoingmasterCards.size();
        //return 0;
    }

    private class HttpRequestPut extends AsyncTask<Void, Void, Void> {
        Long request_id;
        String division;
        boolean approve;

        HttpRequestPut(Long request_id, String division, boolean approve) {
            this.request_id = request_id;
            this.division = division;
            this.approve = approve;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RequestHeaderRestClient rhrc = new RequestHeaderRestClient();
            rhrc.approve(request_id, division, approve);
            return voids[0];
        }

        @Override
        protected void onPostExecute(Void v) {
            onClickListener.onClick(view);
        }
    }
}
