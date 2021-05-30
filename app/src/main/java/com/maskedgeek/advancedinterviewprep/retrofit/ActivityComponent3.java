package com.maskedgeek.advancedinterviewprep.retrofit;


import dagger.Component;

@ActivityScope3
@Component(dependencies = {ApplicationComponent3.class}, modules = {ActivityModule3.class})
public interface ActivityComponent3 {

    public void inject(RetrofitActivity activity);
}
