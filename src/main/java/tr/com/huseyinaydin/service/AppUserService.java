package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.dto.AppUserUpdateDto;

public interface AppUserService {
    void register(AppUserRegisterDto appUserRegisterDto);
    void updateProfile(String username, AppUserUpdateDto appUserUpdateDto);
}
