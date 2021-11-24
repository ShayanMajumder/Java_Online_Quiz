public class student {
    private String name;
    private int score;
    private String id;


    public String getName(){
       return name;
    }

    public int getScore(){
       return score;
    }

    public String getId(){
       return id;
    }

    public void setName(String name){
       this.name = name;
    }

    public void setScore(int score){
       this.score = score;
    }

    public void setId(String id){
       this.id = id;
    }

    public String toString(){
       return "Name: " + name + " Score: " + score + " ID: " + id;
    }
}