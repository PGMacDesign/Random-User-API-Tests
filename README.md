# Random-User-API-Tests

Sample application using the data returned by: https://randomuser.me/documentation

This project will utilize a single seed (In Constants class) for all data pulls 

## What I would have done with more time:

1) Pagination, for now, going to ignore it. easy enough to do though, just add an
    on scroll listener, when reaching listSize()-1, make next call and append to original
    and then notify the data set change
2) add in more customized size markers. For this app I simply included hard-coded
    margin and text sizes. I would prefer to write multiple dimen files and adjust them
    with regards to screen size
3) Add in animations. I love adding in animations (especially when they click things)
    It would be nice to add in a ripple effect for the background of the different areas
    clicked. Also, animating fields when they load would be nice.
4) Better caching, although this is caching images up until a certain point, I would
    prefer to write custom caching stuff.
5) Better UI work. Some of the names (IE 3rd one down on the list with the seed I
    used) looks wrong due to the email being stretched incorrectly.
6) Add filter options for the search. On top of the recyclerview, I would like to
    add filter options where a user can choose to see (For example) only male / female
    employees (option is written into my api calls), exclude some fields, include some
    fields, add a download option (export + email maybe?). All of these things have
    been accounted for in that they are written into my API Calls, but they are not
    hooked up at all.
7) Add horizontal swipe functionality to the recyclerview tiles
8) Put in better imageview sizing on the individual user page. Set it to fitXY by default, but it looks stretched for some people

