package com.example.bollymovies.utils

class ConstantsApp {

    object Api {
        const val API_TOKEN_KEY = "api_key"
        const val API_TOKEN = "92edcd8cc85974b5a895db188182a0af"
        const val QUERY_PARAM_LANGUAGE_KEY = "language"
        const val QUERY_PARAM_LANGUAGE_VALUE = "pt-BR"
        const val QUERY_PARAM_ORIGINAL_LANGUAGE_LABEL = "with_original_language"
        const val QUERY_PARAM_ORIGINAL_LANGUAGE_VALUE = "hi"
        const val QUERY_PARAM_CREDITS_LABEL = "append_to_response"
        const val QUERY_PARAM_CREDITS_VALUE = "credits"
        const val QUERY_PARAM_SORT_BY_LABEL = "sort_by"
        const val QUERY_PARAM_SORT_BY_VALUE = "release_date.desc"
    }

    object  Home {
        const val KEY_INTENT_MOVIE_ID = "movieId"
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }

    object Login {
        lateinit var UserID: String
        var LOGIN_TYPE = 0
    }
}