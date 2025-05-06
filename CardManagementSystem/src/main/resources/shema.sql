DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS client;
DROP TYPE card_status, type_transaction;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE client (
                        id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                        firstName VARCHAR(15) NOT NULL,
                        lastName VARCHAR(40) NOT NULL,
                        middle_name VARCHAR(100),
                        birthday DATE NOT NULL,
                        gender SMALLINT CHECK (gender IN (0, 1)),
                        registration_date DATE NOT NULL
);

CREATE TYPE card_status AS ENUM ('ACTIVE', 'BLOCKED', 'EXPIRED', 'INACTIVE', 'CLOSED');

CREATE TABLE card (
                      id BIGINT GENERATED ALWAYS AS IDENTITY UNIQUE,
                      client_id BIGINT NOT NULL,
                      pan_encrypted BYTEA NOT NULL,
                      pan_masked VARCHAR(19) NOT NULL,
                      validity_period DATE NOT NULL,
                      status card_status NOT NULL,
                      amount BIGINT,
                      FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TYPE type_transaction AS ENUM ('CREDIT', 'DEBIT');

CREATE TABLE transactions (
                     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                     client_id BIGINT NOT NULL,
                     card_id BIGINT NOT NULL,
                     type type_transaction NOT NULL,
                     amount BIGINT NOT NULL,
                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                     FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE,
                     FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE
);

