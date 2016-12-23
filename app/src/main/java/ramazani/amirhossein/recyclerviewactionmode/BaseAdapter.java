package ramazani.amirhossein.recyclerviewactionmode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    private SparseBooleanArray selectedItems;

    protected List<T> mData;
    protected Context mContext;
    protected ClickListener mClickListener;

    public BaseAdapter(Context context, List<T> data, ClickListener listener) {
        selectedItems = new SparseBooleanArray();
        mClickListener = listener;
        mContext = context;
        mData = data;
    }

    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void selectItem(int position) {
        if (!selectedItems.get(position, false)) {
            selectedItems.put(position, true);
        }

        notifyItemChanged(position);
    }

    public void deSelectItem(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        }

        notifyItemChanged(position);
    }

    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    /*****************************************************************************************************************/

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    private void removeRange(int positionStart, int itemCount) {
        for (int i = 0; i < itemCount; ++i) {
            mData.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    public void removeItems(List<Integer> positions) {
        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });
        while (!positions.isEmpty()) {
            if (positions.size() == 1) {
                removeItem(positions.get(0));
                positions.remove(0);
            } else {
                int count = 1;
                while (positions.size() > count && positions.get(count).equals(positions.get(count - 1) - 1)) {
                    ++count;
                }

                if (count == 1) {
                    removeItem(positions.get(0));
                } else {
                    removeRange(positions.get(count - 1), count);
                }

                for (int i = 0; i < count; ++i) {
                    positions.remove(0);
                }
            }
        }
    }

    /******************************************************************************************************/

    @Override
    public int getItemCount() {
        return mData.size();
    }

}