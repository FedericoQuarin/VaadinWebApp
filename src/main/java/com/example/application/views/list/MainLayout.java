package com.example.application.views.list;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        VerticalLayout menu = new VerticalLayout();

        menu.add(new RouterLink("Home", ListView.class));

        addToDrawer(menu);

        H1 title = new H1("Contact Page");
        title.getStyle().set("padding", "20px");
        addToNavbar(title);
    }

}
