package ramazani.amirhossein.recyclerviewactionmode;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amirhossein on 12/23/2016.
 */

public class RecyclerViewAdapter extends BaseAdapter<RecyclerViewAdapter.RecyclerViewHolder, String> {

    public RecyclerViewAdapter(List<String> data, Context context, ClickListener listener) {
        super(context, data, listener);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view, mClickListener, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (isSelected(position)) {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.cardViewBackgroundSelected));
        } else {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.cardViewBackgroundNotSelected));
        }

        holder.update(mData.get(position));
    }

    public class RecyclerViewHolder extends BaseViewHolder {

        private TextView txtTitle;

        public RecyclerViewHolder(View itemView, ClickListener listener, Context context) {
            super(itemView, listener, context);
            txtTitle = (TextView) itemView.findViewById(R.id.txtCardTitle);
        }

        public void update(String text) {
            txtTitle.setText(text);
        }
    }
}
