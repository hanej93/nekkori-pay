package com.nekkoripay.banking.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registered_bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {
    @Id
    @GeneratedValue
    private Long registeredBankAccountId;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    public RegisteredBankAccountJpaEntity(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }

    @Override
    public String toString() {
        return "RegisteredBankAccountJpaEntity{" +
                "registeredBankAccountId=" + registeredBankAccountId +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                '}';
    }
}
