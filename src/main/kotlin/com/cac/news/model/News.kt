/*
 * Copyright 2019 JUN MAO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 */

package com.thoughtworks.cac.news.data.entities

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("uniquekey")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("category")
    val category: Int,
    @SerializedName("author_name")
    val authorName: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnail_pic_s")
    val thumbnailOne: String?,
    @SerializedName("thumbnail_pic_s02")
    val thumbnailTwo: String?,
    @SerializedName("thumbnail_pic_s03")
    val thumbnailThree: String?
)