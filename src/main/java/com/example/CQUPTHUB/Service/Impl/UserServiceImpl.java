package com.example.CQUPTHUB.Service.Impl;

import com.example.CQUPTHUB.DAO.UserMapper;
import com.example.CQUPTHUB.Exception.RegisterException;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.UserService;
import com.example.CQUPTHUB.Tools.CheckCorrectly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    User user = new User();
    private final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/user/";


    //返回登录的用户

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public User loginByUserName(String username, String password) {
        //如果输入的密码和储存的hashcode相匹配，就找到这个User，将其返回
        System.out.println(userMapper.FindUserByUsername(username));
        if (user.matches(password, userMapper.FindPasswordByUsername(username))) {
            return userMapper.FindUserByUsername(username);
        }
        //如果不匹配就返回空值
        else return null;
    }

    /**
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public User loginByPhoneNumber(String phoneNumber, String password) {
        //如果输入的密码和储存的hashcode相匹配，就找到这个User，将其返回
        if (user.matches(password, userMapper.FindPasswordByPhoneNumber(phoneNumber))) {
            return userMapper.FindUserByPhoneNumber(phoneNumber);
        }
        //如果不匹配就返回空值
        else return null;
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @Override
    public User loginByEmail(String email, String password) {
        //如果输入的密码和储存的hashcode相匹配，就找到这个User，将其返回
        if (user.matches(password, userMapper.FindPasswordEmailByEmail(email))) {
            return userMapper.FindUserByEmail(email);
        }
        //如果不匹配就返回空值
        else return null;
    }

    @Override
    public User findeUserByEmail(String email) {
        return userMapper.FindUserByEmail(email);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {

        return userMapper.FindUserByUsername(username);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User findUserByUsername2(User user) {
        return userMapper.existsByUsername2(user);
    }


    /**
     * @param userId
     * @param count
     */
    @Override
    public void updateUserBalance(Long userId, Double count) {
        double nowBalance = userMapper.GetBalance(userId);
        userMapper.updateBalance(nowBalance + count, userId);
    }

    /**
     * @param UserId
     * @param newPassword
     */
    @Override
    public void UpdatePasswordOnly(long UserId, String newPassword) {
        userMapper.updatePassword(newPassword, UserId);
    }


    /**
     * @param user
     */
    @Override
    @Transactional
    public void UpdateUser(User user) {
        CheckCorrectly(user);
        userMapper.updateUser(user);
    }

    public void CheckCorrectly(User user) {
        if (!CheckCorrectly.isValidPhoneNumber(user.getUserPhone())) {
            throw new RegisterException("电话号码的格式不正确");
        }
        if (!CheckCorrectly.isValidEmail(user.getUserEmail())) {
            throw new RegisterException("电子邮箱的格式不正确");
        }
    }


    /**
     * @param user
     */
    @Override
    public void registerInformation(User user) {

        //异常处理
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new RegisterException("用户名不能为空");
        }
        if (user.getUserPassword() == null || user.getUserPassword().isEmpty()) {
            throw new RegisterException("密码不能为空");
        }
        if (user.getUserPhone() == null || user.getUserPhone().isEmpty()) {
            throw new RegisterException("电话号码不能为空");
        }
        if (user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
            throw new RegisterException("电子邮箱地址不能为空");
        }

        CheckCorrectly(user);

        if (userMapper.existsByUsername(user.getUserName())) {
            throw new DuplicateKeyException("用户名已存在");
        }
        if (userMapper.existsByEmail(user.getUserEmail())) {
            throw new DuplicateKeyException("电子邮箱地址已被使用");
        }
        if (userMapper.existsByPhoneNumber(user.getUserPhone())) {
            throw new DuplicateKeyException("电话号码已被使用");
        }
        System.out.println("测试注册能不能调到service层\n");
        userMapper.registerUser(user);
        System.out.println("测试注册进入mapper层后能不能出来\n");

    }

    @Override
    public User uploadProfilePicture(String username, MultipartFile file) throws IOException {
        return null;
    }
}
