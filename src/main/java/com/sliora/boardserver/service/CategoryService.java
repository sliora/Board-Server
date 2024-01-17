package com.sliora.boardserver.service;

import com.sliora.boardserver.dto.CategoryDTO;

public interface CategoryService {

    void register(String accountId, CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(int categoryId);
}