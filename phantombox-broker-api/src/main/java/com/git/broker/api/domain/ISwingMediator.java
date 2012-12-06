package com.git.broker.api.domain;

import javax.swing.JFrame;

/**
 * ISwingMediator interface.
 * <p/>
 * Date: 05.12.12
 * Time: 20:26
 *
 * @author rpleshkov
 */
public interface ISwingMediator extends IMediator {

    /**
     * Sets incoming frame.
     *
     * @param incomingFrame incoming frame
     */
    void setIncomingFrame(JFrame incomingFrame);

    /**
     * Gets incoming frame.
     *
     * @return incoming frame
     */
    JFrame getIncomingFrame();

    /**
     * Sets  answer frame.
     *
     * @param answerFrame answer frame
     */
    void setAnswerFrame(JFrame answerFrame);


    /**
     * Gets  answer frame.
     *
     * @return answer frame
     */
    JFrame getAnswerFrame();
}
