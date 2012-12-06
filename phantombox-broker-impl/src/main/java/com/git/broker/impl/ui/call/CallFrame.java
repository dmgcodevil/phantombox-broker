package com.git.broker.impl.ui.call;

import com.git.broker.api.domain.IMediator;
import com.git.broker.api.domain.ResponseType;
import com.git.domain.api.IConnection;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CallFrame extends JFrame {

    private JPanel contentPane;
    private IMediator mediator;
    private String correlationId;
    private IConnection connection;
    private String subscriberName;
    private JButton btnAnswer;
    private JButton btnCancel;

    /**
     * Create the frame.
     */
    public CallFrame(String subscriberName, String correlationId, IMediator mediator) {
        this.mediator = mediator;
        this.correlationId = correlationId;
        this.connection = connection;
        this.subscriberName = subscriberName;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 196, 111);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel rootPanel = new JPanel();
        contentPane.add(rootPanel, BorderLayout.CENTER);
        rootPanel.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));


        JLabel lblCalling = new JLabel("calling:");
        rootPanel.add(lblCalling, "2, 2");

        JLabel lblSubscriber = new JLabel(subscriberName);
        rootPanel.add(lblSubscriber, "4, 2");

         btnAnswer = new JButton("Answer");

        rootPanel.add(btnAnswer, "2, 4");

         btnCancel = new JButton("Cancel");

        rootPanel.add(btnCancel, "4, 4");
        addListeners();
    }

    private void addListeners() {

        btnAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.answer(ResponseType.ACCEPT, correlationId);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.answer(ResponseType.CANCEL, correlationId);
            }
        });
    }

}
