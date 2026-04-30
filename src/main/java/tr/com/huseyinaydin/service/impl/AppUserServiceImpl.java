package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.mapper.AppUserMapper;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.AppUserService;
import tr.com.huseyinaydin.service.MailService;
import tr.com.huseyinaydin.validation.AppUserValidator;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public void register(AppUserRegisterDto appUserRegisterDto) {
        AppUserValidator.validate(appUserRegisterDto);
        AppUser appUser = appUserMapper.toEntity(appUserRegisterDto);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        appUser.setConfirmCode(code);
        
        appUserRepository.save(appUser);
        
        mailService.sendMail(appUser.getEmail(), "E-Posta Onay Kodu", "Onay kodunuz: " + code);
    }
}
