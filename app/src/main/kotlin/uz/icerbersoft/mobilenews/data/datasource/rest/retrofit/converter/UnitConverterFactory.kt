package uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal object UnitConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): Converter<ResponseBody, *>? = if (type == Unit.javaClass) UnitConverter else null

    private object UnitConverter : Converter<ResponseBody, Unit> {
        override fun convert(value: ResponseBody) = value.close()
    }
}