package com.example.main;

import com.example.main.dao.PersonDao;
import com.example.main.domain.Person;
import com.example.mybatis.configuration.MtConfiguration;
import com.example.mybatis.session.MtSqlSession;
import com.example.mybatis.session.MtSqlSessionFactory;
import com.example.mybatis.session.MtSqlSessionFactoryBuilder;

/**
 * @author gavin
 * @date 2019/4/1 10:01
 */
public class Entry {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MtConfiguration configuration = new MtConfiguration("mybatis-config.properties");
        MtSqlSessionFactoryBuilder sqlSessionFactoryBuilder = new MtSqlSessionFactoryBuilder(configuration);
        MtSqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build();
        MtSqlSession sqlSession = sqlSessionFactory.openSession();
        PersonDao personDao = sqlSession.getMapper(PersonDao.class);
        Person person = personDao.queryPersonById(1L);
        System.out.println(person);
    }
}
