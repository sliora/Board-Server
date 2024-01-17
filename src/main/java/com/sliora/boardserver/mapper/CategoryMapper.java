package com.sliora.boardserver.mapper;

import com.sliora.boardserver.dto.CategoryDTO;

public interface CategoryMapper {
    public int register(CategoryDTO productDTO);

    public void updateCategory(CategoryDTO categoryDTO);

    public void deleteCategory(int categoryId);
}
