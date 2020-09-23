package com.example.unitteststudy.domain.database

import com.example.unitteststudy.domain.datasource.BaseDataSource

class LocalDataBase: BaseDataSource() {
    override fun getNumbers(): List<Int>? {
        return listOf(3,4,5)
    }

    fun saveNumbers(numbers: List<Int>){}

    fun deleteAll() {}

}