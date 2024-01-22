package com.sliora.boardserver.mapper;

import com.sliora.boardserver.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public int register(CommentDTO commentDTO);

    public void updateComments(CommentDTO commentDTO);

    public void deletePostComment(int commentId);
}
