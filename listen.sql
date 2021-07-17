-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2021 at 10:39 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `listen`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(10) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `phone_number` varchar(50) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `user_id`, `last_name`, `first_name`, `full_name`, `birthday`, `phone_number`, `address`, `email`, `city`, `state`) VALUES
(74, 1, 'Huy', 'Huy', 'Vũ Quang Huy', '2021-07-02 07:00:00', '353443440', '125/85, biên hòa, đồng nai', 'huy123@gmail.com', 'Đồng Nai', 'Thành phố Biên Hòa'),
(84, 7, 'Lê', 'Hoa', 'Lê Thị Hoa', '1998-12-01 07:00:00', '0353443550', '123/4,khu phố 3, biên hòa, đồng nai', 'hoalt@gmail.com', 'Đồng Nai', 'Thành phố Biên Hòa'),
(85, 8, 'Nguyễn', 'Nam', 'Nguyễn Huỳnh Phương Nam', '1998-12-15 07:00:00', '22456871', '51243/4,biên hòa, đồng nai', 'namng123@gmail.com', 'Đồng Nai', 'Thành phố Biên Hòa'),
(144, 14, 'Nguyễn', 'Hạnh', 'Nguyễn Thị Hạnh', '1998-12-01 07:00:00', '02518971148', '12/3, HCM, quận 11', 'hanhhn345@gmail.com', 'Thành phố Hồ Chí Minh', 'Quận 11');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(149);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(10) NOT NULL,
  `order_date` datetime NOT NULL,
  `required_date` datetime DEFAULT NULL,
  `shipped_date` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `customer_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_date`, `required_date`, `shipped_date`, `status`, `comments`, `customer_id`) VALUES
(86, '2021-07-05 14:28:26', '2021-07-05 14:28:26', NULL, 'Open', '', 84),
(92, '2021-07-05 16:00:35', '2021-07-05 16:00:35', NULL, 'Open', '', 85),
(94, '2021-07-05 16:03:23', '2021-07-05 16:03:23', NULL, 'Open', '', 85),
(98, '2021-07-05 16:08:52', '2021-07-05 16:08:52', NULL, 'Open', '', 85),
(100, '2021-07-06 16:11:48', '2021-07-06 16:11:48', NULL, 'Open', '', 85),
(102, '2021-07-06 16:15:32', '2021-07-06 16:15:32', NULL, 'Open', '', 84),
(104, '2021-07-06 21:12:18', '2021-07-06 21:12:18', NULL, 'Open', '', 84),
(105, '2021-07-10 17:20:49', '2021-07-10 17:20:49', NULL, 'Open', 'ok ok', 84),
(106, '2021-07-10 21:48:33', '2021-07-10 21:48:33', NULL, 'Open', '', 85),
(107, '2021-07-12 10:19:48', '2021-07-12 10:19:48', NULL, 'Open', '', 74),
(108, '2021-07-12 12:57:57', '2021-07-12 12:57:57', NULL, 'Open', '', 144),
(109, '2021-07-14 20:21:17', '2021-07-14 20:21:17', NULL, 'Open', '', 144),
(110, '2021-07-14 20:22:15', '2021-07-14 20:22:15', NULL, 'Open', '', 144),
(111, '2021-07-17 15:38:27', '2021-07-17 15:38:27', NULL, 'Open', '', 84);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  `quantity_order` int(10) NOT NULL,
  `price_each` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `quantity_order`, `price_each`) VALUES
(88, 86, 12, 1, 2499000),
(93, 92, 4, 1, 5999000),
(95, 94, 13, 1, 4999000),
(99, 98, 40, 3, 670000),
(101, 100, 7, 1, 3390000),
(103, 102, 7, 1, 3390000),
(105, 104, 13, 1, 4999000),
(106, 104, 14, 1, 1899000),
(107, 105, 14, 1, 1899000),
(108, 106, 14, 1, 1899000),
(109, 107, 19, 1, 7499000),
(110, 107, 12, 1, 2499000),
(111, 108, 8, 1, 1192000),
(112, 109, 14, 1, 1899000),
(113, 110, 14, 1, 1899000),
(114, 111, 11, 1, 3832000);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(10) NOT NULL,
  `product_code` int(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_description` varchar(2500) DEFAULT NULL,
  `product_image` text NOT NULL,
  `product_line_id` int(10) NOT NULL,
  `product_vendor` varchar(50) NOT NULL,
  `quantity_in_stock` int(10) NOT NULL,
  `buy_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `product_code`, `product_name`, `product_description`, `product_image`, `product_line_id`, `product_vendor`, `quantity_in_stock`, `buy_price`) VALUES
