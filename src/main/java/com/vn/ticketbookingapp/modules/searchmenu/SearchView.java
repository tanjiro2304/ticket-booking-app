package com.vn.ticketbookingapp.modules.searchmenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vn.ticketbookingapp.mvputils.BaseView;
import com.vn.ticketbookingapp.templates.SecondaryTemplate;


@Route(value = "/search-trains",layout = SecondaryTemplate.class)
public class SearchView extends BaseView<SearchPresenter> {

    private TextField source;
    private TextField destination;

    private Button searchButton;
    @Override
    protected void init() {
        source = new TextField();
        destination = new TextField();
        searchButton = new Button();
    }




}
