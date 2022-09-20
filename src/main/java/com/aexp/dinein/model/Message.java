package com.aexp.dinein.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    @JsonProperty("message_id")
    private int messageId;
    private From from;
    private Chat chat;
    private long date;
    private String text;
}
