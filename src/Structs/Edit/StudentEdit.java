package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Structs.Student; 


public class StudentEdit extends JComponent implements ActionListener {
    Student obj;
    JFrame frame;

    JTextField id_field;
    JTextField name_field;
    JTextField term_field;

    public StudentEdit(Student obj){
        this.obj = obj;

        this.id_field = new JTextField(Integer.toString(this.obj.id));
        this.name_field = new JTextField(this.obj.name);
        this.term_field = new JTextField(Integer.toString(this.obj.term));
    }

    public Student run(){
        frame = new JFrame("Student Editor");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContainer(content);
        
        frame.pack();
        frame.setVisible(true);
        return this.obj;
    }

    void setContainer(Container container){
        id_field.setMaximumSize(new Dimension(200, 20));
        name_field.setMaximumSize(new Dimension(200, 20));
        term_field.setMaximumSize(new Dimension(200, 20));

        container.add(new JLabel("Student ID:"));
        container.add(this.id_field);
        container.add(new JLabel("Name:"));
        container.add(this.name_field);
        container.add(new JLabel("Term:"));
        container.add(this.term_field);

        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        container.add(save_button);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("save")){
            this.obj.id = Integer.parseInt(this.id_field.getText());
            this.obj.name = this.name_field.getText();
            this.obj.term = Integer.parseInt(this.term_field.getText());
            
            if(this.obj.checkData())
                // Student.save(this.obj, this.file_name);
                setVisible(false);
            else
                JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
