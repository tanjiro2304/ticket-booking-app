package com.vn.ticketbookingapp.searchmenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.mvputils.BaseView;

import javax.annotation.PostConstruct;


@Route("/search-trains")
public class SearchView extends BaseView<SearchPresenter> {

    private TextField source;
    private TextField destination;

    private Button searchButton;
    @Override
    @PostConstruct
    protected void init() {
        source = new TextField();
        destination = new TextField();
        searchButton = new Button();

    }




}
