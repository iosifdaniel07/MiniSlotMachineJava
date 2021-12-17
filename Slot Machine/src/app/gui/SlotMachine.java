package app.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class SlotMachine {

    private JPanel _mainPanel, _centerPanel,_botPanel, _topPanel;
    private JFrame _frame;
    private JButton _spin, _gamble;
    private SlotStable[][] _slots;
    private int[][] _valueSlots;
    private BufferedImage _cireasa = null , _lamaie = null, _lebenita = null, _portocale = null,
                          _pruna = null, _struguri = null, _sapte = null;
    private ImageIcon _spinIcon,_gambleIcon;
    private JToggleButton _bet10,_bet20,_bet50,_bet100,_bet200;
    private final  Color customColor = new Color(13, 0, 43);
    private JLabel _soldLabel, _winLabel;
    private JTextField _sum;
    private JTextPane _win;
    private double _bet = 0;
    private ActionListener _l;

    public SlotMachine(){

        if(SwingUtilities.isEventDispatchThread()){

            readImages();

            _mainPanel = new JPanel();
            _mainPanel.setLayout(new BorderLayout());

            initializeTopPanel();
            _mainPanel.add(_topPanel,BorderLayout.PAGE_START);

            initializeMyCenterPanelStable();
            _mainPanel.add(_centerPanel,BorderLayout.CENTER);

            initializeBotPanel();
            _mainPanel.add(_botPanel,BorderLayout.PAGE_END);

            myFrame();

        }else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    readImages();

                    _mainPanel = new JPanel();
                    _mainPanel.setLayout(new BorderLayout());

                    initializeTopPanel();
                    _mainPanel.add(_topPanel,BorderLayout.PAGE_START);

                    initializeMyCenterPanelStable();
                    _mainPanel.add(_centerPanel,BorderLayout.CENTER);

                    initializeBotPanel();
                    _mainPanel.add(_botPanel,BorderLayout.PAGE_END);

                    myFrame();
                }
            });
        }

    }

    public void initializeMyCenterPanelStable(){

        _centerPanel = new JPanel();
        _centerPanel.setLayout(new GridLayout(3,5));

        _slots = new SlotStable[3][5];
        _valueSlots = new int[3][5];

        Random r = new Random();

        for(int i = 0; i < 3; ++i) {
            for (int j = 0; j < 5; ++j) {

                _valueSlots[i][j] = r.nextInt(1, 8);

                switch (_valueSlots[i][j]) {
                    case 1: {
                        _slots[i][j] = new SlotStable(_cireasa);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 2: {
                        _slots[i][j] = new SlotStable(_lamaie);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 3: {
                        _slots[i][j] = new SlotStable(_lebenita);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 4: {
                        _slots[i][j] = new SlotStable(_portocale);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 5: {
                        _slots[i][j] = new SlotStable(_pruna);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 6: {
                        _slots[i][j] = new SlotStable(_struguri);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                    case 7: {
                        _slots[i][j] = new SlotStable(_sapte);
                        _slots[i][j].setBackground(customColor);
                        _centerPanel.add(_slots[i][j]);
                        break;
                    }
                }
            }

        }

    }


    public void initializeBotPanel(){

        _botPanel = new JPanel();
        _botPanel.setLayout(new FlowLayout());
        _botPanel.setBackground(Color.lightGray);

        _bet = 0.20;
        _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);

        try{
            _spinIcon = new ImageIcon(this.getClass().getResource("images/spin.jpg"));
            _spin = new JButton();
            _spin.setIcon(_spinIcon);
            _spin.setPreferredSize(new Dimension(70,70));
            _spin.addActionListener(_l);

        }catch(Exception e){
            e.printStackTrace();
        }

        _bet10 = new JToggleButton("Bet10");
        _bet10.setBackground(Color.RED);
        _bet10.setSelected(true);

        _bet10.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                   _bet20.setSelected(false);
                   _bet50.setSelected(false);
                   _bet100.setSelected(false);
                   _bet200.setSelected(false);
                   _bet = 0.20;
                   _spin.removeActionListener(_l);
                    _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);
                   _spin.addActionListener(_l);

                } else {
                    _bet10.setBackground(Color.RED);
                }
            }
        });

        _bet20 = new JToggleButton("Bet20");
        _bet20.setBackground(Color.RED);
        _bet20.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    _bet10.setSelected(false);
                    _bet50.setSelected(false);
                    _bet100.setSelected(false);
                    _bet200.setSelected(false);
                    _bet = 0.50;
                    _spin.removeActionListener(_l);
                    _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);
                    _spin.addActionListener(_l);

                } else {
                    _bet20.setBackground(Color.RED);

                }
            }
        });

        _bet50 = new JToggleButton("Bet50");
        _bet50.setBackground(Color.RED);
        _bet50.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    _bet10.setSelected(false);
                    _bet20.setSelected(false);
                    _bet100.setSelected(false);
                    _bet200.setSelected(false);
                    _bet = 1;
                    _spin.removeActionListener(_l);
                    _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);
                    _spin.addActionListener(_l);

                } else {
                    _bet50.setBackground(Color.RED);
                }
            }
        });

        _bet100 = new JToggleButton("Bet100");
        _bet100.setBackground(Color.RED);
        _bet100.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    _bet10.setSelected(false);
                    _bet20.setSelected(false);
                    _bet50.setSelected(false);
                    _bet200.setSelected(false);

                  _bet = 2;
                    _spin.removeActionListener(_l);
                    _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);
                    _spin.addActionListener(_l);

                } else {
                    _bet100.setBackground(Color.RED);
                }
            }
        });

        _bet200 = new JToggleButton("Bet200");
        _bet200.setBackground(Color.RED);
        _bet200.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    _bet10.setSelected(false);
                    _bet20.setSelected(false);
                    _bet50.setSelected(false);
                    _bet100.setSelected(false);

                    _bet = 5;
                    _spin.removeActionListener(_l);
                    _l = new SpinEvent(_slots,_valueSlots, _portocale , _struguri , _pruna , _lamaie , _lebenita , _cireasa, _sapte, _win, _bet, _sum);
                    _spin.addActionListener(_l);

                } else {
                    _bet200.setBackground(Color.RED);
                }
            }
        });


        try{
            _gambleIcon = new ImageIcon(this.getClass().getResource("images/gamble.JPG"));
            _gamble = new JButton();
            _gamble.setIcon(_gambleIcon);
            _gamble.setPreferredSize(new Dimension(70,70));

        }catch(Exception e){
            e.printStackTrace();
        }


        _botPanel.add(_bet10);
        _botPanel.add(_bet20);
        _botPanel.add(_bet50);
        _botPanel.add(_bet100);
        _botPanel.add(_bet200);
        _botPanel.add(_spin);

    }

    public void initializeTopPanel(){
        _topPanel = new JPanel();
        _topPanel.setBackground(Color.magenta);

        _soldLabel = new JLabel("Sold:");
        _soldLabel.setForeground(Color.WHITE);
        _soldLabel.setPreferredSize(new Dimension(30,20));
        _topPanel.add(_soldLabel);

        _sum = new JTextField();
        _sum.setPreferredSize(new Dimension(80,20));
        _sum.setText("0");
        _topPanel.add(_sum);

        _winLabel = new JLabel("Current Win:");
        _topPanel.add(_winLabel);

        _win = new JTextPane();
        _win.setPreferredSize(new Dimension(80,20));
        _win.setText("0");
        _topPanel.add(_win);


    }

    public void readImages(){

        try{
           _cireasa = ImageIO.read(getClass().getResource("images/cirese.JPG"));
           _lamaie = ImageIO.read(getClass().getResource("images/lamaie.JPG"));
           _lebenita = ImageIO.read(getClass().getResource("images/lebenita.JPG"));
           _portocale = ImageIO.read(getClass().getResource("images/portocala.JPG"));
           _pruna = ImageIO.read(getClass().getResource("images/pruna.JPG"));
           _sapte = ImageIO.read(getClass().getResource("images/sapte.JPG"));
           _struguri = ImageIO.read(getClass().getResource("images/strugure.JPG"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void myFrame(){
        _frame = new JFrame("Slot");
        _frame.setContentPane(_mainPanel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLocation(500,90);
        _frame.setResizable(false);
        _frame.setSize(650,550);
        _frame.setVisible(true);
    }

    public static void main(String[] args) {

        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    SlotMachine s = new SlotMachine();
                }
            });
        }catch(InterruptedException e){
            System.out.println("... s-a intrerupt executia!");
        }
        catch (InvocationTargetException ex){
            System.out.println("... eroare in metoda run() ");
        }

    }


}
