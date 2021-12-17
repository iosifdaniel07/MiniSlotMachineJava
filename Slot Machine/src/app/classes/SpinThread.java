package app.classes;

import app.gui.SlotStable;
import app.gui.WinSpin;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class SpinThread extends Thread{

    private SlotStable[][] _slots;
    private int[][] _valueSlots;
    private BufferedImage _cireasa , _lamaie , _lebenita , _portocale , _pruna , _struguri, _sapte;
    private Clip _clip;
    private static String filePath = "C:\\Users\\Daniel\\Proiect3\\Project3_MehBet\\src\\app\\gui\\audio\\slotAudio.wav";
    private JTextPane _win;
    private double _bet;
    private JTextField _sold;

    public SpinThread(SlotStable[][] _slots, int[][] _valueSlots,BufferedImage _cireasa, BufferedImage _lamaie,
                      BufferedImage _lebenita, BufferedImage _portocale, BufferedImage _pruna, BufferedImage _struguri,
                      BufferedImage _sapte, JTextPane _win, double _bet, JTextField _sold){

        this._valueSlots = _valueSlots;
        this._slots = _slots;
        this._cireasa = _cireasa;
        this._lamaie = _lamaie;
        this._lebenita = _lebenita;
        this._portocale = _portocale;
        this._pruna = _pruna;
        this._struguri =_struguri;
        this._sapte = _sapte;
        this._win = _win;
        this._bet = _bet;
        this._sold = _sold;
    }

    @Override
    public void run(){

        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            _clip = AudioSystem.getClip();

            _clip.open(audioInputStream);
            _clip.start();

        } catch (Exception e) {
           e.printStackTrace();
        }


        Random r = new Random();
         int time = 5;

        for(int k = 0 ; k < 16; ++k) {

            for (int j = 0; j < 5; ++j) {
                for (int i = 2; i >= 1; --i) {
                    _valueSlots[i][j] = _valueSlots[i - 1][j];
                }
            }

            for (int j = 0; j < 5; ++j) {
                _valueSlots[0][j] = r.nextInt(1, 8);   //-8
            }

            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 5; ++j) {
                    switch (_valueSlots[i][j]) {
                        case 1: {
                            _slots[i][j].setImage(_cireasa);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 2: {
                            _slots[i][j].setImage(_lamaie);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 3: {
                            _slots[i][j].setImage(_lebenita);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 4: {
                            _slots[i][j].setImage(_portocale);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 5: {
                            _slots[i][j].setImage(_pruna);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 6: {
                            _slots[i][j].setImage(_struguri);
                            _slots[i][j].repaint();
                            break;
                        }
                        case 7: {
                            _slots[i][j].setImage(_sapte);
                            _slots[i][j].repaint();
                            break;
                        }


                    }


                }

                try{
                    sleep(time);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

         time += 5;

        }
        _clip.stop();

        int[][] linie = new int[5][5];
        for(int j = 0;j<5;j++) {
            for (int i = 0; i < 5; i++) {
                if(j==0)
                    linie[j][i] = _valueSlots[0][i];
                if(j==1)
                    linie[j][i] = _valueSlots[1][i];
                if(j==2)
                    linie[j][i] = _valueSlots[2][i];
                if(j==3){
                    linie[j][0]= _valueSlots[2][0];
                    linie[j][1]= _valueSlots[1][1];
                    linie[j][2] = _valueSlots[0][2];
                    linie[j][3] = _valueSlots[1][3];
                    linie[j][4] = _valueSlots[2][4];
                }
                if(j==4){
                    linie[j][0]= _valueSlots[0][0];
                    linie[j][1]= _valueSlots[1][1];
                    linie[j][2] = _valueSlots[2][2];
                    linie[j][3] = _valueSlots[1][3];
                    linie[j][4] = _valueSlots[0][4];
                }

            }
        }
        double sum=0;
        WinSpin c;

        c= new WinSpin(linie[0],_bet);
        sum=sum+ c.getWin();
        //System.out.println("Ai castigat " + c.getWin() + " de pe linia 1");
        c=new WinSpin(linie[1],_bet);
        sum = sum + c.getWin();
        //System.out.println("Ai castigat " + c.getWin() + " de pe linia 2");
        c=new WinSpin(linie[2],_bet);
        sum = sum + c.getWin();
       // System.out.println("Ai castigat " + c.getWin() + " de pe linia 3");
        c=new WinSpin(linie[3],_bet);
        sum = sum + c.getWin();
       // System.out.println("Ai castigat " + c.getWin() + " de pe linia 4");
        c=new WinSpin(linie[4],_bet);
        sum = sum + c.getWin();
       // System.out.println("Ai castigat " + c.getWin() + " de pe linia 5");

        //System.out.println("Ai castigat " + sum);
        String textsum = String.valueOf(sum);
        _win.setText(textsum);
        _sold.setText(String.format("%.2f", (Double.valueOf(_sold.getText()) + sum)));
    }

}
