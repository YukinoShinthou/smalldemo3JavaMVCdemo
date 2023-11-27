package kendrick.Xml.DAO;


import jakarta.transaction.Transactional;
import kendrick.Xml.Person.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
    private  SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    private int P_C = 1;
//    private static String connectionURL = "jdbc:postgresql://localhost:5432/first_db";
//    private static String USERNAME = "postgres";
//    private static String PASSWORD = "mirziz123";

//    private static String SELECT="SELECT * FROM person";
//    private static String NEW="INSERT INTO person VALUES()";
//    private static String UPDATE="SELECT * FROM person";
//    private static String DELETE="SELECT * FROM person";

//    private static Connection connection;
//    private static Statement statement;
//    static{
//        try{
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try{
//            connection = DriverManager.getConnection(connectionURL,USERNAME,PASSWORD);
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//    }


//    private final List<Person> personList;
//
//    {
//        personList = new ArrayList<>();
//
//        personList.add(new Person(P_C++,"Tom","Tom@mail.ru",19));
//
//        personList.add(new Person(P_C++,"Kelly","Kelly@mail.ru",15));
//
//        personList.add(new Person(P_C++,"Machine gun","Macho@mail.ru",7));
//    }

    //WE COULD USE (new PersonMapper()) instead of BeanPropertyRowMapper<>() which is usefull when the columns name are the same as the name of fields of object
    @Transactional()
    public List<Person> index() {

        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("Select p from Person p" , Person.class).getResultList();

        return people;

//    return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
//        List<Person> people = new ArrayList<>();
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(ALL);
//
//            while(resultSet.next()){
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setEmail(resultSet.getString("email"));
//                person.setAge(resultSet.getInt("age"));
//
//                people.add(person);
//            }
//
//        }catch (SQLException throwables){
//            throwables.printStackTrace();
//        }
//
//
//
//
//        //return personList;
//        return people;
    }

    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();

        return session.get(Person.class,id);
//        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
//                new Object[]{id},  new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//        Person person = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM person WHERE id = ?"
//            );
//            preparedStatement.setInt(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            person = new Person();
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setEmail(resultSet.getString("email"));
//            person.setAge(resultSet.getInt("age"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return person;
//        //return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

//    @Override
//    public String toString() {
//        return "PersonDAO{" +
//                "personList=" + personList +
//                '}';
//    }
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();

        session.save(person);
//        List<Person> people = new ArrayList<>();

//        jdbcTemplate.update("INsert INTo person(name,email,age) VALUES(?,?,?)" ,
//                person.getName(), person.getEmail(), person.getAge());

//        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(1,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                preparedStatement.setString(1,people.get(i).getName());
//                preparedStatement.setString(2,people.get(i).getEmail());
//                preparedStatement.setInt(3,people.get(i).getAge());
//
//
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });

//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "Insert INTO person VALUES(1,?,?,?);"
//            );
//
//            preparedStatement.setString(1,person.getName());
//            preparedStatement.setString(2,person.getEmail());
//            preparedStatement.setInt(3,person.getAge());
//
//            preparedStatement.executeUpdate();
////        Statement statement = connection.createStatement();
////        statement.executeUpdate("INSERT INTO person VALUES ("
////                + 1 + ','
////                + "'" + person.getName() + "'" + ','
////                + "'" +  person.getEmail() + "'" + ','
////                + person.getAge() +
////                ')');
//    }catch (SQLException throwables){
//        throwables.printStackTrace();
//    }
//       person.setId(P_C++);
//       personList.add(person);
    }
    @Transactional
    public void update(int id, Person person){
        Session session = sessionFactory.getCurrentSession();

        Person new_person = session.get(Person.class, id);
        new_person.setName(person.getName());
        new_person.setAge(person.getAge());
        new_person.setEmail(person.getEmail());
//        jdbcTemplate.update("UPDATE person SET name=? , email=?, age=? where id = ?" , person.getName() , person.getEmail() , person.getAge(),id);

//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                "UPDATE person SET name=? , email=? , age=?  Where id = ?"
//        );
//            preparedStatement.setString(1,person.getName());
//            preparedStatement.setString(2,person.getEmail());
//            preparedStatement.setInt(3,person.getAge());
//            preparedStatement.setInt(4,id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
////        Person updatedPerson = show(id);
////        updatedPerson.setAge(person.getAge());
////        updatedPerson.setEmail((person.getEmail()));
////        updatedPerson.setName(person.getName());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();


        session.delete(session.get(Person.class,id));
//        jdbcTemplate.update("Delete from person where id = ?" , id);
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "DELETE FROM person WHERE id = ?;"
//            );
//            preparedStatement.setInt(1,id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
////        personList.removeIf(person -> person.getId() == id);
    }

}
