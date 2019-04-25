package com.lenovo.dpc.model;

import com.lenovo.dpc.core.AncillaryColumn;
import lombok.Data;


@Data
@AncillaryColumn(tableName = "hierarchy", topic = "hierarchy", pkukList = "hierarchy", ignoreCompareList = "command,timestamp", fullOrDelta = "delta")
public class Hierarchy {

    private String group_or;
    private String brand_or;
    private String series_or;
    private String subseries_or;
    private String machtypedesc;
    private String hierarchy;
    private String command;
    private String timestamp;
    
}
