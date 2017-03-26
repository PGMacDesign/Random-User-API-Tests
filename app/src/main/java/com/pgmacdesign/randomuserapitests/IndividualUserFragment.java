package com.pgmacdesign.randomuserapitests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgmacdesign.pgmacutilities.nonutilities.PGMacUtilitiesConstants;
import com.pgmacdesign.pgmacutilities.utilities.DateUtilities;
import com.pgmacdesign.pgmacutilities.utilities.ImageUtilities;
import com.pgmacdesign.pgmacutilities.utilities.L;
import com.pgmacdesign.pgmacutilities.utilities.StringUtilities;

import java.util.Date;
import java.util.Locale;

/**
 * Fragment for viewing a single user (clicked on from recyclerview)
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class IndividualUserFragment extends android.support.v4.app.Fragment{

    //UI
    private ImageView individual_frag_picture;
    private RelativeLayout individual_frag_top_right_layout;
    private LinearLayout individual_frag_location_layout,
            individual_frag_phone_layout,
            individual_frag_email_layout;
    private TextView individual_frag_dob_tv, individual_frag_name_tv,
            individual_frag_address_tv, individual_frag_phone_tv,
            individual_frag_email_tv;


    public IndividualUserFragment(){}

    public static IndividualUserFragment getInstance(){
        IndividualUserFragment fragment = new IndividualUserFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_user_fragment, container, false);

        individual_frag_picture = (ImageView) view.findViewById(
                R.id.individual_frag_picture);
        individual_frag_top_right_layout = (RelativeLayout) view.findViewById(
                R.id.individual_frag_top_right_layout);
        individual_frag_location_layout = (LinearLayout) view.findViewById(
                R.id.individual_frag_location_layout);
        individual_frag_phone_layout = (LinearLayout) view.findViewById(
                R.id.individual_frag_phone_layout);
        individual_frag_email_layout = (LinearLayout) view.findViewById(
                R.id.individual_frag_email_layout);
        individual_frag_dob_tv = (TextView) view.findViewById(
                R.id.individual_frag_dob_tv);
        individual_frag_name_tv = (TextView) view.findViewById(
                R.id.individual_frag_name_tv);
        individual_frag_address_tv = (TextView) view.findViewById(
                R.id.individual_frag_address_tv);
        individual_frag_phone_tv = (TextView) view.findViewById(
                R.id.individual_frag_phone_tv);
        individual_frag_email_tv = (TextView) view.findViewById(
                R.id.individual_frag_email_tv);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        RandomUserPojo.RUResults user = null;
        try {
            user = (RandomUserPojo.RUResults) MyApplication.getDatabase()
                    .getPersistedObject(RandomUserPojo.RUResults.class);
        } catch (Exception e){
            e.printStackTrace();
            L.toast(getActivity(), Constants.STRING_UNKNOWN_ERROR);
            switchBackToList();
            return;
        }

        if(user == null){
            switchBackToList();
            return;
        }

        //Set Cell
        individual_frag_phone_tv.setText(user.getCell());

        //Set age
        String dob = user.getDob();
        try {
            Date date = DateUtilities.convertStringToDate(
                    dob, PGMacUtilitiesConstants.DATE_YYYY_MM_DD, " ", Locale.US);
            int age = DateUtilities.getAge(date);
            individual_frag_dob_tv.setText(age + " years old");
        } catch (Exception e){
            individual_frag_dob_tv.setText("Born " + dob);
        }

        //Set email
        individual_frag_email_tv.setText(user.getEmail());

        //Set Name
        RandomUserPojo.RUName nameObj = user.getName();
        String name = null;
        if(nameObj != null){
            name = nameObj.getTitle() + " " + nameObj.getFirst() + " " + nameObj.getLast();
        } else {
            name = "";
        }
        individual_frag_name_tv.setText(name);

        //Set address
        RandomUserPojo.RULocation loc = user.getLocation();
        String address = null;
        if(loc != null) {
            address = loc.getStreet() + " " + loc.getCity() + ", " + loc.getState();
        } else {
            address = "";
        }
        individual_frag_address_tv.setText(address);

        //Set image
        RandomUserPojo.RUPicture pic = user.getPicture();
        String imageUrl = pic.getImageLarge();
        if(StringUtilities.isNullOrEmpty(imageUrl)){
            imageUrl = pic.getImageMedium();
        }
        if(StringUtilities.isNullOrEmpty(imageUrl)){
            individual_frag_picture.setImageResource(R.color.Transparent);
        } else {
            ImageUtilities.setImageWithPicasso(
                    imageUrl, individual_frag_picture, R.color.Transparent);
        }
    }

    private void switchBackToList(){
        ((FragmentLink)getActivity()).setFragment(Constants.FRAGMENT_LIST_USERS);
    }
}
