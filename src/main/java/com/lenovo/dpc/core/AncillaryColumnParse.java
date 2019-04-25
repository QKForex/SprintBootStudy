package com.lenovo.dpc.core;

import lombok.Getter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class AncillaryColumnParse<T> {

    @Getter
    private String tableName;
    @Getter
    private String topic;
    @Getter
    private List<String> pkukList;
    @Getter
    private List<String> ignoreCompareList;
    @Getter
    private String fullOrDelta;
/*
    @Getter
    private Class clazz;
*/

    public AncillaryColumnParse(Class<T> clazz) {
        for (Annotation a : clazz.getDeclaredAnnotations()) {
            if (a.annotationType().equals(AncillaryColumn.class)) {
                this.tableName = ((AncillaryColumn) a).tableName();
                this.pkukList = Arrays.asList(((AncillaryColumn) a).pkukList().split(","));
                this.ignoreCompareList = Arrays.asList(((AncillaryColumn) a).ignoreCompareList().split(","));
                this.fullOrDelta = ((AncillaryColumn) a).fullOrDelta();
                this.topic = ((AncillaryColumn) a).topic();
            }
        }
    }
}
