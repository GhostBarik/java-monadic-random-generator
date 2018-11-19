package domain;

import java.util.List;

public final class Group {
    private final String name;
    private final List<User> users;

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
