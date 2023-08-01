package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.dashboard.DashboardView;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        createHeader();
        createMenu();
    }

    private void createHeader() {
        HorizontalLayout header = new HorizontalLayout();

        H1 title = new H1("Vaadin CRM");
        title.addClassNames("text-l", "m-m");
        DrawerToggle drawerToggle = new DrawerToggle();
        Button logout = new Button("Logout");
        logout.addClickListener(e -> securityService.logout());

        header.add(drawerToggle, title, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.expand(title);
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createMenu() {
        VerticalLayout menu = new VerticalLayout();

        menu.add(new RouterLink("Home", ListView.class));
        menu.add(new RouterLink("Dashboard", DashboardView.class));

        addToDrawer(menu);
    }

}
