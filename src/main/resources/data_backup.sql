-- Verileri temizle (İlişki sırasına göre)
DELETE FROM customer_account_processes;
DELETE FROM credit_cards;
DELETE FROM customer_accounts;
DELETE FROM notifications;
DELETE FROM electric_bills;
DELETE FROM contacts;
DELETE FROM users;

-- Örnek Kullanıcılar (Şifreler: 123456 - BCrypt ile encode edilmiştir)
INSERT INTO users (name, surname, district, city, image_url, confirm_code, email, username, password) VALUES
('Hüseyin', 'Aydın', 'Kadıköy', 'İstanbul', '/user.png', 123456, 'huseyin@example.com', 'huseyinaydin', '$2a$10$aDmO3fFAgnPNgBYQkkJgGOh6T7U1vZBJ/BSLlro45EvP/02OSPsN.'),
('Ahmet', 'Yılmaz', 'Çankaya', 'Ankara', '/user.png', 234567, 'ahmet@example.com', 'ahmetyilmaz', '$2a$10$aDmO3fFAgnPNgBYQkkJgGOh6T7U1vZBJ/BSLlro45EvP/02OSPsN.'),
('Ayşe', 'Demir', 'Konak', 'İzmir', '/user.png', 345678, 'ayse@example.com', 'aysedemir', '$2a$10$aDmO3fFAgnPNgBYQkkJgGOh6T7U1vZBJ/BSLlro45EvP/02OSPsN.');

-- Örnek Banka Hesapları (TRY, USD, EUR)
INSERT INTO customer_accounts (account_number, cvc, expiration_date, currency, balance, bank_branch, user_id) VALUES 
('TR123456789012345678901234', 123, '12/28', 'TRY', 25750.00, 'Kadıköy Şubesi', 1),
('TR987654321098765432109876', 456, '10/27', 'USD', 3400.00, 'Kadıköy Şubesi', 1),
('TR555544443333222211110000', 999, '01/29', 'EUR', 1500.00, 'Kadıköy Şubesi', 1),
('TR112233445566778899001122', 789, '05/26', 'EUR', 1200.00, 'Merkez Şube', 2),
('TR334455667788990011223344', 321, '08/29', 'TRY', 5400.75, 'Konak Şubesi', 3),
('TR888877776666555544443333', 111, '03/30', 'USD', 500.00, 'Konak Şubesi', 3);

-- Örnek Hesap Hareketleri (Özellikle Hüseyin Aydın için bolca veri)
INSERT INTO customer_account_processes (process_type, amount, process_date, description, sender_id, receiver_id) VALUES 
('Para Transferi', 1500.00, '2026-05-02 16:30:00', 'Kira Nisan 2026', 1, 3),
('EFT', 2500.00, '2026-05-02 15:45:00', 'Maaş Ödemesi', 2, 1),
('Para Transferi', 200.00, '2026-05-02 14:20:00', 'Market Alışverişi Borcu', 3, 1),
('Para Transferi', 100.00, '2026-05-02 12:10:00', 'Kahve Ismarlama', 1, 2),
('Para Transferi', 750.00, '2026-05-01 18:00:00', 'Fatura Ödemesi Yardım', 1, 3),
('Para Transferi', 1200.00, '2026-05-01 10:00:00', 'Borç İadesi', 2, 1),
('EFT', 3000.00, '2026-04-30 09:00:00', 'Freelance Proje Ödemesi', 3, 1),
('Para Transferi', 50.00, '2026-04-29 21:30:00', 'Yemek Paylaşımı', 1, 2),
('Para Transferi', 450.00, '2026-04-29 14:00:00', 'İnternet Alışverişi', 1, 3),
('Para Transferi', 1000.00, '2026-04-28 11:15:00', 'Haftalık Harçlık', 2, 1),
('Para Transferi', 250.00, '2026-04-27 16:45:00', 'Sinema ve Etkinlik', 1, 3),
('EFT', 5000.00, '2026-04-25 08:30:00', 'Eski Borç Kapatma', 3, 1),
('Para Transferi', 150.00, '2026-04-24 19:20:00', 'Kitap Alımı', 1, 2),
('Para Transferi', 600.00, '2026-04-23 13:10:00', 'Spor Salonu Üyeliği', 1, 3),
('Para Transferi', 2000.00, '2026-04-22 10:00:00', 'Yatırım Transferi', 2, 1);

-- Örnek Bildirimler
INSERT INTO notifications (title, content, date, is_read) VALUES 
('Hoş Geldiniz', 'Aydın Banka Sistemine hoş geldiniz!', '2026-04-20 08:00:00', true),
('Yeni Transfer', 'Hesabınıza 2500 TL tutarında bir transfer yapıldı.', '2026-05-02 15:46:00', false),
('Güvenlik Uyarısı', 'Hesabınıza yeni bir cihazdan giriş yapıldı.', '2026-05-01 22:00:00', false),
('Limit Artışı', 'Kredi kartı limit artış talebiniz onaylandı.', '2026-04-30 14:00:00', true),
('Kampanya', 'Hafta sonuna özel alışverişlerde %10 nakit iade!', '2026-05-01 09:00:00', false),
('Sistem Bakımı', '03.05.2026 tarihinde planlı bakım çalışması yapılacaktır.', '2026-05-02 10:00:00', false),
('Şifre Değişikliği', 'Şifreniz başarıyla güncellendi.', '2026-04-28 16:00:00', true);

-- Örnek Kredi Kartları
INSERT INTO credit_cards (card_number, card_holder_name, expiration_date, cvc, user_id) VALUES 
('4543 1234 5678 9012', 'Hüseyin Aydın', '12/30', '456', 1),
('5412 9876 5432 1098', 'Ahmet Yılmaz', '05/29', '789', 2),
('4000 1111 2222 3333', 'Ayşe Demir', '08/31', '123', 3);

-- Örnek Elektrik Faturaları
INSERT INTO electric_bills (bill_number, customer_name, amount, is_paid) VALUES 
('ELEK-2026-001', 'Hüseyin Aydın', 250.75, false),
('ELEK-2026-002', 'Ahmet Yılmaz', 180.50, true),
('ELEK-2026-003', 'Ayşe Demir', 320.00, false),
('ELEK-2026-004', 'Hüseyin Aydın', 145.20, true),
('ELEK-2026-005', 'Mehmet Öz', 210.00, false),
('ELEK-2026-006', 'Canan Tan', 195.50, true);

-- Örnek İletişim Mesajları
INSERT INTO contacts (name, email, subject, message, date) VALUES 
('Mehmet Can', 'mehmet@test.com', 'Hesap Açılışı', 'Yeni bir ticari hesap açmak istiyorum.', '2026-05-02 11:00:00'),
('Selin Su', 'selin@test.com', 'Kredi Kartı', 'Kredi kartı limitimi artırmak istiyorum.', '2026-05-02 15:00:00'),
('Ali Veli', 'ali@test.com', 'Mobil Uygulama', 'Mobil uygulamada hata alıyorum.', '2026-05-01 14:00:00'),
('Zeynep Ak', 'zeynep@test.com', 'Döviz Kurları', 'Kurlar çok geç güncelleniyor.', '2026-05-01 09:30:00');
