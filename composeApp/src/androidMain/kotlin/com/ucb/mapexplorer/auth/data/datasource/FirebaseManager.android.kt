package com.ucb.mapexplorer.auth.data.datasource


actual class FirebaseManager actual constructor() {

    private val database = FirebaseDatabase.getInstance().reference

    actual suspend fun saveData(path: String, value: String) {
        try {
            database.child(path).setValue(value)
            println("Firebase Android: Guardado exitoso en $path")
        } catch (e: Exception) {
            println("Firebase Android: Error - ${e.message}")
        }
    }
}
