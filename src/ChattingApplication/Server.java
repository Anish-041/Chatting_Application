package ChattingApplication;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.net.*;

public class Server implements ActionListener {
    JTextField text1;
    JPanel a1;
    static Box vertical= Box.createVerticalBox();

    static JFrame f= new JFrame();
    static DataOutputStream dout;
    Server() {
        f.setLayout(null);
        // for header
        JPanel p1= new JPanel();
        p1.setBackground(Color.blue);
        p1.setBounds(0,0,450,63);
        f.add(p1);
        p1.setLayout(null);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2= i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT); // to set the location of arrow
        ImageIcon i3= new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back); // to add image on top;

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);   // to exit the project
            }
        });

        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5= i4.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH); // to set the location of arrow
        ImageIcon i6= new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8= i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT); // to set the location of arrow
        ImageIcon i9= new ImageIcon(i8);
        JLabel video= new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11= i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT); // to set the location of arrow
        ImageIcon i12= new ImageIcon(i11);
        JLabel phone= new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);

        ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14= i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT); // to set the location of arrow
        ImageIcon i15= new ImageIcon(i14);
        JLabel icon= new JLabel(i15);
        icon.setBounds(420,20,10,25);
        p1.add(icon);

        JLabel name= new JLabel("Bruce");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF", Font.BOLD,18));
        p1.add(name);

        JLabel status= new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF", Font.BOLD,14));
        p1.add(status);

        // It is the Chat Area
        a1= new JPanel();
        a1.setBounds(5,65,440,540);
        f.add(a1);
        a1.setLayout(null);

        text1= new JTextField();
        text1.setBounds(5,610,310,40);
        text1.setFont(new Font("SANS SERIF",Font.ITALIC,16));
        f.add(text1);

        JButton send= new JButton("SEND");
        send.setBounds(320,610,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SANS SERIF",Font.ITALIC,16));
        f.add(send);

        // Placement of the frame on the screen!
        f.setSize(450,650);
        f.setLocation(130,37);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Server();

        try {
            ServerSocket skt= new ServerSocket(6001);
            // Below code is written for Infinitely receiving and sending messages.
            while (true) {
                Socket s= skt.accept();
                DataInputStream din= new DataInputStream(s.getInputStream());
                dout= new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg= din.readUTF();
                    JPanel panel= FormatLabel(msg);

                    JPanel left= new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text1.getText();

            JPanel p2 = FormatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END); // It does not take string as an argument.
            vertical.add(right);
            vertical.add(Box.createHorizontalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            // To send the message to the client
            dout.writeUTF(out);

            // To empty the text bar once the message is sent!

            text1.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
            // Above actions are performed to get the messages on the right side of the a1 panel.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel FormatLabel(String out) {
        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel output= new JLabel("<html><p style=\"width: 145 px\">" + out + "</p></html>");
        output.setFont(new Font("Thoma",Font.PLAIN,16));
        output.setForeground(Color.WHITE);
        output.setBackground(Color.BLUE);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(5,10,5,40));

        panel.add(output);

        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");

        JLabel time= new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);
        return  panel;
    }
}
