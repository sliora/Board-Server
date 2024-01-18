package com.sliora.boardserver.mapper;

import com.sliora.boardserver.dto.PostDTO;
import com.sliora.boardserver.dto.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {
    public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
}
