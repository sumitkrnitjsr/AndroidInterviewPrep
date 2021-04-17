package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyDatabaseService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyNetworkService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class}) // Search for modules classes to create and inject into consumers
public interface ApplicationComponent {

    void inject(ConsumerApplication application);

    // Method to share objects between components
    DependencyNetworkService sendNetworkServiceToDependentComponent();

    // Method to share objects between components
    DependencyDatabaseService sendDatabaseServiceToDependentComponent();
}
