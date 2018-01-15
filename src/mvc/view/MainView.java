package mvc.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mvc.controller.GradeController;
import mvc.model.Grade;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;


	private static final Object[] COLUMN_NAMES = {"ID", "Grade", "Weight", "Description"};

	private GradeController controller;

	private JLabel lGrade, lWeight, lDesc;
	private JTextField tfGrade, tfWeight, tfDesc;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public MainView() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1000, 600);

	    tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
	    table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 26));
        table.setRowHeight(table.getRowHeight() + 15);
	    table.setAutoCreateRowSorter(true);

	    JButton addGrade = new JButton("Add grade");
	    addGrade.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){
	    		Grade grade = new Grade(
	    				tfGrade.getText(),
	    				tfWeight.getText(),
	    				tfDesc.getText());
	    		controller.insertGrade(grade);
	    	}
	    });
	    
	    JButton editGrade = new JButton("Edit grade");
	    editGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer gradeId = (Integer) tableModel.getValueAt(selectedRow, 0);
					Grade grade = new Grade(
							gradeId,
		    				tfGrade.getText(),
		    				tfWeight.getText(),
		    				tfDesc.getText());
					controller.updateGrade(grade);
				}
			}
	    });

	    JButton deleteGrade = new JButton("Delete grade");
	    deleteGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Integer gradeId = (Integer) tableModel.getValueAt(selectedRow, 0);
					controller.deleteGrade(gradeId);
				}
			}
	    });
	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
    				tfGrade.setText((String) tableModel.getValueAt(selectedRow, 1));
    				tfWeight.setText((String) tableModel.getValueAt(selectedRow, 2));
    				tfDesc.setText((String) tableModel.getValueAt(selectedRow, 3));
				}
			}
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(table);

	    lGrade = new JLabel("Grade");
	    lWeight = new JLabel("Weight");
	    lDesc = new JLabel("Desc");
	    
	    tfGrade = new JTextField(4);
	    tfWeight = new JTextField(3);
	    tfDesc = new JTextField(15);
//	    tfRokUrodzenia = new JTextField(4);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.add(lGrade);
	    inputPanel.add(tfGrade);
	    inputPanel.add(lWeight);
	    inputPanel.add(tfWeight);
	    inputPanel.add(lDesc);
	    inputPanel.add(tfDesc);
//	    inputPanel.add(lRokUrodzenia);
//	    inputPanel.add(tfRokUrodzenia);
	    inputPanel.add(addGrade);
	    inputPanel.add(deleteGrade);
	    inputPanel.add(editGrade);
	    
	    this.add(scrollPane, BorderLayout.CENTER);
	    this.add(inputPanel, BorderLayout.SOUTH);
	}
	
	public void setController(GradeController controller) {
		this.controller = controller;
	}
	
	public void refreshGrades(List<Grade> grades) {
		
		if (grades.size() > 0){
			tableModel.getDataVector().clear();
			for (Grade grade : grades) {
				tableModel.addRow(new Object[] {grade.getId(), grade.getGrade(), grade.getWeight(),
						grade.getDesc(), });
			}
		} else {
			tableModel.setRowCount(0);
		}
	}
}

