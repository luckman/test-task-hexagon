package com.luckman.hexagon.testtask.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllResult {
    protected int page;
    @JsonProperty("per_page")
    protected int perPage;
    protected int total;
    @JsonProperty("total_pages")
    protected int totalPages;
}
