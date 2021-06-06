package com.example.data.mapper

import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import com.hoc.flowmvi.core.Mapper

internal class UserResponseToUserDomainMapper : Mapper<PostResponse, Post> {
  override fun invoke(response: PostResponse): Post {
    return Post(
      id = response.id,
      body = response.body,
      title = response.title,
      userId = response.userId
    )
  }
}
