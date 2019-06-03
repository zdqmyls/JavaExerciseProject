package AccountSystem.bean;

import AccountSystem.bean.*;

public class Session {
    private static Users users;// Users对象属性

    public static Users getUsers() {
        return users;
    }

    public static void setUsers(Users users) {
        Session.users = users;
    }
}
