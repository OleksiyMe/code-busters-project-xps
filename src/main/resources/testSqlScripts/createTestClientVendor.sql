insert into addresses(id,insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                      address_line1, address_line2, city, state, country, zip_code)
values (999, '2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        '7925 Jones Branch Dr, #3300', 'Tysons', 'Test-SinCity', 'VA', 'United States', '22102-1234');

insert into clients_vendors(id,insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                            client_vendor_type, client_vendor_name, phone, website, address_id, company_id)
values
-- TestValue
(999,'2022-09-15T00:00',2, false,'2022-09-15T00:00',2,'CLIENT','Field To Update', '+1 (111) 123-4567', 'https://www.test.com', 999, 2),
(1000,'2022-09-15T00:00',2, false,'2022-09-15T00:00',2,'CLIENT','Field To Update', '+1 (111) 123-4567', 'https://www.test.com', 999, 2);


insert into companies(id,insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                      title, phone, website, address_id, company_status)
values (999, '2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'TEST','+1 (652) 852-8888', 'https://www.test.com', 1, 'ACTIVE');



insert into users(id, insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                  username, password, firstname, lastname, phone, role_id, company_id, enabled)
values
-- COMPANY-1 / CYDEO / ROOT USER
(999, '2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'admin@admin.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Robert', 'Martin', '+1 (852) 564-5874', 1, 999, true);



insert into invoices(id,insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                     date,invoice_no, invoice_type, invoice_status, client_vendor_id, company_id)
values
-- COMPANY-2 / Green Tech
(999, '2022-09-09 00:00', 2, 'false', '2022-09-09 00:00', 2, '2022-09-09', 'P-001', 'PURCHASE', 'APPROVED', 999, 999);
