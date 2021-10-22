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
  `likehit` bigint unsigned DEFAULT '0',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_article_user` (`user_id`),
  CONSTRAINT `FK_article_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.article:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `user_id`, `subject`, `content`, `likehit`, `created_date`, `updated_date`) VALUES
	(79, 10, '블루베리를 나무위키에서 찾으면 나오는 내용은 다음과 같다', '북아메리카를 비롯한 북반구 전역에 분포하는 식물 및 열매. 이름과 달리 딸기, 라즈베리, 블랙베리 등의 베리류와는 전혀 관계가 없는 진달래과 식물[1]이다. 꽃말은 현명과 친절이다.\r\n\r\n넓게는 산앵도나무속(Vaccinium spp.)에 속한 여러 종들 중 검푸른 열매를 맺는 대부분의 식물들을 뭉뚱그려 블루베리라고 부르며, 동아시아가 원산지인 들쭉나무와 정금나무 및 지포나무, 유럽의 빌베리(bilberry)도 블루베리의 일종으로 간주된다. 물론 산앵도나무나 크랜베리(넌출월귤), 월귤(링곤베리)처럼 열매가 빨간 종들은 당연히 제외된다.\r\n\r\n좁은 의미의 블루베리는 북아메리카가 원산지인 재배종들을 가리킨다. 다만 이러한 재배종들도 학명 자체가 다른 경우가 많다.#\r\n\r\n-- 나무위키', 1, '2021-10-08 15:30:00', '2021-10-21 16:24:00'),
	(91, 10, 'WElog에 새로운 로그를 남기면 행운과 건강과 축복이 함께 합니다.', '남겨 남겨요 왔다 감', 0, '2021-10-11 10:52:00', '2021-10-21 19:55:00'),
	(92, 10, '로그 남기기1', '남겨 남겨요 왔다 감', 2, '2021-10-11 10:52:00', '2021-10-15 16:15:00'),
	(93, 10, '로그 남기기', '남겨 남겨요 왔다 감', 0, '2021-10-11 10:52:15', NULL),
	(94, 10, '로그 남기기', '남겨 남겨요 왔다 감', 0, '2021-10-11 10:52:15', NULL),
	(96, 10, '로그 남기기', '남겨 남겨요 왔다 감', 1, '2021-10-11 10:52:15', NULL),
	(97, 10, '로그 남기기', '남겨 남겨요 왔다 감', 0, '2021-10-11 10:52:15', NULL),
	(98, 10, '로그 남기기', '남겨 남겨요 왔다 감', 0, '2021-10-11 10:52:15', NULL),
	(99, 10, '로그 남기기3', '남겨 남겨요 왔다 감', 1, '2021-10-11 10:52:00', '2021-10-15 16:15:00'),
	(109, 10, '새로움', '새로움', 0, '2021-10-11 11:02:56', NULL),
	(116, 10, 'mylog에서 글쓰기', 'mylog', 0, '2021-10-12 12:18:23', NULL),
	(138, 13, '가입 인사', '안녕하세요?\r\n\r\n새로 가입하게 되었습니다.', 0, '2021-10-14 15:22:23', NULL),
	(139, 11, '테스트 회원 가입인사', '안녕하세요\r\n\r\n반가워요\r\n\r\n후후훟ㅅ', 0, '2021-10-15 09:45:57', NULL),
	(141, 11, '좋아요', '테스트', 0, '2021-10-15 14:11:28', NULL),
	(148, 12, '새로운 글2 3 5', '새로운 글입니다.', 0, '2021-10-15 16:00:00', '2021-10-15 16:01:00'),
	(149, 10, '정렬 확인', '되나?', 1, '2021-10-19 10:39:35', NULL),
	(150, 10, '좋아요 눌러짐', '글 작성하고\r\n\r\n좋아요 안 누른거 같은데\r\n\r\n좋아요가 1이 되나??', 2, '2021-10-19 10:40:29', NULL),
	(151, 11, '댓글 생성, 수정, 삭제 기능 추가', '완료 기념~~~~~~~~~~~~~~~~~', 0, '2021-10-19 15:23:26', NULL),
	(156, 10, '새 글 남기기', '후후훗', 0, '2021-10-20 19:38:02', NULL),
	(157, 10, '첨부파일', '올라가랏', 0, '2021-10-20 20:55:44', NULL),
	(158, 10, '첨부 파일 없이 글 작성', '선택된 파일 없음', 0, '2021-10-20 21:18:46', NULL),
	(159, 10, '첨부파일 추가', '주석 확인용 js', 1, '2021-10-20 21:20:56', NULL),
	(160, 10, '첨부파일', 'ㅇㅇ', 0, '2021-10-20 21:25:00', NULL),
	(162, 10, '파일 핸들', '수정 후 첨부파일', 0, '2021-10-20 21:30:54', NULL),
	(164, 10, '첨부 파일 완료', '크크크크크', 0, '2021-10-20 21:40:00', '2021-10-20 21:44:00'),
	(166, 10, '오늘 로그', '후후훗', 0, '2021-10-20 21:51:10', NULL),
	(167, 10, 'savepath 테스트', '변수 변경', 0, '2021-10-20 21:56:15', NULL),
	(168, 10, 'save path 변경 ', 'data/files', 0, '2021-10-20 21:57:17', NULL),
	(169, 10, 'savepath 변경 21', 'ㅇㅇ22', 0, '2021-10-20 21:57:00', '2021-10-20 22:01:00'),
	(170, 10, '글 쓰기 테스트', '중복 제거', 0, '2021-10-20 22:09:31', NULL),
	(171, 10, 'article_id 오류', '확인용', 0, '2021-10-20 22:36:37', NULL),
	(172, 10, '정리중, 청소중, !!@@!! \'ㅁ\' 깨끗해지고 있어요', '깨끗해지기 위해서는\r\n\r\n청소를 해야 합니다.\r\n\r\n그러기 위해서는 청소기가\r\n\r\n있어야 겠지요.\r\n\r\n쇼핑몰로 이동 ====33 =33 =33', 1, '2021-10-20 22:37:00', '2021-10-21 20:01:00'),
	(173, 10, '첨부파일', '테스트중', 0, '2021-10-20 22:39:20', NULL),
	(174, 10, '제목 길이 확인하기 위해서 길게 작성하고 있습니다.', '첨부 파일 소스 이동 중', 0, '2021-10-20 22:52:00', '2021-10-21 19:55:00'),
	(175, 13, '새로운 첨부파일', '코드 정리중', 3, '2021-10-20 22:58:24', NULL),
	(186, 10, 'IU - 라일락 가사', '세상의 모서리\r\n구부정하게 커버린\r\n골칫거리 Outsider\r\n걸음걸이 옷차림\r\n이어폰 너머 Play list\r\n음악까지 다 Minor\r\n넌 모르지\r\n떨군 고개 위\r\n환한 빛 조명이\r\n어딜 비추는지\r\n느려도 좋으니\r\n결국 알게 되길\r\nThe one and only\r\nYou are my celebrity\r\n잊지마 넌 흐린 어둠 사이\r\n왼손으로 그린 별 하나\r\n보이니 그 유일함이 얼마나\r\n아름다운지 말야\r\nYou are my celebrity\r\nCelebrity\r\nYou are my celebrity\r\n지쳐버린 표정 마치\r\n전원을 꺼놓은 듯이\r\n심장소린 Too quiet\r\n네가 가진 반짝거림\r\n상상력 Identity\r\n까지 모조리 Diet\r\n넌 모르지\r\n아직 못다 핀\r\n널 위해 쓰여진\r\n오래된 사랑시\r\n헤매도 좋으니\r\n웃음 짓게 되길\r\nThe one and only\r\nYou are my celebrity\r\n잊지마 넌 흐린 어둠 사이\r\n왼손으로 그린 별 하나\r\n보이니 그 유일함이 얼마나\r\n아름다운지 말야\r\nYou are my celebrity\r\n발자국마다 이어진 별자리\r\n그 서투른 걸음이 새겨놓은 밑그림\r\n오롯이 너를 만나러 가는 길\r\n그리로 가면 돼 점선을 따라\r\n잊지마 이 오랜 겨울 사이\r\n언 틈으로 피울 꽃 하나\r\n보이니 하루 뒤 봄이 얼마나\r\n아름다울지 말야\r\nYou are my celebrity\r\nCelebrity\r\nYou are my celebrity', 0, '2021-10-21 15:05:12', NULL),
	(196, 10, '코드 정리 후 작성, 수정 테스트를 위한 글 쓰기', '코드 정리\r\n\r\n글 작성\r\n\r\n글 수정\r\n\r\n글 삭제\r\n\r\n테스트 필요!!\r\n\r\n-> 수정 완료', 1, '2021-10-21 23:03:00', '2021-10-21 23:03:00');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- 테이블 welog.article_picture 구조 내보내기
