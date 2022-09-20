package com.aexp.dinein.service;

import com.aexp.dinein.config.TelegramConfig;
import com.aexp.dinein.model.Message;
import com.aexp.dinein.model.MessageResponse;
import com.aexp.dinein.model.MessageResult;
import com.aexp.dinein.util.TelegramUtility;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TelegramBotMessenger {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TelegramConfig config;

    public ResponseEntity<String> sendMessage(Long userId, String message) {
        String sendMessageUrl = config.getBaseUrl() + config.getToken() + "/sendMessage?chat_id=" + userId + "&text=" + message;
        return restTemplate.getForEntity(sendMessageUrl, String.class);
    }

    public Pair<String, Long> getMessage() {
        String getUpdateUrl = config.getBaseUrl() + config.getToken() + "/getUpdates?offset=-1";
        ResponseEntity<MessageResponse> response = restTemplate.getForEntity(getUpdateUrl, MessageResponse.class);
        MessageResponse msgResp = response.getBody();
        String latestMessage = "";
        Long userId = 0L;
        if(msgResp != null && !msgResp.getResult().isEmpty()) {
            List<MessageResult> messageResponse = msgResp.getResult();
            Message result = messageResponse.get(messageResponse.size() - 1).getMessage();
            latestMessage = result.getText();
            checkExistingUser(result);
            userId = result.getFrom().getId();
        }
        //sendMessage("You sent ::: " + latestMessage);
        return Pair.of(latestMessage, userId);
    }

    private void checkExistingUser(Message result) {
        Long userId = result.getFrom().getId();
        if(!TelegramUtility.users.containsKey(userId)) {
            TelegramUtility.initializeUser(result);
        }
    }
}
