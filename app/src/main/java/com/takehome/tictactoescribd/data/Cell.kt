package com.takehome.tictactoescribd.data


class Cell (var user: User?){

    fun isEmpty(): Boolean {
        return user == null || isNullOrEmpty(user!!.value)
    }
    fun isNullOrEmpty(value: String?): Boolean {
        return value == null || value.length == 0
    }


}