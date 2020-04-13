package it.frigir.msscbreweryclient.web.client

import it.frigir.msscbreweryclient.web.model.BeerDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
internal class BreweryClientTest {

    @Autowired
    lateinit var breweryClient: BreweryClient

    @Test
    fun getBeerById() {
        val beerDto = breweryClient.getBeerById(UUID.randomUUID())
        assertNotNull(beerDto)
    }

    @Test
    fun saveNewBeer() {
        //given
        val beerDto = BeerDto().apply {
            id = UUID.randomUUID()
        }
        val uri = breweryClient.saveNewBeer(beerDto)
        assertNotNull(uri)
        println(uri)
    }

    @Test
    fun updateBeer() {
        //given
        val beerDto = BeerDto().apply {
            id = UUID.randomUUID()
        }
        breweryClient.updateBeer(UUID.randomUUID(), beerDto)
    }

    @Test
    fun deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID())
    }
}