/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rob
 */


public class Student{
private String name;
private int[] tests;
//Default:nameis""and3scoresare0
public Student(){
this (""); 
}
//Default:nameisnmand3scoresare0
public Student (String nm){
this (nm, 3);
}
//Nameisnmandnscoresare0
public Student(String nm,int n){
name=nm;                                                                                                                                                
tests = new int[n];
for(int i =0;i <tests.length; i++)
tests[i]=0;
}
//Nameis nm and scores are int
public Student(String nm, int[]t){
name=nm;
tests=new int[t.length];
for(int i=0;i<tests.length;i++)
tests[i]=t[i];
}
//Buildsacopyofs
public Student(Student s){
this(s.name, s.tests);
}
public void setName(String nm){
name=nm;
}
public String getName(){
return name;
}
public void setScore(int i, int score){
tests[i-1]=score;
}
public int getScore(int i){
return tests[i-1];
}
public int getAverage(){
int sum=0;
for(int score : tests)
sum += score;
return sum/tests.length;
}
public int getHighScore(){
int highScore=0;
for(int score : tests)
highScore = Math.max (highScore, score);
return highScore;
}
public String toString(){
String str = "Name: " + name + "\n";
//for(int i = 0; i<tests.length; i++)
//str += "test " +i+ ": " + tests[i] + "\n";
str += "" +  Integer.toString(getHighScore());
return str;
} 
//Returnsnulliftherearenoerrorselsereturns
//anappropriateerrormessage.
public String validateData(){
if(name.equals("")) return "SORRY: name required";
for (int score : tests){
if(score <0 || score > 100){
String str= "SORRY: must have" + 0
+ " <= test score <="+100;
return str;
}
}
return null;
}
}
