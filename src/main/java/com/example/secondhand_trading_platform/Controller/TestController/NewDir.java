package com.example.secondhand_trading_platform.Controller.TestController;

import java.io.*;
import java.nio.file.*;
import java.util.*;



public class NewDir {
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String IMAGES_ROOT = PROJECT_ROOT + "/images";

    public String selectInitialPicture(String username) {
        Random random = new Random();
        int randomNumber = random.nextInt(20) + 1; // 生成1到20之间的随机数
        String sourcePicturePath = IMAGES_ROOT + "/SystemDefaultPicture/SystemDefaultPicture_" + randomNumber + ".jpg";
        String targetFolderPath = IMAGES_ROOT + "/user/" + username;

        try {
            // 创建目标文件夹路径
            File targetFolder = new File(targetFolderPath);

            // 如果文件夹不存在，则创建文件夹
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            // 复制初始头像文件到用户文件夹
            Path sourcePath = Paths.get(sourcePicturePath);
            Path targetPath = Paths.get(targetFolder.getAbsolutePath() + "/SystemDefaultPicture.jpg");
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

            // 返回更新后的图片路径
            return targetPath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // 返回默认的图片路径，或者处理异常情况
            return IMAGES_ROOT + "/defaultPicture.jpg";
        }
    }
}
