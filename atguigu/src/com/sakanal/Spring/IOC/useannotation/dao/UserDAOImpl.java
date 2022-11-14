package com.sakanal.Spring.IOC.useannotation.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public void add() {
        System.out.println("UserDAOImpl add.........");
    }
}
