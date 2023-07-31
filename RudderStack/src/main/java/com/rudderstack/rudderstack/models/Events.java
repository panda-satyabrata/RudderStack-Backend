package com.rudderstack.rudderstack.models;

import lombok.Data;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Events {

    @Id
    private String _id;

    private String name;

    private String description;

    private Object rules;
}
