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

//主框架
public class NotepadMainFrame extends JFrame implements ActionListener{

    /*内容面板*/
    private JPanel contentPane;

    /*编辑区*/
    public JTextPane textArea;

    /*上方菜单栏*/
    private JMenuBar menuBar;
    //上方菜单栏元素
    //文件
    private JMenu itemFile;
    private JMenuItem itemNew;				   //新建
    private JMenuItem itemOpen;                //打开
    private JMenuItem itemSave;                //保存
    private JMenuItem itemSaveAs;              //另存为
    private JSeparator separator;			   //分隔线
    private JMenuItem itemPage;				   //页面设置
    private JMenuItem itemPrint;			   //打印
    private JSeparator separator_1;			   //分隔线    
    private JMenuItem itemExit;				   //退出
    //编辑
    private JMenu itemEdit;
    private JMenuItem itemUndo;				   //撤销    
    private JMenuItem itemRedo;                //恢复   
    private JSeparator separator_2;            //分隔线
    private JMenuItem itemCut;				   //剪切
    private JMenuItem itemCopy;				   //复制
    private JMenuItem itemPaste;			   //粘贴
    private JMenuItem itemDelete;			   //删除
    private JSeparator separator_3;            //分隔线
    private JMenuItem itemFind;				   //查找
    private JMenuItem itemFindNext;			   //查找下一个
    private JMenuItem itemReplace;			   //替换
    private JMenuItem itemSelectAll;		   //全选
    private JSeparator separator_4;            //分隔线
    private JMenuItem itemTime;				   //日期/时间
    private JMenuItem itemTurnTo;			   //转到
    //图片
    private JMenu itemPicture;                 
    private JMenuItem itemInsert;              //插入
    private JMenuItem itemZoom;                //缩放    
    //格式
    private JMenu itFormat;					   
    private JMenuItem itemFont;				   //字体大小
    private JMenuItem itemColor;			   //字体颜色
    private JMenuItem itemFontColor;		   //背景颜色
    //private JCheckBoxMenuItem itemNextLine;	   //自动换行
    //查看
    private JMenu itemCheck;				   
    private JCheckBoxMenuItem itemStatement;   //状态栏
    //上传
    private JMenu itemUpload;

    /*滚动栏*/
    private JScrollPane scrollPane;

    /*右键弹出菜单*/
    private JPopupMenu popupMenu;
    private JMenuItem popM_Undo;		  //撤销
    private JMenuItem popM_Redo;		  //恢复   
    private JSeparator separator_5;       //分隔线      
    private JMenuItem popM_Cut;			  //剪切
    private JMenuItem popM_Copy;		  //复制
    private JMenuItem popM_Paste;		  //粘贴
    private JMenuItem popM_Delete;		  //删除
    private JSeparator separator_6;		  //分隔线
    private JMenuItem popM_SelectAll;	  //全选

    /*状态栏*/
    private JToolBar toolState;
    public static JLabel label1;          //状态栏时间
    public static JLabel label4;
    private JLabel label2;                //状态栏行数和列数
    private JLabel label3;
    GregorianCalendar c=new GregorianCalendar();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    int min=c.get(Calendar.MINUTE);
    int second=c.get(Calendar.SECOND); 
    int linenum = 1;                      //文本的行数
    int sum=0;

    /*某些参数*/
    //1：新建 
    //2：修改过
    //3：保存过的
    int flag=0;
    //当前文件名
    String currentFileName=null;    
    PrintJob  p=null;//声明一个要打印的对象
    Graphics  g=null;//要打印的对象    
    //当前文件路径
    String currentPath=null;    
    //背景颜色
    JColorChooser jcc1=null;
    Color color=Color.BLACK;    
    
    //插入图片
    public Icon insertIcon;

    //撤销管理器
    public UndoManager undoMgr = new UndoManager();     
    //剪贴板
    public Clipboard clipboard = new Clipboard("系统剪切板"); 

    /*构造函数*/
    public NotepadMainFrame(){
        /*设置风格*/
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

        /*对JFrame进行基础设置*/
        setTitle("无标题");    
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 721, 772);

