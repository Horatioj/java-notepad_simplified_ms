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

//�����
public class NotepadMainFrame extends JFrame implements ActionListener{

    /*�������*/
    private JPanel contentPane;

    /*�༭��*/
    public JTextPane textArea;

    /*�Ϸ��˵���*/
    private JMenuBar menuBar;
    //�Ϸ��˵���Ԫ��
    //�ļ�
    private JMenu itemFile;
    private JMenuItem itemNew;				   //�½�
    private JMenuItem itemOpen;                //��
    private JMenuItem itemSave;                //����
    private JMenuItem itemSaveAs;              //���Ϊ
    private JSeparator separator;			   //�ָ���
    private JMenuItem itemPage;				   //ҳ������
    private JMenuItem itemPrint;			   //��ӡ
    private JSeparator separator_1;			   //�ָ���    
    private JMenuItem itemExit;				   //�˳�
    //�༭
    private JMenu itemEdit;
    private JMenuItem itemUndo;				   //����    
    private JMenuItem itemRedo;                //�ָ�   
    private JSeparator separator_2;            //�ָ���
    private JMenuItem itemCut;				   //����
    private JMenuItem itemCopy;				   //����
    private JMenuItem itemPaste;			   //ճ��
    private JMenuItem itemDelete;			   //ɾ��
    private JSeparator separator_3;            //�ָ���
    private JMenuItem itemFind;				   //����
    private JMenuItem itemFindNext;			   //������һ��
    private JMenuItem itemReplace;			   //�滻
    private JMenuItem itemSelectAll;		   //ȫѡ
    private JSeparator separator_4;            //�ָ���
    private JMenuItem itemTime;				   //����/ʱ��
    private JMenuItem itemTurnTo;			   //ת��
    //ͼƬ
    private JMenu itemPicture;                 
    private JMenuItem itemInsert;              //����
    private JMenuItem itemZoom;                //����    
    //��ʽ
    private JMenu itFormat;					   
    private JMenuItem itemFont;				   //�����С
    private JMenuItem itemColor;			   //������ɫ
    private JMenuItem itemFontColor;		   //������ɫ
    //private JCheckBoxMenuItem itemNextLine;	   //�Զ�����
    //�鿴
    private JMenu itemCheck;				   
    private JCheckBoxMenuItem itemStatement;   //״̬��
    //�ϴ�
    private JMenu itemUpload;

    /*������*/
    private JScrollPane scrollPane;

    /*�Ҽ������˵�*/
    private JPopupMenu popupMenu;
    private JMenuItem popM_Undo;		  //����
    private JMenuItem popM_Redo;		  //�ָ�   
    private JSeparator separator_5;       //�ָ���      
    private JMenuItem popM_Cut;			  //����
    private JMenuItem popM_Copy;		  //����
    private JMenuItem popM_Paste;		  //ճ��
    private JMenuItem popM_Delete;		  //ɾ��
    private JSeparator separator_6;		  //�ָ���
    private JMenuItem popM_SelectAll;	  //ȫѡ

    /*״̬��*/
    private JToolBar toolState;
    public static JLabel label1;          //״̬��ʱ��
    public static JLabel label4;
    private JLabel label2;                //״̬������������
    private JLabel label3;
    GregorianCalendar c=new GregorianCalendar();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    int min=c.get(Calendar.MINUTE);
    int second=c.get(Calendar.SECOND); 
    int linenum = 1;                      //�ı�������
    int sum=0;

    /*ĳЩ����*/
    //1���½� 
    //2���޸Ĺ�
    //3���������
    int flag=0;
    //��ǰ�ļ���
    String currentFileName=null;    
    PrintJob  p=null;//����һ��Ҫ��ӡ�Ķ���
    Graphics  g=null;//Ҫ��ӡ�Ķ���    
    //��ǰ�ļ�·��
    String currentPath=null;    
    //������ɫ
    JColorChooser jcc1=null;
    Color color=Color.BLACK;    
    
    //����ͼƬ
    public Icon insertIcon;

    //����������
    public UndoManager undoMgr = new UndoManager();     
    //������
    public Clipboard clipboard = new Clipboard("ϵͳ���а�"); 

    /*���캯��*/
    public NotepadMainFrame(){
        /*���÷��*/
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

        /*��JFrame���л�������*/
        setTitle("�ޱ���");    
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 721, 772);

