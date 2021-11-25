 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
 
class registration1 extends JFrame
    implements ActionListener {
 
    private Container cont;

    private JLabel title;
    private JLabel name;
    private JTextField tname;


    private JLabel id;

    private JTextArea tout;
    private JLabel setr;
    private JTextArea setc;
    boolean quizstart = false;

    private JTextField t_id;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton starts;
    private JButton reset;
 
 
    public registration1()
    {
        setTitle("Registration Form");
        setBounds(600, 200, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        cont = getContentPane();
        cont.setLayout(null);
 
        title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(200, 300);
        title.setLocation(200, -100);
        cont.add(title);


        id = new JLabel("ID");
        id.setFont(new Font("Arial", Font.PLAIN, 20));
        id.setSize(100, 20);
        id.setLocation(100, 150);
        cont.add(id);
 
        t_id = new JTextField();
        t_id.setFont(new Font("Arial", Font.PLAIN, 15));
        t_id.setSize(150, 20);
        t_id.setLocation(200, 150);
        cont.add(t_id);



 
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        cont.add(name);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        cont.add(tname);
 
        
 
 
        starts = new JButton("Start Test");
        starts.setFont(new Font("Arial", Font.PLAIN, 15));
        starts.setSize(100, 20);
        starts.setLocation(150, 250);
        starts.addActionListener(this);
        cont.add(starts);
 
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 250);
        reset.addActionListener(this);
        cont.add(reset);
 
        
 
        setVisible(true);
    }
 
  
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == starts) {
            
                
 
                
                quizstart = true;
                Quiz current = new Quiz(tname.getText(),t_id.getText());
                JFrame timer_frame = new JFrame();
                JLabel TimerLable = new JLabel();
                timer_frame.setLayout(new FlowLayout());
                timer_frame.setBounds(1000, 100, 400, 100);

                timer_frame.add(TimerLable);
                timer_frame.setVisible(true);

                Timer clock = new Timer();

                clock.scheduleAtFixedRate(new TimerTask() {

                    int i = current.duration;

                    public void run() {

                        TimerLable.setText("Time left: " + i);
                
                        i--;

                        if (i < 0) {

                            clock.cancel();

                            TimerLable.setText("Your Quiz time is UP");



                            current.result();
                        }
                    }
                }, 0, 1000);
        }
 
        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            t_id.setText(def);
            setr.setText(def);
            tout.setText(def);
            term.setSelected(false);
            setc.setText(def);
        }
    }
}
 
class registration {
 
    public static void main(String[] args) throws Exception
    {
        registration1 abc = new registration1();
		
    }
}