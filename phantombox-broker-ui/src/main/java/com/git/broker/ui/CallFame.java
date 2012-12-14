package com.git.broker.ui;

import com.git.broker.api.domain.ICallFame;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CallFame extends BaseFrame implements ICallFame {

    private JPanel contentPane;
    private JButton btnCancel;
    private JLabel lblContactName;
    private IRequest request;
    private IJmsExchanger jmsExchanger;
    private String contactName;

    private static final String CANCEL = "cancel";
    private static final String CALL_ICO = "/com/git/ui/ico/call.png";
    private static final String CONTACT_NAME = "Contact name";
    private static final String CALL = "Call";
    private static final String IMAGE_PANEL_CONFIG = "4, 4, 3, 1, fill, fill";
    private static final String CONTACT_NAME_CONFIG = "4, 2, 3, 1, left, default";
    private static final String MAX_51DLU_DEFAULT = "max(51dlu;default)";
    private static final String MAX_36DLU_DEFAULT = "max(36dlu;default)";
    private static final String MAX_33DLU_DEFAULT = "max(33dlu;default)";
    private static final String MAX_59DLU_DEFAULT = "max(59dlu;default)";
    private static final String CALL_LABEL_POS = "2, 2";
    private static final String CANCEL_BUTTON_POS = "4, 8";

    private static final int X = 100;
    private static final int Y = 100;
    private static final int WIDTH = 205;
    private static final int HEIGHT = 278;
    private static final int TOP = 5;
    private static final int LEFT = 5;
    private static final int BOTTOM = 5;
    private static final int RIGHT = 5;

    /**
     * Gets content pane.
     *
     * @return content pane
     */
    public JPanel getContentPane() {
        return contentPane;
    }

    /**
     * Sets content pane.
     *
     * @param contentPane content pane
     */
    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJmsExchanger(IJmsExchanger jmsExchanger) {
        this.jmsExchanger = jmsExchanger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContactName() {
        return contactName;
    }

    @Override
    public void showFrame() {
        setVisible(true);
    }

    @Override
    public void close() {
        jmsExchanger.cancelCall(request);
        dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CallFame frame = new CallFame();
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
    public CallFame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(X, Y, WIDTH, HEIGHT);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_51DLU_DEFAULT),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode(MAX_36DLU_DEFAULT),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode(MAX_33DLU_DEFAULT),
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode(MAX_59DLU_DEFAULT),
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));

        BufferedImage image = null;
        try {
            image = ImageIO.read(CallFame.class.getResource(CALL_ICO));
        } catch (IOException ex) {
            // TODO handle exception !
        }
        JPanel imagePanel = new ImagePanel(image);
        contentPane.add(imagePanel, IMAGE_PANEL_CONFIG);

        JLabel lblCall = new JLabel(CALL);
        contentPane.add(lblCall, CALL_LABEL_POS);

        lblContactName = new JLabel(CONTACT_NAME);
        contentPane.add(lblContactName, CONTACT_NAME_CONFIG);

        btnCancel = new JButton(CANCEL);
        contentPane.add(btnCancel, CANCEL_BUTTON_POS);

        alignment(this);

    }

    /**
     * Constructor with parameters.
     *
     * @param request     request
     * @param contactName contact name
     */
    public CallFame(IRequest request, IJmsExchanger jmsExchanger, String contactName) {
        this();
        this.request = request;
        this.jmsExchanger = jmsExchanger;
        this.contactName = contactName;
        this.lblContactName.setText(contactName);
        addListeners();
    }

    private void addListeners() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }
}
