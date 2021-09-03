package com.example.sephoraapptestapplication.di.modules

import com.example.sephoraapptestapplication.feature.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
open abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun dashboardActivity(): MainActivity

}