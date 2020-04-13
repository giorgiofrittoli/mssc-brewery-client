package it.frigir.msscbreweryclient.web.config


import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class BlockingRestTemplateCustomizer(
        @Value("\${custom.restTemplate.maxTotal}") val maxTotal: Integer,
        @Value("\${custom.restTemplate.defaultMaxPerRoute}") val defaultMaxPerRoute: Integer,
        @Value("\${custom.restTemplate.requestTimeout}") val requestTimeout: Integer,
        @Value("\${custom.restTemplate.socketTimeout}") val socketTimeout: Integer) : RestTemplateCustomizer {

    fun clientHttpRequestFactory(): ClientHttpRequestFactory {

        val connectionManager = PoolingHttpClientConnectionManager()
        connectionManager.maxTotal = maxTotal.toInt()
        connectionManager.defaultMaxPerRoute = defaultMaxPerRoute.toInt()

        val requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(requestTimeout.toInt())
                .setSocketTimeout(socketTimeout.toInt())
                .build()

        val httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy())
                .build()

        return HttpComponentsClientHttpRequestFactory(httpClient)
    }

    override fun customize(restTemplate: RestTemplate?) {
        restTemplate?.requestFactory = clientHttpRequestFactory()
    }
}