package com.example.CQUPTHUB.DAO;


import com.example.CQUPTHUB.POJO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;


@Mapper
@Transactional
public interface UserMapper  {

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
            "picture_url, user_role, user_balance, user_state) " + "VALUES (#{userName}, #{userPassword}, #{userEmail}" +
            ", #{userPhone}, #{registrationTime}, #{userNickname}, #{pictureUrl}, #{userRole}, #{userBalance}, #{userState})")
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

    @Select("SELECT COUNT(*) > 0 FROM edu_user WHERE user_nickname = #{userNickname}")
    boolean existsByNickname(String userNickname);

    @Update("UPDATE edu_user SET user_phone = #{phoneNumber}, user_nickname = #{nickname}, " +
            "picture_url = #{profilePictureUrl} " +
            "WHERE user_id = #{id}")

    @Update("UPDATE  edu_user SET user_balance = #{balance} WHERE user_id = #{userid}")
    void updateBalance(double balance , Long userid);

    @Select("SELECT user_balance from edu_user where user_id = #{userid}")
    double GetBalance(Long userid);

    //更新电话
    @Update("UPDATE  edu_user SET user_phone = #{userPhone} where user_name = #{userName}")
    void updatePhoneNumber(@Param("userPhone") String userPhone , @Param("userName") String userName);

    //更新昵称
    @Update("UPDATE edu_user set user_nickname = #{userNickname} where user_name = #{userName}")
    void updateNickname(@Param("userNickname") String userNickname , @Param("userName") String userName);

    //更新密码
    @Update("UPDATE edu_user SET user_password = #{userPassword} where user_name = #{userName}")
    void updatePassword(@Param("userPassword") String userPassword, @Param("userName") String userName);
}
