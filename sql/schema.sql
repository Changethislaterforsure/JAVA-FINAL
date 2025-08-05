-- Users Table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL
);

-- Memberships Table
CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    membership_description TEXT,
    membership_cost DECIMAL(10, 2) NOT NULL,
    member_id INTEGER REFERENCES users(user_id) ON DELETE CASCADE
);

-- Workout Classes Table
CREATE TABLE workout_classes (
    workout_class_id SERIAL PRIMARY KEY,
    class_type VARCHAR(50) NOT NULL,
    class_description TEXT,
    trainer_id INTEGER REFERENCES users(user_id) ON DELETE SET NULL
);

-- Gym Merchandise Table
CREATE TABLE gym_merch (
    merch_id SERIAL PRIMARY KEY,
    merch_name VARCHAR(100) NOT NULL,
    merch_type VARCHAR(50),
    merch_price DECIMAL(10, 2) NOT NULL,
    quantity_in_stock INTEGER NOT NULL
);