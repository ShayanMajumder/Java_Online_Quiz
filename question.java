public class question {
    private String Question;
    private int ans;
    private String[] options = new String[4];



    public String getQuestion(){
       return Question;
    }

    public String[] getOption(){
       return options;
      }
    public int getAns(){
       return ans;
    }

    public void setQuestion(String Question){
       this.Question = Question;
    }

    public void setOption(String[] options){
       this.options = options;
    }

    public void setAns(int ans){
       this.ans = ans;
    }

}