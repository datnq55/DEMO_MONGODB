package com.luvsoft.controllers;

import java.util.ArrayList;

import com.luvsoft.entities.Student;
import com.luvsoft.utils.MongoDBConnection;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class StudentController {

    public ArrayList<Student> fetchAll() {
        ArrayList<Student> list = new ArrayList<Student>();
        DBCollection collection = MongoDBConnection.database
                .getCollection("employee");
        DBCursor cursor = collection.find();

        while (cursor.hasNext()) {
            Student student = new Student();
            student.setName(cursor.next().get("name").toString());
            student.setId(cursor.curr().get("_id").toString());

            list.add(student);
        }
        return list;
    }
}
