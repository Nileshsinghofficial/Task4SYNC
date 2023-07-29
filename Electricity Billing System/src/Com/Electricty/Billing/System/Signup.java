package Com.Electricty.Billing.System;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    JButton create, back;
    Choice accountType;
    JTextField meter,username, name, password;
    Signup(){
        setSize(700,400);
        setLocation(450,150);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
        panel.setLayout(null);
        panel.setBackground(new Color(255,255,255));
        panel.setForeground(new Color(34,139,34));
        add(panel);

        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(new Color(0x123456));
        heading.setFont(new Font("Seguin Print",Font.BOLD,14));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(new Color(0x123456));
        lblmeter.setFont(new Font("Seguin Print",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);

        JLabel lblusername = new JLabel("User Name");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(new Color(0x123456));
        lblusername.setFont(new Font("Seguin Print",Font.BOLD,14));
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(new Color(0x123456));
        lblname.setFont(new Font("Seguin Print",Font.BOLD,14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where  meter_no ='"+meter.getText()+"'");
                    while (rs.next()){
                        name.setText(rs.getString("name"));

                    }
                } catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(new Color(0x123456));
        lblpassword.setFont(new Font("Seguin Print",Font.BOLD,14));
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(0x123456));
        create.setForeground(Color.white);
        create.setBounds(140,260,120,25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(new Color(0x123456));
        back.setForeground(Color.white);
        back.setBounds(300,260,120,25);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon("C:\\Users\\Nilesh Singh\\IdeaProjects\\Electricity Billing System\\src\\Com\\Electricty\\Billing\\System\\Icon\\signupp.png");
        Image i2 = i1.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415,30,250,250);
        panel.add(image);



        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
         String atype = accountType.getSelectedItem();
         String susername = username.getText();
         String sname = name.getText();
         String spassword = password.getText();
         String smeter = meter.getText();

         try{
             Conn C = new Conn();
             String query = null;
             if (accountType.equals("Admin")){
                 query = "Insert into login values('"+smeter+"','"+susername+"','"+sname+"', '"+spassword+"', '"+atype+"' )";
             } else {
                 query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
             }

             C.s.executeUpdate(query);

             JOptionPane.showMessageDialog(null,"Account Create Successfuly");
             setVisible(false);
             new Login();

         }catch (Exception ea){
             ea.printStackTrace();
         }


        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();

        }


    }

    public static void main(String[] args) {
        new Signup();

    }

}
