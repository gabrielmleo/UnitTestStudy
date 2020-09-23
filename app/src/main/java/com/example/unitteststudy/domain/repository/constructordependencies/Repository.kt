package com.example.unitteststudy.domain.repository.constructordependencies

import com.example.unitteststudy.domain.database.LocalDataBase
import com.example.unitteststudy.domain.manager.CacheManager
import com.example.unitteststudy.domain.datasource.RemoteDataSource
import com.example.unitteststudy.domain.manager.AnalyticsManager

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val cacheManager: CacheManager,
    private val localDataBase: LocalDataBase,
    private val analyticsManager: AnalyticsManager
) {

    fun getListFromRemoteDataSource(): List<Int>{
        return remoteDataSource.getNumbers()
    }

    fun getListFromRemoteDataSourceAndSaveInStorage(): List<Int> {
        return remoteDataSource.getNumbers().also {
            updateLocalInformation(it)
        }
    }

    fun getListFromLocalOrGetRemote(): List<Int> {
        return cacheManager.getNumbers() ?:
        localDataBase.getNumbers() ?:
        remoteDataSource.getNumbers().also {
            updateLocalInformation(it)
        }
    }

    fun getRemoteClearDataAndSaveLocal() {
        val numbers = remoteDataSource.getNumbers()
        deleteLocalData()
        updateLocalInformation(numbers)
    }

    private fun deleteLocalData() {
        cacheManager.clear()
        localDataBase.deleteAll()
    }

    private fun updateLocalInformation(numbers: List<Int>) {
        localDataBase.saveNumbers(numbers)
        cacheManager.saveNumbers(numbers)
    }

}