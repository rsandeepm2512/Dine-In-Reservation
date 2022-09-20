package com.aexp.dinein.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageResponse {

    private boolean ok;
    private List<MessageResult> result;
}
