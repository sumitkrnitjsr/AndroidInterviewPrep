package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(value = RetentionPolicy.SOURCE)
@interface ActivityScope{
}

@Scope
@Retention(value = RetentionPolicy.SOURCE)
@interface ApplicationScope{

}

