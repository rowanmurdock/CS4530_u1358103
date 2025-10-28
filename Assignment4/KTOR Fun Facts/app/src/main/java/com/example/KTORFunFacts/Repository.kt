package com.example.KTORFunFacts

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import io.ktor.client.request.get


class Repository (val scope: CoroutineScope,
                  private val dao: FunFactDao,
                  private val client: HttpClient) {

    val allFacts: Flow<List<FunFact>> = dao.getAllFact()

    fun addNewFunFact() {
        scope.launch {
            val fact: FunFact = client.get("https://uselessfacts.jsph.pl/random.json?language=en").body()
            dao.addNewFunFact(fact)
        }
    }

}