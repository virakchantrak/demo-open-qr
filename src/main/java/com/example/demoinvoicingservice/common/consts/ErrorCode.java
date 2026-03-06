package com.example.demoinvoicingservice.common.consts;

public enum ErrorCode {
    U0001("The invoice is not valid."),
    U0002("Cannot create the invoice due to the same reference number and merchant."),
    U0003("Cannot cancel the invoice due to the paid status."),
    U0004("Cannot cancel the invoice due to the cancelled status."),
    U0005("Cannot update invoice’s payment due to the paid status."),
    U0006("Cannot update invoice’s payment due to cancelled status."),
    U0007("Cannot update invoice’s payment due to overdue payment status."),
    UOOO8("Fail to response from transaction"),
    UOOO9("Cannot update invoice’s payment due to the same transaction id."),
    UOO10("Error to encrypt data."),
    UOO11("Error to decrypt data."),
    UOO12("Cannot update invoice’s payment due to this invoice is in processing"),
    U0013("Cannot create the invoice due to this invoice is in processing."),
    U0014("Cannot update invoice’s payment due to the same hash id."),
    U0404("Crc verify is failed."),
    U444("Invalid request");

    private String desc;

    ErrorCode(String desc) {
        this.desc = desc;
    }


    public String getCode() {
        return this.name();
    }

    public String getDesc() {
        return desc;
    }
}
