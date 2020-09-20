package com.example.unitteststudy.domain.database

import com.example.unitteststudy.domain.datasource.BaseDataSource

class localDataBase: BaseDataSource() {
    override fun getNumbers(): List<Int> {
        return listOf(3,4,5)
    }

}
