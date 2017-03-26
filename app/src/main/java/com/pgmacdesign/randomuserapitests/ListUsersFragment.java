package com.pgmacdesign.randomuserapitests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgmacdesign.pgmacutilities.adaptersandlisteners.CustomClickCallbackLink;
import com.pgmacdesign.pgmacutilities.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmacutilities.utilities.L;
import com.pgmacdesign.pgmacutilities.utilities.ProgressBarUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fragment to house the recyclerview and the data to fill it
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class ListUsersFragment extends android.support.v4.app.Fragment implements
        CustomClickCallbackLink, OnTaskCompleteListener,
        SwipeRefreshLayout.OnRefreshListener {

        //UI
    private RecyclerView recycler_view;
    private SwipeRefreshLayout swipe_refresh_layout;

    //Vars
    private List<RandomUserPojo.RUResults> listUsers;
    private RecyclerviewAdapter adapter;


    public ListUsersFragment(){}

    public static ListUsersFragment getInstance(){
        ListUsersFragment fragment = new ListUsersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_users_fragment, container, false);

        listUsers = new ArrayList<>();
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipe_refresh_layout = (SwipeRefreshLayout) view.findViewById(
                R.id.swipe_refresh_layout);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.adapter = new RecyclerviewAdapter(getActivity(), this);
        this.recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler_view.setAdapter(adapter);
        this.swipe_refresh_layout.setOnRefreshListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        if(swipe_refresh_layout == null){
            return; //Not loaded yet
        }
        swipe_refresh_layout.setRefreshing(true);
        APICalls.getUsers(this, 0);
        ProgressBarUtilities.showSVGProgressDialog(getActivity());
    }

    @Override
    public void onTaskComplete(Object o, int i) {
        swipe_refresh_layout.setRefreshing(false);
        ProgressBarUtilities.dismissProgressDialog();
        switch (i){
            case Constants.TAG_NO_NETWORK_CONNECTION:
                //No web connectivity, make toast
                String noConnection = null;
                try {
                    noConnection = (String) o;
                } catch (Exception e){
                    noConnection = Constants.STRING_NO_CONNECTIVITY;
                }
                L.Toast(getActivity(), noConnection);
                break;

            case Constants.TAG_RETROFIT_CALL_FAILURE:
                //Call failed, make toast
                String callFailed = null;
                try {
                    callFailed = (String) o;
                } catch (Exception e){
                    callFailed = Constants.STRING_UNKNOWN_ERROR;
                }
                L.Toast(getActivity(), callFailed);
                break;

            case Constants.TAG_RETROFIT_CALL_SUCCESS:
                RandomUserPojo pojo = (RandomUserPojo) o;
                //update data here.       //resultses: Not to be confused with elevensies
                RandomUserPojo.RUResults[] resultses = pojo.getResults();
                if(resultses != null){
                    this.listUsers = Arrays.asList(resultses);
                    adapter.setResults(listUsers);
                }

                break;

        }
    }

    /**
     * {@link CustomClickCallbackLink} &&
     * {@link com.pgmacdesign.pgmacutilities.adaptersandlisteners.CustomClickListener}
     */
    @Override
    public void itemClicked(Object o, Integer tag, Integer pos) {
        if(tag == Constants.TAG_RECYCLERVIEW_CLICK){
            RandomUserPojo.RUResults user = (RandomUserPojo.RUResults) o;
            if(user != null){
                MyApplication.getDatabase().persistObject(
                        RandomUserPojo.RUResults.class, user);
                //Move to other fragment
                ((FragmentLink)getActivity()).setFragment(Constants.FRAGMENT_INDIVIDUAL_USER);
            }
        }
    }

    @Override
    public void onStop() {
        ProgressBarUtilities.dismissProgressDialog();
        super.onStop();
    }
}
