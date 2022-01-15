package bek.dev.categorycrudapp.service;

import bek.dev.categorycrudapp.entity.Category;
import bek.dev.categorycrudapp.payload.CategoryDto;
import bek.dev.categorycrudapp.payload.Result;
import bek.dev.categorycrudapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {

        return categoryRepository.findAll();

    }

    public Result saveCategory(CategoryDto categoryDto) {
        Category category=new Category();
        Boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("Category already exist by name: "+categoryDto.getName(), false);
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalCategory.isPresent()){
                category.setParentCategory(optionalCategory.get());
            }else {
                return new Result("Category not found with id: " + categoryDto.getParentCategoryId(), false);
            }
        }
        Category savedCategory = categoryRepository.save(category);
        return new Result("Successfully saved with id: "+savedCategory.getId(), true);
    }

    public Result editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Category not found with id:" + categoryDto.getParentCategoryId(), false);
        Category editingCategory = optionalCategory.get();
        Boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("Category already exist by name: "+categoryDto.getName(), false);
        editingCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (categoryOptional.isPresent()){
                editingCategory.setParentCategory(categoryOptional.get());
            }else {
                return new Result("Category not found with id:" + categoryDto.getParentCategoryId(), false);
            }
        }
        categoryRepository.save(editingCategory);
        return new Result("Successfully edited with id: "+ editingCategory.getId(), true);
    }

    public Result deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result("Error", false);
        }
    }

    public Category getById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }
}
