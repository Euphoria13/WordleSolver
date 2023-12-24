import java.io.*;
import java.util.ArrayList;

public class main {
    public static void main(String[]args) throws IOException {
        ArrayList<String> preAllowed= new ArrayList<>();
        ArrayList<String> preAnswers= new ArrayList<>();
        Brains ta= new Brains();
        // File file = new File("C:\\Users\\kevin\\IdeaProjects\\Wordle\\twl-wordlist");
        File file = new File("C:\\Users\\kevin\\IdeaProjects\\Wordle\\wordle-allowed-guesses.txt");
         BufferedReader br = new BufferedReader(new FileReader(file));
         String st;
         while ((st = br.readLine()) != null){
            preAllowed.add(st);
         }
         // File file2 = new File("C:\\Users\\kevin\\IdeaProjects\\Wordle\\twl-wordlist-pared");
         File file2 = new File("C:\\Users\\kevin\\IdeaProjects\\Wordle\\wordle-answers-alphabetical.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        while ((st = br2.readLine()) != null){
            preAnswers.add(st);
        }
        String[] allowed= new String[preAllowed.size()];
        String[] answers= new String[preAnswers.size()];
        for (int count1=0; count1< preAllowed.size();count1++){
            allowed[count1]=preAllowed.get(count1);
        }
        for (int count2=0; count2< preAnswers.size();count2++){
            answers[count2]=preAnswers.get(count2);
        }
        fastSquabble game= new fastSquabble(answers,allowed);
        while(true) {
            // game.play();
            ta.solver(answers, allowed, true);
        }
        //System.out.print(ta.averageFinder(answers, allowed, true));


        /*
        int[][] LetterFreq=new int[5][26];
        for(int icount=0; icount< answers.length;icount++){
            for(int icount2=0; icount2<5; icount2++){
                LetterFreq[icount2][(int) answers[icount].charAt(icount2) -(int)'a']++;
            }
        }
        for(int icount3=0; icount3<LetterFreq[0].length;icount3++){
            for(int icount4=0; icount4<5; icount4++){
                System.out.print(LetterFreq[icount4][icount3]+" ");
            }
            System.out.println("");
        }


        ArrayList<String> added= new ArrayList<>();
        for(int icount=0; icount< allowed.length;icount++){
            if(allowed[icount].charAt(0)=='b'|| allowed[icount].charAt(0)=='p'||allowed[icount].charAt(0)=='g'||allowed[icount].charAt(0)=='0'){
                if(allowed[icount].charAt(1)=='a'||allowed[icount].charAt(1)=='o'||allowed[icount].charAt(1)=='u'||allowed[icount].charAt(1)=='0'){
                    if(allowed[icount].charAt(2)=='r'||allowed[icount].charAt(2)=='n'||allowed[icount].charAt(2)=='m'||allowed[icount].charAt(2)=='0'){
                        if(allowed[icount].charAt(3)=='e'||allowed[icount].charAt(3)=='t'||allowed[icount].charAt(3)=='k'||allowed[icount].charAt(3)=='0'){
                            if(allowed[icount].charAt(4)=='s'||allowed[icount].charAt(4)=='y'||allowed[icount].charAt(4)=='d'||allowed[icount].charAt(4)=='0'){
                                added.add(allowed[icount]);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(added);

 */

    }
}
