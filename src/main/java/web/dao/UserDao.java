package web.dao;

import org.hibernate.collection.internal.PersistentList;
import org.springframework.stereotype.Component;
import web.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDao {
    private int COUNT_ID;
    private List<User> people;

    {
     people = new ArrayList<>();
     people.add(new User(++COUNT_ID, "Tom", "Hilfiger"));
     people.add(new User(++COUNT_ID, "Vashirone", "Constantine"));
     people.add(new User(++COUNT_ID, "Elena", "Furce"));
    }

    public List<User> index() {
        return people;
    }

    public User getUser(int Id) {
        return people.stream().filter(user -> user.getId() == Id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++COUNT_ID);
        people.add(user);
    }

    public void update(int id, User user) {
        User userById = getUser(id);
        userById.setName(user.getName());
        userById.setSurname(user.getSurname());
    }

    public void delete(int id) {
        people.removeIf(o->o.getId()==id);
    }



}
