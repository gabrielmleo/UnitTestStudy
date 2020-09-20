package com.example.unitteststudy.domain.repository.constructordependencies

import com.example.unitteststudy.domain.database.localDataBase
import com.example.unitteststudy.domain.manager.CacheManager
import com.example.unitteststudy.domain.datasource.LocalDataSource
import com.example.unitteststudy.domain.datasource.RemoteDataSource
import com.example.unitteststudy.domain.manager.AnalyticsManager

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val cacheManager: CacheManager,
    private val localDataBase: localDataBase,
    private val analyticsManager: AnalyticsManager
) {

    fun getListFromRemoteDataSource(): List<Int>{
        return remoteDataSource.getNumbers()
    }

    fun getListFromRemoteDataSourceAndSaveInStorage() {

    }

    fun getListFromRemoteAndUpdateDataSourceAndCache() {

    }
}