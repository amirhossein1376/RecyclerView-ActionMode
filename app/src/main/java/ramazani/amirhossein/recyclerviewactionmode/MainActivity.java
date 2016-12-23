package ramazani.amirhossein.recyclerviewactionmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListener {

    private List<String> data;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager manager;

    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            data.add("Some Title Or Description " + i);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(data, this, this);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }

    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        int count = adapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }


    @Override
    public void onItemClicked(int position) {
        if (actionMode != null) {
            toggleSelection(position);
        } else {
            //Item Clicked!!! Do Some work
        }

    }

    @Override
    public boolean onItemLongClicked(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }

        toggleSelection(position);
        return true;
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_selected, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_delete:
                    adapter.clearSelection();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelection();
            actionMode = null;
        }
    }

}
