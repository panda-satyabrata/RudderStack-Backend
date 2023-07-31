package com.rudderstack.rudderstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class TrackingPlanDto {

    @JsonProperty("display_name")
    private String displayName;

    private EventsDto rules;
}

