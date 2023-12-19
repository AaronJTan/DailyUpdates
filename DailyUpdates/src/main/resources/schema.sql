CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  enabled boolean NOT NULL DEFAULT TRUE,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (username),
  UNIQUE KEY (email)
);

CREATE TABLE user_roles (
  user_id bigint NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id),
  KEY (role_id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) UNIQUE NOT NULL
);

CREATE TABLE languages (
    id int PRIMARY KEY AUTO_INCREMENT,
    language_code varchar(3) UNIQUE NOT NULL
);

CREATE TABLE countries (
    id int PRIMARY KEY AUTO_INCREMENT,
    country_code varchar(3) UNIQUE NOT NULL
);

CREATE TABLE sources (
    id varchar(50) PRIMARY KEY NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    url varchar(255) NOT NULL,
    category_id int NOT NULL,
    language_id int NOT NULL,
    country_id int NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id),
    FOREIGN KEY (language_id) REFERENCES languages (id),
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

CREATE TABLE feeds (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT UC_FeedName UNIQUE (user_id,name),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE feed_sources (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    feed_id bigint NOT NULL,
    source_id varchar(50) NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT UC_FeedSource UNIQUE (feed_id,source_id),
    FOREIGN KEY (feed_id) REFERENCES feeds(id) ON DELETE CASCADE,
    FOREIGN KEY (source_id) REFERENCES sources(id)
);

CREATE TABLE favourite_articles (
     id bigint PRIMARY KEY AUTO_INCREMENT,
     userid bigint NOT NULL,
     article_json json NOT NULL,
     created_at timestamp DEFAULT CURRENT_TIMESTAMP,
     updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (userid) REFERENCES users(id)
);

