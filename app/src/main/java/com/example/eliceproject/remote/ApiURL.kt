package com.example.eliceproject.remote

object ApiURL {
    private const val MAIN_SCHEME = "https"

    // api base url
    private const val DOMAIN = "api-rest.elice.io"
    const val BASE_URL = "$MAIN_SCHEME://$DOMAIN"

    // base path
    private const val BASE_PATH = "/org/academy"

    // course
    private const val COURSE = "$BASE_PATH/course"
    const val COURSE_LIST = "$COURSE/list/"
    const val COURSE_DETAIL = "$COURSE/get/"

    // lecture
    private const val LECTURE = "$BASE_PATH/lecture"
    const val LECTURE_LIST = "$LECTURE/list/"
}
