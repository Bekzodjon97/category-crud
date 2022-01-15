package bek.dev.categorycrudapp.controller;

import bek.dev.categorycrudapp.entity.Category;
import bek.dev.categorycrudapp.payload.CategoryDto;
import bek.dev.categorycrudapp.payload.Result;
import bek.dev.categorycrudapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @PostMapping
    public Result saveCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

}
