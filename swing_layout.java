import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class swing_layout
{
    static int index;
    public static void login() throws IOException
    {
        JFrame f = new JFrame("Login");
        f.setSize(500,500);
        JPanel p = new JPanel(new GridLayout(0,1));
        JLabel L1 = new JLabel("Account Number");
        JLabel L2 = new JLabel("Pin");
        JLabel L3 = new JLabel("Quick Cash");
        L1.setFont(new Font("Verdana",10,25));
        L2.setFont(new Font("Verdana",10,25));
        L3.setFont(new Font("Verdana",15,30));
        f.setLayout(new GridLayout(0,1));
        JTextField acc_num = new JTextField(10);
        JTextField pin = new JTextField(10);
        JButton b = new JButton("Submit");
        ActionListener p1 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //System.out.println("Valid Data. Thank you Priya");
                    //System.out.println(acc_num.getText());
                    //b.add(p2);
                    index = swing_master.validate(acc_num, pin);
                    if(index != -1)
                        menu(b);
                    else
                    {
                        System.out.println("Incorrect login details. Please try again");
                        System.exit(0);
                    }
                }
            };
        b.addActionListener(p1); 
        p.add(L3); 
        p.add(L1);
        p.add(acc_num);
        p.add(L2);
        p.add(pin);
        p.add(b);
        f.add(p);
        f.setVisible(true);
    }

    public static void menu(JButton b)
    {
        JFrame f1 = new JFrame("MENU");
        f1.setSize(500,500);
        JPanel p2 = new JPanel(new GridLayout(0,1));
        b.add(p2);
        JButton b2 = new JButton("Check Account Balance");
        b2.setFont(new Font("Arial",10,25));
        ActionListener p = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        balance();
                    }
                    catch (IOException IOException)
                    {
                        IOException.printStackTrace();
                    }
                }
            };
        b2.addActionListener(p); 
        JButton b3 = new JButton("Withdraw Cash");
        b3.setFont(new Font("Arial",10,25));
        ActionListener p1 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    withdraw();
                }
            };
        b3.addActionListener(p1); 
        JButton b4 = new JButton("Deposit Cash");
        b4.setFont(new Font("Arial",10,25));
        ActionListener p3 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    deposit();
                }
            };
        b4.addActionListener(p3); 
        JButton b5 = new JButton("Exit");
        b5.setFont(new Font("Arial",10,25));
        ActionListener p4 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        swing_master.update_file();
                    }
                    catch (IOException IOException)
                    {
                        IOException.printStackTrace();
                    }
                    System.out.println("Thank you for choosing Speedy Cash. Have a nice day.");
                    System.exit(0);
                }
            };
        b5.addActionListener(p4); 
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        f1.add(p2);
        f1.setVisible(true);
    }

    public static void balance() throws IOException
    {
        swing_master.display_balance(index);
    }

    public static void withdraw()
    {
        JFrame f = new JFrame("Withdraw Cash");
        f.setSize(500,500);
        JPanel p = new JPanel(new GridLayout(0,1));
        JLabel L = new JLabel("Enter withdrawal amount");
        L.setFont(new Font("Times New Roman",12,25));
        JTextField w = new JTextField(10);
        JLabel L1 = new JLabel("Enter date of transaction");
        L1.setFont(new Font("Times New Roman",12,25));
        JTextField dt = new JTextField(10);
        JButton b = new JButton("Submit");
        b.setFont(new Font("Arial",10,25));
        ActionListener p1 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String w1 = w.getText();
                    String dt1 = dt.getText();
                    boolean chk = swing_master.check_balance(index, Integer.parseInt(w1));
                    if(!chk)
                    {
                        System.out.println("Withdrawal unsuccessful. Try Again.");
                        System.exit(0);
                    }
                    swing_master.update_balance(index,'W',Integer.parseInt(w1));
                    System.out.println("Withdrawal Successful.");
                }
            };
        b.addActionListener(p1); 
        p.add(L);
        p.add(w);
        p.add(L1);
        p.add(dt);
        p.add(b);
        f.add(p);
        f.setVisible(true);
    }

    public static void deposit()
    {
        JFrame f = new JFrame("Deposit Cash");
        f.setSize(500,500);
        JPanel p = new JPanel(new GridLayout(0,1));
        JLabel L = new JLabel("Enter deposit amount");
        L.setFont(new Font("Times New Roman",12,25));
        JTextField d = new JTextField(10);
        JLabel L1 = new JLabel("Enter date of transaction");
        L1.setFont(new Font("Times New Roman",12,25));
        JTextField dt = new JTextField(10);
        JButton b = new JButton("Submit");
        b.setFont(new Font("Arial",10,25));
        ActionListener p1 = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String d1 = d.getText();
                    String dt1 = dt.getText();
                    swing_master.update_balance(index,'D',Integer.parseInt(d1));
                    System.out.println("Deposit Successful.");
                }
            };
        b.addActionListener(p1);
        p.add(L);
        p.add(d);
        p.add(L1);
        p.add(dt);
        p.add(b);
        f.add(p);
        f.setVisible(true);
    }

    public static void change_pin() throws IOException
    {
        JFrame f = new JFrame("PIN change");
        f.setSize(500,500);
        JPanel p = new JPanel(new GridLayout(0,1));
        JLabel L = new JLabel("Enter account number");
        L.setFont(new Font("Times New Roman",12,25));
        JTextField a1 = new JTextField(10);
        JLabel L1 = new JLabel("Enter new PIN");
        L1.setFont(new Font("Times New Roman",12,25));
        JTextField pin1 = new JTextField(10);
        JLabel L2 = new JLabel("Re-enter new PIN");
        L2.setFont(new Font("Times New Roman",12,25));
        JTextField pin2 = new JTextField(10);
        JButton b = new JButton("Submit");
        ActionListener p1 = new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        boolean chk = swing_master.change_pin(a1, pin1, pin2); 
                        swing_master.update_file();
                        if(chk)
                            System.out.println("PIN changed successfully");
                    }
                    catch (IOException IOException)
                    {
                        IOException.printStackTrace();
                    }
                }
            };
        b.addActionListener(p1);
        p.add(L);
        p.add(a1);
        p.add(L1);
        p.add(pin1);
        p.add(L2);
        p.add(pin2);
        p.add(b);
        f.add(p);
        f.setVisible(true);
    }
}