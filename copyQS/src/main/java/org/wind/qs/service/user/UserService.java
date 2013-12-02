package org.wind.qs.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.DateProvider;
import org.wind.qs.dao.UserDao;
import org.wind.qs.entity.User;
import org.wind.qs.service.ServiceException;

@Service("userService")
public class UserService{
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	private DateProvider dateProvider = DateProvider.DEFAULT;
	
	public User findByLoginName(String username){
		
		return userDao.findByLoginName(username);
	}
	
	public User getUser(long userId){
		return userDao.findOne(userId);
	}
	
	public List<User> getUserList(){
		return (List<User>) userDao.findAll();
	}
	
	public void updateUser(User user){
		 userDao.save(user);
	}
	
	public void registerUser(User user){
		
		encryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(dateProvider.getDate());
		user.setSalt("passwordsalt");
		userDao.save(user);
	}
	
	public void deleteUser(long id){
		if(isSuperAdmin(id)){
			log.warn("operator is trying to delete the super administrator");
			throw new ServiceException("can not delete the super administrator!");
		}
		//userDao.delete(id);
	}
	
	//like a agreement to mark super administrator
	private boolean isSuperAdmin(long id){
		return (-999 == id);
	}
	
	private void encryptPassword(User user){
	}
	
}
