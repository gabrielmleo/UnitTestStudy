package com.example.unitteststudy.domain.datasource

abstract class BaseDataSource {
    abstract fun getNumbers(): List<Int>
}