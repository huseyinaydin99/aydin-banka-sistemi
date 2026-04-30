package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import tr.com.huseyinaydin.dto.ContactDto;
import tr.com.huseyinaydin.entity.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toEntity(ContactDto dto);
    ContactDto toDto(Contact entity);
}
