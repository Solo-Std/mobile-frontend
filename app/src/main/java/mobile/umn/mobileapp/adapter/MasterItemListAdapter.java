package mobile.umn.mobileapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.R;

/**
 * Created by user on 5/17/2018.
 */

public class MasterItemListAdapter extends ArrayAdapter<MasterItem> {
    private Context context;
    private List<MasterItem> masterItems;

    public MasterItemListAdapter(Context context, List<MasterItem> masterItems){
        super(context, R.layout.master_item_list_layout, masterItems);
        this.context = context;
        this.masterItems = masterItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.master_item_list_layout, parent, false);
        MasterItem masterItem = masterItems.get(position);

        TextView textviewId = (TextView) view.findViewById(R.id.textViewId);
        textviewId.setText(String.valueOf(masterItem.getItem_id()));

        TextView textviewCode = (TextView) view.findViewById(R.id.textViewCode);
        textviewCode.setText(masterItem.getItem_code());

        TextView textviewName = (TextView) view.findViewById(R.id.textViewName);
        textviewName.setText(masterItem.getItem_name());

        return view;
    }
}
