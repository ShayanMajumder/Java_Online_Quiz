import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;

public class admin {
  public void leaderboard() {
    try {
      File file = new File("results.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      TreeMap<Object, Object> treeMap = new TreeMap<>();
      String str;
      while ((str = bufferedReader.readLine()) != null) {
        String[] arrayOfString = str.split(":", 2);
        String s = arrayOfString[0].split(",",2)[0];
        treeMap.put(-Integer.valueOf(Integer.parseInt(s)), arrayOfString[1]);
      } 
      fileReader.close();
      System.out.println("Contents of File: ");
      System.out.println("*********************LEADER BOARD*************************");
      for (Map.Entry<Object, Object> entry : treeMap.entrySet())
        System.out.println((String)entry.getValue()); 
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public void addQuestion() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the question: ");
    String str = scanner.nextLine();
    System.out.println("Enter the four options(Serialwise one after the other): ");
    String[] arrayOfString = new String[4];
    int i;
    for (i = 0; i < 4; i++)
      arrayOfString[i] = scanner.nextLine(); 
    System.out.println("Enter the answer(Enter the correct option number 1-4): ");
    i = scanner.nextInt();
    question question = new question();
    question.setQuestion(str);
    question.setOption(arrayOfString);
    question.setAns(i-1);
    try {
      FileWriter fileWriter = new FileWriter("QuestionBank.txt", true);
      fileWriter.write(question.getQuestion() + "~" + question.getOption()[0] + "~" + question.getOption()[1] + "~" + question.getOption()[2] + "~" + question.getOption()[3] + '~' + question.getAns() + "\n");
      fileWriter.close();
    } catch (IOException iOException) {
      System.out.println("An error occurred.");
      iOException.printStackTrace();
    } 
  }
  public void addConfig() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the Time Duration in seconds:\n ");
    String sec = scanner.nextLine();
    System.out.println("Enter number of questions (Please check Question bank for max number of Q): \n");
    String Q = scanner.nextLine();
    
    try {
      FileWriter fileWriter = new FileWriter("config.txt");
      fileWriter.write(sec + "~" + Q + "\n");
      fileWriter.close();
    } catch (IOException iOException) {
      System.out.println("An error occurred.");
      iOException.printStackTrace();
    } 
  }
  public static void main(String[] paramArrayOfString) {
    admin admin1 = new admin();
    Scanner scanner = new Scanner(System.in); 
    int a;
    System.out.println("Press 1 to Add Question\nPress 2 to see leader board\nPress 3 to add configuration \n ");
    a = scanner.nextInt();
    do{
    switch(a){  
      //Case statements  
      case 1: admin1.addQuestion();  
      break;  
      case 2: admin1.leaderboard(); 
      System.out.println("\n Press 1 to continue");
      a = scanner.nextInt();
      break;  
      case 3: admin1.addConfig(); 
      break;  
      //Default case statement  
      default:System.out.println("Invalid Input");  
      }
      System.out.print("\033[H\033[2J");  

      System.out.println("Press 1 to Add Question\nPress 2 to see leader board\nPress 3 to add configuration\nPress 4 to exit ");
      a = scanner.nextInt();
      System.out.print("\033[H\033[2J");  


    }while(!(a == 4));
    

  }
}

