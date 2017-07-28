package com.example.wzs.myapplication.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseFragment;




public class FragmentBuilder {



    private static FragmentBuilder fragmentBuilder;
    public FragmentManager fragmentManager;
    private BaseFragment fragment;

    private FragmentBuilder(){
        init();
    }
    public static synchronized FragmentBuilder getInstance(){

        if (fragmentBuilder==null)
            fragmentBuilder=new FragmentBuilder();

        return fragmentBuilder;
    }
    private void init(){
        fragmentManager = HXApplication.context.getSupportFragmentManager();
    }
    public FragmentBuilder startFragment(Class<? extends BaseFragment> fragmentClass){

        String fragmentName = fragmentClass.getSimpleName();

        fragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentName);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment ==null){
            try {
                fragment =fragmentClass.newInstance();
                transaction.add(R.id.mFram,fragment,fragmentName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        if (HXApplication.lastFragment!=null){
            transaction.hide(HXApplication.lastFragment);
        }
        transaction.show(fragment);

        transaction.addToBackStack(fragmentName);

        HXApplication.lastFragment=fragment;

        transaction.commit();
        return this;
    }

    public FragmentBuilder setParams(Bundle bundle){
        fragment.setParams(bundle);
        return this;
    }

    public BaseFragment build(){
        return fragment;
    }


}
