package com.studentmarket.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface AccountDao extends CrudRepository<Account, Long> {

     /**
      * Return the user having the passed email or null if no user is found.
      *
      * @param email the user email.
      */
     public Account findByEmail(String email);

} // class UserDao
