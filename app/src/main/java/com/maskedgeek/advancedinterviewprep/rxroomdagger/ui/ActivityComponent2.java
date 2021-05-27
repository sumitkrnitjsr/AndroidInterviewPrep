package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;


import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent2.class}, modules = {ActivityModule.class})
public interface ActivityComponent2 {

    public void inject(RxDaggerRoomActivity activity);
}
