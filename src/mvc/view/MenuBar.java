//package mvc.view;
//
//import java.awt.event.*;
//import javax.swing.*;
// 
//public class MenuBar extends JFrame{
//     
//    public MenuBar(){
//        pokazUI();
//    }
//     
//    private void pokazUI(){
//         
//        JMenuBar menubar = new JMenuBar();
//        ImageIcon ikona = new ImageIcon("exit.jpg");
//         
//        JMenu plik = new JMenu("Plik");
//        plik.setMnemonic(KeyEvent.VK_P);
//         
//        JMenuItem eMenuPoz = new JMenuItem("Wyście", ikona);
//        eMenuPoz.setMnemonic(KeyEvent.VK_W);
//        eMenuPoz.setToolTipText("Zakończ program");
//        eMenuPoz.addActionListener(new ActionListener() {
// 
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//         
//        plik.add(eMenuPoz);
//        menubar.add(plik);
//        setJMenuBar(menubar);
//         
//        setTitle("Proste menu");
//        setSize(300,200);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//}