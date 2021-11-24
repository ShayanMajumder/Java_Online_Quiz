import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Timer;




public class QuizProgram {

     //Initializaions and Instantiations
    static Scanner cin = new Scanner(System.in);
    final static String outputFilePath
        = "results.txt";
    static long interval;
    static Timer timer;

    

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        long duration = 1000000;
        int score = 0;
         final int NumberofQuestions = 5;
         student student = new student();

         System.out.println("\n\n\nQuiz 1.0\n\n");
         System.out.println("\n\n\nEnter Name\n\n");
         student.setName(cin.nextLine());
         System.out.println("\n\n\nEnter ID\n\n");
         student.setId(cin.nextLine());
         System.out.print("\033[H\033[2J");  
        System.out.flush();
         Hashtable<String, student> Students = new Hashtable<String, student>();

           //Store questions and answers in 2 dimensional array
           String[][] QandA = {
                               {"Who sing this the song 'Locked Away':?","\nChoose 1 for R. City & Adam Levine's. \nChoose 2 for Justin Bieber.  \nChoose 3 for Selena Gomez. \nChoose 4 for Katy Perry.","1",""},
                               {"Who sing this the song 'Beauty and the beast'?","\nChoose 1 for R. City & Adam Levine's. \nChoose 2 for Justin Bieber.  \nChoose 3 for Selena Gomez. \nChoose 4 for Katy Perry.","2",""},
                               {"Who sing this the song 'Heal the world'?","\nChoose 1 for R. City & Adam Levine's. \nChoose 2 for Micheal Jackson.  \nChoose 3 for Selena Gomez. \nChoose 4 for Katy Perry.","2",""},
                               {"Who sing this the song 'Roar'?","\nChoose 1 for R. City & Adam Levine's. \nChoose 2 for Justin Bieber.  \nChoose 3 for Selena Gomez. \nChoose 4 for Katy Perry.","4",""},
                               {"Who sing this the song 'The Heart Wants What It Wants '?","\nChoose 1 for R. City & Adam Levine's. \nChoose 2 for Justin Bieber.  \nChoose 3 for Selena Gomez. \nChoose 4 for Katy Perry.","3",""} };

          String[] Answers = new String[(int) NumberofQuestions];

         //loop through each string in the array and compare it to answers
         //for(int x = 0; x < NumberofQuestions; x++)
          int x=0;
          long eta = 1000;
        timer = new Timer();
        interval = duration/1000;
        
          do
         {  
              //System.out.print("" + (x+1) + ". " + QandA[x][0] + "   "+QandA[x][1]);
              
            
              StringBuilder string = new StringBuilder(250);   
             eta = duration-(System.currentTimeMillis()-startTime);
             
              String etaHms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                      TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                      TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1)); 
              string
                  .append('\r')
                  .append(String.format("Time Left: %s \n", etaHms))
                  .append(String.format("%d .  %s  %s \n Answer is --> ",(x+1),QandA[x][0] , QandA[x][1] ));
                          
              System.out.print(string);
            
              Answers[x] = String.valueOf(cin.nextInt());

              Answers[x].toLowerCase();
              QandA[x][3] = Answers[x];

              if(QandA[x][2].equals(Answers[x]))
              {
                   score++;
              }//close if 
              else
              {
                  System.out.println("\n Incorrect. The right answer is "+QandA[x][2]);
              }

              System.out.print("\n");
              x++;
              System.out.print("\033[H\033[2J");  
              System.out.flush();
            
         }while(eta>0 && x <NumberofQuestions );//close outer loop                                 
          
         student.setScore(score);
         System.out.println("\n\t\tYou got " + score + " of "
                                + NumberofQuestions + " right!\n\n\n");
        System.out.println("Press 1 to review Answers and any other number to exit");
        if (cin.nextInt() == 1)
        {

        
        x = 0;
        do{
        StringBuilder string = new StringBuilder(250);   

        string
        .append(String.format("%d .  %s  %s \n Correct Option is --> %s \n Marked Option is --> %s \n",(x+1),QandA[x][0] , QandA[x][1],QandA[x][2] , QandA[x][3] ));
                
        System.out.print(string);
        x++;
        }while(x<NumberofQuestions);
    }

         Students.put(String.valueOf((NumberofQuestions-student.getScore()))+','+student.getId(), student);

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
        

    }//close main() function  

//-------------------------------------------------------------------------------

}//close Quiz class