DROP TABLE IF EXISTS `article_picture`;
CREATE TABLE IF NOT EXISTS `article_picture` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint unsigned NOT NULL DEFAULT '0',
  `filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `original` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` bigint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_articlepicture_article` (`article_id`),
  CONSTRAINT `FK_articlepicture_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.article_picture:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article_picture` DISABLE KEYS */;
INSERT INTO `article_picture` (`id`, `article_id`, `filename`, `path`, `original`, `size`) VALUES
	(7, 169, '188387750905800.png', 'src/main/resources/static/data/files/20211020', 'React.png', 5720),
	(8, 171, '190712710030900.png', 'src/main/resources/static/data/files/20211020', 'nodejs.png', 2345),
	(9, 172, '190761770374700.png', 'src/main/resources/static/data/files/20211020', 'js.png', 1843),
	(10, 173, '190875782257500.png', 'src/main/resources/static/data/files/20211020', 'java.png', 2106),
	(11, 174, '191672912650600.png', 'src/main/resources/static/data/files/20211020', 'nodejs.png', 2345),
	(12, 175, '192020253539400.png', 'src/main/resources/static/data/files/20211020', 'java.png', 2106),
	(13, 79, '2116588630800.png', 'src/main/resources/static/data/files/20211021', 'pie.png', 533195),
	(24, 186, '22482790712800.jpg', 'src/main/resources/static/data/files/20211021', '81867444_1616662460652_1_600x600.jpg', 160355),
	(32, 196, '278711859676999.png', 'src/main/resources/static/data/files/20211021', 'React.png', 5720);
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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.comment:~29 rows (대략적) 내보내기
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
	(41, 159, 10, 'reply', '2021-10-20 21:22:02'),
	(43, 150, 13, '밤이야 - 수정버전', '2021-10-20 23:11:00'),
	(45, 150, 13, '잘 되네', '2021-10-20 23:11:32'),
	(47, 175, 10, '자바 댓글 최근', '2021-10-21 09:03:32'),
	(48, 79, 10, '작성도 되', '2021-10-21 10:04:01'),
	(67, 186, 10, 'IU♥', '2021-10-21 22:58:49'),
	(68, 186, 10, '아이유 짱 - 수정', '2021-10-21 22:58:00'),
	(71, 196, 10, '댓글2 ㅋㅋㅋ', '2021-10-21 23:04:00'),
	(72, 196, 10, '댓글3', '2021-10-21 23:04:33'),
	(74, 150, 10, '관리자 댓글', '2021-10-22 14:53:41');
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

-- 테이블 데이터 welog.like_article_user:~14 rows (대략적) 내보내기
/*!40000 ALTER TABLE `like_article_user` DISABLE KEYS */;
INSERT INTO `like_article_user` (`article_id`, `user_id`) VALUES
	(79, 10),
	(92, 10),
	(96, 10),
	(99, 10),
	(149, 10),
	(159, 10),
	(172, 10),
	(175, 10),
	(196, 10),
	(92, 11),
	(150, 11),
	(175, 11),
	(150, 13),
	(175, 13);
/*!40000 ALTER TABLE `like_article_user` ENABLE KEYS */;

-- 테이블 welog.role 구조 내보내기
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.role:~0 rows (대략적) 내보내기
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;

-- 테이블 데이터 welog.user:~0 rows (대략적) 내보내기
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
	(25, 'test5', '$2a$10$HhZTLoTgWcdQLxw7uJh4VesAd341.Kdfd3nSsqJwZTv7e2RL2kEm2', b'1'),
	(26, 'test6', '$2a$10$4mdxFOOXJ1uDHnK6dzSVBOTffrswhhXrwJs1Avsa9ITdeIlq3Mja.', b'1');
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

-- 테이블 데이터 welog.user_role:~0 rows (대략적) 내보내기
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
	(26, 1),
	(10, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
