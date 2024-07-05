package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CatFact {
    @JsonProperty("fact")
    private String fact;
    @JsonProperty("length")
    private long length;
}
