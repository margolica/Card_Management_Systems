DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
                       id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       first_name VARCHAR(15) NOT NULL,
                       last_name VARCHAR(40) NOT NULL,
                       middle_name VARCHAR(100),
                       birthday TIMESTAMP NOT NULL,
                       role VARCHAR(20) NOT NULL CHECK (role IN ('ROLE_USER', 'ROLE_ADMIN')),
                       gender SMALLINT CHECK (gender IN (0, 1)),
                       registration_date TIMESTAMP NOT NULL
);
CREATE TABLE roles (
                       id SMALLINT GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
                       name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL,
                             role_id SMALLINT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE cards (
                       id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                       client_id BIGINT NOT NULL,
                       pan_encrypted BYTEA NOT NULL,
                       pan_masked VARCHAR(19) NOT NULL,
                       validity_period TIMESTAMP NOT NULL,
                       status VARCHAR(10) NOT NULL,
                       amount BIGINT,
                       FOREIGN KEY (client_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE transactions (
                              id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              client_id BIGINT NOT NULL,
                              card_id BIGINT NOT NULL,
                              type VARCHAR(7) NOT NULL,
                              amount BIGINT NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (client_id) REFERENCES users (id) ON DELETE CASCADE,
                              FOREIGN KEY (card_id) REFERENCES cards (id) ON DELETE CASCADE
);

