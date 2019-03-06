package edu.eci.arsw.alexandria.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Category {
    @NonNull private String name;
    private List<Article> articles = new ArrayList<>();
    private List<Category> subCategories = new ArrayList<>();
}
