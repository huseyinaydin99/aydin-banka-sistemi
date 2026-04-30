package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.business.rules.AppUserRules;
import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.dto.AppUserUpdateDto;
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

    @Override
    @Transactional
    public void updateProfile(String username, AppUserUpdateDto updateDto) {
        AppUser user = appUserRepository.findByUsername(username).orElse(null);
        AppUserRules.checkUserExists(user);

        if (updateDto.getEmail() != null && !updateDto.getEmail().equals(user.getEmail())) {
            AppUserRules.checkEmailIsUnique(appUserRepository.findByEmail(updateDto.getEmail()).isPresent());
            user.setEmail(updateDto.getEmail());
        }

        if (updateDto.getName() != null) user.setName(updateDto.getName());
        if (updateDto.getSurname() != null) user.setSurname(updateDto.getSurname());
        if (updateDto.getCity() != null) user.setCity(updateDto.getCity());
        if (updateDto.getDistrict() != null) user.setDistrict(updateDto.getDistrict());
        if (updateDto.getImageUrl() != null) user.setImageUrl(updateDto.getImageUrl());
        
        if (updateDto.getPassword() != null && !updateDto.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        appUserRepository.save(user);
    }
}
