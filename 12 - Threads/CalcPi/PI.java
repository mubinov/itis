package CalcPi;

/**
 * Example 4. Pi
 */

import javax.swing.*;

class PI extends Thread
{
    int from,to ;
    static int n =72000000,np=4;
    double h,sum,x;
    static double ssum = 0.0 ;
    static int j;
    JTextArea display;

    public PI(JTextArea display ){
        j = 0 ;
        this.display = display;
    }

    public double f(double a)
    {
        return(4.0 / (1.0 + a*a));
    }

    synchronized void count()
    {
        j = j + 1 ;
        ssum += h * sum ;
        display.setText(String.valueOf(ssum));
    }

    public void run()
    {
        for (int k=0; k<np; k++)
        {
            from = k * n / np;
            to = (k + 1) * n / np;

            h = 1.0 / (double) n;
            sum = 0.0;

            for (int i=from; i<to; i++)
            {
                x = h * ((double) i - 0.5);
                sum += f(x);
            }
            count() ;
            try{
                sleep(1000);
            }catch (InterruptedException e){

            }
        }

    }
}