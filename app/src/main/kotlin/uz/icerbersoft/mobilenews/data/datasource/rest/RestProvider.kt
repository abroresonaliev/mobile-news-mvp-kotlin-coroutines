package uz.icerbersoft.mobilenews.data.datasource.rest

import uz.icerbersoft.mobilenews.data.datasource.rest.service.RestService

internal interface RestProvider {

    val restService: RestService
}