package com.majia.dao;

import com.majia.entity.User;

import java.util.List;

public interface UserDAO {

    User selectById(int id);

    List<User> selectAll();
}
