DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
                        id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                        firstName VARCHAR(15) NOT NULL,
                        lastName VARCHAR(40) NOT NULL,
                        middle_name VARCHAR(100),
                        birthday DATE NOT NULL,
                        role VARCHAR(10) NOT NULL,
                        gender SMALLINT CHECK (gender IN (0, 1)),
                        registration_date DATE NOT NULL
);

CREATE TABLE cards (
                      id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                      client_id BIGINT NOT NULL,
                      pan_encrypted BYTEA NOT NULL,
                      pan_masked VARCHAR(19) NOT NULL,
                      validity_period DATE NOT NULL,
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

