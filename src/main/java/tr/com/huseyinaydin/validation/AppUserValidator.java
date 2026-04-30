package tr.com.huseyinaydin.validation;

import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.exception.ValidationException;

public class AppUserValidator {
    public static void validate(AppUserRegisterDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ValidationException("Ad alanı boş geçilemez");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().isEmpty()) {
            throw new ValidationException("Soyad alanı boş geçilemez");
        }
        if (dto.getEmail() == null || !dto.getEmail().contains("@")) {
            throw new ValidationException("Geçerli bir e-posta adresi giriniz");
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new ValidationException("Şifre en az 6 karakter olmalıdır");
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new ValidationException("Şifreler uyuşmuyor");
        }
    }
}
