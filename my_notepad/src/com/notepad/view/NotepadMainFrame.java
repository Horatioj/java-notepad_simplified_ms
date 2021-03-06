package com.notepad.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Document;
import javax.swing.text.Utilities;
import javax.swing.undo.UndoManager;

import com.notepad.util.Clock;
import com.notepad.util.MQFontChooser;
import com.notepad.util.SystemParam;
//import com.notepad.util.Client;
//import com.notepad.util.Server;

//??????
public class NotepadMainFrame extends JFrame implements ActionListener{

    /*????????*/
    private JPanel contentPane;

    /*??????*/
    public JTextPane textArea;

    /*??????????*/
    private JMenuBar menuBar;
    //??????????????
    //????
    private JMenu itemFile;
    private JMenuItem itemNew;				   //????
    private JMenuItem itemOpen;                //????
    private JMenuItem itemSave;                //????
    private JMenuItem itemSaveAs;              //??????
    private JSeparator separator;			   //??????
    private JMenuItem itemPage;				   //????????
    private JMenuItem itemPrint;			   //????
    private JSeparator separator_1;			   //??????    
    private JMenuItem itemExit;				   //????
    //????
    private JMenu itemEdit;
    private JMenuItem itemUndo;				   //????    
    private JMenuItem itemRedo;                //????   
    private JSeparator separator_2;            //??????
    private JMenuItem itemCut;				   //????
    private JMenuItem itemCopy;				   //????
    private JMenuItem itemPaste;			   //????
    private JMenuItem itemDelete;			   //????
    private JSeparator separator_3;            //??????
    private JMenuItem itemFind;				   //????
    private JMenuItem itemFindNext;			   //??????????
    private JMenuItem itemReplace;			   //????
    private JMenuItem itemSelectAll;		   //????
    private JSeparator separator_4;            //??????
    private JMenuItem itemTime;				   //????/????
    private JMenuItem itemTurnTo;			   //????
    //????
    private JMenu itemPicture;                 
    private JMenuItem itemInsert;              //????
    private JMenuItem itemZoom;                //????    
    //????
    private JMenu itFormat;					   
    private JMenuItem itemFont;				   //????????
    private JMenuItem itemColor;			   //????????
    private JMenuItem itemFontColor;		   //????????
    //private JCheckBoxMenuItem itemNextLine;	   //????????
    //????
    private JMenu itemCheck;				   
    private JCheckBoxMenuItem itemStatement;   //??????
    //????
    private JMenu itemUpload;

    /*??????*/
    private JScrollPane scrollPane;

    /*????????????*/
    private JPopupMenu popupMenu;
    private JMenuItem popM_Undo;		  //????
    private JMenuItem popM_Redo;		  //????   
    private JSeparator separator_5;       //??????      
    private JMenuItem popM_Cut;			  //????
    private JMenuItem popM_Copy;		  //????
    private JMenuItem popM_Paste;		  //????
    private JMenuItem popM_Delete;		  //????
    private JSeparator separator_6;		  //??????
    private JMenuItem popM_SelectAll;	  //????

    /*??????*/
    private JToolBar toolState;
    public static JLabel label1;          //??????????
    public static JLabel label4;
    private JLabel label2;                //????????????????
    private JLabel label3;
    GregorianCalendar c=new GregorianCalendar();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    int min=c.get(Calendar.MINUTE);
    int second=c.get(Calendar.SECOND); 
    int linenum = 1;                      //??????????
    int sum=0;

    /*????????*/
    //1?????? 
    //2????????
    //3??????????
    int flag=0;
    //??????????
    String currentFileName=null;    
    PrintJob  p=null;//????????????????????
    Graphics  g=null;//????????????    
    //????????????
    String currentPath=null;    
    //????????
    JColorChooser jcc1=null;
    Color color=Color.BLACK;    
    
    //????????
    public Icon insertIcon;

    //??????????
    public UndoManager undoMgr = new UndoManager();     
    //??????
    public Clipboard clipboard = new Clipboard("??????????"); 

    /*????????*/
    public NotepadMainFrame(){
        /*????????*/
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }

