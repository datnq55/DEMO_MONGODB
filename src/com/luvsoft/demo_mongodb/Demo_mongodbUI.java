package com.luvsoft.demo_mongodb;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.luvsoft.controllers.StudentController;
import com.luvsoft.entities.Student;
import com.luvsoft.utils.MongoDBConnection;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("demo_mongodb")
public class Demo_mongodbUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Demo_mongodbUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // Connect to mongoDB database
        MongoDBConnection dbconnection = new MongoDBConnection();
        dbconnection.connectMongoDB();

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        if (MongoDBConnection.database == null) {
            layout.addComponent(new Label("Connection failed!......"));
        }

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                String collectionString = "";
                // View all elements of database
                StudentController studentController = new StudentController();
                ArrayList<Student> list = studentController.fetchAll();
                int size = list == null ? 0 : list.size();
                if (size > 0) {
                    Student student;
                    for (int i = 0; i < size; i++) {
                        student = list.get(i);
                        collectionString += "ID: " + student.getId()
                                + " | Name: " + student.getName() + "\n";
                    }
                } else {
                    collectionString = "Could not find any student in database!...";
                }

                layout.addComponent(new Label(collectionString));
            }
        });
        layout.addComponent(button);
    }

}