(1, 2467, 'SONY WF-XB700', 'Tai nghe True Wireless Sony WF-XB700 ( Nobox ) là dòng tai nghe True Wireless không có box với một mức giá cực kì dễ chịu, dễ dàng tiếp cận hơn với nhiều tín đồ âm thanh, Tai nghe True Wireless Sony WF-XB700 ( Nobox ) Mang tới cấu hình mạnh mẽ và mới nhất của True Wireless Sony với công nghệ tăng cường âm Bass độc quyền Extra Bass của True Wireless Sony.', '/product_image/SONY_WF-1000XM3.jpg', 1, 'Sony', 1000, 1299000),
(3, 2112, 'SONY WF-1000XM3', 'Sony luôn mang lại cho người dùng những sản phẩm vô cùng chất lượng với từng giá tiền mà người dùng bỏ ra, những sản phẩm trên cả tuyệt vời và đôi khi vượt trội hơn tầm giá khá nhiều, để luôn đem tới cho người dùng nhiều sản phảm hoàn hảo hơn nữa hãng Sony đã đem tới cho người dùng sản phẩm Tai nghe True Wireless Sony WF-1000XM3 ( No Box ) với công nghệ chống ồn tuyệt vời.', '/product_image/SONY_WF-1000XM3.jpg', 1, 'Sony', 1000, 2250000),
(4, 1525, 'TAI NGHE BLUETOOTH SONY WH-1000XM3', 'WH-1000XM3 của Sony mang tới những cải tiến đáng giá cho chiếc tai nghe này như cổng USB C, khả năng lọc tiếng ồn tốt hơn tới 4 lần sao với WH-1000XM2 cũng những cải tiến vượt bậc hơn.\r\nSony WH-1000XM3 sử dụng CPU dành riêng cho mục đích chống ồn có tên QN1. Giờ đây, tạp âm như tiếng người, xe cộ có thể bị loại bỏ và không ảnh hưởng đến trải nghiệm nghe nhạc.', '/product_image/sony_wh1000xm3.jpg', 1, 'Sony', 152, 5999000),
(7, 1195, 'TAI NGHE SONY WF-SP700N TRUE WIRELESS', 'Tại triển lãm CES 2018 hãng âm thanh sony đã cho ra mắt mẫu tai nghe không dây hoàn toàn WF-SP700N hướng đến người nghe có nhu cầu thể thao hoặc vận động cường độ cao đi kèm khả năng chống bắn nước, chống ồn ấn tượng.', '/product_image/earbuds_0.jpg', 1, 'Sony', 1060, 3390000),
(8, 17, 'SONY WI-C400', 'Tại IFA Berlin 2017 – sự kiện lớn nhất về thiết bị điện tử tiêu dùng ở Châu Âu, Sony đã tung ra các sản phẩm mới của mình. Tai nghe Sony WI-C400 là chiếc tai nghe Bluetooth cao cấp dành cho điện thoại di động, máy tính bảng, máy nghe nhạc, có thiết kệ dạng vòng cổ, với nhiều màu sắc trẻ trung hướng tới những người trẻ năng động.\r\n', '/product_image/250_17_2.jpg', 1, 'Sony', 1524, 1192000),
(11, 12, 'SONY WH-H800 (WIRELESS)', 'WH H800 là sản phẩm được đặc biệt thiết kế dành riêng cho những người phải di chuyển liên tục. Chính vì thế mà kích thước và khối lượng của chúng khá nhỏ gọn chỉ khi nào đeo lên tai người dùng mới cảm nhận được.\r\n\r\nMặc dù thế nhưng phần earcup cũng được thiết kế tròn, ôm trọn vành tai. Dây nối đi kèm được thiết kế với lớp vỏ cao su chất lượng tốt. Sản phẩm được thiết kế với 5 màu là: Horizon Green, Moonlit Blue, Grayish Black, Twilight Red, Pale Gold cực kỳ ấn tượng với người dùng.\r\n\r\n', '/product_image/250_12_2.jpg', 1, 'Sony', 1460, 3832000),
(12, 571, 'TAI NGHE BEATS STUDIO 2.0 WIRELESS (NOBOX)', 'Tai nghe Beats Studio 2.0 Wireless là chiếc headphones wireless có thiết kế tinh tế và khỏe khoắn với logo chữ “B” nổi bật. Về tổng thể chất liệu để làm ra chiếc Beat Wireless vẫn chủ yếu là nhựa, tuy nhiên phần vỏ ngoài đệm dòng tai nghe ear – pad này lại được làm từ chất liệu da cao cấp mềm mại, mang đến sự thoải mái và dễ chịu cho người sử dụng. Thiết kế với 3 màu sắc: matte black, titanium, black sang trọng và thanh lịch, Beats Studio 2.0 Wireless tạo ấn tượng sâu sắc ngay từ cái nhìn đầu tiên, điều này khá khác với dòng Beats Studio 2.0.', '/product_image/beats_studio_wireless_2_matte_black.jpg', 1, 'Beats', 562, 2499000),
(13, 1819, 'TAI NGHE POWERBEATS PRO TRUE WIRELESS', 'Beats đã mang tới rất nhiều mẫu mã thương hiệu nổi tiếng, mang tới thêm cả sự đồng bộ tuyệt hảo với các dòng điện thoại sở hữu IOS tối đa, nhưng đối với hệ điều hành Android thì vẫn sử dụng bình thường nhé. Beats đã bất ngờ công bố một sản phẩm cực kì đẳng cấp được nói rằng đây là một dòng sản phẩm vô cùng phá cách đồng thời lấy cảm hứng người đàn anh trước trước của mình Powerbeats 3 Wireless với kiểu dáng móc vành tai nhưng sản phẩm hôm nay Beats họ mang tới là một dòng không dây hoàn toàn – true wireless được mang tên gọi là Tai nghe True Wireless Powerbeats Pro', '/product_image/250_1819-2.jpg', 1, 'Beats', 1524, 4999000),
(14, 572, 'TAI NGHE BEATS SOLO 2 WIRELESS (NOBOX)', 'Beats Solo 2 Wireless (Nobox) là sản phẩm tai nghe không có vỏ hộp, mới hoàn toàn 100% chưa qua sử dụng. Thiết kế bắt mắt với lớp vỏ sáng bóng, nhiều màu sắc trẻ trung cho người dùng thêm nhiều lựa chọn như: gold, đen đỏ, xám đen, xanh ghi, trắng, bạc…\r\n\r\nEar cup kiểu dáng hiện đại, siêu sang với logo hình chữ “B” của hãng được in ở mặt ngoài. Đệm tai nghe thiết kế siêu nhẹ, bên ngoài bọc chất liệu da trơn cao cấp mềm mại, độ bền cao, mang đến sự thoải mái, rất êm tai cho người đeo.', '/product_image/beats_solo_2_wireless_nobox.jpg', 1, 'Beats', 546, 1899000),
(15, 1446, 'BEATS STUDIO 3 WIRELESS LIMITED TEN YEAR', 'Tai nghe bluetooth Beats Studio 3 Wireless phiên bản kỷ niệm thực sự là siêu phẩm tai nghe cả về hình thức lẫn chất lượng âm thanh mang đến sự sang trọng, tinh tế cho người dùng.\r\n\r\nVẫn thiết kế model bản thường với cổng kết nối sạc, cable, các nút điều chỉnh âm lượng, ngay cả nút tắt Bluetooth và đèn thông báo được bố trí một cách khéo léo, đẹp mắt ngay bên thân tai nghe cực tiện lợi giúp cho chúng ta có thể dễ dàng sử dụng.', '/product_image/beats_studio_3__red_limited.jpg', 1, 'Beats', 1526, 7999000),
(18, 1031, 'BEATS SOLO 3 WIRELESS', 'Vẫn như những đàn anh cùng hãng đi trước, thiết kế của tai nghe Beats Solo 3 Wireless vẫn đặt sự sành điệu, phong cách lên hàng đầu khi kết hợp hài hòa giữa kiểu dáng, màu sắc cho đến việc chăm chút những bộ phận chi tiết nhất. Có đến 9 phiên bản màu để người dùng thỏa thích lựa chọn sản phẩm phù hợp với cá tính bản thân.\r\n\r\nKích cỡ Solo 3 Wireless khá nhỏ, đặc biệt có phần bản lề giúp gấp gọn tai nghe một cách tiện lợi. Cơ cấu phần bản lề có thể kéo dài thêm 2cm giúp những người có kích cỡ đầu và tai to không phải bỡ ngỡ khi dùng. Bộ phận trùm tai rất mềm và thoải mái khi đeo.', '/product_image/beats_solo_3_wireless_pop_blue.jpg', 1, 'Beats', 1460, 4999000),
(19, 565, 'BEATS STUDIO 3 WIRELESS', 'Tai nghe bluetooth Beats Studio 3 Wireless là siêu phẩm đầu tiên trên thế giới sử dụng thiết kế nguyên khối kim loại trên bề mặt sản phẩm mang đến sự sang trọng, tinh tế cho người dùng. Tai nghe Beats Studio 3 Wireless là dòng tai nghe được Apple cải tiến rất nhiều tư kiểu dáng, mẫu mã giúp cho người sử dụng dễ dàng chọn lựa được những chiếc tai nghe ưng ý nhất. Không những thế phần khớp bẻ có thể cất gọn gàng được làm chắc chắn hơn dòng Solo 2.', '/product_image/the_beats_skyline_collection_midnight_black.jpg', 1, 'Beats', 5624, 7499000),
(20, 2460, 'BOSE QUIETCOMFORT EARBUDS', 'Trước đó, Bose đã đem tới khá nhiều sản phẩm tai nghe bluetooth với chất âm cực kì êm tai với những dải mid ngọt ngào, âm bass có độ sâu, chắc chắn hoàn hảo một cách vô cùng chỉnh chu. Không ngừng cố gắng phát triển để mang tới những dòng sản phẩm chất lượng hơn hết, Bose đã bất ngờ cho ra mắt một dòng sản phẩm tai nghe True Wireless cực kì hoàn hảo trong thời điểm hiện tại được mang tên gọi Tai nghe Bose QuietComfort Earbuds ', '/product_image/bose_quietcomfort_earbuds_black.jpg', 1, 'Bose', 5487, 8690000),
(21, 2602, 'BOSE SPORT EARBUDS', 'Tai nghe Bose Sport Earbuds (No Box) là dòng tai nghe True Wireless không có bao bì, với một mức giá cực kì dễ chịu, dễ dàng tiếp cận hơn với nhiều tín đồ âm thanh', '/product_image/250_2602_tai_nghe_bose_sport_earbuds_nobox.jpg', 1, 'Bose', 1524, 4500000),
(23, 2601, 'BOSE QUIETCOMFORT EARBUDS', 'Tai nghe BOSE QUIETCOMFORT EARBUDS (No box) là dòng tai nghe True Wireless không có bao bì, với một mức giá cực kì dễ chịu, dễ dàng tiếp cận hơn với nhiều tín đồ âm thanh.', '/product_image/250_2601_tai_nghe_true_wireless_bose_quietcomfort_earbuds.jpg', 1, 'Bose', 1060, 6500000),
(24, 1109, 'BOSE SOUNDSPORT FREE WIRELESS', 'Bose SoundSport Free là mẫu tai nghe không dây hoàn toàn đầu tiên của Bose vừa ra mắt tại Việt Nam. SoundSport Free phù hợp cho các hoạt động thể thao ngoài trời, cũng như nhiều hoạt động khác cần sử dụng đến loại tại nghe không dây chất lượng này. Mang đến cho người dùng những tính năng và trải nghiệm tuyệt vời nhất hiện nay.', '/product_image/250_1109_avata.jpg', 1, 'Bose', 6548, 4999000),
(25, 1110, 'BOSE QC35 SERI II', 'Chiếc tai nghe được nâng cấp từ bản QC35 trước đây với độ cải tiến công nghệ chống ồn và đột thoải mái nhất định khi đeo và là sản phẩm cạnh tranh trực tiếp với Sony WH-1000XM2 Hãng Bose đã thiết kế lại tai nghe với công nghệ Bluetooth và công nghệ khử ồn đạt đẳng cấp thế giới làm tăng khả năng trải nghiệm âm nhạc của bạn nghe hay hơn. Đồng nghĩa với việc bạn có thể thưởng thức âm nhạc mà không bị ảnh hưởng hay bị làm phiền bởi các yếu tố bên ngoài.', '/product_image/250_1110_a_quietcomfort_35_wireless_headphones.jpg', 1, 'Bose', 846, 7499000),
(26, 362, 'BOSE SOUNDSPORT WIRELESS', 'Tập luyện thể thao là một hoạt động đòi hỏi nhiều cố gắng. Đó là lý do bạn đòi hỏi tai nghe có khả năng đương đầu với thử thách. Do không cần dây kết nối, tai nghe không dây SoundSport giúp bạn luôn vận động cùng âm thanh mạnh mẽ và các đầu tai nghe luôn nằm cố định và thoải mái. Tai nghe chịu mồ hôi và thời tiết với tuổi thọ pin lên đến 6 giờ mỗi lần sạc. Vì vậy sẽ không có gì ngăn cản bạn luyện tập. Dễ dàng kết nối với thiết bị của bạn bằng ghép đôi Bluetooth® và NFC. Một khi bạn đã thử dùng những tai nghe này, bạn sẽ nhận ra tai nghe SoundSport không chỉ được đặt tên cho hoạt động tập luyện mà chúng được tạo ra cho hoạt động này.\r\n\r\nSoundSport Wireless có mức giá vừa phải khoảng $150 và sử dụng thiết kế open. Nhờ eartip StayHear (được đi kèm tai nghe với 3 size S / M / L), housing của SoundSport Wireless được nằm chắc chắn trên tai người đeo mà không cần phải ấn tai nghe vào quá sâu trong ống tai, giảm thiểu cảm giác tù túng và bí hơi khi đeo trong thời gian dài.', '/product_image/250_511_tai_nge_bluetooth_bose_sound_sport_wireless.jpg', 1, 'Bose', 1460, 3690000),
(27, 2024, 'JBL TUNE 600BTNC', 'Để lựa chọn cho mình một chiếc tai nghe có chất âm hay , dễ nghe nhiều thể loại nhạc và đặc biệt là cũng phải có thêm những tính năng nổi bật nữa, Đó chính là những điều mà người tiêu dùng đều muốn hướng đến . Vì thế hãng để làm hài lòng những vị khách hàng khó tính nhất , nên hãng JBL đã tung ra một sản phẩm mới rất hấp dẫn mẫu Tai nghe bluetooth JBL TUNE 600BTNC được trang bị công nghệ chống ồn chủ động cùng với một mức giá khá êm ái dễ dàng tiếp cận với nhiều khách hàng\r\n\r\n', '/product_image/250_2024_q.jpg', 1, 'JBL', 1060, 1890000),
(28, 2788, 'JBL TUNE 225TWS', 'Thương hiêu JBL là một thương hiệu âm thanh đã quá đỗi nổi tiếng, quen thuộc với tất cả mọi người dùng dùng trên toàn thế giới, người dùng luôn tin tưởng an thâm khi sử dụng mọi sản phẩm mà thương hiệu Mỹ - JBL họ mang tới. Những sản phẩm công nghệ âm thanh tuyệt vời luôn mang tới chất lượng âm thanh mạnh mẽ, bùng nổ và đi kèm với đó chính là những công nghệ âm thanh cao cấp tiên tiến tuyệt vời. Đặc biệt hơn nữa là những sản phẩm của JBL họ luôn sở hữu một độ bền không thể chê trách vào đâu được nữa.', '/product_image/250_2788_jbl_tune_225tws_white.jpg', 1, 'JBL', 1524, 1890000),
(29, 1874, 'JBL UNDER ARMOUR FLASH TRUE WIRELESS', 'Mỗi tai nghe True Wireless đều mang trên mang những thiết kế khác nhau , tính năng khác nhau , chuyển để nghe nhạc hay như là chuyên để tập thể thao . Việc tập thể thao giờ đây là không thể thiếu cho mỗi người , nâng cao sức khỏe là điều tiết yếu , nên hãng JBL đã kết hợp với hãng trời trang thể thao nổi tiếng Under Armour  để cho ra mắt sản phẩm Tai nghe JBL Under Armour Flash True Wireless  với nhiều tính năng cực kì hoành tráng phù hợp hơn bao giờ hết với những tín đồ vừa yêu âm nhạc vừa yêu thể thao', '/product_image/250_1874-jbl_ua_twsflash_hero.jpg', 1, 'JBL', 546, 3000000),
(30, 1237, 'JBL FREE', 'Tai nghe không dây JBL Free lấy hai tông màu đen trắng là chủ đạo, có thiết kế sang trọng và hiện đại. Kích thước nhỏ gọn có thể mang theo bất kỳ đâu, lúc di chuyển xa hay tập luyện thể thao. Đầu nút tai JBL Free có thiết kế khá vừa vặn và nhẹ nhàng. Nhiều khách hàng phản hồi khi vận động mạnh tai nghe vẫn nằm cố định trên tai, không bị lỏng hoặc rơi ra ngoài.', '/product_image/250_1237_496_3.jpg', 1, 'JBL', 1526, 1200000),
(31, 1239, 'JBL E55BT', 'Dòng tai nghe E-Series của JBL được người dùng tầm trung biết đến không chỉ với hiệu năng ấn tượng, chất âm độc đáo mà còn với kiểu dáng thời trang, một tiêu chuẩn khá mới cho các dòng tai nghe JBL. Sản phẩm tai nghe bluetooth JBL E55BT đạt được cả 3 tiêu chuẩn đó với mức giá rẻ dưới $150, mang lại cho khách hàng thêm một tùy chọn nữa phù hợp với nhu cầu chi tiêu của mình. Đi kèm với kiểu dáng over-ear hầm hố là khối lượng rất nhẹ và độ thoải mái cực cao khi đeo, E55BT dễ dàng trở thành trợ thủ đắc lực cho cả giải trí và công việc.', '/product_image/250_1239_fsdfsdf.jpg', 1, 'JBL', 8462, 3789000),
(32, 2390, 'JBL LIVE 300TWS (TRUE WIRELESS)', 'JBL thương hiệu âm thanh đã quá nổi tiếng trên thị trường âm thanh rộng lớn. Luôn cập nhật đến với mọi người những sản phẩm tuyệt vời hơn bao giờ hết, với những công nghệ đi trước xu thế, những thiết kế mang tính tương lai của mình. Mới đây, hãng JBL đã tiếp tục cho ra mắt sản phẩm tai nghe true wireless được mang tên Tai nghe True Wireless JBL Live 300TWS ', '/product_image/250_2390_tai_nghe_true_wireless_jbl_live_300tws_black.jpg', 1, 'JBL', 8794, 3990000),
(33, 2171, 'SAMSUNG GALAXY BUDS+', 'Tai nghe Samsung Galaxy Buds+ được sở hữu trên mình một thiết kế cũng không có khá nhiều thay đổi về ngoại hình nhưng trên Tai nghe Samsung Galaxy Buds+ đã được hãng Samsung thay đổi về chất liệu lớp sơn từ dạng nhựa mờ sang dạng bóng, điều này để thay đổi nhằm tăng sự cao cấp thêm cho sản phẩm đồng thời sẽ giữ được sản phẩm của mình luôn luôn nằm trong trạng thái mới nhất có thế như những lần sử dụng đầu tiên', '/product_image/250_2171_tai_nghe_true_wireless_samsung_glaxy_buds_plus.jpg', 1, 'Samsung', 4568, 1350000),
(34, 2666, 'SAMSUNG GALAXY BUDS PRO', 'Tính năng nổi trội và đáng được quan tâm nhất trên Tai nghe Samsung Galaxy Buds Pro đó chính là việc trang bị tính năng chống ồn chủ động Active Noise Canceling - lọc tới 99% tiếng ồn xung quanh thông qua mic bên trong và mic bên ngoài thông qua thời gian thực. Có thể chuyển đổi giữa các mức ANC Cao và Thấp trong ứng dụng Galaxy Wearable. Giúp cho mọi người dùng có thể cách ly hoàn toàn được với những âm thanh xung quanh môi trường của người dùng để cung cấp tới trải nghiệm vô cùng hoàn hảo chỉ có âm nhạc và bạn mà thôi.', '/product_image/250_2666_tai_nghe_samsung_galaxy_buds_pro_black.jpg', 1, 'Samsung', 4875, 4990000),
(35, 977, 'SAMSUNG LEVEL U PRO (NOBOX)', 'Tai nghe Bluetooth Samsung Level U PRO (nobox) thuộc thương hiệu Samsung Hàn Quốc nên có chất lượng đảm bảo, kế thừa những ưu điểm nổi bật của các dòng tai nghe đi trước Level U PRO (nobox) nổi bật lên như một dòng sản phẩm hàng đầu hiện nay. Tai nghe có thiết kế vòng cung ôm từ phía sau cổ với vành khung có thể điều chỉnh được rất thuận tiện khi sử dụng. Chất âm cũng được xếp vào loại tốt hiện nay. Xứng đáng với sự kỳ vọng của các tín đồ âm nhạc trên thế giới.', '/product_image/250_977_s1_samsung_level_u_pro_nobox_gold.jpg', 1, 'Samsung', 1460, 949000),
(36, 979, 'SAMSUNG LEVEL U PRO', 'Samsung Level U Pro là một người đồng hành cùng bộ đôi flagship Galaxy S7/Galaxy S7 Edge. Sản phẩm được thiết kế như một chiếc vòng cổ thời trang, trọng lượng mỏng nhẹ giúp người dùng luôn cam thấy thoải mái khi đeo trong thời gian dài. Level U Pro có độ cong vừa phải, các góc lượn ôm sát vào cơ thể, khớp nối có độ cứng cao hơn so với người tiền nhiệm Level U.\r\n\r\nMiếng đệm tai nghe cũng được làm từ loại chất liệu mềm mại, có khả năng chống mồ hôi hiệu quả. Thân tai nghe được thiết kế bằng chất liệu nhựa bóng mờ mang lại cảm giác sang trọng hơn hẳn. Các phần nút bấm cũng được hoàn thiện tốt hơn. Đồng thời trên Level U Pro cũng đã tích hợp thêm chức năng Pairing vào nút nguồn.', '/product_image/250_979_tai_nghe_bluetooth_samsung_level_u_pro.gif', 1, 'Samsung', 546, 1099000),
(37, 1198, 'SAMSUNG U FLEX', 'Samsung U Flex là mẫu tai nghe được thiết kế đặc biệt để có thể vừa vặn với hầu hết hoạt động thường ngày của người dùng. Thoạt trông Samsung U Flex không mấy khác biệt với các mẫu tai nghe không dây U Series mà hãng từng giới thiệu vì sản phẩm mới cũng có thiết kế vòng đeo cổ dạng chữ U, 2 củ tai nhỏ nhắn dạng in-ear có thể nhét sâu vào trong ống tai người dùng.', '/product_image/250_1198_samsung_u_flex_blue.jpg', 1, 'Samsung', 1524, 1499000),
(38, 502, 'SAMSUNG LEVEL U', 'Tai nghe Samsung Level U là dòng tai nghe bluetooth sở hữu nhiều tính năng nổi bật, thu hút đông đảo khách hàng. Tai nghe được tích hợp micro giúp người dùng rảnh tay đàm thoai, có khả năng tương thích với nhiều thiết bị di động và pin sử dụng tốt lên đến 10 nghe nhạc liên tiếp. Sở hữu nhiều tính năng tuyệt vời giúp cho thiết bị này được rất nhiều khách hàng ưa thích.', '/product_image/250_502_tai_nghe_bluetooth_samsung_level_u.gif', 1, 'Samsung', 846, 999000),
(39, 2735, 'HOCO W28', 'Tai nghe bluetooth Hoco W28Giới thiệu tai nghe Hoco W28Tai nghe bluetooth Hoco W28 là dòng tai nghe bluetooth on ear giá rẻ mới nhất của hãng âm thanh đế từ Trung Quốc. Đây là dòng tai nghe phổ thông với mức giá rẻ được ưa chuộng tại Trung Quốc nhờ vào các tính năng khá đầy đủ mà hãng mang đến', '/product_image/250_2735_a_hoco_w28_songlongmedia_bluetooth.jpg', 1, 'HOCO', 123, 399000),
(40, 3, 'TAI NGHE SONY EXTRABASS MDR-XB55AP', 'Chỉ trong mấy tháng vừa qua, thị trường âm thanh thế giới đã sục sôi bởi sự xuất hiện của các dòng tai nghe đẳng cấp hoàn toàn mới từ thương hiệu Sony. Sự ra mắt các dòng tai nghe mới đã khiến các tín đồ âm nhạc có thêm những trải nghiệm mới mẻ. Để tiếp tục tiếp lửa cho dòng eXtra bass rất nổi tiếng của mình, Sony mới đây đã ra mắt phiên bản nâng cấp cho chiếc SONY XB50AP của mình mang tên XB55AP với thiết kế được chau chuốt hơn. Tai nghe Sony XB55AP là dòng sản phẩm được ưa chuộng tại Việt Nam.\r\nVới những màu sắc tươi mới như xanh lá, đỏ, xanh biển… với độ màu đậm nét hơn dòng tai nghe XB50AP, sản phẩm mới đã tạo được sự tò mò với nhiều người chơi âm thanh ngay từ khi hãng vừa ra mắt sản phẩm. Từ thiết kế cho đến chất âm, tai nghe Sony XB55AP đã tạo ra sự tò mò với nhiều người chơi âm thanh. Đây là một sản phẩm rất đáng lựa chọn trong dòng eXtra bass của Sony với âm thanh đầy mê hoặc. Là phiên bản nâng cấp từ MDR XB50AP, sự hoàn thiện hơn về chất lượng âm thanh sẽ được mọi người đón chờ. ', '/product_image/250_3_4_sony_mdr_xb55ap_1.jpg', 2, 'Sony', 5624, 670000),
(41, 2641, 'JBL LIVE 100', 'Bạn cần một khoảng thời gian riêng tư, không bị làm phiền bởi những âm thanh thanh bên ngoài, bạn muốn nghe nhạc trong điều kiện hoàn toàn thư giãn sau chuỗi ngày làm việc mệt mỏi vì thế người dùng hãy sắm ngay cho mình một chiếc tai nghe ngay lập tức nhé. Hôm nay mình xin được giới thiệu đến cho người dùng một sản phẩm tai nghe inear đến từ ông lớn JBL đã quá đổi nổi tiếng tại Mỹ được mang tên gọi Tai nghe JBL LIVE 100.', '/product_image/250_2641_tai_nghe_jbl_live_100_black.jpg', 2, 'JBL', 1460, 950000),
(42, 2556, 'JBL QUANTUM 50', 'Để đánh mạnh hơn nữa trong thị trường tai nghe inear, đặc biệt là trong mảng tai nghe hỗ trợ chơi game đa dụng, hãng JBL đã nghiên cứu và tung ra cho mình một chuỗi tai nghe Gaming được gọi tắt là chuỗi tai nghe JBL Quantum từ những chiếc tai chụp đầu vừa mới được ra mắt đã chiếm trọn ham muốn sở hữu của người dùng, cho nên hãng JBL đã cho ra mắt một mẫu tai nghe Inear hỗ trợ gaming cực kì đặc biệt với sự tương thích rất rộng với nhiều thiết bị sử dụng của mọi người dùng được mang tên gọi Tai nghe Gaming JBL Quantum 50 ', '/product_image/250_2556_tai_nghe_gaming_jbl_quantum_50_black.jpg', 2, 'JBL', 152, 890000),
(43, 2386, 'JBL QUANTUM 300\r\n', 'Tai nghe Gaming JBL Quantum 300 sở hữu trên mình là một thiết kế cực kì độc đáo, mang đậm chất phong cách nổi bật, đặc biệt hơn sở hữu trên mình là một trọng lượng cực kì nhẹ nhàng chỉ khoảng 245g mà thôi. Việc tối ưu trọng lượng của sản phẩm ở mức tối đa như thế hãng JBL đã mất khá nhiều tâm huyết bởi sự lựa chọn từ những vật liệu cao cấp có trọng lượng nhẹ để nhằm tăng sự dẻo dai, độ bền cứng cáp theo thời gian dành cho người tiêu dùng ', '/product_image/250_2386_tai_nghe_gaming_jbl_quantum_300.png', 2, 'JBL', 1000, 1990000),
(44, 2387, 'JBL QUANTUM 400', 'Tai nghe Gaming JBL Quantum 400 sở hữu một trọng lượng cực kì nhẹ chỉ rơi khoảng 274g mà thôi. Để có trọng lượng nhẹ như vậy, hãng JBL đã phải sử dụng những chất liệu cao cấp có độ bền cao đồng thời sở hữu trọng lượng cũng phải cực kì nhẹ nhàng, để đem tới độ bền, dẻo dải trong suốt quá trình sử dụng về sau ', '/product_image/250_2387_tai_nghe_gaming_jbl_quantum_400.jpg', 2, 'JBL', 1526, 2490000),
(45, 2385, 'JBL QUANTUM 200', 'Tai nghe Gaming JBL Quantum 200 với tính chất là tai nghe hỗ trợ chơi game cho nên phần đệm đầu cũng như phần đệm tai của sản phẩm đều được hãng thiết kế một cách cực kì tỉ mỉ và công phu với những chất liệu cao cấp, nhằm tăng khả năng thoát kí, thoáng đãng và đặc biệt là phải êm ái không gây đau cấn nhức tai của người dùng trong khoảng thời gian sử dụng hàng giờ đồng hồ chơi game liền', '/product_image/250_2385_tai_nghe_gaming_jbl_quantum_200.jpg', 2, 'JBL', 457, 1390000),
(46, 1604, 'JBL C150SI', 'Thoáng nhìn qua Tai nghe JBL C150SI sở dũng một thiết kế khá là nhỏ gọn , thích hợp bỏ túi để giúp người sử dụng dễ dàng thuận tiện khi đi lại nhiều lần trong ngay , Phần housing được thiết kế bằng nhựa cao cấp với những đường nét và nước sơn mượt mà trơn chu đem mà lại rất nhỏ gọn trên vành tai của người dùng , đem lại những trải nghiệm được nói là cực kì thoải mái ,\r\n\r\nCòn đối với phần dây dẫn , dây dẫn với chiều dài là 1.2m , độ dài vừa phải không quá dài và cũng không quá ngắn để thuận tiện khi các bạn nghe với điện thoại hay trên PC laptop...', '/product_image/250_1604_jbl_c150si_black.jpg', 2, 'JBL', 1526, 349000),
(47, 553, 'SONY MDR-ZX310AP', 'Mặc dù ra mắt cùng lúc với chiếc MDR-ZX110AP nhưng chiếc tai nghe Sony MDR-ZX310AP lại mang đến cho người dùng một ấn tượng đặc biệt với thiết kế độc đáo cùng âm bass chất lượng cao. Bên cạnh đó, với mức giá vô cùng hấp dẫn ZX310AP đang là sản phẩm tai nghe bán chạy nhất tại songlongmedia.com. Vậy chiếc tai nghe này có những điểm khác biệt gì chúng ta sẽ cùng nhau đi tìm hiểu trong bài viết dưới đây.', '/product_image/250_553_1.jpg', 2, 'Sony', 1060, 690000),
(48, 1091, 'SONY MDR-EX155AP', 'Tai nghe Sony EX155ap là dòng tai nghe In Ear có thiết kế trẻ trung, năng động và rất thời trang, thuộc dạng đút tai nhỏ gọn dễ dàng mang theo khi di chuyển. Với thiết kế như vậy vừa làm tăng tính hiệu quả trong cách âm vừa tạo độ bám chắc chắn không bị rơi ra ngoài. Sản phẩm chắc chắn là sự lựa chọn hàng đầu cho những bạn trẻ yêu thích vận động, thể thao.', '/product_image/250_1091_a_sony_mdr_ex155ap.jpg', 2, 'Sony', 6548, 390000),
(49, 1084, 'SONY MDR-ZX110AP', 'ZX110AP và ZX310AP là sản phẩm nằm trong phân khúc tai nghe giá rẻ được ưa chuộng nhất của thương hiệu Sony ở thời điểm hiện tại. Với thiết kế trẻ trung, hiện đại cùng khả năng chống ồn tuyệt vời chiếc tai nghe Sony ZX110AP là lựa chọn hàng đầu của những người có khả năng tài chính eo hẹp như các bạn sinh viên, học sinh. Và bài viết này chúng ta sẽ cùng đi sâu phân tích, tìm hiểu sự khác biệt của chiếc tai nghe giá rẻ mà chất âm không hề rẻ này.', '/product_image/250_1084_1.jpg', 2, 'Sony', 846, 439000),
(50, 1085, 'SONY MDR-XB250', 'Tai nghe on-ear dành cho điện thoại di động, máy tính bảng \r\nSử dụng driver 30mm cho âm thanh mạnh mẽ\r\nDải tần số thấp (5hz-22khz) mở rộng giúp âm bộ gõ, âm bass và bass trầm trở nên mạnh mẽ hơn. \r\nThiết kế gọn với earcup có thể xoay \r\nCáp răng cưa siêu bền giúp giảm rối dây\r\nĐệm tai nghe mềm mại tạo cảm giác thoải mái khi đeo \r\nCho phép bạn nghe - và cảm nhận âm nhạc - với một sự thích thú bất ngờ', '/product_image/250_1085_a_sony_mdr_xb250_black__2.jpg', 2, 'Sony', 487, 499000),
(51, 1552, 'SONY IER M9', 'Tai nghe Sony IER M9 là 1 trong 2 dòng tai nghe in-ear cao cấp ra mắt tại triển lãm Hong Kong Hi-End AV Show 2018 dành cho đối tượng khách hàng chuyên nghiệp cũng như các audiophile khó tính nhất.\r\nÂm thanh của IER-M9 có độ chững chạc và thoáng đãng, có được độ tách lớp cao giúp cho công việc phòng thu hoặc các audiophile thích chất âm trong trẻo chính xác.\r\nIER-M9 sử dụng 5 driver BA gồm có 1 woofer bass, 1 full-range xử lý giải âm low-mid, 1 full-range xử lý giải âm high-mid, 1 tweeter treble, và hơn cả đó là 1 chiếc super-tweeter được sử dụng trong mẫu đầu bảng IER-Z1R', '/product_image/250_1552_a_tai_nghe_sony_m9.jpg', 2, 'Sony', 8764, 21590000),
(52, 2428, 'HOCO ES39 TRUE WIRELESS', '<p><strong style=\"font-weight: bold; color: rgb(51, 51, 51); font-family: Roboto, arial, verdana, sans-serif; text-align: justify;\">Hoco</strong><span style=\"color: rgb(51, 51, 51); font-family: Roboto, arial, verdana, sans-serif; text-align: justify;\">&nbsp;mang đến cho người dùng khá nhiều sản phẩm cực kì chất lượng với những tính năng công nghệ tuyệt vời, không có điểm gì phải chê trách, mới đây để tiếp nối những thành công của mình trong chuỗi tai nghe không dây hoàn toàn hãng Hoco đã đem tới cho người dùng một sản phẩm được mang tên&nbsp;Tai nghe True Wireless&nbsp;</span><strong style=\"font-weight: bold; color: rgb(51, 51, 51); font-family: Roboto, arial, verdana, sans-serif; text-align: justify;\">Hoco ES39</strong><span style=\"color: rgb(51, 51, 51); font-family: Roboto, arial, verdana, sans-serif; text-align: justify;\">&nbsp;thừa hưởng được những tính năng công nghệ của những người đàn anh đi trước</span><img src=\"http://localhost/image/2428_tai-nghe-true-wirelese-hoco-es39.jpg\" style=\"width: 600.137px;\"></p><p>                                          \n                                        </p>', '/product_image/250_2428_tai_nghe_hoco_es39_true_wireless.jpg', 1, 'HOCO', 124, 699000);

