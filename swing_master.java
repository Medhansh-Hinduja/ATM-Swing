import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class swing_master
{
    static int account_num[], pin_num[], bal_amt[];
    static String n[];
    static char account_type[];
    public static int validate(JTextField acc_num, JTextField pin)
    {
        int x=-1;
        String s="";
        for(int a=0; a<account_num.length; a++)  // to validate the acc_num and pin
        {
            if((String.valueOf(account_num[a]).equals(acc_num.getText())) && (String.valueOf(pin_num[a]).equals(pin.getText())))
            {
                x=a;
                break;
            }
        }
        return x;
    }

    public static void loading() throws IOException //loads and stores data from the file into the member arrays
    {
        FileReader fr = new FileReader("Master.txt");
        BufferedReader bw = new BufferedReader(fr);
        int x=0,cnt=0, chk=0, a=0, c=0, p=0, cnt1=0;;
        String s="";
        String A[];
        while((s=bw.readLine())!=null)  
        {
            ++cnt;
        }
        account_num = new int[cnt];
        pin_num = new int[cnt];
        n = new String[cnt];
        account_type = new char[cnt];
        bal_amt = new int[cnt];
        String s1="";
        FileReader fr1 = new FileReader("Master.txt");
        BufferedReader bw1 = new BufferedReader(fr1);
        while((s1=bw1.readLine())!=null && cnt1!=cnt)
        {
            cnt1++;
            chk=0;
            s1.trim();
            s1+=',';
            for(int b=0; b<s1.length(); b++)
            {
                if(s1.charAt(b) == ',')
                {
                    chk++;
                }
            }
            A = new String[chk];
            String s2; p=0;
            for(int b=0; b<s1.length(); b++,p++)
            {
                c = s1.indexOf(',',b);
                s2 = s1.substring(b,c);
                A[p] = s2;
                b=c;
            }
            account_num[a] = Integer.parseInt(A[0]);
            pin_num[a] = Integer.parseInt(A[1]);
            n[a] = A[2];
            account_type[a] = A[3].charAt(0);
            bal_amt[a] = Integer.parseInt(A[4]);
            a++;
        }
        bw1.close();
        fr1.close();
        bw.close();
        fr.close();
    }

    public static void display_balance(int index) throws IOException
    {
        System.out.println("Balance amount: " + bal_amt[index]);
    }

    public static void update_balance(int index, char t_type, int amt) // to change the balance amount in the member array after a transaction
    {
        if(t_type == 'D')
            bal_amt[index] += amt;
        else if(t_type == 'W')
            bal_amt[index] -= amt;
    }

    public static boolean check_balance(int index, int amt)
    {
        /**  The maximum limit that can be withdrawn is Rs.25000 in the
        case of Savings account and Rs.50000 in the case of Current
        Account.
        Ensure a minimum amount of Rs. 5000 is maintained in the
        case of Savings account and Rs.10000 is maintained in the
        case of Current Account. */
        if(account_type[index] == 'S' && amt < 25000 && (bal_amt[index] - amt > 5000))
            return true;
        else if(account_type[index] == 'C' && amt < 50000 && (bal_amt[index] - amt > 10000))
            return true;
        return false;
    }

    public static int get_accnum(int index)
    {
        return account_num[index];
    }

    public static int get_index(int acc_num)
    {
        for(int a=0; a<account_num.length; a++)
        {
            if(account_num[a] == acc_num)
            {
                return a;
            }
        }
        return -1;
    }

    public static void update_file() throws IOException
    {
        FileWriter fw = new FileWriter("/Users/medhanshhinduja/ATM-Swing/Master.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw); 
        for(int a=0; a<account_num.length; a++)
        {
            pw.println(account_num[a] + "," + pin_num[a] + "," + n[a] + "," + account_type[a] + "," + bal_amt[a]);
        }
        pw.close();
        bw.close();
        fw.close();
    }

    /**public static void last3_transactions(int index) throws IOException
    {
    int cnt=0;
    FileReader fr = new FileReader("/Users/medhansh/Documents/Projects/Transactions.txt");
    BufferedReader bw = new BufferedReader(fr);
    String s="",s2="";
    int c=0,b=1;
    while((s=bw.readLine()) != null)
    {
    c = s.indexOf(',',b);
    s2 = s.substring(0,3);
    if(String.valueOf(account_num[index]).equals(s2))
    {
    System.out.println(s);
    cnt++;
    }
    if(cnt==3)
    break;
    }
    bw.close();
    fr.close();
    } */

    public static boolean change_pin(JTextField acc_num, JTextField pin1, JTextField pin2)
    {
        int index = get_index(Integer.parseInt(acc_num.getText()));
        if(index != -1 && pin1.getText().equals(pin2.getText()))
        {
            pin_num[index] = Integer.parseInt(pin1.getText());
            return true;
        } 
        else
        {
            System.out.println("Incorrect account number. Try again");
            return false;
        }
    }
}
