package com.springcookbook.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcookbook.model.User;

@Repository
@Transactional
public class UserRepository {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public void add(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	public User findById(Long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public List<User> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		return criteria.list();
	}

}