        /*���ñ߿�*/
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        /*�����ı�����*/
        textArea = new JTextPane();

        /*���û���*/
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
        contentPane.add(scrollPane, BorderLayout.CENTER); //��ӵ�����С��м䡿        

        /*�½��Ϸ��˵�*/
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //��һ���˵��� �ļ���F��
        itemFile = new JMenu("�ļ�(F)");
        itemFile.setMnemonic('F');	//���ÿ�ݼ�"F"
        menuBar.add(itemFile);
        //�½�
        itemNew = new JMenuItem("�½�(N)");
        itemNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));   //���ÿ�ݼ�Ctrl+"N"
        itemNew.addActionListener(this);
        itemFile.add(itemNew);
        //��
        itemOpen = new JMenuItem("��(O)");
        itemOpen.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));   //���ÿ�ݼ�Ctrl+"O"
        itemOpen.addActionListener(this);
        itemFile.add(itemOpen);
        //����
        itemSave = new JMenuItem("����(S)");
        itemSave.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));   //���ÿ�ݼ�Ctrl+"S"
        itemSave.addActionListener(this);
        itemFile.add(itemSave);
        //���Ϊ
        itemSaveAs = new JMenuItem("���Ϊ(A)");
        itemSaveAs.addActionListener(this);
        itemFile.add(itemSaveAs);
        //��ӷָ���
        separator = new JSeparator();
        itemFile.add(separator);
        //ҳ������
        itemPage = new JMenuItem("ҳ������(U)",'U');
        itemPage.addActionListener(this);
        itemFile.add(itemPage);
        //��ӡ
        itemPrint = new JMenuItem("��ӡ(P)...",'P');
        itemPrint.setAccelerator(KeyStroke.getKeyStroke('P', KeyEvent.CTRL_DOWN_MASK));   //�ÿ�ݼ�Ctrl+"P"
        itemPrint.addActionListener(this);
        itemFile.add(itemPrint);
        //��ӷָ���
        separator_1 = new JSeparator(); 
        itemFile.add(separator_1);
        //�˳�
        itemExit = new JMenuItem("�˳�(X)",'X');
        itemExit.addActionListener(this);
        itemFile.add(itemExit);
        //�ڶ����˵��� �༭��E��
        itemEdit = new JMenu("�༭(E)");
        itemEdit.setMnemonic('E');
        menuBar.add(itemEdit);
        //����
        itemUndo = new JMenuItem("����(U)",'U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"Z"
        itemUndo.addActionListener(this);
        itemEdit.add(itemUndo);
        //�ָ�
        itemRedo = new JMenuItem("�ָ�(R)");
        itemRedo.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"R"
        itemRedo.addActionListener(this);
        itemEdit.add(itemRedo);
        //����
        itemCut = new JMenuItem("����(T)",'T');
        itemCut.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"X"
        itemCut.addActionListener(this);
        //��ӷָ��� 
        separator_2 = new JSeparator();
        itemEdit.add(separator_2);
        itemEdit.add(itemCut);
        //����
        itemCopy = new JMenuItem("����(C)",'C');
        itemCopy.addActionListener(this);
        itemCopy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"C"
        itemEdit.add(itemCopy);
        //ճ��
        itemPaste = new JMenuItem("ճ��(P)",'P');
        itemPaste.addActionListener(this);
        itemPaste.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"V"
        itemEdit.add(itemPaste);
        //ɾ��
        itemDelete = new JMenuItem("ɾ��(L)",'L');
        itemDelete.addActionListener(this);
        itemDelete.setAccelerator(KeyStroke.getKeyStroke('D', KeyEvent.CTRL_DOWN_MASK));    //���ÿ�ݼ�Ctrl+"D"
        itemEdit.add(itemDelete);
        //�ָ���
        separator_3 = new JSeparator();
        itemEdit.add(separator_3);
        //����
        itemFind = new JMenuItem("����(F)",'F');
        itemFind.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));   //���ÿ�ݼ�Ctrl+"F"
        itemFind.addActionListener(this);
        itemEdit.add(itemFind);
        //������һ��
        itemFindNext = new JMenuItem("������һ��(N)",'N');
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        itemFindNext.addActionListener(this);
        itemEdit.add(itemFindNext);
        //�滻
        itemReplace = new JMenuItem("�滻(R)",'R');
        itemReplace.addActionListener(this);
        itemReplace.setAccelerator(KeyStroke.getKeyStroke('H', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"H"
        itemEdit.add(itemReplace);

        //ȫѡ
        itemSelectAll = new JMenuItem("ȫѡ(A)",'A');
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"A"
        itemSelectAll.addActionListener(this);
        //�ָ���
        separator_4 = new JSeparator();
        itemEdit.add(separator_4);
        itemEdit.add(itemSelectAll);
        //����/ʱ��
        itemTime = new JMenuItem("ʱ��/����(D)",'D');
        itemTime.addActionListener(this);
        itemTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        itemEdit.add(itemTime);
        //ת��
        itemTurnTo = new JMenuItem("ת��(G)",'G');
        itemTurnTo.addActionListener(this);
        itemTurnTo.setAccelerator(KeyStroke.getKeyStroke('G', KeyEvent.CTRL_DOWN_MASK));  //���ÿ�ݼ�Ctrl+"G"
        itemEdit.add(itemTurnTo);
        //�������˵��� ͼƬ
        itemPicture = new JMenu("ͼƬ(P)");
        itemPicture.setMnemonic('P');
        menuBar.add(itemPicture);
        //����
        itemInsert = new JMenuItem("����(I)");
        itemInsert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                JFileChooser f = new JFileChooser();
                f.showOpenDialog(null);
                insertFile(f.getSelectedFile());
            }
        });
        itemPicture.add(itemInsert);
        //����
        itemZoom = new JMenuItem("����(Z)");
        itemZoom.addActionListener(this);
        itemPicture.add(itemZoom);
        //���ĸ��˵��� ��ʽ
        itFormat = new JMenu("��ʽ(O)");
        itFormat.setMnemonic('O');
        menuBar.add(itFormat);
        //�����С
        itemFont = new JMenuItem("�����С(F)...");
        itemFont.addActionListener(this);
        itFormat.add(itemFont);
        //������ɫ
        itemFontColor = new JMenuItem("������ɫ(I)...");
        itemFontColor.addActionListener(this);
        itFormat.add(itemFontColor);        
        //������ɫ
        itemColor = new JMenuItem("������ɫ(C)...");
        itemColor.addActionListener(this);
        itFormat.add(itemColor);
        // �Զ�����
        /*
        itemNextLine = new JCheckBoxMenuItem("�Զ�����(W)");
        itemNextLine.addActionListener(this);
        itFormat.add(itemNextLine);*/

        //������˵��� �鿴
        itemCheck = new JMenu("�鿴(V)");
        itemCheck.setMnemonic('V');
        menuBar.add(itemCheck);
        //״̬��
        itemStatement = new JCheckBoxMenuItem("״̬��(S)");
        itemStatement.addActionListener(this);
        itemCheck.add(itemStatement);

        itemUpload = new JMenu("�ϴ�(U)");
        itemUpload.setMnemonic('U');
        menuBar.add(itemUpload);

        /*�½��Ҽ������˵�*/
        popupMenu = new JPopupMenu();
        addPopup(textArea, popupMenu);
        //����
        popM_Undo = new JMenuItem("����(U)");
        popM_Undo.addActionListener(this);
        popupMenu.add(popM_Undo);
        //�ָ�
        popM_Redo = new JMenuItem("�ָ�(R)");
        popM_Redo.addActionListener(this);
        popupMenu.add(popM_Redo);
        //�ָ���
        separator_5 = new JSeparator();
        popupMenu.add(separator_5);
        //����
        popM_Cut = new JMenuItem("����(T)");
        popM_Cut.addActionListener(this);
        popupMenu.add(popM_Cut);
        //����
        popM_Copy = new JMenuItem("����(C)");
        popM_Copy.addActionListener(this);
        popupMenu.add(popM_Copy);
        //ճ��
        popM_Paste = new JMenuItem("ճ��(P)");
        popM_Paste.addActionListener(this);
        popupMenu.add(popM_Paste);
        //ɾ��
        popM_Delete = new JMenuItem("ɾ��(D)");
        popM_Delete.addActionListener(this);
        popupMenu.add(popM_Delete);
        //�ָ���
        separator_6 = new JSeparator();
        popupMenu.add(separator_6);
        //ȫѡ
        popM_SelectAll = new JMenuItem("ȫѡ(A)");
        popM_SelectAll.addActionListener(this);
        popupMenu.add(popM_SelectAll);

        // ���������˵�
        final JPopupMenu jp=new JPopupMenu();    //��������ʽ�˵������������ǲ˵���
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//ֻ��Ӧ����Ҽ������¼�
                {
                    jp.show(e.getComponent(),e.getX(),e.getY());//�����λ����ʾ����ʽ�˵�
                }
            }
        });

        /*����״̬��*/
        toolState = new JToolBar();
        toolState.setSize(textArea.getSize().width, 10);
        //���ϵͳʱ��
        label1 = new JLabel("    ��ǰϵͳʱ�䣺" + hour + ":" + min + ":" + second+" ");
        toolState.add(label1);  
        toolState.addSeparator();
        //�����������
        label2 = new JLabel("    �� " + linenum + " ��");
        toolState.add(label2);
        textArea.addCaretListener(new CaretListener() {        //��¼����������
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
                    label2.setText("    �� " + linenum + " ��");
                } catch (Exception ex) { }
            }});
        toolState.addSeparator();
        //�������ͳ��
        label3 = new JLabel("    һ�� " + sum +" ��  ");
        toolState.add(label3);  
        textArea.addCaretListener(new CaretListener() {        //��¼����������
            public void caretUpdate(CaretEvent e) {
                try {
                    sum=NotepadMainFrame.this.textArea.getText().toString().length();
                    label3.setText("    һ�� " +sum+" ��  ");
                }
                catch(Exception ex) { }
            }});
        toolState.addSeparator();
        label4 = new JLabel("    ����״̬��");
        toolState.add(label4);

        contentPane.add(toolState, BorderLayout.SOUTH);  //��״̬����ӵ������
        toolState.setVisible(true);
        toolState.setFloatable(false);
        Clock clock=new Clock();
        clock.start();   //����ʱ���߳�


        //��ӳ���������
        textArea.getDocument().addUndoableEditListener(undoMgr);
        isChanged();
        this.MainFrameWidowListener();

    }

    /*������*/
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

    /*��Ϊ����*/
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemOpen){            //��
            openFile();
        }else if(e.getSource()==itemSave){        //����
            //������ļ��Ǵ򿪵ģ��Ϳ���ֱ�ӱ���
            save();
        }else if(e.getSource()==itemSaveAs){    //���Ϊ
            saveAs();
        }else if(e.getSource()==itemNew){        //�½�
            newFile();
        }else if(e.getSource()==itemExit){        //�˳�
            exit();
        }else if(e.getSource()==itemPage){        //ҳ������
            PageFormat pf = new PageFormat();
            PrinterJob.getPrinterJob().pageDialog(pf); 
        }else if(e.getSource()==itemPrint){        //��ӡ
            //��ӡ��
            Print();
        }else if(e.getSource()==itemUndo || e.getSource()==popM_Undo){        //����
            if(undoMgr.canUndo()){
                undoMgr.undo();
            }
        }else if(e.getSource()==itemRedo || e.getSource()==popM_Redo){        //�ָ�
            if(undoMgr.canRedo()){
                undoMgr.redo();
            }
        }else if(e.getSource()==itemCut || e.getSource()==popM_Cut){        //����
            cut();
        }else if(e.getSource()==itemCopy || e.getSource()==popM_Copy){        //����
            copy();
        }else if(e.getSource()==itemPaste || e.getSource()==popM_Paste){    //ճ��
            paste();
        }else if(e.getSource()==itemDelete || e.getSource()==popM_Delete){    //ɾ��
            String tem=textArea.getText().toString();
            textArea.setText(tem.substring(0,textArea.getSelectionStart())); 
        }else if(e.getSource()==itemFind){        //����
            mySearch();
        }else if(e.getSource()==itemFindNext){    //������һ��
            mySearch();
        }else if(e.getSource()==itemReplace){    //�滻
            mySearch();
        }else if(e.getSource()==itemSelectAll || e.getSource()==popM_SelectAll){    //ѡ��ȫ��
            textArea.selectAll();
        }else if(e.getSource()==itemTime){        //ʱ��/����
            textArea.replaceSelection(hour+":"+min+" "+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH));
        }else if(e.getSource()==itemTurnTo){    //ת��
            turnTo();
        }
        else if(e.getSource()==itemFont){        //���������С
            // ��������ѡ��������������ΪԤ��ֵ
            MQFontChooser fontChooser = new MQFontChooser(textArea.getFont());
            fontChooser.showFontDialog(this);
            Font font = fontChooser.getSelectFont();
            // ���������õ�JTextArea��
            textArea.setFont(font);
        }else if(e.getSource()==itemColor){        //���ñ�����ɫ
            jcc1 = new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1,"ѡ�񱳾���ɫ",-1);
            color = jcc1.getColor();
            textArea.setBackground(color);
        }/*else if(e.getSource()==itemNextLine){    //�����Զ�����
            if(itemNextLine.isSelected()){
                textArea.getScrollableTracksViewportWidth();
            }else{
                textArea.getScrollableTracksViewportWidth();
            }
        }*/
        else if(e.getSource()==itemFontColor){    //����������ɫ
            jcc1=new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1, "ѡ��������ɫ", -1);
            color = jcc1.getColor();
            textArea.setForeground(color);
        }else if(e.getSource()==itemStatement){    //����״̬
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
    /*����ͼƬ��С*/
    private void zoomImage() {
        //���õ���
        final JDialog zoomDialog = new JDialog(this, "����ͼƬ");
        zoomDialog.setSize(600, 100);
        zoomDialog.setResizable(true);
        zoomDialog.setLocation(300, 280);
        zoomDialog.setVisible(true);

        //���ó�������
        JLabel zoomLabel1 = new JLabel("���Ÿ߶�:");
        final JTextField textHight = new JTextField(5);
        JLabel zoomLabel2 = new JLabel("���ſ��:");
        final JTextField textWidth = new JTextField(5);

        //���ð�ť
        JButton okButton = new JButton("ȷ��");
        JButton cancelButton = new JButton("ȡ��");

        //��������
        Container con = zoomDialog.getContentPane();
        con.setLayout(new FlowLayout());
        con.add(zoomLabel1);
        con.add(textHight);
        con.add(zoomLabel2);
        con.add(textWidth);
        con.add(okButton);
        con.add(cancelButton);
        
        //��ȡҪ�༭��ͼƬ�Ļ�������
        textHight.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconHeight()));
        textWidth.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconWidth()));

        
        //��ť��Ϊ
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
    /*���ļ�*/
    private void openFile() {
        if(flag==2 && this.currentPath==null){
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽�ޱ���?",
             "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();
            }
        }else if(flag==2 && this.currentPath!=null){
            //2�����򿪵��ļ�2��������ļ�3���������޸�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽"+
            this.currentPath+"?", "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();
            }
        }
        //���ļ�ѡ���
        JFileChooser choose=new JFileChooser();
        //ѡ���ļ�
        int result=choose.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //ȡ��ѡ����ļ�
            File file=choose.getSelectedFile();
            //���Ѵ��ڵ��ļ�����ǰ���ļ���������
            currentFileName=file.getName();
            //�����ļ�ȫ·��
            currentPath=file.getAbsolutePath();
            flag=3;
            this.setTitle(this.currentPath);
            BufferedReader br=null;
            try {
                //�����ļ���[�ַ���]
                InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
                br=new BufferedReader(isr);//��̬��
                //��ȡ����
                StringBuffer sb=new StringBuffer();
                String line=null;
                while((line=br.readLine())!=null){
                    sb.append(line+SystemParam.LINE_SEPARATOR);
                }
                //��ʾ���ı���[���]
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
    /*�½��ļ���ֻ�иĹ��ĺͱ��������Ҫ���� */    
    private void newFile() {
        if(flag==0 || flag==1){        //���������±�Ϊ0�����½��ĵ�Ϊ1
            return;
        }else if(flag==2 && this.currentPath==null){        //�޸ĺ�
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽�ޱ���?",
             "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();        //���Ϊ                
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("�ޱ���");
                flag=1;
            }
            return;
        }else if(flag==2 && this.currentPath!=null ){
            //2����������ļ�Ϊ3���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽"+
            this.currentPath+"?", "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();        //ֱ�ӱ��棬��·��
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("�ޱ���");
                flag=1;
            }
        }else if(flag==3){        //������ļ�
            this.textArea.setText("");
            flag=1;
            this.setTitle("�ޱ���");
        }
    }
    /*�˳���ť���ʹ��ڵĺ��ʵ��һ���Ĺ���*/
    private void exit() {
        if(flag==2 && currentPath==null){
            //���ǵ���С����
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽�ޱ���?",
             "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.saveAs();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else if(flag==2 && currentPath!=null){
            //���ǵ���С����
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽"+currentPath+"?",
             "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.save();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else{
            //���ǵ���С����
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "ȷ���رգ�", "ϵͳ��ʾ",
             JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    /*��ӡ*/
    public void Print()
    {
        try{
            p = getToolkit().getPrintJob(this,"ok",null);//����һ��Printfjob ���� p
            g = p.getGraphics();//p ��ȡһ�����ڴ�ӡ�� Graphics �Ķ���
            //g.translate(120,200);//�ı��齨��λ�� 
            this.textArea.printAll(g);
            p.end();//�ͷŶ��� g  
        }
        catch(Exception a){

        } 
    }
    /*���и��ƺ�ճ��*/
    public void cut(){
        copy();
        if (this.textArea.getSelectedText() != null){
            this.textArea.replaceSelection("");
        }
    }    
    public void copy(){
        //�϶�ѡȡ�ı�
        String temp = this.textArea.getSelectedText();
        //�ѻ�ȡ�����ݸ��Ƶ������ַ����������̳��˼�����ӿ�
        StringSelection text = new StringSelection(temp);
        //�����ݷ��ڼ�����
        this.clipboard.setContents(text, null);
    }
    public void paste(){
        //Transferable�ӿڣ��Ѽ����������ת��������
        Transferable contents = this.clipboard.getContents(this);
        //DataFalvor���ж��Ƿ��ܰѼ����������ת����������������
        DataFlavor flavor = DataFlavor.stringFlavor;
        //�������ת��
        if(contents.isDataFlavorSupported(flavor)){
            String str;
            try {//��ʼת��
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
    /*����*/
    public void mySearch() {
        final JDialog findDialog = new JDialog(this, "�������滻", true);
        Container con = findDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchContentLabel = new JLabel("��������(N) :");
        JLabel replaceContentLabel = new JLabel("�滻Ϊ(P)�� :");
        final JTextField findText = new JTextField(22);
        final JTextField replaceText = new JTextField(22);
        final JCheckBox matchcase = new JCheckBox("���ִ�Сд");
        ButtonGroup bGroup = new ButtonGroup();
        final JRadioButton up = new JRadioButton("����(U)");
        final JRadioButton down = new JRadioButton("����(D)");
        down.setSelected(true);
        bGroup.add(up);
        bGroup.add(down);
        JButton searchNext = new JButton("������һ��(F)");
        JButton replace = new JButton("�滻(R)");
        final JButton replaceAll = new JButton("ȫ���滻(A)");
        searchNext.setPreferredSize(new Dimension(110, 22));
        replace.setPreferredSize(new Dimension(110, 22));
        replaceAll.setPreferredSize(new Dimension(110, 22));
        // "�滻"��ť���¼�����
        replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (replaceText.getText().length() == 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection("");
                if (replaceText.getText().length() > 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection(replaceText.getText());
            }
        });

        // "�滻ȫ��"��ť���¼�����
        replaceAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                textArea.setCaretPosition(0); // �����ŵ��༭����ͷ
                int a = 0, b = 0, replaceCount = 0;

                if (findText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(findDialog, "����д��������!", "��ʾ",
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
                            JOptionPane.showMessageDialog(findDialog, "�Ҳ��������ҵ�����!",
                             "���±�", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(findDialog, "�ɹ��滻" + replaceCount +
                             "��ƥ������!", "�滻�ɹ�", JOptionPane.INFORMATION_MESSAGE);
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
        }); /* "�滻ȫ��"��ť���¼�������� */

        // "������һ��"��ť�¼�����
        searchNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int a = 0, b = 0;
                int FindStartPos = textArea.getCaretPosition();
                String str1, str2, str3, str4, strA, strB;
                str1 = textArea.getText();
                str2 = str1.toLowerCase();
                str3 = findText.getText();
                str4 = str3.toLowerCase();
                // "���ִ�Сд"��CheckBox��ѡ��
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
                    JOptionPane.showMessageDialog(null, "�Ҳ��������ҵ�����!", "���±�",
                     JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });/* "������һ��"��ť�¼�������� */
        // "ȡ��"��ť���¼�����
        JButton cancel = new JButton("ȡ��");
        cancel.setPreferredSize(new Dimension(110, 22));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findDialog.dispose();
            }
        });

        // ����"�������滻"�Ի���Ľ���
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();

        JPanel direction = new JPanel();
        direction.setBorder(BorderFactory.createTitledBorder("���� "));
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

        // ����"�������滻"�Ի���Ĵ�С���ɸ��Ĵ�С(��)��λ�úͿɼ���
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
    /*�Ƿ��б仯*/
    private void isChanged() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //�������ҽ����˶�ʹ�ÿ�ݼ�������û�������ַ�ȴû�иı�textArea�����ݵ��ж�
                Character c=e.getKeyChar();
                if(c != null && !textArea.getText().toString().equals("")){
                    flag=2;
                }
            }
        });
    }
    /*�½��Ļ򱣴�����˳�ֻ������ѡ��*/
    private void MainFrameWidowListener() {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(flag==2 && currentPath==null){
                    //���ǵ���С����
                    //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽�ޱ���?",
                     "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.saveAs();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else if(flag==2 && currentPath!=null){
                    //���ǵ���С����
                    //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this,
                     "�Ƿ񽫸��ı��浽"+currentPath+"?", "���±�", JOptionPane.YES_NO_CANCEL_OPTION,
                      JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.save();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else{
                    //���ǵ���С����
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "ȷ���رգ�",
                     "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
            }
        });
    }
    /*����*/
    private void save() {
        if(this.currentPath==null){
            this.saveAs();
            if(this.currentPath==null){
                return;
            }
        }
        FileWriter fw=null;
        //����
        try {
            fw=new FileWriter(new  File(currentPath));
            fw.write(textArea.getText());
            //����Ƚ��٣���Ҫд
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
    /*���Ϊ*/
    private void saveAs() {
        //�򿪱����
        JFileChooser choose=new JFileChooser();
        //ѡ���ļ�
        int result=choose.showSaveDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //ȡ��ѡ����ļ�[�ļ������Լ������]
            File file=choose.getSelectedFile();
            FileWriter fw=null;
            //����
            try {
                fw=new FileWriter(file);
                fw.write(textArea.getText());
                currentFileName=file.getName();
                currentPath=file.getAbsolutePath();
                //����Ƚ��٣���Ҫд
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
    /*ת��*/
    private void turnTo() {
        final JDialog gotoDialog = new JDialog(this, "ת��������");
        JLabel gotoLabel = new JLabel("����(L):");
        final JTextField linenum = new JTextField(5);
        linenum.setText("1");
        linenum.selectAll();

        JButton okButton = new JButton("ȷ��");
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int totalLine = NotepadMainFrame.this.linenum;
                int[] lineNumber = new int[totalLine + 1];
                String s = textArea.getText();
                int pos = 0, t = 0;

                while (true) {
                    pos = s.indexOf('\12', pos);
                    // System.out.println("����pos:"+pos);
                    if (pos == -1)
                        break;
                    lineNumber[t++] = pos++;
                }

                int gt = 1;
                try {
                    gt = Integer.parseInt(linenum.getText());
                } catch (NumberFormatException efe) {
                    JOptionPane.showMessageDialog(null, "����������!", "��ʾ", JOptionPane.WARNING_MESSAGE);
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

                gotoDialog.dispose();//�رմ���
            }

        });

        JButton cancelButton = new JButton("ȡ��");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gotoDialog.dispose();
            }
        });

        //�������ӵ�������
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

    /*����ͼƬ*/
    public void insertFile(File file){
        Document doc = textArea.getStyledDocument();
        textArea.setCaretPosition(doc.getLength());
        insertIcon = new ImageIcon(file.getPath());
        textArea.insertIcon(insertIcon); 
        int end = textArea.getCaretPosition();
        int start = end - 1;
    }
    
}