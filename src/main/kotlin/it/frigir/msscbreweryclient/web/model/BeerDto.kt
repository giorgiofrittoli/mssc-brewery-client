package it.frigir.msscbreweryclient.web.model

import java.util.*

class BeerDto {
    lateinit var id: UUID
    var name: String? = null
    var style: String? = null
    var upc: Long? = null
}