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
public class CallFrame {
    private JButton acceptBtn;
    private JPanel questionPanel;
    private JButton cancelBtn;

    public CallFrame(final IMediator mediator, final String correlationId,
                     final IConnection connection) {
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.answer(ResponseType.ACCEPT, correlationId, connection);
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
