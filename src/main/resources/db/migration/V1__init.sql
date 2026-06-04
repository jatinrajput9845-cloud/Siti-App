CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','EMPLOYEE','CUSTOMER') NOT NULL,
    status ENUM('ACTIVE','BLOCKED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(100),
    shop_name VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(100),
    mobile VARCHAR(15),
    email VARCHAR(100),
    address TEXT,
    created_by_employee_id BIGINT,
    status ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (created_by_employee_id) REFERENCES employees(id)
);

CREATE TABLE customer_service (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    stb_number VARCHAR(50),
    vc_number VARCHAR(50),
    plan_name VARCHAR(100),
    plan_price DECIMAL(10,2),
    validity_days INT,
    start_date DATE,
    end_date DATE,
    status ENUM('ACTIVE','EXPIRED','SUSPENDED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE recharges (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    service_id BIGINT,
    amount DECIMAL(10,2),
    payment_mode ENUM('CASH','UPI','CARD'),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    validity_till DATE,
    recharged_by_employee_id BIGINT
);

CREATE TABLE notifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    type ENUM('SMS','EMAIL'),
    message TEXT,
    status ENUM('SENT','FAILED') DEFAULT 'SENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);