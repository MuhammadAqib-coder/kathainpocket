package com.first.kathainpocket1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class Viewpager2 extends FragmentStateAdapter
{
    public Viewpager2(FragmentManager fm, Lifecycle lifecycle)
    {
        super(fm,lifecycle);
    }

    @Override
    public Fragment createFragment(int position)
    {
        switch(position)
        {
            case 0:
                return new OwnBusinessFragment();
            case 1:
                return new PartnershipBusinessFragment();
            case 2:
                return new HomeExpensesFragment();
        }
        return null;
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }
}
