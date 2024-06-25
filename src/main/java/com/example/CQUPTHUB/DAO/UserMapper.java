package com.example.CQUPTHUB.DAO;

import com.example.CQUPTHUB.POJO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface UserMapper {

    @Select("select * from edu_user where user_name = #{username}")
    User FindUserByUsername(@Param("username") String username);

    @Select("select user_password from edu_user where user_name = #{username}")
    String FindPasswordByUsername(@Param("username") String username);

    @Select("select user_password from edu_user where user_email = #{email}")
    String FindPasswordEmailByEmail(@Param("email") String email);

    @Select("select user_password from edu_user where user_phone = #{phone_number}")
    String FindPasswordByPhoneNumber(@Param("phone_number") String phone_number);

    @Select("select * from edu_user where user_email=#{email}")
    User FindUserByEmail(@Param("email") String email);

    @Select("select * from edu_user where user_phone=#{phone_number}")
    User FindUserByPhoneNumber(@Param("phone_number") String phone_number);

    @Insert("INSERT INTO edu_user (user_name, user_password, user_email, user_phone, registration_time, user_nickname, " +
            "picture_url, user_role, user_balance) " +
            "VALUES (#{userName}, #{passWord}, #{email}, #{phoneNumber}, #{registrationDate}, " + "#{nickName} ," +
            "#{pictureUrl}, #{role}, #{balance})")
    void registerUser(User user);

    @Select("SELECT COUNT(*) > 0 FROM edu_user WHERE user_name = #{username}")
    boolean existsByUsername(String username);

    @Select("SELECT * FROM edu_user WHERE user_name = #{username} and user_email != #{email} and " +
            "user_phone != #{phoneNumber}")
    User existsByUsername2(User user);

    @Select("SELECT COUNT(*) > 0 FROM edu_user WHERE user_email = #{email}")
    boolean existsByEmail(String email);

    @Select("SELECT COUNT(*) > 0 FROM edu_user WHERE user_phone = #{phoneNumber}")
    boolean existsByPhoneNumber(String phoneNumber);

    @Update("UPDATE edu_user SET user_password = #{password} where user_id = #{userid}")
    void updatePassword(@Param("password") String password, @Param("userid") Long userid);

    @Update("UPDATE edu_user SET user_phone = #{phoneNumber}, user_nickname = #{nickname}, " +
            "picture_url = #{profilePictureUrl} " +
            "WHERE user_id = #{id}")
    void updateUser(User user);

    @Update("UPDATE  edu_user SET user_balance = #{balance} WHERE user_id = #{userid}")
    void updateBalance(double balance , Long userid);

    @Select("SELECT user_balance from edu_user where user_id = #{userid}")
    double GetBalance(Long userid);

    //更新昵称
    @Update("UPDATE edu_user SET user_nickname = #{nickname} WHERE user_id = #{userid}")
    void updateNickName(String nickname , Long userid);

    //更新电话
    @Update("UPDATE  edu_user SET user_phone = #{phoneNumber} WHERE user_id = #{userid}")
    void updatePhoneNumber(String phoneNumber , Long userid);
}
