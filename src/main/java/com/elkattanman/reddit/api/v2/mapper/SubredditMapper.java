package com.elkattanman.reddit.api.v2.mapper;

import com.elkattanman.reddit.api.v2.model.SubredditDto;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubredditMapper {

    SubredditMapper INSTANCE = Mappers.getMapper( SubredditMapper.class );

    @Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}