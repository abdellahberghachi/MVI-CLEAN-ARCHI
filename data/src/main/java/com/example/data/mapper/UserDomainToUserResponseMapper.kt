package com.example.data.mapper

import com.example.data.remote.PostResponse
import com.example.domain.entities.Post
import com.hoc.flowmvi.core.Mapper

internal class UserDomainToUserResponseMapper : Mapper<Post, PostResponse> {
  override fun invoke(domain: Post): PostResponse {
    return PostResponse(
      id = domain.id,
      body = domain.body,
      title = domain.title,
      userId = domain.userId
    )
  }
}
