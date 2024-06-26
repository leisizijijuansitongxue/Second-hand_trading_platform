package com.example.CQUPTHUB.Service;
import com.example.CQUPTHUB.POJO.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public interface UserService {
    User loginByUserName(String username , String password);
    User loginByPhoneNumber(String phoneNumber , String password);
    User loginByEmail(String email , String password);
    User findeUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserByUsername2(User user);

    void registerInformation(User user);

    void updateUserNickname(String nickname , String userName);
    void updateUserPhoneNumber(String phoneNumber , String userName);
    void updateUserPasswordByOldPassword(String newPassword , String oldPassword , String userName);
    void updateUserPasswordByEmail(String newPassword ,String userName);
}
