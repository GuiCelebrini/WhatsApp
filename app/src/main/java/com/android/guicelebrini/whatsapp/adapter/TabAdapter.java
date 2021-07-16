package com.android.guicelebrini.whatsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.guicelebrini.whatsapp.fragment.ChatsFragment;
import com.android.guicelebrini.whatsapp.fragment.ContactsFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = {"CONVERSAS", "CONTATOS"};

    public TabAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0 :
                fragment = new ChatsFragment();
                break;
            case 1 :
                fragment = new ContactsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[ position ];
    }
}
