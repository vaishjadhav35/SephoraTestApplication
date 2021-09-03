package com.example.sephoraapptestapplication.di.modules;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sephoraapptestapplication.feature.viewmodel.DataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel::class)
    abstract fun bindDashboardViewModel(dataViewModel: DataViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
