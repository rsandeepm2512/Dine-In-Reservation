package com.aexp.dinein.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResult {

    @JsonProperty("update_id")
    private long updateId;
    private Message message;
}
