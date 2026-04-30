package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.dto.CustomerAccountProcessDto;
import tr.com.huseyinaydin.entity.CustomerAccountProcess;

@Mapper(componentModel = "spring")
public interface CustomerAccountProcessMapper {
    @Mapping(target = "senderCustomer.id", source = "senderId")
    @Mapping(target = "receiverCustomer.id", source = "receiverId")
    CustomerAccountProcess toEntity(CustomerAccountProcessDto dto);

    @Mapping(target = "senderId", source = "senderCustomer.id")
    @Mapping(target = "receiverId", source = "receiverCustomer.id")
    @Mapping(target = "receiverAccountNumber", ignore = true)
    CustomerAccountProcessDto toDto(CustomerAccountProcess entity);
}