        /*??JFrame????????????*/
        setTitle("??????");    
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 721, 772);

        /*????????*/
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        /*????????????*/
        textArea = new JTextPane();

        /*????????*/
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
        contentPane.add(scrollPane, BorderLayout.CENTER); //????????????????????        

        /*????????????*/
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //???????????? ??????F??
        itemFile = new JMenu("????(F)");
        itemFile.setMnemonic('F');	//??????????"F"
        menuBar.add(itemFile);
        //????
        itemNew = new JMenuItem("????(N)");
        itemNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));   //??????????Ctrl+"N"
        itemNew.addActionListener(this);
        itemFile.add(itemNew);
        //????
        itemOpen = new JMenuItem("????(O)");
        itemOpen.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));   //??????????Ctrl+"O"
        itemOpen.addActionListener(this);
        itemFile.add(itemOpen);
        //????
        itemSave = new JMenuItem("????(S)");
        itemSave.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));   //??????????Ctrl+"S"
        itemSave.addActionListener(this);
        itemFile.add(itemSave);
        //??????
        itemSaveAs = new JMenuItem("??????(A)");
        itemSaveAs.addActionListener(this);
        itemFile.add(itemSaveAs);
        //??????????
        separator = new JSeparator();
        itemFile.add(separator);
        //????????
        itemPage = new JMenuItem("????????(U)",'U');
        itemPage.addActionListener(this);
        itemFile.add(itemPage);
        //????
        itemPrint = new JMenuItem("????(P)...",'P');
        itemPrint.setAccelerator(KeyStroke.getKeyStroke('P', KeyEvent.CTRL_DOWN_MASK));   //????????Ctrl+"P"
        itemPrint.addActionListener(this);
        itemFile.add(itemPrint);
        //??????????
        separator_1 = new JSeparator(); 
        itemFile.add(separator_1);
        //????
        itemExit = new JMenuItem("????(X)",'X');
        itemExit.addActionListener(this);
        itemFile.add(itemExit);
        //???????????? ??????E??
        itemEdit = new JMenu("????(E)");
        itemEdit.setMnemonic('E');
        menuBar.add(itemEdit);
        //????
        itemUndo = new JMenuItem("????(U)",'U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"Z"
        itemUndo.addActionListener(this);
        itemEdit.add(itemUndo);
        //????
        itemRedo = new JMenuItem("????(R)");
        itemRedo.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"R"
        itemRedo.addActionListener(this);
        itemEdit.add(itemRedo);
        //????
        itemCut = new JMenuItem("????(T)",'T');
        itemCut.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"X"
        itemCut.addActionListener(this);
        //?????????? 
        separator_2 = new JSeparator();
        itemEdit.add(separator_2);
        itemEdit.add(itemCut);
        //????
        itemCopy = new JMenuItem("????(C)",'C');
        itemCopy.addActionListener(this);
        itemCopy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"C"
        itemEdit.add(itemCopy);
        //????
        itemPaste = new JMenuItem("????(P)",'P');
        itemPaste.addActionListener(this);
        itemPaste.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"V"
        itemEdit.add(itemPaste);
        //????
        itemDelete = new JMenuItem("????(L)",'L');
        itemDelete.addActionListener(this);
        itemDelete.setAccelerator(KeyStroke.getKeyStroke('D', KeyEvent.CTRL_DOWN_MASK));    //??????????Ctrl+"D"
        itemEdit.add(itemDelete);
        //??????
        separator_3 = new JSeparator();
        itemEdit.add(separator_3);
        //????
        itemFind = new JMenuItem("????(F)",'F');
        itemFind.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));   //??????????Ctrl+"F"
        itemFind.addActionListener(this);
        itemEdit.add(itemFind);
        //??????????
        itemFindNext = new JMenuItem("??????????(N)",'N');
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        itemFindNext.addActionListener(this);
        itemEdit.add(itemFindNext);
        //????
        itemReplace = new JMenuItem("????(R)",'R');
        itemReplace.addActionListener(this);
        itemReplace.setAccelerator(KeyStroke.getKeyStroke('H', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"H"
        itemEdit.add(itemReplace);

        //????
        itemSelectAll = new JMenuItem("????(A)",'A');
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"A"
        itemSelectAll.addActionListener(this);
        //??????
        separator_4 = new JSeparator();
        itemEdit.add(separator_4);
        itemEdit.add(itemSelectAll);
        //????/????
        itemTime = new JMenuItem("????/????(D)",'D');
        itemTime.addActionListener(this);
        itemTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        itemEdit.add(itemTime);
        //????
        itemTurnTo = new JMenuItem("????(G)",'G');
        itemTurnTo.addActionListener(this);
        itemTurnTo.setAccelerator(KeyStroke.getKeyStroke('G', KeyEvent.CTRL_DOWN_MASK));  //??????????Ctrl+"G"
        itemEdit.add(itemTurnTo);
        //???????????? ????
        itemPicture = new JMenu("????(P)");
        itemPicture.setMnemonic('P');
        menuBar.add(itemPicture);
        //????
        itemInsert = new JMenuItem("????(I)");
        itemInsert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                JFileChooser f = new JFileChooser();
                f.showOpenDialog(null);
                insertFile(f.getSelectedFile());
            }
        });
        itemPicture.add(itemInsert);
        //????
        itemZoom = new JMenuItem("????(Z)");
        itemZoom.addActionListener(this);
        itemPicture.add(itemZoom);
        //???????????? ????
        itFormat = new JMenu("????(O)");
        itFormat.setMnemonic('O');
        menuBar.add(itFormat);
        //????????
        itemFont = new JMenuItem("????????(F)...");
        itemFont.addActionListener(this);
        itFormat.add(itemFont);
        //????????
        itemFontColor = new JMenuItem("????????(I)...");
        itemFontColor.addActionListener(this);
        itFormat.add(itemFontColor);        
        //????????
        itemColor = new JMenuItem("????????(C)...");
        itemColor.addActionListener(this);
        itFormat.add(itemColor);
        // ????????
        /*
        itemNextLine = new JCheckBoxMenuItem("????????(W)");
        itemNextLine.addActionListener(this);
        itFormat.add(itemNextLine);*/

        //???????????? ????
        itemCheck = new JMenu("????(V)");
        itemCheck.setMnemonic('V');
        menuBar.add(itemCheck);
        //??????
        itemStatement = new JCheckBoxMenuItem("??????(S)");
        itemStatement.addActionListener(this);
        itemCheck.add(itemStatement);

        itemUpload = new JMenu("????(U)");
        itemUpload.setMnemonic('U');
        menuBar.add(itemUpload);

        /*????????????????*/
        popupMenu = new JPopupMenu();
        addPopup(textArea, popupMenu);
        //????
        popM_Undo = new JMenuItem("????(U)");
        popM_Undo.addActionListener(this);
        popupMenu.add(popM_Undo);
        //????
        popM_Redo = new JMenuItem("????(R)");
        popM_Redo.addActionListener(this);
        popupMenu.add(popM_Redo);
        //??????
        separator_5 = new JSeparator();
        popupMenu.add(separator_5);
        //????
        popM_Cut = new JMenuItem("????(T)");
        popM_Cut.addActionListener(this);
        popupMenu.add(popM_Cut);
        //????
        popM_Copy = new JMenuItem("????(C)");
        popM_Copy.addActionListener(this);
        popupMenu.add(popM_Copy);
        //????
        popM_Paste = new JMenuItem("????(P)");
        popM_Paste.addActionListener(this);
        popupMenu.add(popM_Paste);
        //????
        popM_Delete = new JMenuItem("????(D)");
        popM_Delete.addActionListener(this);
        popupMenu.add(popM_Delete);
        //??????
        separator_6 = new JSeparator();
        popupMenu.add(separator_6);
        //????
        popM_SelectAll = new JMenuItem("????(A)");
        popM_SelectAll.addActionListener(this);
        popupMenu.add(popM_SelectAll);

        // ????????????
        final JPopupMenu jp=new JPopupMenu();    //????????????????????????????????
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//??????????????????????
                {
                    jp.show(e.getComponent(),e.getX(),e.getY());//????????????????????????
                }
            }
        });

        /*??????????*/
        toolState = new JToolBar();
        toolState.setSize(textArea.getSize().width, 10);
        //????????????
        label1 = new JLabel("    ??????????????" + hour + ":" + min + ":" + second+" ");
        toolState.add(label1);  
        toolState.addSeparator();
        //????????????
        label2 = new JLabel("    ?? " + linenum + " ??");
        toolState.add(label2);
        textArea.addCaretListener(new CaretListener() {        //??????????????
            public void caretUpdate(CaretEvent e) {
                int totalCharacters = NotepadMainFrame.this.textArea.getText().length(); 
                int lineCount = (totalCharacters == 0) ? 1 : 0;
                try {
                    int offset = totalCharacters; 
                    while (offset > 0) {
                       offset = Utilities.getRowStart(NotepadMainFrame.this.textArea, offset) - 1;
                       lineCount++;
                    }
                    linenum = lineCount;
                    label2.setText("    ?? " + linenum + " ??");
                } catch (Exception ex) { }
            }});
        toolState.addSeparator();
        //????????????
        label3 = new JLabel("    ???? " + sum +" ??  ");
        toolState.add(label3);  
        textArea.addCaretListener(new CaretListener() {        //??????????????
            public void caretUpdate(CaretEvent e) {
                try {
                    sum=NotepadMainFrame.this.textArea.getText().toString().length();
                    label3.setText("    ???? " +sum+" ??  ");
                }
                catch(Exception ex) { }
            }});
        toolState.addSeparator();
        label4 = new JLabel("    ??????????");
        toolState.add(label4);

        contentPane.add(toolState, BorderLayout.SOUTH);  //????????????????????
        toolState.setVisible(true);
        toolState.setFloatable(false);
        Clock clock=new Clock();
        clock.start();   //????????????


        //??????????????
        textArea.getDocument().addUndoableEditListener(undoMgr);
        isChanged();
        this.MainFrameWidowListener();

    }

    /*??????*/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NotepadMainFrame frame = new NotepadMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*????????*/
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemOpen){            //????
            openFile();
        }else if(e.getSource()==itemSave){        //????
            //??????????????????????????????????
            save();
        }else if(e.getSource()==itemSaveAs){    //??????
            saveAs();
        }else if(e.getSource()==itemNew){        //????
            newFile();
        }else if(e.getSource()==itemExit){        //????
            exit();
        }else if(e.getSource()==itemPage){        //????????
            PageFormat pf = new PageFormat();
            PrinterJob.getPrinterJob().pageDialog(pf); 
        }else if(e.getSource()==itemPrint){        //????
            //??????
            Print();
        }else if(e.getSource()==itemUndo || e.getSource()==popM_Undo){        //????
            if(undoMgr.canUndo()){
                undoMgr.undo();
            }
        }else if(e.getSource()==itemRedo || e.getSource()==popM_Redo){        //????
            if(undoMgr.canRedo()){
                undoMgr.redo();
            }
        }else if(e.getSource()==itemCut || e.getSource()==popM_Cut){        //????
            cut();
        }else if(e.getSource()==itemCopy || e.getSource()==popM_Copy){        //????
            copy();
        }else if(e.getSource()==itemPaste || e.getSource()==popM_Paste){    //????
            paste();
        }else if(e.getSource()==itemDelete || e.getSource()==popM_Delete){    //????
            String tem=textArea.getText().toString();
            textArea.setText(tem.substring(0,textArea.getSelectionStart())); 
        }else if(e.getSource()==itemFind){        //????
            mySearch();
        }else if(e.getSource()==itemFindNext){    //??????????
            mySearch();
        }else if(e.getSource()==itemReplace){    //????
            mySearch();
        }else if(e.getSource()==itemSelectAll || e.getSource()==popM_SelectAll){    //????????
            textArea.selectAll();
        }else if(e.getSource()==itemTime){        //????/????
            textArea.replaceSelection(hour+":"+min+" "+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH));
        }else if(e.getSource()==itemTurnTo){    //????
            turnTo();
        }
        else if(e.getSource()==itemFont){        //????????????
            // ????????????????????????????????
            MQFontChooser fontChooser = new MQFontChooser(textArea.getFont());
            fontChooser.showFontDialog(this);
            Font font = fontChooser.getSelectFont();
            // ????????????JTextArea??
            textArea.setFont(font);
        }else if(e.getSource()==itemColor){        //????????????
            jcc1 = new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1,"????????????",-1);
            color = jcc1.getColor();
            textArea.setBackground(color);
        }/*else if(e.getSource()==itemNextLine){    //????????????
            if(itemNextLine.isSelected()){
                textArea.getScrollableTracksViewportWidth();
            }else{
                textArea.getScrollableTracksViewportWidth();
            }
        }*/
        else if(e.getSource()==itemFontColor){    //????????????
            jcc1=new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1, "????????????", -1);
            color = jcc1.getColor();
            textArea.setForeground(color);
        }else if(e.getSource()==itemStatement){    //????????
            if(itemStatement.isSelected()){

                toolState.setVisible(true);
            }else{
                toolState.setVisible(false);
            }
        }else if(e.getSource()==itemInsert){
            
        }else if(e.getSource()==itemZoom){
            zoomImage();
        }else if(e.getSource()==itemUpload){
            //uploadFile();
        }
    }
    /*????????????*/
    private void zoomImage() {
        //????????
        final JDialog zoomDialog = new JDialog(this, "????????");
        zoomDialog.setSize(600, 100);
        zoomDialog.setResizable(true);
        zoomDialog.setLocation(300, 280);
        zoomDialog.setVisible(true);

        //????????????
        JLabel zoomLabel1 = new JLabel("????????:");
        final JTextField textHight = new JTextField(5);
        JLabel zoomLabel2 = new JLabel("????????:");
        final JTextField textWidth = new JTextField(5);

        //????????
        JButton okButton = new JButton("????");
        JButton cancelButton = new JButton("????");

        //????????
        Container con = zoomDialog.getContentPane();
        con.setLayout(new FlowLayout());
        con.add(zoomLabel1);
        con.add(textHight);
        con.add(zoomLabel2);
        con.add(textWidth);
        con.add(okButton);
        con.add(cancelButton);
        
        //??????????????????????????
        textHight.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconHeight()));
        textWidth.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconWidth()));

        
        //????????
        okButton.addActionListener(new ActionListener() {
            

            public void actionPerformed(ActionEvent e) {
                int height = Integer.parseInt(textHight.getText());
                int width = Integer.parseInt(textWidth.getText());
                ImageIcon imageIcon = (ImageIcon) insertIcon;
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_AREA_AVERAGING); // scale it the smooth way  
                insertIcon = new ImageIcon(newimg); 
                int end = textArea.getCaretPosition();
                int start = end - 1;
                textArea.select(start, end);
                textArea.replaceSelection("");
                textArea.insertIcon(insertIcon);
                
                
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomDialog.dispose();
            }
        });



    }
    /*????????*/
    private void openFile() {
        if(flag==2 && this.currentPath==null){
            //1??????????????????0??????????????1??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "???????????????????????",
             "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();
            }
        }else if(flag==2 && this.currentPath!=null){
            //2??????????????2????????????3????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "????????????????"+
            this.currentPath+"?", "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();
            }
        }
        //??????????????
        JFileChooser choose=new JFileChooser();
        //????????
        int result=choose.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //??????????????
            File file=choose.getSelectedFile();
            //????????????????????????????????????
            currentFileName=file.getName();
            //??????????????
            currentPath=file.getAbsolutePath();
            flag=3;
            this.setTitle(this.currentPath);
            BufferedReader br=null;
            try {
                //??????????[??????]
                InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
                br=new BufferedReader(isr);//????????
                //????????
                StringBuffer sb=new StringBuffer();
                String line=null;
                while((line=br.readLine())!=null){
                    sb.append(line+SystemParam.LINE_SEPARATOR);
                }
                //????????????[????]
                textArea.setText(sb.toString());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally{
                try {
                    if(br!=null) br.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    /*?????????????????????????????????????? */    
    private void newFile() {
        if(flag==0 || flag==1){        //??????????????0??????????????1
            return;
        }else if(flag==2 && this.currentPath==null){        //??????
            //1??????????????????0??????????????1??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "???????????????????????",
             "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();        //??????                
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("??????");
                flag=1;
            }
            return;
        }else if(flag==2 && this.currentPath!=null ){
            //2????????????????3??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "????????????????"+
            this.currentPath+"?", "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();        //????????????????
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("??????");
                flag=1;
            }
        }else if(flag==3){        //??????????
            this.textArea.setText("");
            flag=1;
            this.setTitle("??????");
        }
    }
    /*????????????????????????????????????*/
    private void exit() {
        if(flag==2 && currentPath==null){
            //??????????????
            //1??????????????????0??????????????1??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "???????????????????????",
             "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.saveAs();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else if(flag==2 && currentPath!=null){
            //??????????????
            //1??????????????????0??????????????1??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "????????????????"+currentPath+"?",
             "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.save();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else{
            //??????????????
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "??????????", "????????",
             JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    /*????*/
    public void Print()
    {
        try{
            p = getToolkit().getPrintJob(this,"ok",null);//????????Printfjob ???? p
            g = p.getGraphics();//p ?????????????????? Graphics ??????
            //g.translate(120,200);//?????????????? 
            this.textArea.printAll(g);
            p.end();//???????? g  
        }
        catch(Exception a){

        } 
    }
    /*??????????????*/
    public void cut(){
        copy();
        if (this.textArea.getSelectedText() != null){
            this.textArea.replaceSelection("");
        }
    }    
    public void copy(){
        //????????????
        String temp = this.textArea.getSelectedText();
        //????????????????????????????????????????????????????
        StringSelection text = new StringSelection(temp);
        //????????????????
        this.clipboard.setContents(text, null);
    }
    public void paste(){
        //Transferable??????????????????????????????
        Transferable contents = this.clipboard.getContents(this);
        //DataFalvor????????????????????????????????????????????
        DataFlavor flavor = DataFlavor.stringFlavor;
        //????????????
        if(contents.isDataFlavorSupported(flavor)){
            String str;
            try {//????????
                str=(String)contents.getTransferData(flavor);
                this.textArea.replaceSelection(str);

            } catch(UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
    /*????*/
    public void mySearch() {
        final JDialog findDialog = new JDialog(this, "??????????", true);
        Container con = findDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchContentLabel = new JLabel("????????(N) :");
        JLabel replaceContentLabel = new JLabel("??????(P)?? :");
        final JTextField findText = new JTextField(22);
        final JTextField replaceText = new JTextField(22);
        final JCheckBox matchcase = new JCheckBox("??????????");
        ButtonGroup bGroup = new ButtonGroup();
        final JRadioButton up = new JRadioButton("????(U)");
        final JRadioButton down = new JRadioButton("????(D)");
        down.setSelected(true);
        bGroup.add(up);
        bGroup.add(down);
        JButton searchNext = new JButton("??????????(F)");
        JButton replace = new JButton("????(R)");
        final JButton replaceAll = new JButton("????????(A)");
        searchNext.setPreferredSize(new Dimension(110, 22));
        replace.setPreferredSize(new Dimension(110, 22));
        replaceAll.setPreferredSize(new Dimension(110, 22));
        // "????"??????????????
        replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (replaceText.getText().length() == 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection("");
                if (replaceText.getText().length() > 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection(replaceText.getText());
            }
        });

        // "????????"??????????????
        replaceAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                textArea.setCaretPosition(0); // ????????????????????
                int a = 0, b = 0, replaceCount = 0;

                if (findText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(findDialog, "??????????????!", "????",
                     JOptionPane.WARNING_MESSAGE);
                    findText.requestFocus(true);
                    return;
                }
                while (a > -1) {

                    int FindStartPos = textArea.getCaretPosition();
                    String str1, str2, str3, str4, strA, strB;
                    str1 = textArea.getText();
                    str2 = str1.toLowerCase();
                    str3 = findText.getText();
                    str4 = str3.toLowerCase();

                    if (matchcase.isSelected()) {
                        strA = str1;
                        strB = str3;
                    } else {
                        strA = str2;
                        strB = str4;
                    }

                    if (up.isSelected()) {
                        if (textArea.getSelectedText() == null) {
                            a = strA.lastIndexOf(strB, FindStartPos - 1);
                        } else {
                            a = strA.lastIndexOf(strB, FindStartPos - findText.getText().length() - 1);
                        }
                    } else if (down.isSelected()) {
                        if (textArea.getSelectedText() == null) {
                            a = strA.indexOf(strB, FindStartPos);
                        } else {
                            a = strA.indexOf(strB, FindStartPos - findText.getText().length() + 1);
                        }

                    }

                    if (a > -1) {
                        if (up.isSelected()) {
                            textArea.setCaretPosition(a);
                            b = findText.getText().length();
                            textArea.select(a, a + b);
                        } else if (down.isSelected()) {
                            textArea.setCaretPosition(a);
                            b = findText.getText().length();
                            textArea.select(a, a + b);
                        }
                    } else {
                        if (replaceCount == 0) {
                            JOptionPane.showMessageDialog(findDialog, "??????????????????!",
                             "??????", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(findDialog, "????????" + replaceCount +
                             "??????????!", "????????", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    if (replaceText.getText().length() == 0 && textArea.getSelectedText() != null) {
                        textArea.replaceSelection("");
                        replaceCount++;
                    }
                    if (replaceText.getText().length() > 0 && textArea.getSelectedText() != null) {
                        textArea.replaceSelection(replaceText.getText());
                        replaceCount++;
                    }
                }// end while
            }
        }); /* "????????"?????????????????? */

        // "??????????"????????????
        searchNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int a = 0, b = 0;
                int FindStartPos = textArea.getCaretPosition();
                String str1, str2, str3, str4, strA, strB;
                str1 = textArea.getText();
                str2 = str1.toLowerCase();
                str3 = findText.getText();
                str4 = str3.toLowerCase();
                // "??????????"??CheckBox??????
                if (matchcase.isSelected()) {
                    strA = str1;
                    strB = str3;
                } else {
                    strA = str2;
                    strB = str4;
                }

                if (up.isSelected()) {
                    if (textArea.getSelectedText() == null) {
                        a = strA.lastIndexOf(strB, FindStartPos - 1);
                    } else {
                        a = strA.lastIndexOf(strB, FindStartPos - findText.getText().length() - 1);
                    }
                } else if (down.isSelected()) {
                    if (textArea.getSelectedText() == null) {
                        a = strA.indexOf(strB, FindStartPos);
                    } else {
                        a = strA.indexOf(strB, FindStartPos - findText.getText().length() + 1);
                    }

                }
                if (a > -1) {
                    if (up.isSelected()) {
                        textArea.setCaretPosition(a);
                        b = findText.getText().length();
                        textArea.select(a, a + b);
                    } else if (down.isSelected()) {
                        textArea.setCaretPosition(a);
                        b = findText.getText().length();
                        textArea.select(a, a + b);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "??????????????????!", "??????",
                     JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });/* "??????????"???????????????? */
        // "????"??????????????
        JButton cancel = new JButton("????");
        cancel.setPreferredSize(new Dimension(110, 22));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findDialog.dispose();
            }
        });

        // ????"??????????"????????????
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();

        JPanel direction = new JPanel();
        direction.setBorder(BorderFactory.createTitledBorder("???? "));
        direction.add(up);
        direction.add(down);
        direction.setPreferredSize(new Dimension(170, 60));
        JPanel replacePanel = new JPanel();
        replacePanel.setLayout(new GridLayout(2, 1));
        replacePanel.add(replace);
        replacePanel.add(replaceAll);

        topPanel.add(searchContentLabel);
        topPanel.add(findText);
        topPanel.add(searchNext);
        centerPanel.add(replaceContentLabel);
        centerPanel.add(replaceText);
        centerPanel.add(replacePanel);
        bottomPanel.add(matchcase);
        bottomPanel.add(direction);
        bottomPanel.add(cancel);

        con.add(topPanel);
        con.add(centerPanel);
        con.add(bottomPanel);

        // ????"??????????"????????????????????????(??)??????????????
        findDialog.setSize(410, 210);
        findDialog.setResizable(false);
        findDialog.setLocation(230, 280);
        findDialog.setVisible(true);
    }

    

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() 
        {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
    /*??????????*/
    private void isChanged() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //??????????????????????????????????????????????????????textArea????????????
                Character c=e.getKeyChar();
                if(c != null && !textArea.getText().toString().equals("")){
                    flag=2;
                }
            }
        });
    }
    /*????????????????????????????????*/
    private void MainFrameWidowListener() {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(flag==2 && currentPath==null){
                    //??????????????
                    //1??????????????????0??????????????1??????????????
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "???????????????????????",
                     "??????", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.saveAs();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else if(flag==2 && currentPath!=null){
                    //??????????????
                    //1??????????????????0??????????????1??????????????
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this,
                     "????????????????"+currentPath+"?", "??????", JOptionPane.YES_NO_CANCEL_OPTION,
                      JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.save();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else{
                    //??????????????
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "??????????",
                     "????????", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
            }
        });
    }
    /*????*/
    private void save() {
        if(this.currentPath==null){
            this.saveAs();
            if(this.currentPath==null){
                return;
            }
        }
        FileWriter fw=null;
        //????
        try {
            fw=new FileWriter(new  File(currentPath));
            fw.write(textArea.getText());
            //??????????????????
            fw.flush();
            flag=3;
            this.setTitle(this.currentPath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(fw!=null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*??????*/
    private void saveAs() {
        //??????????
        JFileChooser choose=new JFileChooser();
        //????????
        int result=choose.showSaveDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //??????????????[??????????????????]
            File file=choose.getSelectedFile();
            FileWriter fw=null;
            //????
            try {
                fw=new FileWriter(file);
                fw.write(textArea.getText());
                currentFileName=file.getName();
                currentPath=file.getAbsolutePath();
                //??????????????????
                fw.flush();
                this.flag=3;
                this.setTitle(currentPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally{
                try {
                    if(fw!=null) fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*????*/
    private void turnTo() {
        final JDialog gotoDialog = new JDialog(this, "??????????");
        JLabel gotoLabel = new JLabel("????(L):");
        final JTextField linenum = new JTextField(5);
        linenum.setText("1");
        linenum.selectAll();

        JButton okButton = new JButton("????");
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int totalLine = NotepadMainFrame.this.linenum;
                int[] lineNumber = new int[totalLine + 1];
                String s = textArea.getText();
                int pos = 0, t = 0;

                while (true) {
                    pos = s.indexOf('\12', pos);
                    // System.out.println("????pos:"+pos);
                    if (pos == -1)
                        break;
                    lineNumber[t++] = pos++;
                }

                int gt = 1;
                try {
                    gt = Integer.parseInt(linenum.getText());
                } catch (NumberFormatException efe) {
                    JOptionPane.showMessageDialog(null, "??????????!", "????", JOptionPane.WARNING_MESSAGE);
                    linenum.requestFocus(true);
                    return;
                }

                if (gt < 2 || gt >= totalLine) {
                    if (gt < 2)
                        textArea.setCaretPosition(0);
                    else
                        textArea.setCaretPosition(s.length());
                } else
                    textArea.setCaretPosition(lineNumber[gt - 2] + 1);

                gotoDialog.dispose();//????????
            }

        });

        JButton cancelButton = new JButton("????");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gotoDialog.dispose();
            }
        });

        //??????????????????
        Container con = gotoDialog.getContentPane();
        con.setLayout(new FlowLayout());
        con.add(gotoLabel);
        con.add(linenum);
        con.add(okButton);
        con.add(cancelButton);

        gotoDialog.setSize(200, 100);
        gotoDialog.setResizable(false);
        gotoDialog.setLocation(300, 280);
        gotoDialog.setVisible(true);
    }

    /*????????*/
    public void insertFile(File file){
        Document doc = textArea.getStyledDocument();
        textArea.setCaretPosition(doc.getLength());
        insertIcon = new ImageIcon(file.getPath());
        textArea.insertIcon(insertIcon); 
        int end = textArea.getCaretPosition();
        int start = end - 1;
    }
    
}