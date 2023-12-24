import java.util.ArrayList;
import java.util.Scanner;
public class fastSquabble {
    String[] answers;
    String[] allowed;
    String letters="filchbaresponkdgumty";
    String alphabet="abcdefghijklmnopqrstuvwxyz";
    public fastSquabble(String[] answers, String[] allowed){
        this.answers=answers;
        this.allowed=allowed;
    }

    public void play(){
        //System.out.println("What letters were successful?");
        Scanner reader= new Scanner(System.in);
        String correct=reader.next();
        char[] letters=parse(correct);
        arrayPrinter(updater(answers,letters,correct));
        arrayPrinter(updater(allowed,letters,correct));
    }

    public void arrayPrinter(String[] array){
        for(int icount1=0; icount1<array.length;icount1++) {
            System.out.print(array[icount1]+" ");
        }
        System.out.println("");
    }
    public char[] parse(String correct){
        ArrayList<Character> newList = new ArrayList<>();
        boolean failed;
        for(int icount1=0; icount1<letters.length();icount1++) {
            failed=false;
            for (int icount2=0; icount2<correct.length() && !failed;icount2++) {
                if (letters.charAt(icount1) == correct.charAt(icount2)){
                    failed=true;
                }
            }
            if (!failed){
                newList.add(letters.charAt(icount1));
            }
        }

        char[] newListFinal = new char[newList.size()];
        for (int count2 = 0; count2 < newList.size(); count2++) {
            newListFinal[count2] = newList.get(count2);
        }
        return newListFinal;
    }

    public String[] updater(String[] possible, char[] letters, String correct) {
        ArrayList<String> newList = new ArrayList<String>();
        boolean failed;
        for (int count1 = 0; count1 < possible.length; count1++) {
            failed = false;
            for (int count2 = 0; count2 < letters.length && (!failed); count2++) {
                if (possible[count1].indexOf(letters[count2]) != -1) {
                    failed = true;
                }
            }
            if (!failed) {
                newList.add(possible[count1]);
            }
        }
        for(int icount=0; icount< newList.size(); icount++){
        failed=false;
            for (int icount2=0; icount2<correct.length() && ! failed;icount2++) {
                if(newList.get(icount).indexOf(correct.charAt(icount2))==-1){
                    failed=true;
                }
            }
            if(failed){
                newList.remove(icount);
                icount--;
            }
        }
        String[] newListFinal = new String[newList.size()];
        for (int count2 = 0; count2 < newList.size(); count2++) {
            newListFinal[count2] = newList.get(count2);
        }

        return newListFinal;
    }
}
