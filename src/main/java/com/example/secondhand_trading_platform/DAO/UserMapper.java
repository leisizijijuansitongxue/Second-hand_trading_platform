package com.example.secondhand_trading_platform.DAO;

import com.example.secondhand_trading_platform.POJO.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where username=#{username}")
    User FindUserByUsername(@Param("username") String username);

    @Select("select users.password from users where username = #{username}")
    String FindPasswordByUsername(@Param("username") String username);

    @Select("select users.password from users where email = #{email}")
    String FindPasswordEmailByEmail(@Param("email") String email);

    @Select("select users.password from users where phone_number = #{phone_number}")
    String FindPasswordByPhoneNumber(@Param("phone_number") String phone_number);

    @Select("select * from users where email=#{email}")
    User FindUserByEmail(@Param("email") String email);

    @Select("select * from users where phone_number=#{phone_number}")
    User FindUserByPhoneNumber(@Param("phone_number") String phone_number);

    @Insert("INSERT INTO users (username, password, email, phone_number, registration_date, first_name, last_name, " +
            "profile_picture_url, role, balance) " +
            "VALUES (#{username}, #{password}, #{email}, #{phoneNumber}, #{registrationDate}, " +
            "#{firstName}, #{lastName}, #{profilePictureUrl}, #{role}, #{balance})")
    void registerUser(User user);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE username = #{username}")
    boolean existsByUsername(String username);

    @Select("SELECT * FROM users WHERE username = #{username} and email != #{email} and " +
            "phone_number != #{phoneNumber}")
    User existsByUsername2(User user);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE email = #{email}")
    boolean existsByEmail(String email);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE phone_number = #{phoneNumber}")
    boolean existsByPhoneNumber(String phoneNumber);

    @Update("UPDATE second_hands.users SET password = #{password} where id = #{userid}")
    void updatePassword(@Param("password") String password, @Param("userid") Long userid);

    @Update("UPDATE users SET email = #{email}, phone_number = #{phoneNumber}, " +
            "first_name = #{firstName}, last_name = #{lastName}, profile_picture_url = #{profilePictureUrl} " +
            "WHERE id = #{id}")
    void updateUser(User user);

    @Update("UPDATE  users SET balance = #{balance} WHERE id = #{userid}")
    void updateBalance(double balance , Long userid);

    @Select("SELECT users.balance from users where id = #{userid}")
    double GetBalance(Long userid);
}
