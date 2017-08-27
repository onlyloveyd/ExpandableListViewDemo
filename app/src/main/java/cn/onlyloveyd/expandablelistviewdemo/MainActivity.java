package cn.onlyloveyd.expandablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("expandable menu");
        getMenuInflater().inflate(R.menu.expandable_menu, menu);////绑定菜单xml
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(this, "this is first menu item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(this, "this is second menu item", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<YEAdapter.GroupItem> mGroupItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            YEAdapter.GroupItem item = new YEAdapter.GroupItem();
            item.title = "this is " + i + "nd group item";
            List<YEAdapter.ChildItem> mChildItems = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                YEAdapter.ChildItem item1 = new YEAdapter.ChildItem();
                item1.message = "this is " + i + "nd group item's " + j + "nd child";
                mChildItems.add(item1);
            }
            item.mChildList = mChildItems;
            mGroupItems.add(item);
        }


        final ExpandableListView expandableListView = (ExpandableListView) this.findViewById(R.id.expandable_list);

        YEAdapter yeAdapter = new YEAdapter(this, mGroupItems);
        expandableListView.setAdapter(yeAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, groupPosition + "nd group's " + childPosition + "nd Item is clicked!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this, groupPosition + "nd group is clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, "the " + groupPosition + "nd group is collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "the " + groupPosition + "nd group is expanded", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD)
                {
                    expandableListView.showContextMenu();   ///////子项显示菜单
                    return true;
                }
                return false;
            }
        });
        registerForContextMenu(expandableListView);


    }
}
