package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.dto.CreditCardDto;
import tr.com.huseyinaydin.entity.CreditCard;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    @Mapping(target = "userId", source = "appUser.id")
    CreditCardDto toDto(CreditCard entity);

    @Mapping(target = "appUser.id", source = "userId")
    CreditCard toEntity(CreditCardDto dto);
}