package com.elkattanman.reddit.api.v2.mapper;

import com.elkattanman.reddit.api.v2.model.PostDto;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.Subreddit;
import com.elkattanman.reddit.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postDto.description")
    @Mapping(target = "user", source = "user")
    Post map(PostDto postDto, Subreddit subreddit, User user);


    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostDto mapToDto(Post post);
}
