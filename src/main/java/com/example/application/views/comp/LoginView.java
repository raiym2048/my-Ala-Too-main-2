package com.example.application.views.comp;

import com.example.application.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | Vaadin CRM")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();
    private final UserService userService;

    public LoginView(UserService userService) {
        this.userService = userService;

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("");
        System.out.println("call main\n\n");


        login.addLoginListener(e -> auth(e.getUsername(), e.getPassword())

        );
        add(new H1("Vaadin CRM"), login);
        add(createRegistrationLink());
    }

    private void auth(String email, String password) {
        System.out.println("auth void running!\n\n");

        if (userService.auth(email, password)) {
            System.out.println("auth void running2!\n\n");

            UI.getCurrent().navigate("register");
        } else {
            System.out.println("log error 46!\n\n");

            login.setError(true);
        }
    }

    private Anchor createRegistrationLink() {
        Anchor registrationLink = new Anchor("register", "Don't have an account? Register here");
        return registrationLink;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
