package com.lenovo.dpc.model;

import com.lenovo.dpc.core.AncillaryColumn;
import lombok.Data;

@Data
@AncillaryColumn(tableName = "feature", topic = "feature", pkukList = "ctoCode,featurecode", ignoreCompareList = "command,timestamp", fullOrDelta = "delta")
public class Feature {

    private String ctoCode;

    private String featurecode;

    private String announceDate;

    private String withdrawnDate;

    private String audience;

    private String cfgflag;

    private String command;

    private String timestamp;


}
