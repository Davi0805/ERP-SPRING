package com.gnose.mvp.Authorization;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAccess {
    String permission();

    // is possible to use spring expression language, to get variable from body or path
    String companyId();
}
