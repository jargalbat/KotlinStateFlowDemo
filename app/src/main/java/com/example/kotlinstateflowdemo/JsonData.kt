package com.example.kotlinstateflowdemo

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class JsonData (
    val data: Data,
    val issue: Issue,

    @Json(name = "bookmarked_pages")
    val bookmarkedPages: List<Any?>,

    @Json(name = "liked_pages")
    val likedPages: List<Any?>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<JsonData>(json)
    }
}

data class Data (
    val chapters: List<Chapter>,
    val cookie: List<Cookie>,

    @Json(name = "page_url")
    val pageURL: String,

    @Json(name = "thumb_url")
    val thumbURL: String
)

data class Chapter (
    val id: String,
    val name: String,
    val title: String,
    val path: String,

    @Json(name = "text_path")
    val textPath: String,

    @Json(name = "speech_path")
    val speechPath: Any? = null,

    @Json(name = "share_link")
    val shareLink: String,

    @Json(name = "page_number")
    val pageNumber: Long,

    val thumb: String,
    val screenshot: String,

    @Json(name = "can_read")
    val canRead: Boolean
)

data class Cookie (
    val key: String,
    val value: String,
    val domain: String,
    val path: String,

    @Json(name = "max-age")
    val maxAge: Long
)

data class Issue (
    val id: String,
    val title: String,
    val name: String,
    val description: String,
    val date: String,
    val type: String,

    @Json(name = "updated_at")
    val updatedAt: String,

    val cover: Cover,

    @Json(name = "web_link")
    val webLink: String,

    @Json(name = "deep_link")
    val deepLink: String,

    val region: String,

    @Json(name = "language_code")
    val languageCode: String,

    val language: String,
    val size: Double,
    val magazine: Magazine,

    @Json(name = "is_xtra")
    val isXtra: Boolean,

    @Json(name = "is_unlimited")
    val isUnlimited: Boolean,

    @Json(name = "likes_count")
    val likesCount: Long,

    val status: Status
)

data class Cover (
    val sm: String,
    val md: String
)

data class Magazine (
    val id: String,
    val name: String,
    val publisher: Publisher,
    val logo: Cover
)

data class Publisher (
    val id: String,
    val name: String
)

data class Status (
    val purchased: Boolean,
    val subscribed: Boolean,

    @Json(name = "can_comment")
    val canComment: Boolean,

    @Json(name = "has_preview")
    val hasPreview: Boolean,

    @Json(name = "is_bookmarked")
    val isBookmarked: Boolean
)
