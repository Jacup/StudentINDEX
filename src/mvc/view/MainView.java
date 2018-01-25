package mvc.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mvc.controller.GradeController;
import mvc.model.Grade;


import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Object[] COLUMN_NAMES = {"ID", "Ocena", "Waga", "Opis"};

    private GradeController controller;
    
    private JLabel lGrade, lWeight, lDescription;
    private JTextField tfGrade, tfWeight, tfDescription;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel tablePanel, menuPanel;
    private JButton addGrade, editGrade, deleteGrade, editGradeSave, utworz_tabele;
    int location_x = 800;
    int location_y = 300;

    public MainView() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Student INDEX");
        this.setLocationRelativeTo(null);
        this.setResizable(true);


        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder("Oceny"));

        tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(tableModel);
        table.setRowHeight(table.getRowHeight() + 20);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(770, 360));
        tablePanel.add(scrollPane);
        this.add(tablePanel);

        
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createTitledBorder("Narzędzia"));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(220,150));
        this.add(menuPanel, BorderLayout.SOUTH);

        initializeMenubar();
        
        setVisible(true);
    }
    
    private void initializeMenubar() {
    	JMenuBar menubar = new JMenuBar();
    	
    	JMenu plik = new JMenu("Plik");
        plik.setMnemonic(KeyEvent.VK_P);
        JMenu chooseDB = new JMenu("Baza danych");
        chooseDB.setMnemonic(KeyEvent.VK_B);
        
        ButtonGroup difGroup = new ButtonGroup();
        
        JMenuItem addGrade = new JMenuItem("Dodaj ocenę");
        addGrade.setMnemonic(KeyEvent.VK_D);
        
        JMenuItem editGrade = new JMenuItem("Edytuj ocenę");
        editGrade.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem deleteGrade = new JMenuItem("Usuń ocenę");
        deleteGrade.setMnemonic(KeyEvent.VK_U);
        
        JMenuItem exit = new JMenuItem("Wyjście");
        exit.setMnemonic(KeyEvent.VK_W);
        exit.setToolTipText("Zakończ program");
        
        JRadioButtonMenuItem mysqlButton = new JRadioButtonMenuItem("MySQL");
        mysqlButton.setSelected(true);
        chooseDB.add(mysqlButton);
        
        JRadioButtonMenuItem sqliteButton = new JRadioButtonMenuItem("SQLite");
        sqliteButton.setSelected(false);
        chooseDB.add(sqliteButton);
        


        addGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	newGrade(true);
            }
        });
        
        editGrade.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		edytuj(true);
        	}
        });
        
        deleteGrade.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		  int selectedRow = table.getSelectedRow();
                  if (selectedRow >=0 ) {
                      Integer gradeId = (Integer) tableModel.getValueAt(selectedRow, 0);
                      controller.deleteGrade(gradeId);
                  }
        	}
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mysqlButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                controller.chooseDatabase(0);
            }
        });
        
        sqliteButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	controller.chooseDatabase(1);
            }
        });
        

        
        plik.add(addGrade);
        plik.add(editGrade);
        plik.add(deleteGrade);
        plik.addSeparator();
        plik.add(exit);
        difGroup.add(sqliteButton);
        difGroup.add(mysqlButton);

        
        menubar.add(plik);
        menubar.add(chooseDB);
        setJMenuBar(menubar);
    	
    }


    public void selectedGrade() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tfGrade.setText((String) tableModel.getValueAt(selectedRow, 1));
                    tfWeight.setText((String) tableModel.getValueAt(selectedRow, 2));
                    tfDescription.setText((String) tableModel.getValueAt(selectedRow, 3));
                }
                editGradeSave.setEnabled(true);
            }
        });
    }


    public void newGrade(boolean active) {
    	 JFrame okno=new JFrame("Dodaj ocenę");
         okno.setSize(300,250);
         okno.setLocationRelativeTo(null);
         okno.setVisible(active);
         okno.setResizable(false);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createTitledBorder("Wprowadź wartości"));
