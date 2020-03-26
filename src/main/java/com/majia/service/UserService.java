package com.majia.service;

import com.majia.dao.UserDAO;
import com.majia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User selectUser(int id){
        return userDAO.selectById(id);
    }

    public List<User> selectAll(){
        List<User> list = userDAO.selectAll();
        return list;
    }
}
