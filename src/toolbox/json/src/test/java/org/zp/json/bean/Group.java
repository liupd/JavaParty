package org.zp.json.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Group {
    @JSONField(ordinal = 1)
    private long id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private List<User> users = new ArrayList<User>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Group))
            return false;
        if (this.id == ((Group) obj).getId() && this.name.equals(((Group) obj).getName()))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}