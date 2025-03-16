package com.owen.mapper;

import com.owen.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

	@Select("select * from user  where user_name = #{userName}")
	User findByUserName(String userName);

	@Insert("insert into user(user_name, password) values(#{userName}, #{password})")
	void add(User user);

	@Select("select *  from user")
	List<User> findAll();
}
