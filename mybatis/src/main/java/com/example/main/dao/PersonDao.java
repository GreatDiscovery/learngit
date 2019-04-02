package com.example.main.dao;

import com.example.main.domain.Person;

/**
 * @author gavin
 * @date 2019/4/1 19:08
 */
public interface PersonDao {
    Person queryPersonById(Long id);
}
