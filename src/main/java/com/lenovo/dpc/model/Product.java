package com.lenovo.dpc.model;

import com.lenovo.dpc.core.AncillaryColumn;
import lombok.Data;

@Data
@AncillaryColumn(tableName = "cntryproduct", topic = "cntryproduct", ignoreCompareList = "command,timestamp", pkukList = "country,ctoCode", fullOrDelta = "delta")
public class Product {

    private String country;

    private String ctoCode;

    private String announceDate;

    private String withdrawnDate;

    private String command;

    private String timestamp;
}
