package ramazani.amirhossein.recyclerviewactionmode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amirhossein on 12/23/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<String> mResources;
    private Context mContext;

    public RecyclerViewAdapter(List<String> resources, Context context) {
        mResources = resources;
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.update(mResources.get(position));
    }

    @Override
    public int getItemCount() {
        return mResources.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtCardTitle);
        }

        public void update(String text) {
            txtTitle.setText(text);
        }
    }
}
