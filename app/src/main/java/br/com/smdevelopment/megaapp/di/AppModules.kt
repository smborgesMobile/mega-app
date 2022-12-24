package br.com.smdevelopment.megaapp.di

import br.com.smdevelopment.megaapp.util.RandomListGenerator
import br.com.smdevelopment.megaapp.util.RandomListGeneratorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Provides
    fun provideRandomUtil(): RandomListGenerator {
        return RandomListGeneratorImpl()
    }
}