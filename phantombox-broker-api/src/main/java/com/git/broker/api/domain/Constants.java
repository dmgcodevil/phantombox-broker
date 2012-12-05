package com.git.broker.api.domain;

/**
 * Constants.
 * <p/>
 * Date: 28.11.12
 * Time: 12:11
 *
 * @author rpleshkov
 */
public class Constants {

    private Constants() {
        throw new AssertionError();
    }

    /**
     * CLASS_NAME_PROPERTY.
     */
    public static final String CLASS_NAME_PROPERTY = "className";

    /**
     * CONTACT_ID_PROPERTY.
     */
    public static final String CONTACT_ID_PROPERTY = "contactId";

    /**
     * IP_ADDRESS.
     */
    public static final String IP_ADDRESS = "ipAddress";

    /**
     * AUDIO_PORT.
     */
    public static final String AUDIO_PORT = "audioPort";

    /**
     * VIDEO_PORT.
     */
    public static final String VIDEO_PORT = "videoPort";

    /**
     * CALL_REQUEST_QUEUE.
     */
    public static final String CALL_REQUEST_QUEUE = "call.request.queue";

    /**
     * CALL_RESPONSE_QUEUE.
     */
    public static final String CALL_RESPONSE_QUEUE = "call.response.queue";
}
