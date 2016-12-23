package ramazani.amirhossein.recyclerviewactionmode;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by amirhossein on 12/23/2016.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public CardView mCardView;
    public ClickListener mListener;
    public Context mContext;

    public BaseViewHolder(View itemView, ClickListener listener, Context context) {
        super(itemView);
        this.mListener = listener;
        this.mContext = context;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mCardView = (CardView) itemView.findViewById(R.id.card);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClicked(getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mListener != null) {
            return mListener.onItemLongClicked(getLayoutPosition());
        }

        return false;
    }
}
