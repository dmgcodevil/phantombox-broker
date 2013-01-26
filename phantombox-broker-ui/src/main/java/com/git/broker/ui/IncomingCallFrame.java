package com.git.broker.ui;

import com.git.broker.api.domain.IIncomingCallFrame;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.ResponseType;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


import org.apache.commons.lang3.StringUtils;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IncomingCallFrame extends BaseFrame implements IIncomingCallFrame {

    private static final String CALL = "Call";

    private IRequest request;
    private IJmsExchanger jmsExchanger;
    private JPanel contentPane;
    private JButton btnAnswer;
    private JButton btnCancel;
    private JLabel lblSubscriber;
    private static final String PHONE_ICO = "/com/git/ui/ico/phone.png";
    private static final String CANCEL_ICO = "/com/git/ui/ico/cancel.png";
    private static final int HEIGHT = 175;
    private static final int WIDTH = 230;
    private static final int Y = 100;
    private static final int X = 100;
    private static final int TOP = 5;
    private static final int LEFT = 5;
    private static final int BOTTOM = 5;
    private static final int RIGHT = 5;
    private static final String CALL_LABEL_CONFIG = "2, 2, left, default";
    private static final String MAX_20DLU_DEFAULT = "max(20dlu;default)";
    private static final String MAX_20DLU_DEFAULT1 = "max(20dlu;default)";
    private static final String MAX_20DLU_DEFAULT2 = "max(20dlu;default)";
    private static final String MAX_20DLU_DEFAULT3 = "max(20dlu;default)";
    private static final String MAX_33DLU_DEFAULT = "max(33dlu;default)";
    private static final String MAX_27DLU_DEFAULT = "max(27dlu;default)";
    private static final String SUBSCRIBER_NAME_LABEL = "4, 2, 5, 1";
    private static final String ANSWER_BUTTON_POS = "2, 4, 3, 1";
    private static final String CANCEL_BUTTON_POS = "6, 4, 3, 1";

    /**
     * {@inheritDoc}
     */
    @Override
    public IRequest getRequest() {
        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IJmsExchanger getJmsExchanger() {
        return jmsExchanger;
    }

    @Override
    public void showFrame() {
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJmsExchanger(IJmsExchanger jmsExchanger) {
        this.jmsExchanger = jmsExchanger;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IncomingCallFrame frame = new IncomingCallFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor.
     */
    public IncomingCallFrame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(X, Y, WIDTH, HEIGHT);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_20DLU_DEFAULT),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_20DLU_DEFAULT1),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_20DLU_DEFAULT2),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_20DLU_DEFAULT3),
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode(MAX_33DLU_DEFAULT),
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode(MAX_27DLU_DEFAULT),
                FormFactory.RELATED_GAP_ROWSPEC,}));

        JLabel lblCall = new JLabel(CALL);
        contentPane.add(lblCall, CALL_LABEL_CONFIG);

        lblSubscriber = new JLabel(StringUtils.EMPTY);
        contentPane.add(lblSubscriber, SUBSCRIBER_NAME_LABEL);

        URL answerImageURL = IncomingCallFrame.class.getResource(PHONE_ICO);
        URL cancelImageURL = IncomingCallFrame.class.getResource(CANCEL_ICO);
        btnAnswer = new JButton(new ImageIcon(answerImageURL));
        contentPane.add(btnAnswer, ANSWER_BUTTON_POS);

        btnCancel = new JButton(new ImageIcon(cancelImageURL));
        contentPane.add(btnCancel, CANCEL_BUTTON_POS);

        alignment(this);
    }

    /**
     * Constructor with parameters.
     *
     * @param request      {@link IRequest}
     * @param jmsExchanger {@link IJmsExchanger}
     */
    public IncomingCallFrame(IRequest request, IJmsExchanger jmsExchanger) {
        this();
        this.request = request;
        this.jmsExchanger = jmsExchanger;
        lblSubscriber.setText(request.getContact().getName());
        addListeners();
    }

    private void addListeners() {
        btnAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(ResponseType.ACCEPT);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer(ResponseType.CANCEL);
                dispose();
            }
        });
    }

    private void answer(ResponseType type) {
        jmsExchanger.answer(type, request.getCorrelationId());
    }

}
