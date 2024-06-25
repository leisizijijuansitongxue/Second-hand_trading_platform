DROP TABLE IF EXISTS `edu_user`;

CREATE TABLE `edu_user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL UNIQUE COMMENT '用户名',
  `user_password` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `user_email` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL UNIQUE COMMENT '用户邮箱',
  `user_phone` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL UNIQUE COMMENT '用户电话',
  `user_role` ENUM('STUDENT', 'TEACHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户角色',
  `nick_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL UNIQUE COMMENT '用户昵称',
  `user_balance` DECIMAL(10, 2) NOT NULL DEFAULT 0.0 COMMENT '用户余额',
  `registration_time` DATETIME NOT NULL COMMENT '用户注册时间',
  `picture_url` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户头像路径',
  `teacher_information_id` INT(11) COMMENT '教师表键值',
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `fk_teacher_information` FOREIGN KEY (`teacher_information_id`) REFERENCES `edu_teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
