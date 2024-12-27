package com.example.jainitri

class ColorRepository(private val colorDao: ColorDao) {
    suspend fun insertColor(colorEntity: ColorEntity) {
        colorDao.insertColor(colorEntity)
    }

    suspend fun getAllColors(): List<ColorEntity> {
        return colorDao.getAllColors()
    }
}
