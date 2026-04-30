package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.dto.CustomerAccountDto;
import tr.com.huseyinaydin.entity.CustomerAccount;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {
    @Mapping(target = "appUser.id", source = "userId")
    @Mapping(target = "sentProcesses", ignore = true)
    @Mapping(target = "receivedProcesses", ignore = true)
    CustomerAccount toEntity(CustomerAccountDto dto);

    @Mapping(target = "userId", source = "appUser.id")
    CustomerAccountDto toDto(CustomerAccount entity);
}
