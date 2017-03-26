package com.pgmacdesign.randomuserapitests;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentLink{

    private int container;
    private int whichFragmentActive;
    private Fragment activeFragment;

    private IndividualUserFragment individualUserFragment;
    private ListUsersFragment listUsersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        setFragment(Constants.FRAGMENT_LIST_USERS);
    }

    private void initVariables(){
        container = R.id.main_layout;
    }

    private void initFragments(){
        individualUserFragment = IndividualUserFragment.getInstance();
        listUsersFragment = ListUsersFragment.getInstance();
    }

    @Override
    public void setFragment(int whichFragment) {
        if(individualUserFragment == null || listUsersFragment == null){
            initFragments();
        }

        FragmentManager manager = getSupportFragmentManager();
        for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
            manager.popBackStack();
        }

        //Fragment Transaction
        FragmentTransaction transaction = manager.beginTransaction();
        if(whichFragment == Constants.FRAGMENT_INDIVIDUAL_USER){
            whichFragmentActive = Constants.FRAGMENT_INDIVIDUAL_USER;
            activeFragment = individualUserFragment;
        } else {
            whichFragmentActive = Constants.FRAGMENT_LIST_USERS;
            activeFragment = listUsersFragment;
        }
        transaction.replace(container, activeFragment);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();

        //Update the onResume here for each fragment upon loading
        this.activeFragment.onStart();
    }

    @Override
    public void onBackPressed() {
        if(whichFragmentActive == Constants.FRAGMENT_INDIVIDUAL_USER){
            setFragment(Constants.FRAGMENT_LIST_USERS);
        } else {
            super.onBackPressed();
        }

    }
}
