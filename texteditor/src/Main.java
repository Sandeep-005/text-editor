import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener{

    JFrame f;

    JTextArea t;

    editor(){
        f=new JFrame("Notepad");

        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.Metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        t=new JTextArea();

        JMenuBar menu=new JMenuBar();

        JMenu file=new JMenu("File");
        JMenuItem m1=new JMenuItem("New");
        JMenuItem m2=new JMenuItem("Open");
        JMenuItem m3=new JMenuItem("Save");
        JMenuItem m4=new JMenuItem("Print");

        //adding actionlistener to the menuitem
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);

        JMenu edit=new JMenu("Edit");
        JMenuItem m5=new JMenuItem("Cut");
        JMenuItem m6=new JMenuItem("Copy");
        JMenuItem m7=new JMenuItem("Paste");

        //adding actionlistener to the menuitem
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        //adding menuitems to edit menu
        edit.add(m5);
        edit.add(m6);
        edit.add(m7);

        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);

        //adding menu to menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(500,500);

        f.show();
    }






    //function for adding the functionality to each of the menu item
    public void actionPerformed(ActionEvent e){

        //extracting the action performed
        String s=e.getActionCommand();

        if(s.equals("New")){
            t.setText("");
        }
        else if(s.equals("Open")) {

            JFileChooser j = new JFileChooser("D:");

            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "", s2 = "";

                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    s2 = br.readLine();

                    while ((s1 = br.readLine()) != null) {
                        s2 = s2 + '\n' + s1;
                    }

                    t.setText(s2);
                } catch (Exception et) {
                    JOptionPane.showMessageDialog(f,et.getMessage());
                }
            }
        }
        else if(s.equals("Save")){

            JFileChooser j=new JFileChooser("D:");

            int r=j.showSaveDialog(null);
            if(r==JFileChooser.APPROVE_OPTION){
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter fw=new FileWriter(fi,false);

                    BufferedWriter bw=new BufferedWriter(fw);
                    
                    bw.write(t.getText());

                    bw.flush();
                    bw.close();
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());

                }
            }

        }
        else if(s.equals("Print")) {
            try {
                t.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Close")){
            t.setVisible(false);
        }
    }

public static void main(String args[]) {
    editor e = new editor();
   }
}
