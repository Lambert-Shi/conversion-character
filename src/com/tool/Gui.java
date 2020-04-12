package com.tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * @author Lambert.Shi
 * @description 界面
 * @date 2020/4/10
 */
public class Gui extends JFrame implements ActionListener {
    /** 文件选择器 **/
    private JFileChooser jFileChooser;
    /** 按钮点击事件 **/
    private JButton startButton, endButton, conversionButton;
    /** 按钮显示 **/
    private JButton authorText, startEncodingTextShow, endEncodingTextShow;
    /** 文本显示 **/
    private JTextField startText, endText, startEncodingText, endEncodingText;
    /** 需要转变编码的文件路径 **/
    private List<String> startPaths = new ArrayList<String>();
    /** 保存位置路径集 **/
    private String endPath;

    /**
     * 界面预初始化
     */
    private void init(){
        this.setTitle("编码转换工具");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Gui() {
        init();
        this.setLayout(new GridLayout(5,2,5,15));

        startButton = new JButton("选择文件");
        this.add(startButton);

        startText = new JTextField();
        startText.setEnabled(false);
        this.add(startText);

        startEncodingTextShow = new JButton("选择字符");
        startEncodingTextShow.setEnabled(false);
        this.add(startEncodingTextShow);

        startEncodingText = new JTextField();
        this.add(startEncodingText);

        endEncodingTextShow = new JButton("转换字符");
        endEncodingTextShow.setEnabled(false);
        this.add(endEncodingTextShow);

        endEncodingText = new JTextField();
        this.add(endEncodingText);

        endButton = new JButton("保存目录");
        this.add(endButton);

        endText = new JTextField();
        endText.setEnabled(false);
        this.add(endText);

        authorText = new JButton("by@Lambert.Shi");
        authorText.setEnabled(false);
        this.add(authorText);

        conversionButton = new JButton("开始");
        this.add(conversionButton);

        jFileChooser = new JFileChooser(new File("."));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.setMultiSelectionEnabled(true);

        startButton.addActionListener(this);
        endButton.addActionListener(this);
        conversionButton.addActionListener(this);

    }

    /**
     * 文件选择器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        if (jButton == startButton) {
            int status = jFileChooser.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION){
                File[] files = jFileChooser.getSelectedFiles();
                startPaths.clear();
                for (File file : files) {
                    startPaths.add(file.getPath());
                }
                startText.setText("选中个数：" + startPaths.size());
            }
        } else if (jButton == endButton){
            int status = jFileChooser.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                endPath = file.getPath();
                endText.setText("保存路径:" + endPath);
            }
        } else if (jButton == conversionButton){
            for (String startPath : startPaths) {
                String startEncoding = startEncodingText.getText().trim();
                String endEncoding = endEncodingText.getText().trim();
                Integer result = Services.execute(startPaths, endPath, startEncoding, endEncoding);
                if (result == 0){
                    JOptionPane.showMessageDialog(jButton , "请输入正确的编码", "提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}