package com.geekymax.client.gui;

import com.geekymax.client.service.OutputService;
import com.geekymax.util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

/**
 * Provides the menu bar of the application.
 *
 * @author Max Huang
 */
public final class MenuBar extends Observable {
    private final JMenuBar menuBar = new JMenuBar();

    /**
     * Creates the menu bar and the different menus (file / edit / help).
     */
    public MenuBar() {
        menuBar.add(createFileMenu());
        menuBar.add(createHelpMenu());
    }

    /**
     * Returns the JMenuBar object.
     *
     * @return the JMenuBar object.
     */
    public JMenuBar get() {
        return menuBar;
    }

    /**
     * Creates the file menu.
     * <p>
     * The file menu contains an "Exit" item, to quit the application.
     * The file menu contains an "open" item, to open the local md.
     * The file menu contains an "output" item, to output this document in html or md.
     * </p>
     *
     * @return the newly created file menu.
     */
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        // todo 打开本地文件功能
        JMenuItem open = new JMenuItem("open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // 文件输出功能
        JMenuItem outputMarkdown = new JMenuItem("output as markdown");
        JMenuItem outputHtml = new JMenuItem("output as HTML");
        outputMarkdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("C:/file.md"));
                fileChooser.showSaveDialog(null);
                File file = fileChooser.getSelectedFile();
                OutputService.getInstance().outputAsMarkdown(file);
            }
        });
        outputHtml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("C:/file.html"));
                fileChooser.showSaveDialog(null);
                File file = fileChooser.getSelectedFile();
                OutputService.getInstance().outputAsHtml(file);
            }
        });
        // 退出应用程序
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('x');
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenu output = new JMenu("output");
        output.add(outputMarkdown);
        output.add(outputHtml);
        fileMenu.add(open);
        fileMenu.add(output);
        fileMenu.add(exit);
        return fileMenu;
    }

    /**
     * Creates the help menu.
     * <p>
     * The help menu contains an "About" item, to display some software information.
     * </p>
     *
     * @return the newly created help menu.
     */
    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('h');

        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('a');
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuBar.getParent(),
                        String.format("Extremely simple Markdown to HTML converter%nPowered by MarkdownJ%nhttps://github.com/nilhcem"),
                        "Markdown2HTML: About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        helpMenu.add(about);
        return helpMenu;
    }
}
