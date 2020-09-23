package com.example.unitteststudy.finished

import com.example.unitteststudy.domain.database.LocalDataBase
import com.example.unitteststudy.domain.datasource.RemoteDataSource
import com.example.unitteststudy.domain.manager.AnalyticsManager
import com.example.unitteststudy.domain.manager.CacheManager
import com.example.unitteststudy.domain.repository.constructordependencies.Repository
import io.mockk.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RepositoryFinishedTest {

    val remoteDataSourceMock: RemoteDataSource = mockk()
    val cacheManagerMock: CacheManager = mockk()
    val localDataBaseMock: LocalDataBase = mockk()
    val analyticsManagerMock: AnalyticsManager = mockk()
    val subject = Repository(
        remoteDataSourceMock,
        cacheManagerMock,
        localDataBaseMock,
        analyticsManagerMock
    )

    @Nested
    inner class `Given a repository` {

        @Test
        fun `When call getRemote Should delegate call to remoteDataSource`() {
            //Preparation
            val numbersMock = listOf(1,2,3)
            every { remoteDataSourceMock.getNumbers() } returns numbersMock

            //Execution
            subject.getListFromRemoteDataSource()

            //Verification
            verify { remoteDataSourceMock.getNumbers() }
        }

        @Test
        fun `When call Remote Should Also save in local storage`() {
            //Preparation
            val numbersMock = listOf(1,2,3)
            every { remoteDataSourceMock.getNumbers() } returns numbersMock
            //Ponto de atenção, passar nos comportamentos os mocks corretos
            every { localDataBaseMock.saveNumbers(numbersMock) } just runs
            every { cacheManagerMock.saveNumbers(numbersMock) } just runs

            //Execution
            subject.getListFromRemoteDataSourceAndSaveInStorage()

//            verifySequence {
//                remoteDataSourceMock.getNumbers()
//                localDataBaseMock.saveNumbers(numbersMock)
//                cacheManagerMock.saveNumbers(numbersMock)
//            }

            //Verification
            verify (exactly = 1){ remoteDataSourceMock.getNumbers() }
            verify (exactly = 1){ localDataBaseMock.saveNumbers(numbersMock) }
            verify (exactly = 1){ cacheManagerMock.saveNumbers(numbersMock) }

        }

        @Test
        fun `When fetch new data from remote Should first clear local data and then save`() {
            val numbersMock = listOf(1,2,3)
            every { remoteDataSourceMock.getNumbers() } returns numbersMock
            every { cacheManagerMock.clear() } just runs
            every { localDataBaseMock.deleteAll() } just runs
            every { localDataBaseMock.saveNumbers(numbersMock) } just runs
            every { cacheManagerMock.saveNumbers(numbersMock) } just runs

            subject.getRemoteClearDataAndSaveLocal()

//            verify(exactly = 1) { remoteDataSourceMock.getNumbers() }
//            verify(exactly = 1) { cacheManagerMock.clear() }
//            verify(exactly = 1) { localDataBaseMock.deleteAll() }
//            verify(exactly = 1) { localDataBaseMock.saveNumbers(numbersMock) }
//            verify(exactly = 1) { cacheManagerMock.saveNumbers(numbersMock) }

//            verifyOrder {
//                remoteDataSourceMock.getNumbers()
//                cacheManagerMock.clear()
//                localDataBaseMock.deleteAll()
//                localDataBaseMock.saveNumbers(numbersMock)
//                cacheManagerMock.saveNumbers(numbersMock)
//            }

            verifySequence {
                remoteDataSourceMock.getNumbers()
                cacheManagerMock.clear()
                localDataBaseMock.deleteAll()
                localDataBaseMock.saveNumbers(numbersMock)
                cacheManagerMock.saveNumbers(numbersMock)
            }
        }
    }

    @Nested
    inner class `Given a repository with local data storage` {

        @Test
        fun `When cache is not empty Should fetch only from cache`() {
            //Preparation
            val numbersMock = listOf(1,2,3)
            every { cacheManagerMock.getNumbers() } returns numbersMock

            //Execution
            subject.getListFromLocalOrGetRemote()

            //Verification
            verify (exactly = 1){ cacheManagerMock.getNumbers() }
            verify (exactly = 0){ localDataBaseMock.getNumbers() }
            verify (exactly = 0){ remoteDataSourceMock.getNumbers() }

        }

        @Test
        fun `When cache is empty and local database is not Should fetch only from local database`() {
            //Preparation
            val numbersMock = listOf(1,2,3)
            every { cacheManagerMock.getNumbers() } returns null
            every { localDataBaseMock.getNumbers() } returns numbersMock

            //Execution
            subject.getListFromLocalOrGetRemote()

            //Verification
            verify (exactly = 1) { cacheManagerMock.getNumbers() }
            verify (exactly = 1) { localDataBaseMock.getNumbers() }
            verify (exactly = 0) { remoteDataSourceMock.getNumbers() }

            verifyOrder {
                cacheManagerMock.getNumbers()
                localDataBaseMock.getNumbers()
            }
        }

        @Test
        fun `When all local storage is empty Should fetch from remote and save locally`() {
            //Preparation
            val numbersMock = listOf(1,2,3)
            every { cacheManagerMock.getNumbers() } returns null
            every { localDataBaseMock.getNumbers() } returns null
            every { remoteDataSourceMock.getNumbers() } returns numbersMock
            every { localDataBaseMock.saveNumbers(numbersMock) } just runs
            every { cacheManagerMock.saveNumbers(numbersMock) } just runs

            //Execution
            subject.getListFromLocalOrGetRemote()

            //Verification
            verify (exactly = 1) { cacheManagerMock.getNumbers() }
            verify (exactly = 1) { localDataBaseMock.getNumbers() }
            verify (exactly = 1) { remoteDataSourceMock.getNumbers() }
            verify (exactly = 1) { localDataBaseMock.saveNumbers(numbersMock) }
            verify (exactly = 1) { cacheManagerMock.saveNumbers(numbersMock) }

            verifyOrder {
                cacheManagerMock.getNumbers()
                localDataBaseMock.getNumbers()
                remoteDataSourceMock.getNumbers()
                localDataBaseMock.saveNumbers(numbersMock)
                cacheManagerMock.saveNumbers(numbersMock)
            }

        }
    }
}