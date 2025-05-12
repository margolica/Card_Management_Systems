-- Добавление ролей
INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

INSERT INTO users (
    username, password, email, first_name, last_name, middle_name,
    birthday, gender, registration_date, role
) VALUES
      (
          'user', 'user', 'ivan@example.com', 'Иван', 'Иванов', 'Иванович',
          '1990-05-12 00:00:00', 'MALE', NOW(), 'ROLE_USER'
      ),
      (
          'anna_admin', 'secure456', 'anna@example.com', 'Анна', 'Петрова', 'Сергеевна',
          '1988-09-23 00:00:00', 'FEMALE', NOW(), 'ROLE_ADMIN'
      );


-- Привязка пользователей к ролям (с учетом ID, сгенерированных выше)
-- Предполагается: Иван = id 1, Анна = id 2; ROLE_USER = 1, ROLE_ADMIN = 2
INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1),
                                               (2, 2);

-- Добавление карт
INSERT INTO cards (client_id, pan_encrypted, pan_masked, validity_period, status, amount) VALUES
                                                                                              (1, 'pwogrhuerpiuhgbnedrfipsbhnubv', '1234********5678', '2026-12-31 00:00:00', 'ACTIVE', 100000),
                                                                                              (2, 'lsidgjfhbpiughperiushrgpieurh', '4321********8765', '2025-06-30 00:00:00', 'BLOCKED', 50000);

-- Добавление транзакций
INSERT INTO transactions (user_id, card_id, type, amount) VALUES
                                                                (1, 1, 'DEBIT', 5000),
                                                                (1, 1, 'CREDIT', 15000),
                                                                (2, 2, 'DEBIT', 2000);
