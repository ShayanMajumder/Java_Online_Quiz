import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;


public class MCQ extends JPanel implements ActionListener{
	int correctAns;
	Quiz quiz;	
 
 	
 	JPanel qPanel=new JPanel();
 	JPanel aPanel=new JPanel();
	JRadioButton[] responses;
	ButtonGroup group=new ButtonGroup();
 	JPanel botPanel=new JPanel();
	JButton next=new JButton("Next Q");
	JButton finish=new JButton("End");

	boolean count = false;
	boolean wcount = false;
	int selected = 100;
    int correct;
    String question;
	boolean used = false;
 
	
	public MCQ(String q, List<String> options, int ans, Quiz quiz, int curr,int fin){

		if(curr!=fin)
		{
        this.correct = ans;
        
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		correctAns=ans;

		this.question = q;
		this.quiz=quiz;

 		qPanel.add(new JLabel(q));
		add(qPanel);
 		responses=new JRadioButton[options.size()];
		for(int i=0;i<options.size();i++){
			responses[i]=new JRadioButton(options.get(i));
			responses[i].addActionListener(this);
			group.add(responses[i]);
			aPanel.add(responses[i]);
		}
		add(aPanel);
 		next.addActionListener(this);
		finish.addActionListener(this);
		botPanel.add(next);
		botPanel.add(finish);
		add(botPanel);
		}
		else
		{
		this.correct = ans;
        
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		correctAns=ans;

		this.question = q;
		this.quiz=quiz;

 		qPanel.add(new JLabel(q));
		add(qPanel);
 		responses=new JRadioButton[options.size()];
		for(int i=0;i<options.size();i++){
			responses[i]=new JRadioButton(options.get(i));
			responses[i].addActionListener(this);
			group.add(responses[i]);
			aPanel.add(responses[i]);
		}
		add(aPanel);
		finish.addActionListener(this);
		botPanel.add(finish);
		add(botPanel);	
		}
	}
	
	public void actionPerformed(ActionEvent e){
		Object src=e.getSource();
 		if(src.equals(next) ){
			showResult(); 
 			quiz.next();
		}
 		if(src.equals(finish)){
			showResult();
			quiz.result();
			
		}
 		for(int i=0;i<responses.length;i++){
			if(src==responses[i]){
				selected=i;
			}
		}
	}
	
	public void showResult(){
		quiz.total++;
		if(selected==correctAns && count == false){
			quiz.C++;

			count = true;
  		}
  		else if(selected != correctAns && wcount == false)
  		{
			quiz.W++;
			wcount = true;
		}
	}
}