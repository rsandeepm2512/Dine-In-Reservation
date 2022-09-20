package com.aexp.dinein.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class From {

    private long id;

    @JsonProperty("is_bot")
    private boolean isBot;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("language_code")
    private String languageCode;
}
