package com.vn.ticketbookingapp.mvputils;

public interface View<P extends Presenter> {
    P getPresenter();
}