        /*设置边框*/
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        /*设置文本区域*/
        textArea = new JTextPane();

        /*设置滑块*/
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
        contentPane.add(scrollPane, BorderLayout.CENTER); //添加到面板中【中间】        

        /*新建上方菜单*/
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //第一个菜单栏 文件（F）
        itemFile = new JMenu("文件(F)");
        itemFile.setMnemonic('F');	//设置快捷键"F"
        menuBar.add(itemFile);
        //新建
        itemNew = new JMenuItem("新建(N)");
        itemNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));   //设置快捷键Ctrl+"N"
        itemNew.addActionListener(this);
        itemFile.add(itemNew);
        //打开
        itemOpen = new JMenuItem("打开(O)");
        itemOpen.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));   //设置快捷键Ctrl+"O"
        itemOpen.addActionListener(this);
        itemFile.add(itemOpen);
        //保存
        itemSave = new JMenuItem("保存(S)");
        itemSave.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));   //设置快捷键Ctrl+"S"
        itemSave.addActionListener(this);
        itemFile.add(itemSave);
        //另存为
        itemSaveAs = new JMenuItem("另存为(A)");
        itemSaveAs.addActionListener(this);
        itemFile.add(itemSaveAs);
        //添加分隔线
        separator = new JSeparator();
        itemFile.add(separator);
        //页面设置
        itemPage = new JMenuItem("页面设置(U)",'U');
        itemPage.addActionListener(this);
        itemFile.add(itemPage);
        //打印
        itemPrint = new JMenuItem("打印(P)...",'P');
        itemPrint.setAccelerator(KeyStroke.getKeyStroke('P', KeyEvent.CTRL_DOWN_MASK));   //置快捷键Ctrl+"P"
        itemPrint.addActionListener(this);
        itemFile.add(itemPrint);
        //添加分隔线
        separator_1 = new JSeparator(); 
        itemFile.add(separator_1);
        //退出
        itemExit = new JMenuItem("退出(X)",'X');
        itemExit.addActionListener(this);
        itemFile.add(itemExit);
        //第二个菜单栏 编辑（E）
        itemEdit = new JMenu("编辑(E)");
        itemEdit.setMnemonic('E');
        menuBar.add(itemEdit);
        //撤销
        itemUndo = new JMenuItem("撤销(U)",'U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"Z"
        itemUndo.addActionListener(this);
        itemEdit.add(itemUndo);
        //恢复
        itemRedo = new JMenuItem("恢复(R)");
        itemRedo.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"R"
        itemRedo.addActionListener(this);
        itemEdit.add(itemRedo);
        //剪切
        itemCut = new JMenuItem("剪切(T)",'T');
        itemCut.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"X"
        itemCut.addActionListener(this);
        //添加分割线 
        separator_2 = new JSeparator();
        itemEdit.add(separator_2);
        itemEdit.add(itemCut);
        //复制
        itemCopy = new JMenuItem("复制(C)",'C');
        itemCopy.addActionListener(this);
        itemCopy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"C"
        itemEdit.add(itemCopy);
        //粘贴
        itemPaste = new JMenuItem("粘贴(P)",'P');
        itemPaste.addActionListener(this);
        itemPaste.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"V"
        itemEdit.add(itemPaste);
        //删除
        itemDelete = new JMenuItem("删除(L)",'L');
        itemDelete.addActionListener(this);
        itemDelete.setAccelerator(KeyStroke.getKeyStroke('D', KeyEvent.CTRL_DOWN_MASK));    //设置快捷键Ctrl+"D"
        itemEdit.add(itemDelete);
        //分割线
        separator_3 = new JSeparator();
        itemEdit.add(separator_3);
        //查找
        itemFind = new JMenuItem("查找(F)",'F');
        itemFind.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));   //设置快捷键Ctrl+"F"
        itemFind.addActionListener(this);
        itemEdit.add(itemFind);
        //查找下一个
        itemFindNext = new JMenuItem("查找下一个(N)",'N');
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        itemFindNext.addActionListener(this);
        itemEdit.add(itemFindNext);
        //替换
        itemReplace = new JMenuItem("替换(R)",'R');
        itemReplace.addActionListener(this);
        itemReplace.setAccelerator(KeyStroke.getKeyStroke('H', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"H"
        itemEdit.add(itemReplace);

        //全选
        itemSelectAll = new JMenuItem("全选(A)",'A');
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"A"
        itemSelectAll.addActionListener(this);
        //分割线
        separator_4 = new JSeparator();
        itemEdit.add(separator_4);
        itemEdit.add(itemSelectAll);
        //日期/时间
        itemTime = new JMenuItem("时间/日期(D)",'D');
        itemTime.addActionListener(this);
        itemTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        itemEdit.add(itemTime);
        //转到
        itemTurnTo = new JMenuItem("转到(G)",'G');
        itemTurnTo.addActionListener(this);
        itemTurnTo.setAccelerator(KeyStroke.getKeyStroke('G', KeyEvent.CTRL_DOWN_MASK));  //设置快捷键Ctrl+"G"
        itemEdit.add(itemTurnTo);
        //第三个菜单栏 图片
        itemPicture = new JMenu("图片(P)");
        itemPicture.setMnemonic('P');
        menuBar.add(itemPicture);
        //插入
        itemInsert = new JMenuItem("插入(I)");
        itemInsert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                JFileChooser f = new JFileChooser();
                f.showOpenDialog(null);
                insertFile(f.getSelectedFile());
            }
        });
        itemPicture.add(itemInsert);
        //缩放
        itemZoom = new JMenuItem("缩放(Z)");
        itemZoom.addActionListener(this);
        itemPicture.add(itemZoom);
        //第四个菜单栏 格式
        itFormat = new JMenu("格式(O)");
        itFormat.setMnemonic('O');
        menuBar.add(itFormat);
        //字体大小
        itemFont = new JMenuItem("字体大小(F)...");
        itemFont.addActionListener(this);
        itFormat.add(itemFont);
        //字体颜色
        itemFontColor = new JMenuItem("字体颜色(I)...");
        itemFontColor.addActionListener(this);
        itFormat.add(itemFontColor);        
        //背景颜色
        itemColor = new JMenuItem("背景颜色(C)...");
        itemColor.addActionListener(this);
        itFormat.add(itemColor);
        // 自动换行
        /*
        itemNextLine = new JCheckBoxMenuItem("自动换行(W)");
        itemNextLine.addActionListener(this);
        itFormat.add(itemNextLine);*/

        //第五个菜单栏 查看
        itemCheck = new JMenu("查看(V)");
        itemCheck.setMnemonic('V');
        menuBar.add(itemCheck);
        //状态栏
        itemStatement = new JCheckBoxMenuItem("状态栏(S)");
        itemStatement.addActionListener(this);
        itemCheck.add(itemStatement);

        itemUpload = new JMenu("上传(U)");
        itemUpload.setMnemonic('U');
        menuBar.add(itemUpload);

        /*新建右键弹出菜单*/
        popupMenu = new JPopupMenu();
        addPopup(textArea, popupMenu);
        //撤销
        popM_Undo = new JMenuItem("撤销(U)");
        popM_Undo.addActionListener(this);
        popupMenu.add(popM_Undo);
        //恢复
        popM_Redo = new JMenuItem("恢复(R)");
        popM_Redo.addActionListener(this);
        popupMenu.add(popM_Redo);
        //分割线
        separator_5 = new JSeparator();
        popupMenu.add(separator_5);
        //剪切
        popM_Cut = new JMenuItem("剪切(T)");
        popM_Cut.addActionListener(this);
        popupMenu.add(popM_Cut);
        //复制
        popM_Copy = new JMenuItem("复制(C)");
        popM_Copy.addActionListener(this);
        popupMenu.add(popM_Copy);
        //粘贴
        popM_Paste = new JMenuItem("粘贴(P)");
        popM_Paste.addActionListener(this);
        popupMenu.add(popM_Paste);
        //删除
        popM_Delete = new JMenuItem("删除(D)");
        popM_Delete.addActionListener(this);
        popupMenu.add(popM_Delete);
        //分割线
        separator_6 = new JSeparator();
        popupMenu.add(separator_6);
        //全选
        popM_SelectAll = new JMenuItem("全选(A)");
        popM_SelectAll.addActionListener(this);
        popupMenu.add(popM_SelectAll);

        // 创建弹出菜单
        final JPopupMenu jp=new JPopupMenu();    //创建弹出式菜单，下面三项是菜单项
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//只响应鼠标右键单击事件
                {
                    jp.show(e.getComponent(),e.getX(),e.getY());//在鼠标位置显示弹出式菜单
                }
            }
        });

        /*设置状态栏*/
        toolState = new JToolBar();
        toolState.setSize(textArea.getSize().width, 10);
        //添加系统时间
        label1 = new JLabel("    当前系统时间：" + hour + ":" + min + ":" + second+" ");
        toolState.add(label1);  
        toolState.addSeparator();
        //添加行数列数
        label2 = new JLabel("    共 " + linenum + " 行");
        toolState.add(label2);
        textArea.addCaretListener(new CaretListener() {        //记录行数和列数
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
                    label2.setText("    共 " + linenum + " 行");
                } catch (Exception ex) { }
            }});
        toolState.addSeparator();
        //添加字数统计
        label3 = new JLabel("    一共 " + sum +" 字  ");
        toolState.add(label3);  
        textArea.addCaretListener(new CaretListener() {        //记录行数和列数
            public void caretUpdate(CaretEvent e) {
                try {
                    sum=NotepadMainFrame.this.textArea.getText().toString().length();
                    label3.setText("    一共 " +sum+" 字  ");
                }
                catch(Exception ex) { }
            }});
        toolState.addSeparator();
        label4 = new JLabel("    网络状态：");
        toolState.add(label4);

        contentPane.add(toolState, BorderLayout.SOUTH);  //将状态栏添加到面板上
        toolState.setVisible(true);
        toolState.setFloatable(false);
        Clock clock=new Clock();
        clock.start();   //开启时钟线程


        //添加撤销管理器
        textArea.getDocument().addUndoableEditListener(undoMgr);
        isChanged();
        this.MainFrameWidowListener();

    }

    /*主函数*/
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

    /*行为动作*/
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemOpen){            //打开
            openFile();
        }else if(e.getSource()==itemSave){        //保存
            //如果该文件是打开的，就可以直接保存
            save();
        }else if(e.getSource()==itemSaveAs){    //另存为
            saveAs();
        }else if(e.getSource()==itemNew){        //新建
            newFile();
        }else if(e.getSource()==itemExit){        //退出
            exit();
        }else if(e.getSource()==itemPage){        //页面设置
            PageFormat pf = new PageFormat();
            PrinterJob.getPrinterJob().pageDialog(pf); 
        }else if(e.getSource()==itemPrint){        //打印
            //打印机
            Print();
        }else if(e.getSource()==itemUndo || e.getSource()==popM_Undo){        //撤销
            if(undoMgr.canUndo()){
                undoMgr.undo();
            }
        }else if(e.getSource()==itemRedo || e.getSource()==popM_Redo){        //恢复
            if(undoMgr.canRedo()){
                undoMgr.redo();
            }
        }else if(e.getSource()==itemCut || e.getSource()==popM_Cut){        //剪切
            cut();
        }else if(e.getSource()==itemCopy || e.getSource()==popM_Copy){        //复制
            copy();
        }else if(e.getSource()==itemPaste || e.getSource()==popM_Paste){    //粘贴
            paste();
        }else if(e.getSource()==itemDelete || e.getSource()==popM_Delete){    //删除
            String tem=textArea.getText().toString();
            textArea.setText(tem.substring(0,textArea.getSelectionStart())); 
        }else if(e.getSource()==itemFind){        //查找
            mySearch();
        }else if(e.getSource()==itemFindNext){    //查找下一个
            mySearch();
        }else if(e.getSource()==itemReplace){    //替换
            mySearch();
        }else if(e.getSource()==itemSelectAll || e.getSource()==popM_SelectAll){    //选择全部
            textArea.selectAll();
        }else if(e.getSource()==itemTime){        //时间/日期
            textArea.replaceSelection(hour+":"+min+" "+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH));
        }else if(e.getSource()==itemTurnTo){    //转到
            turnTo();
        }
        else if(e.getSource()==itemFont){        //设置字体大小
            // 构造字体选择器，参数字体为预设值
            MQFontChooser fontChooser = new MQFontChooser(textArea.getFont());
            fontChooser.showFontDialog(this);
            Font font = fontChooser.getSelectFont();
            // 将字体设置到JTextArea中
            textArea.setFont(font);
        }else if(e.getSource()==itemColor){        //设置背景颜色
            jcc1 = new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1,"选择背景颜色",-1);
            color = jcc1.getColor();
            textArea.setBackground(color);
        }/*else if(e.getSource()==itemNextLine){    //设置自动换行
            if(itemNextLine.isSelected()){
                textArea.getScrollableTracksViewportWidth();
            }else{
                textArea.getScrollableTracksViewportWidth();
            }
        }*/
        else if(e.getSource()==itemFontColor){    //设置字体颜色
            jcc1=new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1, "选择字体颜色", -1);
            color = jcc1.getColor();
            textArea.setForeground(color);
        }else if(e.getSource()==itemStatement){    //设置状态
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
    /*调整图片大小*/
    private void zoomImage() {
        //设置弹窗
        final JDialog zoomDialog = new JDialog(this, "缩放图片");
        zoomDialog.setSize(600, 100);
        zoomDialog.setResizable(true);
        zoomDialog.setLocation(300, 280);
        zoomDialog.setVisible(true);

        //设置长宽像素
        JLabel zoomLabel1 = new JLabel("缩放高度:");
        final JTextField textHight = new JTextField(5);
        JLabel zoomLabel2 = new JLabel("缩放宽度:");
        final JTextField textWidth = new JTextField(5);

        //设置按钮
        JButton okButton = new JButton("确定");
        JButton cancelButton = new JButton("取消");

        //设置容器
        Container con = zoomDialog.getContentPane();
        con.setLayout(new FlowLayout());
        con.add(zoomLabel1);
        con.add(textHight);
        con.add(zoomLabel2);
        con.add(textWidth);
        con.add(okButton);
        con.add(cancelButton);
        
        //获取要编辑的图片的基本数据
        textHight.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconHeight()));
        textWidth.setText(String.valueOf(NotepadMainFrame.this.insertIcon.getIconWidth()));

        
        //按钮行为
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
    /*打开文件*/
    private void openFile() {
        if(flag==2 && this.currentPath==null){
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到无标题?",
             "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();
            }
        }else if(flag==2 && this.currentPath!=null){
            //2、（打开的文件2，保存的文件3）条件下修改
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到"+
            this.currentPath+"?", "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();
            }
        }
        //打开文件选择框
        JFileChooser choose=new JFileChooser();
        //选择文件
        int result=choose.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //取得选择的文件
            File file=choose.getSelectedFile();
            //打开已存在的文件，提前将文件名存起来
            currentFileName=file.getName();
            //存在文件全路径
            currentPath=file.getAbsolutePath();
            flag=3;
            this.setTitle(this.currentPath);
            BufferedReader br=null;
            try {
                //建立文件流[字符流]
                InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
                br=new BufferedReader(isr);//动态绑定
                //读取内容
                StringBuffer sb=new StringBuffer();
                String line=null;
                while((line=br.readLine())!=null){
                    sb.append(line+SystemParam.LINE_SEPARATOR);
                }
                //显示在文本框[多框]
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
    /*新建文件，只有改过的和保存过的需要处理 */    
    private void newFile() {
        if(flag==0 || flag==1){        //刚启动记事本为0，刚新建文档为1
            return;
        }else if(flag==2 && this.currentPath==null){        //修改后
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到无标题?",
             "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();        //另存为                
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("无标题");
                flag=1;
            }
            return;
        }else if(flag==2 && this.currentPath!=null ){
            //2、（保存的文件为3）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到"+
            this.currentPath+"?", "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();        //直接保存，有路径
            }else if(result==JOptionPane.NO_OPTION){
                this.textArea.setText("");
                this.setTitle("无标题");
                flag=1;
            }
        }else if(flag==3){        //保存的文件
            this.textArea.setText("");
            flag=1;
            this.setTitle("无标题");
        }
    }
    /*退出按钮，和窗口的红叉实现一样的功能*/
    private void exit() {
        if(flag==2 && currentPath==null){
            //这是弹出小窗口
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到无标题?",
             "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.saveAs();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else if(flag==2 && currentPath!=null){
            //这是弹出小窗口
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到"+currentPath+"?",
             "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.save();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else{
            //这是弹出小窗口
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "确定关闭？", "系统提示",
             JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    /*打印*/
    public void Print()
    {
        try{
            p = getToolkit().getPrintJob(this,"ok",null);//创建一个Printfjob 对象 p
            g = p.getGraphics();//p 获取一个用于打印的 Graphics 的对象
            //g.translate(120,200);//改变组建的位置 
            this.textArea.printAll(g);
            p.end();//释放对象 g  
        }
        catch(Exception a){

        } 
    }
    /*剪切复制和粘贴*/
    public void cut(){
        copy();
        if (this.textArea.getSelectedText() != null){
            this.textArea.replaceSelection("");
        }
    }    
    public void copy(){
        //拖动选取文本
        String temp = this.textArea.getSelectedText();
        //把获取的内容复制到连续字符器，这个类继承了剪贴板接口
        StringSelection text = new StringSelection(temp);
        //把内容放在剪贴板
        this.clipboard.setContents(text, null);
    }
    public void paste(){
        //Transferable接口，把剪贴板的内容转换成数据
        Transferable contents = this.clipboard.getContents(this);
        //DataFalvor类判断是否能把剪贴板的内容转换成所需数据类型
        DataFlavor flavor = DataFlavor.stringFlavor;
        //如果可以转换
        if(contents.isDataFlavorSupported(flavor)){
            String str;
            try {//开始转换
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
    /*搜索*/
    public void mySearch() {
        final JDialog findDialog = new JDialog(this, "查找与替换", true);
        Container con = findDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchContentLabel = new JLabel("查找内容(N) :");
        JLabel replaceContentLabel = new JLabel("替换为(P)　 :");
        final JTextField findText = new JTextField(22);
        final JTextField replaceText = new JTextField(22);
        final JCheckBox matchcase = new JCheckBox("区分大小写");
        ButtonGroup bGroup = new ButtonGroup();
        final JRadioButton up = new JRadioButton("向上(U)");
        final JRadioButton down = new JRadioButton("向下(D)");
        down.setSelected(true);
        bGroup.add(up);
        bGroup.add(down);
        JButton searchNext = new JButton("查找下一个(F)");
        JButton replace = new JButton("替换(R)");
        final JButton replaceAll = new JButton("全部替换(A)");
        searchNext.setPreferredSize(new Dimension(110, 22));
        replace.setPreferredSize(new Dimension(110, 22));
        replaceAll.setPreferredSize(new Dimension(110, 22));
        // "替换"按钮的事件处理
        replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (replaceText.getText().length() == 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection("");
                if (replaceText.getText().length() > 0 && textArea.getSelectedText() != null)
                    textArea.replaceSelection(replaceText.getText());
            }
        });

        // "替换全部"按钮的事件处理
        replaceAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                textArea.setCaretPosition(0); // 将光标放到编辑区开头
                int a = 0, b = 0, replaceCount = 0;

                if (findText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(findDialog, "请填写查找内容!", "提示",
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
                            JOptionPane.showMessageDialog(findDialog, "找不到您查找的内容!",
                             "记事本", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(findDialog, "成功替换" + replaceCount +
                             "个匹配内容!", "替换成功", JOptionPane.INFORMATION_MESSAGE);
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
        }); /* "替换全部"按钮的事件处理结束 */

        // "查找下一个"按钮事件处理
        searchNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int a = 0, b = 0;
                int FindStartPos = textArea.getCaretPosition();
                String str1, str2, str3, str4, strA, strB;
                str1 = textArea.getText();
                str2 = str1.toLowerCase();
                str3 = findText.getText();
                str4 = str3.toLowerCase();
                // "区分大小写"的CheckBox被选中
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
                    JOptionPane.showMessageDialog(null, "找不到您查找的内容!", "记事本",
                     JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });/* "查找下一个"按钮事件处理结束 */
        // "取消"按钮及事件处理
        JButton cancel = new JButton("取消");
        cancel.setPreferredSize(new Dimension(110, 22));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findDialog.dispose();
            }
        });

        // 创建"查找与替换"对话框的界面
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();

        JPanel direction = new JPanel();
        direction.setBorder(BorderFactory.createTitledBorder("方向 "));
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

        // 设置"查找与替换"对话框的大小、可更改大小(否)、位置和可见性
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
    /*是否有变化*/
    private void isChanged() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //在这里我进行了对使用快捷键，但是没有输入字符却没有改变textArea中内容的判断
                Character c=e.getKeyChar();
                if(c != null && !textArea.getText().toString().equals("")){
                    flag=2;
                }
            }
        });
    }
    /*新建的或保存过的退出只有两个选择*/
    private void MainFrameWidowListener() {
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(flag==2 && currentPath==null){
                    //这是弹出小窗口
                    //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到无标题?",
                     "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.saveAs();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else if(flag==2 && currentPath!=null){
                    //这是弹出小窗口
                    //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this,
                     "是否将更改保存到"+currentPath+"?", "记事本", JOptionPane.YES_NO_CANCEL_OPTION,
                      JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.save();
                    }else if(result==JOptionPane.NO_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }else{
                    //这是弹出小窗口
                    int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "确定关闭？",
                     "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result==JOptionPane.OK_OPTION){
                        NotepadMainFrame.this.dispose();
                        NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
            }
        });
    }
    /*保存*/
    private void save() {
        if(this.currentPath==null){
            this.saveAs();
            if(this.currentPath==null){
                return;
            }
        }
        FileWriter fw=null;
        //保存
        try {
            fw=new FileWriter(new  File(currentPath));
            fw.write(textArea.getText());
            //如果比较少，需要写
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
    /*另存为*/
    private void saveAs() {
        //打开保存框
        JFileChooser choose=new JFileChooser();
        //选择文件
        int result=choose.showSaveDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            //取得选择的文件[文件名是自己输入的]
            File file=choose.getSelectedFile();
            FileWriter fw=null;
            //保存
            try {
                fw=new FileWriter(file);
                fw.write(textArea.getText());
                currentFileName=file.getName();
                currentPath=file.getAbsolutePath();
                //如果比较少，需要写
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
    /*转到*/
    private void turnTo() {
        final JDialog gotoDialog = new JDialog(this, "转到下列行");
        JLabel gotoLabel = new JLabel("行数(L):");
        final JTextField linenum = new JTextField(5);
        linenum.setText("1");
        linenum.selectAll();

        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int totalLine = NotepadMainFrame.this.linenum;
                int[] lineNumber = new int[totalLine + 1];
                String s = textArea.getText();
                int pos = 0, t = 0;

                while (true) {
                    pos = s.indexOf('\12', pos);
                    // System.out.println("引索pos:"+pos);
                    if (pos == -1)
                        break;
                    lineNumber[t++] = pos++;
                }

                int gt = 1;
                try {
                    gt = Integer.parseInt(linenum.getText());
                } catch (NumberFormatException efe) {
                    JOptionPane.showMessageDialog(null, "请输入行数!", "提示", JOptionPane.WARNING_MESSAGE);
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

                gotoDialog.dispose();//关闭窗体
            }

        });

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gotoDialog.dispose();
            }
        });

        //将组件添加到容器里
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

    /*插入图片*/
    public void insertFile(File file){
        Document doc = textArea.getStyledDocument();
        textArea.setCaretPosition(doc.getLength());
        insertIcon = new ImageIcon(file.getPath());
        textArea.insertIcon(insertIcon); 
        int end = textArea.getCaretPosition();
        int start = end - 1;
    }
    
}