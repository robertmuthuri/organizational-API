SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY auto_increment,
  full_name VARCHAR,
  position VARCHAR,
  role VARCHAR,
  department_id INTEGER
);

CREATE TABLE IF NOT EXISTS departments (
  id SERIAL PRIMARY KEY auto_increment,
  name VARCHAR,
  description VARCHAR,
  total_employees INTEGER
);

CREATE TABLE IF NOT EXISTS news (
  id SERIAL PRIMARY KEY  auto_increment,
    heading VARCHAR,
    news_link VARCHAR,
    news_type VARCHAR
);

CREATE TABLE IF NOT EXISTS department_news (
 id int PRIMARY KEY auto_increment,
 heading VARCHAR,
 news_link VARCHAR,
);

CREATE TABLE IF NOT EXISTS departments_departmentnews (
 id int PRIMARY KEY auto_increment,
 department_id INTEGER,
 departmentnews_id INTEGER
);