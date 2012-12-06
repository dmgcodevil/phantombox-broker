package com.git.broker.impl.ui;

import com.git.broker.api.domain.IMediator;
import com.git.broker.api.domain.ResponseType;
import com.git.domain.api.IConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/5/12
 * Time: 7:16 PM
 */
public class CallFrame extends JFrame {

    private JButton acceptBtn;
    private JPanel questionPanel;
    private JButton cancelBtn;
    private JLabel subscriberLabel;
    private JLabel subscriber;
    private IMediator mediator;
    private String correlationId;

    public CallFrame() {
    }

    public CallFrame(String subscriberName, String correlationId, IMediator mediator, IConnection connection) {
        this();
        this.mediator = mediator;
        this.correlationId = correlationId;
        this.subscriber.setText(subscriberName);
        addListeners();
    }

    private void addListeners() {

        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.answer(ResponseType.ACCEPT, correlationId);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.answer(ResponseType.CANCEL, correlationId);
            }
        });
    }

    public void setData(CallFrame data) {
    }

    public void getData(CallFrame data) {
    }

    public boolean isModified(CallFrame data) {
        return false;
    }
}
