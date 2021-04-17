package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.scopes.ActivityScope;

import dagger.Component;

// Write methods in ApplicationComponent to provide dependency objects
@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class}) // Search for modules classes to create and inject into consumers
public interface ActivityComponent {
    void inject(ConsumerActivity activity);
}
