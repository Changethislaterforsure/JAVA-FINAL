

BEGIN;


DROP TABLE IF EXISTS memberships CASCADE;
DROP TABLE IF EXISTS workout_classes CASCADE;
DROP TABLE IF EXISTS merchandise CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ===== USERS =====
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL
);

-- ===== MEMBERSHIPS =====
CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    description TEXT,
    cost DECIMAL(10, 2) NOT NULL,
    member_id INTEGER REFERENCES users(user_id) ON DELETE CASCADE
);

-- ===== WORKOUT CLASSES =====
CREATE TABLE workout_classes (
    class_id SERIAL PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    description TEXT,
    date DATE,
    time TIME,
    trainer_id INTEGER REFERENCES users(user_id) ON DELETE SET NULL
);

-- ===== MERCHANDISE =====
CREATE TABLE merchandise (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    description VARCHAR(100),
    price DECIMAL(10, 2) NOT NULL,
    quantity INTEGER NOT NULL
);

COMMIT;