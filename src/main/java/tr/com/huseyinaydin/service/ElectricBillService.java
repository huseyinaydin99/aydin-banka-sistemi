package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.ElectricBillDto;

public interface ElectricBillService {
    ElectricBillDto getBillDetails(String billNumber);
    void payBill(String billNumber, Integer accountId);
}