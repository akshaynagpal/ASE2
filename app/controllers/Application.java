package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import models.Person;
import play.data.FormFactory;
import javax.inject.Inject;
import java.sql.*;
import java.util.List;

import static play.libs.Json.*;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    @Transactional
    public Result addPerson() throws ClassNotFoundException {
        Person person = formFactory.form(Person.class).bindFromRequest().get();

        String myDriver = "com.mysql.jdbc.Driver";
        String myURL = "jdbc:mysql://localhost:3306/users";
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL, "root", "1234");
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO user_table (email, name, password)"+"VALUES ('"+person.email+"','"+ person.name+"','"+person.password+"')");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //JPA.em().persist(person);
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getPersons() {
        List<Person> persons = (List<Person>) JPA.em().createQuery("select p from Person p").getResultList();
        return ok(toJson(persons));
    }
}
