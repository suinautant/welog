-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.25 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- welog 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `welog`;
CREATE DATABASE IF NOT EXISTS `welog` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `welog`;

-- 테이블 welog.article 구조 내보내기
DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned DEFAULT '0',
  `subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image_src` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `likehit` bigint unsigned DEFAULT '0',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_article_user` (`user_id`),
  CONSTRAINT `FK_article_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.article:~42 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `user_id`, `subject`, `content`, `image_src`, `likehit`, `created_date`, `updated_date`) VALUES
	(79, 10, '길이가 어떻게 될까요? 123aaabb - role 수정', 'aaaabbb', NULL, 6, '2021-10-08 15:30:00', '2021-10-11 11:32:00'),
	(91, 10, '로그 남기기2', '남겨 남겨요 왔다 감', NULL, 4, '2021-10-11 10:52:00', '2021-10-15 16:15:00'),
	(92, 10, '로그 남기기1', '남겨 남겨요 왔다 감', NULL, 2, '2021-10-11 10:52:00', '2021-10-15 16:15:00'),
	(93, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(94, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(95, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 1, '2021-10-11 10:52:15', NULL),
	(96, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 1, '2021-10-11 10:52:15', NULL),
	(97, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(98, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(99, 10, '로그 남기기3', '남겨 남겨요 왔다 감', NULL, 1, '2021-10-11 10:52:00', '2021-10-15 16:15:00'),
	(100, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 1, '2021-10-11 10:52:15', NULL),
	(101, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(102, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(103, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 1, '2021-10-11 10:52:15', NULL),
	(104, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(105, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(106, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(107, 10, '로그 남기기', '남겨 남겨요 왔다 감', NULL, 0, '2021-10-11 10:52:15', NULL),
	(109, 10, '새로움', '새로움', NULL, 0, '2021-10-11 11:02:56', NULL),
	(110, 10, 'test34', 'test3\r\n\r\ntest3\r\n\r\ntest3\r\n\r\ntest\r\n\r\ntest3\r\n\r\ntest', NULL, 0, '2021-10-11 11:40:00', '2021-10-11 15:08:00'),
	(111, 10, 'ㅁㄴㅇㄹ', 'ㅁㄴㅇㄹ', NULL, 1, '2021-10-11 13:47:35', NULL),
	(112, 10, '333', '333', NULL, 0, '2021-10-11 13:47:48', NULL),
	(113, 10, '33', '333', NULL, 0, '2021-10-11 13:48:11', NULL),
	(114, 10, '444', '444', NULL, 0, '2021-10-11 13:48:15', NULL),
	(116, 10, 'mylog에서 글쓰기', 'mylog', NULL, 0, '2021-10-12 12:18:23', NULL),
	(117, 10, 'ddfsdf', 'sdf', NULL, 0, '2021-10-13 09:39:41', NULL),
	(124, 16, 'aa', 'aa', NULL, 0, '2021-10-13 11:28:34', NULL),
	(125, 16, 'bb', 'bb', NULL, 0, '2021-10-13 11:28:39', NULL),
	(135, 12, 'test', 'test', NULL, 0, '2021-10-14 12:41:11', NULL),
	(138, 13, '가입 인사', '안녕하세요?\r\n\r\n새로 가입하게 되었습니다.', NULL, 0, '2021-10-14 15:22:23', NULL),
	(139, 11, '테스트 회원 가입인사', '안녕하세요\r\n\r\n반가워요\r\n\r\n후후훟ㅅ', NULL, 0, '2021-10-15 09:45:57', NULL),
	(140, 7, 'asdf', 'bbb', NULL, 0, '2021-10-15 14:07:02', NULL),
	(141, 11, '좋아요', '테스트', NULL, 0, '2021-10-15 14:11:28', NULL),
	(142, 11, '좋아요2', 'ㅁㄴㅇㄻㄴㅇㄹ', NULL, 0, '2021-10-15 14:11:55', NULL),
	(143, 11, '좋아요2', 'ㅇㅇㅇ', NULL, 0, '2021-10-15 14:18:10', NULL),
	(144, 11, '좋아요3', 'ㅋㅋ', NULL, 0, '2021-10-15 14:22:17', NULL),
	(145, 11, '좋아요4', 'ㅇㅇ', NULL, 0, '2021-10-15 14:23:07', NULL),
	(147, 11, '좋아요6 2 3 4 5 6 7  8', 'ㅇㅇㅇ', NULL, 0, '2021-10-15 14:43:00', '2021-10-15 14:50:00'),
	(148, 12, '새로운 글2 3 5', '새로운 글입니다.', NULL, 0, '2021-10-15 16:00:00', '2021-10-15 16:01:00'),
	(149, 10, '정렬 확인', '되나?', NULL, 1, '2021-10-19 10:39:35', NULL),
	(150, 10, '좋아요 눌러짐', '글 작성하고\r\n\r\n좋아요 안 누른거 같은데\r\n\r\n좋아요가 1이 되나??', NULL, 1, '2021-10-19 10:40:29', NULL),
	(151, 11, '댓글 생성, 수정, 삭제 기능 추가', '완료 기념~~~~~~~~~~~~~~~~~', NULL, 0, '2021-10-19 15:23:26', NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- 테이블 welog.article_picture 구조 내보내기
DROP TABLE IF EXISTS `article_picture`;
CREATE TABLE IF NOT EXISTS `article_picture` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint unsigned NOT NULL DEFAULT '0',
  `original_filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stored_filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stored_filepath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filesize` bigint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_articlepicture_article` (`article_id`),
  CONSTRAINT `FK_articlepicture_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.article_picture:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_picture` ENABLE KEYS */;

-- 테이블 welog.comment 구조 내보내기
DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint unsigned NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`article_id`),
  KEY `FK_comment_article` (`article_id`),
  KEY `FK_comment_user` (`user_id`),
  CONSTRAINT `FK_comment_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FK_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.comment:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `article_id`, `user_id`, `content`, `created_date`) VALUES
	(1, 91, 25, '댓글 테스트', '2021-10-18 11:24:53'),
	(2, 91, 10, '관리자예요', '2021-10-18 11:25:24'),
	(3, 92, 10, '관리자 댓글', '2021-10-18 13:47:52'),
	(4, 91, 10, '첫 번째 textarea 댓글', '2021-10-18 22:22:30'),
	(8, 92, 10, '여기도 댓글', '2021-10-18 22:28:29'),
	(9, 79, 13, '성지 순례', '2021-10-18 22:29:03'),
	(11, 93, 13, '댓글 없네? ㅋㅋㅋㅋㅋ\r\n\r\n한 줄 띄우기', '2021-10-18 22:34:42'),
	(13, 91, 13, '마지막 댓글?', '2021-10-18 22:40:08'),
	(14, 91, 10, '다음 날', '2021-10-19 09:54:28'),
	(17, 149, 10, 'html 태그 들어가나?\r\n\r\n<b>강조</b>', '2021-10-19 11:16:20'),
	(18, 149, 10, '두 번째 댓글 라잇', '2021-10-19 11:16:41'),
	(19, 149, 10, '대대대', '2021-10-19 11:20:20'),
	(20, 150, 10, '대앳글', '2021-10-19 11:34:21'),
	(21, 91, 10, '작성', '2021-10-19 11:44:15'),
	(22, 150, 10, '어드민', '2021-10-19 14:01:52'),
	(28, 150, 11, '위에 수정 \r\n\r\n작성합니다.\r\n\r\n--\r\n중간 수정\r\n--\r\n수정합니다.\r\n\r\n저 아래까지\r\n\r\nㅋㅋㅋ', '2021-10-19 15:17:00'),
	(29, 150, 11, '처음 이예 요', '2021-10-19 15:18:44'),
	(30, 150, 11, '댓글 다 됩니다~~~!!', '2021-10-19 15:21:19'),
	(31, 79, 11, '좋아요 제일 많은 곳\r\n\r\n수정 되나요?', '2021-10-19 15:22:00'),
	(33, 141, 10, '타 사용자 글에 댓글 달기 test', '2021-10-20 12:15:48'),
	(35, 79, 10, '댓글이징', '2021-10-20 15:47:36');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 welog.like_article_user 구조 내보내기
DROP TABLE IF EXISTS `like_article_user`;
CREATE TABLE IF NOT EXISTS `like_article_user` (
  `article_id` bigint unsigned NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`article_id`,`user_id`),
  KEY `FK_like_article_user_user` (`user_id`),
  CONSTRAINT `FK_like_article_user_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FK_like_article_user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.like_article_user:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `like_article_user` DISABLE KEYS */;
INSERT INTO `like_article_user` (`article_id`, `user_id`) VALUES
	(79, 10),
	(91, 10),
	(92, 10),
	(96, 10),
	(99, 10),
	(149, 10),
	(79, 11),
	(91, 11),
	(92, 11),
	(150, 11),
	(79, 12),
	(95, 12),
	(100, 12),
	(103, 12),
	(111, 12),
	(79, 13),
	(91, 13),
	(79, 24),
	(79, 25),
	(91, 25);
/*!40000 ALTER TABLE `like_article_user` ENABLE KEYS */;

-- 테이블 welog.role 구조 내보내기
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.role:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 테이블 welog.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.user:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`) VALUES
	(7, 'role', '$2a$10$WyvEpz2lG.kUgljBcwcbNuSXuD8Ab8z5jPVex0nG4qN8FIxe0BSge', b'1'),
	(8, 'admin2', '$2a$10$cmstvPnX1nZI0b09iuzLS.I6jc6Ki4AaAS1utsAMRIrNBHA8vAJ8e', b'1'),
	(10, 'admin', '$2a$10$Nk8y4Y9jMEWMFIYhfjsCK.4hd53abPWWomjYmZYDnL.9SUTUTnql6', b'1'),
	(11, 'test', '$2a$10$EHFAt4pgdUKzrWnnNIMRheYTLgyLy6RNu2rBu69C31fPk77hwo3em', b'1'),
	(12, 'test2', '$2a$10$T7QbtrNiiEhe4aXy0Vzlk.3u0QxDkLZiNaoGUnqR8aQXitGrWm9qm', b'1'),
	(13, 'test3', '$2a$10$bO1qu9tX/1u2dOmM2jJFF.UFHqcy2w8C0b53xWLmuRUrNfaKqoRxy', b'1'),
	(16, 'test00', '$2a$10$NYAtnVD0gTYvSLeHNR9RoeaxMiDVBMxbOe53wdsIiPup3bHJbpBG6', b'1'),
	(24, 'test4', '$2a$10$WAiiwoQNVQQFzPIQjNym4.h/zJK/oZwak.UVlnmQELo7.RE.Or4kG', b'1'),
	(25, 'test5', '$2a$10$HhZTLoTgWcdQLxw7uJh4VesAd341.Kdfd3nSsqJwZTv7e2RL2kEm2', b'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 welog.user_role 구조 내보내기
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint unsigned NOT NULL,
  `role_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_user_role_role` (`role_id`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.user_role:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(7, 1),
	(8, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1),
	(24, 1),
	(25, 1),
	(10, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
