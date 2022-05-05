import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class swing_main
{
    public static void main() throws IOException
    {
        swing_master.loading();
        JFrame f = new JFrame("Introduction");
        f.setSize(500,500);
        JPanel p = new JPanel(new GridLayout(0,1));
        f.setLayout(new GridLayout(0,1));
        JButton b = new JButton("Login");
        b.setFont(new Font("Arial",20,25));
        JButton b1 = new JButton("Change Pin number");
        b1.setFont(new Font("Arial",20,25));
        ActionListener p1 = new ActionListener() //Login button
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try //exception handler
                    {
                        swing_layout.login();
                    }
                    catch (IOException IOException)
                    {
                        IOException.printStackTrace();
                    }
                }
            };
        b.addActionListener(p1);
        ActionListener p2 = new ActionListener() //Login button
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        swing_layout.change_pin();
                    }
                    catch (IOException IOException)
                    {
                        IOException.printStackTrace();
                    }
                }
            };
        b1.addActionListener(p2);
        p.add(b);
        p.add(b1);
        f.add(p);
        f.setVisible(true);
    }
}