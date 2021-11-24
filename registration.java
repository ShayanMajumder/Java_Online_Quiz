// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
 
class registration1
    extends JFrame
    implements ActionListener {
 
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel mno;
    private JTextField tmno;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    boolean quizstart = false;
 
 
    // constructor, to initialize the components
    // with default values.
    public registration1()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Candidate Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);
 
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);
 
        mno = new JLabel("ID");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(100, 150);
        c.add(mno);
 
        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(150, 20);
        tmno.setLocation(200, 150);
        c.add(tmno);
 
 
        sub = new JButton("Start Test");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);
 
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);
 
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);
 
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);
 
        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);
 
        setVisible(true);
    }
 
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            
                String data
                    = "Name : "
                      + tname.getText() + "\n"
                      + "ID : "
                      + tmno.getText() + "\n";
                
                
 
                tout.setText(data);
                tout.setEditable(false);
                res.setText("Best Of Luck...");
                quizstart = true;
                Quiz current = new Quiz(tname.getText(),tmno.getText());
                JFrame jframe = new JFrame();
                JLabel jLabel = new JLabel();
                jframe.setLayout(new FlowLayout());
                jframe.setBounds(500, 300, 400, 100);

                jframe.add(jLabel);
                jframe.setVisible(true);

                Timer timer = new Timer();

                timer.scheduleAtFixedRate(new TimerTask() {
                    int i = current.duration;

                    public void run() {

                        jLabel.setText("Time left: " + i);
                        i--;

                        if (i < 0) {
                            timer.cancel();
                            jLabel.setText("Time Over");
                            current.showSummary();
                        }
                    }
                }, 0, 1000);
        }
 
        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            tmno.setText(def);
            res.setText(def);
            tout.setText(def);
            term.setSelected(false);
            resadd.setText(def);
        }
    }
}
 
// Driver Code
class registration {
 
    public static void main(String[] args) throws Exception
    {
        registration1 f = new registration1();
		
    }
}