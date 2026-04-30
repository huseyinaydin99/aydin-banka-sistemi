package tr.com.huseyinaydin.business.rules;

import tr.com.huseyinaydin.entity.CustomerAccount;
import tr.com.huseyinaydin.entity.ElectricBill;
import tr.com.huseyinaydin.exception.ValidationException;

import java.math.BigDecimal;

public class ElectricBillRules {

    public static void checkBillExists(ElectricBill bill) {
        if (bill == null) {
            throw new ValidationException("Fatura bulunamadı");
        }
    }

    public static void checkBillIsNotPaid(ElectricBill bill) {
        if (bill.getIsPaid()) {
            throw new ValidationException("Fatura zaten ödenmiş");
        }
    }

    public static void checkAccountExists(CustomerAccount account) {
        if (account == null) {
            throw new ValidationException("Hesap bulunamadı");
        }
    }

    public static void checkBalanceIsSufficient(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new ValidationException("Yetersiz bakiye");
        }
    }
}
