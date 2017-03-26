package com.pgmacdesign.randomuserapitests;

/**
 * Link to communicate between activity and fragments
 * Created by PatrickSSD2 on 3/25/2017.
 */
public interface FragmentLink {

    /**
     * Set fragment to switch to
     * @param whichFragment Will either be:
     *                      {@link Constants#FRAGMENT_LIST_USERS} or
     *                      {@link Constants#FRAGMENT_INDIVIDUAL_USER}
     */
    public void setFragment(int whichFragment);

}
