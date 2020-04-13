package it.frigir.msscbreweryclient.web.client

import it.frigir.msscbreweryclient.web.model.BeerDto
import it.frigir.msscbreweryclient.web.model.CustomerDto
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*

@ConfigurationProperties("brewery", ignoreInvalidFields = false)
@Component
class BreweryClient(restTemplateBuilder: RestTemplateBuilder, val restTemplate: RestTemplate = restTemplateBuilder.build()) {
    val BEER_PATH_V1 = "/api/v1/beer/"
    val CUSTOMER_PATH_V1 = "/api/v1/customer/"
    lateinit var apihost: String

    fun getBeerById(id: UUID): BeerDto? {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + id.toString(), BeerDto::class.java)
    }

    fun saveNewBeer(beerDto: BeerDto): URI? {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto)
    }

    fun updateBeer(id: UUID, beerDto: BeerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + id.toString(), beerDto)
    }

    fun deleteBeer(id: UUID) {
        restTemplate.delete(apihost + BEER_PATH_V1 + id.toString())
    }

    fun getCostumerById(id: UUID): CustomerDto? {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + id, CustomerDto::class.java)
    }

    fun saveNewCustomer(customerDto: CustomerDto): URI? {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto)
    }

    fun updateCustomer(id: UUID, customerDto: CustomerDto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + id, customerDto)
    }

    fun deleteCustomer(id: UUID) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + id)
    }

}