package com.git.broker.api.domain;

import javax.swing.*;

/**
 * Enter class description.
 * <p/>
 * Date: 05.12.12
 * Time: 20:26
 *
 * @author rpleshkov
 */
public interface ISwingMediator extends IMediator {

    void setIncomingFrame(JFrame IncomingFrame);

    JFrame getIncomingFrame();

    void setAnswerFrame(JFrame answerFrameFrame);

    JFrame getAnswerFrame();
}
