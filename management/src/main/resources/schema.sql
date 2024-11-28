CREATE TABLE IF NOT EXISTS `book` (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  isbn_number VARCHAR(20) NOT NULL UNIQUE,
  publish_date VARCHAR(20) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  book_type VARCHAR(20) NOT NULL,
  author VARCHAR(100) DEFAULT NULL,
  number_of_pages BIGINT DEFAULT NULL,
  created_at DATETIME DEFAULT NULL,
  last_modified_at DATETIME DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;