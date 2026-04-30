package tr.com.huseyinaydin.mapper;

import org.mapstruct.Mapper;
import tr.com.huseyinaydin.dto.ElectricBillDto;
import tr.com.huseyinaydin.entity.ElectricBill;

@Mapper(componentModel = "spring")
public interface ElectricBillMapper {
    ElectricBillDto toDto(ElectricBill entity);
    ElectricBill toEntity(ElectricBillDto dto);
}