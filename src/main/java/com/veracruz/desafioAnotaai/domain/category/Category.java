package com.veracruz.desafioAnotaai.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category(CategoryDTO categoryDTO) {
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerId = categoryDTO.ownerId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("title", this.title);
        json.put("descriptionn", this.description);
        json.put("ownerId", this.ownerId);
        json.put("type", "category");

        return json.toString();
    }

    public String deleteToString() {
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("ownerId", this.ownerId);
        json.put("type", "delete-category");

        return json.toString();
    }
}