-- --------------------------------------------------------

--
-- Table structure for table `product_lines`
--

CREATE TABLE `product_lines` (
  `id` int(10) NOT NULL,
  `product_line` varchar(50) NOT NULL,
  `description` varchar(2500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `product_lines`
--

INSERT INTO `product_lines` (`id`, `product_line`, `description`) VALUES
(1, 'Bluetooth', 'Tai nghe Bluetooth hay tai nghe không dây là thiết bị truyền âm thanh từ nguồn phát (máy nghe nhạc, điện thoại, máy tính bảng) nhờ công nghệ truyền dẫn không dây Bluetooth.'),
(2, 'Wire', 'Loại tai nghe có thiết kế phần củ loa (driver) với ống dẫn âm nhỏ gọn, thuôn dài, dễ dàng tiến sâu vào trong tai để truyền âm nhanh và cách âm tốt hơn.');

-- --------------------------------------------------------

--
-- Table structure for table `rates`
--

CREATE TABLE `rates` (
  `id` int(10) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `orderdetails_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `rate_score` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rates`
--

INSERT INTO `rates` (`id`, `comments`, `orderdetails_id`, `product_id`, `customer_id`, `create_date`, `update_date`, `rate_score`) VALUES
(135, 'Cũng được, nhưng hơi đau tai', 103, 7, 84, '2021-07-10 20:22:26', '2021-07-10 20:22:26', 3),
(136, 'Chất', 105, 13, 84, '2021-07-10 20:22:34', '2021-07-10 20:22:34', 5),
(139, 'Tạm', 93, 4, 85, '2021-07-10 21:48:47', '2021-07-10 21:48:47', 3),
(140, 'Hàng ok', 108, 14, 85, '2021-07-10 21:48:55', '2021-07-10 21:48:55', 5),
(141, 'hàng chất lượng', 109, 19, 74, '2021-07-12 10:21:58', '2021-07-12 10:21:58', 5),
(145, 'hàng lỗi ', 88, 12, 84, '2021-07-14 11:52:47', '2021-07-14 11:52:47', 2),
(146, 'ok', 111, 8, 144, '2021-07-14 20:21:01', '2021-07-14 20:21:01', 5);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `phone_number`, `level`) VALUES
(1, 'huyvq', '123456', 'Vũ Quang Huy', '353443440', 'customer'),
(7, 'hoalt', '123456', 'Lê Thị Hoa', '0353443550', 'customer'),
(8, 'namng123', '123456', 'Nguyễn Huỳnh Phương Nam', '022456871', 'customer'),
(9, 'admin', 'admin', 'administrator', '032654987', 'admin'),
(14, 'hanhprovip123', '123456', 'Nguyễn Thị Hạnh', '02518971148', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `phone_number` (`phone_number`),
  ADD UNIQUE KEY `UK_euat1oase6eqv195jvb71a93s` (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_orders_customers` (`customer_id`) USING BTREE;

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `order_details_orders` (`order_id`) USING BTREE,
  ADD KEY `fk_order_details_product` (`product_id`) USING BTREE;

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `uk_product_code` (`product_code`) USING BTREE,
  ADD KEY `product_line_id` (`product_line_id`) USING BTREE;

--
-- Indexes for table `product_lines`
--
ALTER TABLE `product_lines`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `product_line` (`product_line`) USING BTREE;

--
-- Indexes for table `rates`
--
ALTER TABLE `rates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_dcm8vqrjbb5li47l1kl9haidx` (`orderdetails_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `phone_number` (`phone_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `product_lines`
--
ALTER TABLE `product_lines`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_order_details_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_details_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`product_line_id`) REFERENCES `product_lines` (`id`);

--
-- Constraints for table `rates`
--
ALTER TABLE `rates`
  ADD CONSTRAINT `FKrc2rrqf6sx1cc21e9yuyduf4d` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `fk_orderdetails` FOREIGN KEY (`orderdetails_id`) REFERENCES `order_details` (`id`),
  ADD CONSTRAINT `fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
