package com.pgmacdesign.randomuserapitests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgmacdesign.pgmacutilities.adaptersandlisteners.CustomClickCallbackLink;
import com.pgmacdesign.pgmacutilities.adaptersandlisteners.CustomClickListener;
import com.pgmacdesign.pgmacutilities.utilities.ImageUtilities;
import com.pgmacdesign.pgmacutilities.utilities.StringUtilities;

import java.util.List;

/**
 * Adapter for the recyclerview in the list users activity
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RandomUserPojo.RUResults> mListResults;
    private Context context;
    private LayoutInflater mInflater;
    private CustomClickCallbackLink clickCallbackLink;
    private CustomClickListener clickListener;

    /**
     * Constructor
     */
    public RecyclerviewAdapter(Context context, CustomClickCallbackLink clickCallbackLink){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.clickCallbackLink = clickCallbackLink;
    }

    public void setResults(List<RandomUserPojo.RUResults> mListResults){
        this.mListResults = mListResults;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_adapter_layout, parent, false);
        UserHolder holder = new UserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        RandomUserPojo.RUResults user = mListResults.get(position);
        UserHolder holder = (UserHolder) holder1;

        if(user == null){
            return;
        }

        RandomUserPojo.RUName nameObj = user.getName();
        if(nameObj != null) {
            String name = nameObj.getTitle() + " "
                    + nameObj.getFirst() + " "
                    + nameObj.getLast();
            holder.recycler_adapter_tv1.setText(name);
        } else {
            holder.recycler_adapter_tv1.setText("");
        }

        String email = user.getEmail();
        if(!StringUtilities.isNullOrEmpty(email)){
            email = email.trim();
            holder.recycler_adapter_tv2.setText(email);
        } else {
            holder.recycler_adapter_tv2.setText("");
        }

        RandomUserPojo.RUPicture picture = user.getPicture();
        boolean setTransparent = false;
        if(picture != null){
            String imageUrl = null;
            imageUrl = picture.getImageThumbnail();
            if(StringUtilities.isNullOrEmpty(imageUrl)){
                imageUrl = picture.getImageMedium();
            }
            if(StringUtilities.isNullOrEmpty(imageUrl)){
                imageUrl = picture.getImageLarge();
            }
            if(StringUtilities.isNullOrEmpty(imageUrl)){
                setTransparent = true;
            } else {
                ImageUtilities.setCircularImageWithPicasso(imageUrl,
                        holder.recycler_adapter_iv, R.color.Transparent, context);
            }
        } else {
            setTransparent = true;
        }
        if(setTransparent){
            holder.recycler_adapter_iv.setImageResource(R.color.Transparent);
        }

        this.clickListener = new CustomClickListener(clickCallbackLink,
                Constants.TAG_RECYCLERVIEW_CLICK, user, position);
        holder.recycler_adapter_main_layout.setOnClickListener(this.clickListener);
    }

    @Override
    public int getItemCount() {
        try {
            return mListResults.size();
        } catch (Exception e){
            return 0;
        }
    }



    class UserHolder extends RecyclerView.ViewHolder {

        private RelativeLayout recycler_adapter_main_layout;
        private ImageView recycler_adapter_iv;
        private TextView recycler_adapter_tv1, recycler_adapter_tv2;

        UserHolder(View itemView){
            super(itemView);
            recycler_adapter_iv = (ImageView) itemView.findViewById(
                    R.id.recycler_adapter_iv);
            recycler_adapter_main_layout = (RelativeLayout) itemView.findViewById(
                    R.id.recycler_adapter_main_layout);
            recycler_adapter_tv1 = (TextView) itemView.findViewById(
                    R.id.recycler_adapter_tv1);
            recycler_adapter_tv2 = (TextView) itemView.findViewById(
                    R.id.recycler_adapter_tv2);
        }


    }
}
