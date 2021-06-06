package com.example.data.remote

import com.google.gson.annotations.SerializedName

data class PostResponse(
  @SerializedName( "body")
  val body: String, // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
  @SerializedName("id")
  val id: Int, // 100
  @SerializedName( "title")
  val title: String, // at nam consequatur ea labore ea harum
  @SerializedName("userId")
  val userId: Int // 10
)