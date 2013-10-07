package com.gdg.miagegi.mondial2014;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.gdg.miagegi.mondial2014.adapter.*; 
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.view.GravityCompat;
 
public class MainActivity extends SherlockFragmentActivity {
 
    // Declare Variable
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    MenuListAdapter mMenuAdapter;
    String[] title;
    int[] icon;
    Fragment mNewsFragment = new NewsFragment();
    Fragment mVideosFragment = new VideosFragment();
    Fragment mCalendrierFragment = new CalendrierFragment();
    Fragment mClassementFragment = new ClassementFragment();
    Fragment mStatsFragment = new StatsFragment();
    Fragment mEquipesFragment = new EquipesFragment();
    Fragment mStadesFragment = new StadesFragment();
    Fragment mPalmaresFragment = new PalmaresFragment();
    Fragment mSaviezVousFragment = new SaviezVousFragment();
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        
        title = new String[] { "News", "Videos",
                "Calendrier", "Classement", "Stats", "Equipes",
                "Stades", "Palmarès", "Le Saviez-Vous ?"};
 
        icon = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, 
        		R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
        		R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
 
        // Set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
 
        // Pass results to MenuListAdapter Class
        mMenuAdapter = new MenuListAdapter(this, title, icon);
 
        // Set the MenuListAdapter to the ListView
        mDrawerList.setAdapter(mMenuAdapter);
 
        // Capture button clicks on side menu
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
 
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
 
            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(view);
            }
 
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
            }
        };
 
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }
 
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if (item.getItemId() == android.R.id.home) {
 
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
 
        return super.onOptionsItemSelected(item);
    }
 
   
    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            selectItem(position);
        }
    }
 
    private void selectItem(int position) {
 
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Locate Position
        switch (position) {
        case 0:
            ft.replace(R.id.content_frame, mNewsFragment);
            break;
        case 1:
            ft.replace(R.id.content_frame, mVideosFragment);
            break;
        case 2:
            ft.replace(R.id.content_frame, mCalendrierFragment);
            break;
        case 3:
            ft.replace(R.id.content_frame, mClassementFragment);
            break;
        case 4:
            ft.replace(R.id.content_frame, mStatsFragment);
            break;
        case 5:
            ft.replace(R.id.content_frame, mEquipesFragment);
            break;
        case 6:
            ft.replace(R.id.content_frame, mStadesFragment);
            break;
        case 7:
            ft.replace(R.id.content_frame, mPalmaresFragment);
            break;
        case 8:
            ft.replace(R.id.content_frame, mSaviezVousFragment);
            break;
            
        }
        ft.commit();
        mDrawerList.setItemChecked(position, true);
       
        mDrawerLayout.closeDrawer(mDrawerList);
        title = new String[] { "News", "Videos",
                "Calendrier", "Classement", "Stats", "Equipes",
                "Stades", "Palmarès", "Le Saviez-Vous ?"};
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
	