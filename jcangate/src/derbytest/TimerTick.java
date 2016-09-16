/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author deh
 */
public class TimerTick {
    Toolkit toolkit;
    Timer timer;
    public int count;
    public int flag;
    public long msgct;
    public long msgct_prev;
    
    public TimerTick(int td, int t){
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new SetTick(),
                       td,     //initial delay
                       t);    //subsequent rate
        count = 0;  msgct = 0; msgct_prev = 0;
    }
        class SetTick extends TimerTask {
            @Override
            public void run() {
//                System.out.format("Tick %9d msgct: %d flag: %9d\n",count++, getMsgct(), flag);
                flag += 1;
                msgct += 1;
            }
        }
        public void tickinc(){
            msgct += 1;
        }
        public long getMsgct(){
            long tmp;
            tmp = msgct - msgct_prev;
            msgct_prev = msgct;
            return tmp;
        }
    }            
