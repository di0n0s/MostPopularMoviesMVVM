package com.example.sfcar.mostpopularmovies.model.enumerations

import android.content.Context
import com.example.sfcar.mostpopularmovies.R

enum class ErrorEnum(val httpStatus: Int, val stringId: Int) {
    FIVE_ZERO_ONE(501, R.string.error_service_not_exist),
    FOUR_ZERO_ONE(401, R.string.error_permissions),
    FOUR_ZERO_FIVE(405, R.string.error_service_not_exist_that_format),
    FOUR_TWO_TWO(422, R.string.error_request_parameters_incorret),
    FOUR_ZERO_FOUR(404, R.string.error_id_invalid),
    NINETY_NINE(99, R.string.error_network_connection);
    //TODO add all error codes https://www.themoviedb.org/documentation/api/status-codes


    companion object {
        fun findErrorDescriptionByErrorCode(errorCode: Int, context: Context): String {
            ErrorEnum.values().forEach {
                if (it.httpStatus == errorCode) {
                    return context.getString(it.stringId)
                }
            }
            return context.getString(NINETY_NINE.stringId)
        }
    }

}
