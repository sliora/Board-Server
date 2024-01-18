package com.sliora.boardserver.service;

import com.sliora.boardserver.dto.PostDTO;
import com.sliora.boardserver.dto.PostSearchRequest;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> getProducts(PostSearchRequest postSearchRequest);
}