//        panel1.setLayout(new GridLayout(1,0));
        
        tfGrade = new JTextField(4);
        tfGrade.setFont(new Font("Arial", Font.BOLD,14));
        tfGrade.setPreferredSize(new Dimension(20,20));
        lGrade = new JLabel("Ocena", SwingConstants.RIGHT);

        tfWeight = new JTextField(4);
        tfWeight.setFont(new Font("Arial", Font.BOLD,14));
        tfWeight.setPreferredSize(new Dimension(20,20));
        lWeight = new JLabel("Waga", SwingConstants.RIGHT);

        tfDescription = new JTextField(20);
        tfDescription.setFont(new Font("Arial", Font.BOLD,14));
        tfDescription.setPreferredSize(new Dimension(60,20));
        lDescription = new JLabel("Opis", SwingConstants.RIGHT);

        panel1.add(lGrade);
        panel1.add(tfGrade);
        panel1.add(lWeight);
        panel1.add(tfWeight);
        panel1.add(lDescription);
        panel1.add(tfDescription);
        okno.add(panel1, BorderLayout.CENTER);

        
        JButton addGradeSave = new JButton("Zapisz");
        addGradeSave.setFont(new Font("Arial", Font.BOLD,14));
        JPanel panel2 = new JPanel();
        panel2.add(addGradeSave);
        okno.add(panel2, BorderLayout.SOUTH);

        addGradeSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Grade grade = new Grade(
                	tfGrade.getText(),
                	tfWeight.getText(),
                	tfDescription.getText());
                	controller.insertGrade(grade);


                okno.dispose();
            }
        });

    }

    public void edytuj(boolean active) {
    	 JFrame okno=new JFrame("Edytuj ocenę");
         okno.setSize(300,250);
         okno.setLocationRelativeTo(null);
         okno.setVisible(active);
         okno.setResizable(false);


         JPanel panel1 = new JPanel();
         panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
         panel1.setBorder(BorderFactory.createTitledBorder("Edytuj ocenę"));
        //panel1.setLayout(new GridLayout(3,3));
         
         tfGrade = new JTextField(4);
         tfGrade.setFont(new Font("Arial", Font.BOLD,14));
         tfGrade.setPreferredSize(new Dimension(20,20));
         lGrade = new JLabel("Ocena", SwingConstants.RIGHT);
         
         tfWeight = new JTextField(4);
         tfWeight.setFont(new Font("Arial", Font.BOLD,14));
         tfWeight.setPreferredSize(new Dimension(20,20));
         lWeight = new JLabel("Waga", SwingConstants.RIGHT);


         tfDescription = new JTextField(20);
         tfDescription.setFont(new Font("Arial", Font.BOLD,14));
         tfDescription.setPreferredSize(new Dimension(60,20));
         lDescription = new JLabel("Opis", SwingConstants.RIGHT);

         panel1.add(lGrade);
	     panel1.add(tfGrade);
	     panel1.add(lWeight);
	     panel1.add(tfWeight);
	     panel1.add(lDescription);
	     panel1.add(tfDescription);
	     okno.add(panel1, BorderLayout.CENTER);

        editGradeSave = new JButton("Zapisz");
        editGradeSave.setFont(new Font("Arial", Font.BOLD,14));
        editGradeSave.setEnabled(true);
        JPanel panel2 = new JPanel();
        panel2.add(editGradeSave);
        okno.add(panel2, BorderLayout.SOUTH);

        editGradeSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer gradeId = (Integer) tableModel.getValueAt(selectedRow, 0);
					Grade grade = new Grade(
							gradeId,
		    				tfGrade.getText(),
		    				tfWeight.getText(),
		    				tfDescription.getText());
					controller.updateGrade(grade);
                    System.out.println(gradeId);
                }

                okno.dispose();
            }
        });

    }

    
    public void setController(GradeController controller) {
        this.controller = controller;
    }
    
    
    public void refreshGrades(List<Grade> grades) {

        if (grades.size() > 0) {
            tableModel.getDataVector().clear();
            for (Grade grade : grades) {
                tableModel.addRow(new Object[] {
                        grade.getId(),
                        grade.getGrade(),
                        grade.getWeight(),
                        grade.getDesc(),
                });
            }
        } else {
            tableModel.setRowCount(0);
        }
    }
}