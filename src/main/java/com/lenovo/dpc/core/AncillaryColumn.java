package com.lenovo.dpc.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AncillaryColumn {
    String tableName();

    String pkukList() default "";

    String ignoreCompareList() default "";

    String fullOrDelta() default "delta";

    String topic() default "";
}
