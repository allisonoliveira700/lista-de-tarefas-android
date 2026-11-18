package com.exemplo.listadetarefas

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import org.json.JSONArray
import org.json.JSONObject

data class Tarefa(
    val id: Long,
    val titulo: String,
    val concluida: Boolean = false
)

/** Guarda as tarefas em memória e salva no SharedPreferences a cada mudança. */
class TarefasViewModel(app: Application) : AndroidViewModel(app) {

    private val prefs = app.getSharedPreferences("tarefas", Context.MODE_PRIVATE)

    val tarefas = mutableStateListOf<Tarefa>()

    init {
        carregar()
    }

    fun adicionar(titulo: String) {
        val texto = titulo.trim()
        if (texto.isEmpty()) return
        tarefas.add(0, Tarefa(id = System.currentTimeMillis(), titulo = texto))
        salvar()
    }

    fun alternar(tarefa: Tarefa) {
        val i = tarefas.indexOfFirst { it.id == tarefa.id }
        if (i >= 0) {
            tarefas[i] = tarefa.copy(concluida = !tarefa.concluida)
            salvar()
        }
    }

    fun remover(tarefa: Tarefa) {
        tarefas.removeAll { it.id == tarefa.id }
        salvar()
    }

    fun limparConcluidas() {
        tarefas.removeAll { it.concluida }
        salvar()
    }

    private fun salvar() {
        val array = JSONArray()
        tarefas.forEach {
            array.put(
                JSONObject()
                    .put("id", it.id)
                    .put("titulo", it.titulo)
                    .put("concluida", it.concluida)
            )
        }
        prefs.edit().putString("lista", array.toString()).apply()
    }

    private fun carregar() {
        val json = prefs.getString("lista", null) ?: return
        val array = runCatching { JSONArray(json) }.getOrNull() ?: return
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            tarefas.add(
                Tarefa(
                    id = obj.getLong("id"),
                    titulo = obj.getString("titulo"),
                    concluida = obj.optBoolean("concluida", false)
                )
            )
        }
    }
}
