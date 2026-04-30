package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.entity.AppUser;
import tr.com.huseyinaydin.mapper.AppUserMapper;
import tr.com.huseyinaydin.repository.AppUserRepository;
import tr.com.huseyinaydin.service.AppUserService;
import tr.com.huseyinaydin.validation.AppUserValidator;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(AppUserRegisterDto appUserRegisterDto) {
        AppUserValidator.validate(appUserRegisterDto);
        AppUser appUser = appUserMapper.toEntity(appUserRegisterDto);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }
}
