package com.nodepad.util;  
  
import java.util.Calendar;  
import java.util.GregorianCalendar;

import com.nodepad.view.NotepadMainFrame;  
  
public class Clock extends Thread{  
  
    public void run() {  
        while (true) {  
            GregorianCalendar time = new GregorianCalendar();  
            int hour = time.get(Calendar.HOUR_OF_DAY);  
            int min = time.get(Calendar.MINUTE);  
            int second = time.get(Calendar.SECOND);  
            NotepadMainFrame.label1.setText("    ??ǰʱ?䣺" + hour + ":" + min + ":" + second);  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException exception) {  
            }  
        }  
    }  
}  
