package com.example.demo.domain.recipe;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.dto.recipe.request.RecipeUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Recipe extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, length = 1000)
    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeMaterials> materials = new ArrayList<>();

    private boolean deleted;

    protected Recipe() {
    }

    public Recipe(String title, String imageUrl, String description, List<String> materials) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.deleted = false;
        this.materials = materials.stream()
                .map(material -> new RecipeMaterials(material, this))
                .collect(Collectors.toList());
    }

    public void editPartial(RecipeUpdateRequest request) {
        if (request.getTitle() != null)  {
            this.title = request.getTitle();
        }
        if (request.getImageUrl() != null) {
            this.imageUrl = request.getImageUrl();
        }
        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }
        if (request.getMaterials() != null) {
            this.materials.clear();
            for (String materialName : request.getMaterials()) {
                this.materials.add(new RecipeMaterials(materialName, this));
            }
        }
    }

    public void markDeleted() {
        this.deleted = true;
    }
}
