package com.git.broker.impl.service.producer;

/**
 * Enter class description.
 * <p/>
 * Date: 28.11.12
 * Time: 16:50
 *
 * @author rpleshkov
 */
public class MessageProtocol {
    public String handleProtocolMessage(String messageText) {
        String responseText;
        if ("MyProtocolMessage".equalsIgnoreCase(messageText)) {
            responseText = "I recognize your protocol message";
        } else {
            responseText = "Unknown protocol message: " + messageText;
        }

        return responseText;
    }
}