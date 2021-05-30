package com.maskedgeek.advancedinterviewprep.retrofit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.SOURCE)
@interface ApplicationContext {
}

@Qualifier
@Retention(RetentionPolicy.SOURCE)
@interface ActivityContext {
}
