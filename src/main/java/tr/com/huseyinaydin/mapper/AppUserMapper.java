package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.dto.AppUserRegisterDto;
import tr.com.huseyinaydin.entity.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerAccounts", ignore = true)
    @Mapping(target = "creditCards", ignore = true)
    @Mapping(target = "confirmCode", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "district", ignore = true)
    @Mapping(target = "city", ignore = true)
    AppUser toEntity(AppUserRegisterDto dto);
}
