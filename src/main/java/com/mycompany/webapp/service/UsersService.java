package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.User;

@Service
public class UsersService {

	@Autowired
	private UsersDao usersDao;
	
	public void join(User user) {
		usersDao.insert(user);
		
	}
	
	public String login(User user) {
		User dbUser = usersDao.selectByUid(user.getUid());
		if(dbUser == null) { 
			return "wrongUid";   // 아이디가 디비에 없는 유저이다. 
		}else { 
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			boolean result = bpe.matches(user.getUpassword(), dbUser.getUpassword());
			if(result == false) {
				return "wrongUpassword"; // 비번이 틀렸다. 
			}
		}
		return "success"; // 로그인 성공 
	}
}
