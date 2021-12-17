package app.gui;

public class WinSpin {

    public int[] semn= new int[5];
    public double win=0;

    public WinSpin(int[] semn, double sum){

        int numar=0;
        if(semn[0]==semn[1] && semn[1] == semn[2])
            numar=3;
        if(semn[0]==semn[1] && semn[1] == semn[2] && semn[2] == semn[3])
            numar=4;
        if(semn[0]==semn[1] && semn[1] == semn[2] && semn[2] == semn[3] && semn[3] == semn[4])
            numar=5;


        for(int j=0;j<5;j++){
            if(semn[0]==1){
                if(numar==3){
                    win=0.25*sum;
                }
                else if(numar==4){
                    win=0.5*sum;
                }
                else if(numar==5){
                    win=sum;
                }
                else win=0;
            }
            else if(semn[0]==2){
                if(numar==3){
                    win=0.5*sum;
                }
                else if(numar==4){
                    win=sum;
                }
                else if(numar==5){
                    win=1.5*sum;
                }
                else win=0;
            }
            else if(semn[0]==3){
                if(numar==3){
                    win=sum;
                }
                else if(numar==4){
                    win=2*sum;
                }
                else if(numar==5){
                    win=3*sum;
                }
                else win=0;
            }
            else if(semn[0]==4){
                if(numar==3){
                    win=2*sum;
                }
                else if(numar==4){
                    win=6*sum;
                }
                else if(numar==5){
                    win=9*sum;
                }
                else win=0;
            }
            else if(semn[0]==5){
                if(numar==3){
                    win=3*sum;
                }
                else if(numar==4){
                    win=9*sum;
                }
                else if(numar==5){
                    win=15*sum;
                }
                else win=0;
            }
            else if(semn[0]==6){
                if(numar==3){
                    win=5*sum;
                }
                else if(numar==4){
                    win=10*sum;
                }
                else if(numar==5){
                    win=25*sum;
                }
                else win=0;
            }
            else if(semn[0]==7){
                if(numar==3){
                    win=25*sum;
                }
                else if(numar==4){
                    win=50*sum;
                }
                else if(numar==5){
                    win=100*sum;
                }
                else win=0;
            }

        }
    }
    public double getWin() {
        return win;
    }
}