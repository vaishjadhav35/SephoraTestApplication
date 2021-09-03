package com.example.sephoraapptestapplication.di

import android.app.Application
import com.example.sephoraapptestapplication.baseclasses.BaseApplication
import com.example.sephoraapptestapplication.di.modules.ActivityBindingModule
import com.example.sephoraapptestapplication.di.modules.ContextModule
import com.example.sephoraapptestapplication.di.modules.NetworkModule
import com.example.sephoraapptestapplication.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ViewModelModule::class, NetworkModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    fun inject(baseApplication: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}