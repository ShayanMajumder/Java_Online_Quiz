import java.io.*;
import java.util.*;
import javax.swing.JFrame;


import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JOptionPane;




public class Quiz extends JFrame{
	JPanel p=new JPanel();
	CardLayout cards=new CardLayout();
	int num = 1;
	boolean v = false;
	int i=0;
	


	
	int total=9;

	int W = 0;
	int C=0;
	int unattempted = 0;
	int duration = 500;
	{

	try {
		File file = new File("config.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
		while ((str = bufferedReader.readLine()) != null) {
		  String[] arrayOfString = str.split("~", 3);

          total = Integer.parseInt(arrayOfString[1]);
		  duration = Integer.parseInt(arrayOfString[0]);
		}

		fileReader.close();

    }
		 catch (IOException iOException) {
		iOException.printStackTrace();
		System.out.println(" No questions added");

	  }
	}

	static Scanner cin = new Scanner(System.in);

    final static String outputFilePath
        = "results.txt";
	static student student = new student();
	Hashtable<String, student> Students = new Hashtable<String, student>();
	List<List<String>> answers = new ArrayList<>();
	List<String> str_questions = new ArrayList<>();
	List<Integer> correct_ans = new ArrayList<>();

	{

	try {
		File file = new File("QuestionBank.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
		while ((str = bufferedReader.readLine()) != null) {
		  String[] arrayOfString = str.split("~", 6);
		  List<String> options = new ArrayList<>();
		  options.add(arrayOfString[1]);
		  options.add(arrayOfString[2]);
		  options.add(arrayOfString[3]);
		  options.add(arrayOfString[4]);

          answers.add(options);
		  str_questions.add(arrayOfString[0]);
		  correct_ans.add(Integer.parseInt(arrayOfString[5]));
		}

		fileReader.close();

    }
		 catch (IOException iOException) {
		iOException.printStackTrace();
		System.out.println(" No questions added");

	  }
	}

	List<MCQ> questions = new ArrayList<>();
	{

	for(int j= 0; j < total ; j++)
	{
		questions.add(new MCQ(
			(j+1) + ")"+ str_questions.get(j),
			answers.get(j),
			correct_ans.get(j),this,j,(total-1)
		));
	}
	}
	
	
	public Quiz(String name, String id){

		super("Quiz for " + name + "      " + id);
		setResizable(true);
		setSize(600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		student.setName(name);
        student.setId(id);
		
		p.setLayout(cards);
		num= questions.size();
		for(int i=0;i<num;i++){
			p.add(questions.get(i),"q"+i);
		}
		
		add(p);
		setVisible(true);
	}
	
	public void next(){
		if((C+W)==(total)){
			result();
		}else{
 
 			cards.show(p,"q"+i);
			i = i + 1;
 		 
		}
	}
	
	public void result(){
		student.setScore(C);
		String S = "";
		for(int i=0; i< questions.size(); i++)
		{
			if(questions.get(i).selected!=100)
			{
			if(questions.get(i).correctAns== questions.get(i).selected)
			{
			S = S + '\n'+ (i+1)+ ')' +questions.get(i).question
			 	+ '\n' + "Correct Ans is -> " + answers.get(i).get(questions.get(i).correctAns)
				 + "         " + "You Selected ->" + answers.get(i).get(questions.get(i).selected)+ "   Marks -> +1"; }
			else{
				S = S + '\n'+ (i+1)+ ')' +questions.get(i).question
			 	+ '\n' + "Correct Ans is -> " + answers.get(i).get(questions.get(i).correctAns)
				 + "         " + "You Selected ->" + answers.get(i).get(questions.get(i).selected) + "   Marks -> 0";

			}
		}
			else{
				S = S + '\n'+ (i+1)+ ')' +questions.get(i).question
			 	+ '\n' + "Correct Ans is -> " +answers.get(i).get(questions.get(i).correctAns)
				 + "         " + "You Selected ->" + "Unattempted" + "   Marks -> 0";
				 unattempted++;
			}
			}
		JOptionPane.showMessageDialog(null,"Here are your results"+
 			
 			"\nScore: \t"+C+
 			
 			"\nTotal: "+ (C+W+unattempted) + '\n' + 

			 "**************** SUMMARY OF THE TEST IS ***************************\n" +
			 S


		);
		Students.put(String.valueOf((total+1)-student.getScore()) +','+student.getId(), student);

         File file = new File(outputFilePath);
  
        BufferedWriter bf = null;
  
        try {
  
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file,true));
  
            // iterate map entries
            for (Map.Entry<String, student> entry :
                 Students.entrySet()) {
  
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                         + entry.getValue());
  
                // new line
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
		System.exit(0);
	}
}