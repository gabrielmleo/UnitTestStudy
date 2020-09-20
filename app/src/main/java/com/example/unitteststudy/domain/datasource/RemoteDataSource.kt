package com.example.unitteststudy.domain.datasource

import com.example.unitteststudy.domain.datasource.BaseDataSource

class RemoteDataSource: BaseDataSource() {
    override fun getNumbers(): List<Int> {
        return listOf(1,2,3,4,5)
    }

}
