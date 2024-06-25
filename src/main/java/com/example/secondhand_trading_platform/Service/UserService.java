package com.example.secondhand_trading_platform.Service;
import com.example.secondhand_trading_platform.POJO.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Transactional
public interface UserService {
    User loginByUserName(String username , String password);
    User loginByPhoneNumber(String phoneNumber , String password);
    User loginByEmail(String email , String password);
    User findeUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserByUsername2(User user);

    void updateUserBalance(Long userId , Double count);
    void UpdatePasswordOnly(long UserId , String newPassword);
    void UpdateUser(User user);


    void registerInformation(User user);

    User uploadProfilePicture(String username, MultipartFile file)throws IOException;
}
