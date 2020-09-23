package com.example.unitteststudy.domain.manager

import com.example.unitteststudy.domain.datasource.BaseDataSource

class CacheManager: BaseDataSource() {
    override fun getNumbers(): List<Int>? {
        return listOf(2,3,4)
    }

    fun saveNumbers(numbers: List<Int>) {
    }

    fun clear() {
    }

}
