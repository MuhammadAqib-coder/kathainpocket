package com.first.kathainpocket1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements OwnBusinessFragment.Listener
{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.view_pager);

        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        Viewpager2 adapter1 = new Viewpager2(manager,getLifecycle());
        viewPager.setAdapter(adapter1);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position)
            {
                switch(position)
                {
                    case 0:
                        tab.setText("Own Business");
                        break;
                    case 1:
                        tab.setText("Partnership Business");
                        break;
                    case 2:
                        tab.setText("Home Expenses");
                        break;
                }
            }
        });
        mediator.attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                switch(tab.getPosition())
                {
                    case 0:
                        new OwnBusinessFragment();
                        break;
                    case 1:
                        new PartnershipBusinessFragment();
                        break;
                    case 2:
                        new HomeExpensesFragment();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void GotoActivity()
    {
        Intent intent = new Intent(MainActivity.this,BusinessRegisterActivity.class);
        startActivity(intent);
    }

}