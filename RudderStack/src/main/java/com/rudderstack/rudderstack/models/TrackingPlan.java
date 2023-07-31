package com.rudderstack.rudderstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class TrackingPlan {

    @Id
    private String _id;

    private String displayName;

    private List<String> rules;

}
