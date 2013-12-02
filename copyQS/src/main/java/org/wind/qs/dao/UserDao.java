package org.wind.qs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.wind.qs.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

	User findByLoginName(String loginName);
}
