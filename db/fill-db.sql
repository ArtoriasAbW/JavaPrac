INSERT INTO clients (client_name, phone_number, email, client_address, registration_date, client_type) VALUES
    ('Иванов Алексей Дмитриевич', '+79433234610', 'ivalex@mail.ru', 'г.Москва ул.Пушкина д.6', '28-11-2020', 'частное лицо'),
    ('Поляков Максим Александрович', '+79775693312', 'maxpolyak@gmail.com', 'г.Москва Ломоносовский проспект д.22 к.2', '05-07-2020', 'частное лицо'),
    ('Новиков Дмитрий Андреевич', '79672315476', 'dm_novikov@mail.ru', 'г.Москва ул.Попова д.7', '03-11-2020', 'частное лицо'),
    ('Морозов Павел Ильич', '+79322427920', 'morozov_pi@ya.ru', 'г.Москва ул.Мичурина д.11 к.5', '11-04-2020', 'частное лицо');

INSERT INTO departments (department_name, department_address, department_phone_number) VALUES
    ('Солнцевский', 'г.Москва ул.Ленина д.22', '+79436321042'),
    ('Центральный', 'г.Москва ул.Центральная д.11', '+79142432424');

INSERT INTO account_types (type_name, max_credit, profitability, additional_info) VALUES
    ('Базовый', 150000, 7, '');


INSERT INTO accounts (account_number, account_status, client, account_balance, department, account_type, opening_date) VALUES
    ('756363456346', 'активный', 1, 80000, 1, 1, '28-11-2020'),
    ('143242323432', 'активный', 2, 950000, 1, 1, '11-04-2016'),
    ('754632342544', 'активный', 3, 2000, 2, 1, '01-02-2021'),
    ('645345645543', 'активный', 4, 54353, 1, 1, '22-11-2019');

INSERT INTO account_history (transaction_id, transaction_date, sender, receiver, amount) VALUES
    ('323236546365434', '01-01-2021 14:32:11', '756363456346', '143242323432', 10003),
    ('654645624324234', '20-02-2021 23:23:32', '756363456346', '754632342544', 2000),
    ('675453423145635', '26-12-2020 04:32:21', '143242323432', '645345645543', 32133);
