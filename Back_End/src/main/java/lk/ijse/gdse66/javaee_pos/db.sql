drop database javaee_pos;
create database javaee_pos;
use javaee_pos;

create table customer(
                         id VARCHAR(100) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         mobile VARCHAR(15) UNIQUE NOT NULL,
                         nic VARCHAR(20) UNIQUE NOT NULL,
                         city VARCHAR(35) NOT NULL,
                         street VARCHAR(100) NOT NULL
);

create table item(
                     id VARCHAR(100) PRIMARY KEY,
                     item_category VARCHAR(100) NOT NULL,
                     unit_price DECIMAL NOT NULL,
                     qty INT NOT NULL,
                     item_description TEXT NOT NULL
);

create table `order`(
                        id VARCHAR(100) PRIMARY KEY,
                        date date NOT NULL
);

create table order_detail(
                             orderId VARCHAR(100),
                             itemId VARCHAR(100),
                             ordered_qty INT,
                             total_price DECIMAL,
                             CONSTRAINT FOREIGN KEY (orderId) REFERENCES `order`(id),
                             CONSTRAINT FOREIGN KEY (itemId) REFERENCES item(id)
);