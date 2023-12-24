import java.util.*;
import java.lang.*;
public class Brains {

    public double averageFinder(String[] possible, String[] guessable, boolean rule) {
        int sum;
        int[] guessesReq= new int[possible.length];
        final String[] possibleOG=possible;
        final String[] guessableOG=guessable;
        for (int icount3 = 0; icount3 < possibleOG.length; icount3++) {
            possible=possibleOG;
            guessable=guessableOG;
            sum=0;
            sum++;
            String word = "salet";
            int[] store=outputMaker(word, possibleOG[icount3]);
            possible = updater(possible, word, store);
            if(rule){
                guessable=updater(guessable, word, store);
            }
            if(store[0]==2 && store[1]==2 && store[2]==2 && store[3]==2 && store[4]==2){
                sum--;
            }
            while (possible.length > 1) {
                sum++;
                if (possible.length < 4) {
                    guessable = possible;
                }
                word = BestWord(possible, guessable);
                store=outputMaker(word, possibleOG[icount3]);
                possible = updater(possible, word, store);
                if(rule){
                    guessable=updater(guessable, word, store);
                }
                if(store[0]==2 && store[1]==2 && store[2]==2 && store[3]==2 && store[4]==2){
                    sum--;
                }
            }
            if (possible.length == 1) {
                sum++;
                System.out.println(possibleOG[icount3]+" "+sum);
            } else {
                System.out.println("Something unexpected happened");
            }
            guessesReq[icount3]=sum;
        }
        double sum2=0;
        for(int icount1=0; icount1< guessesReq.length;icount1++){
            sum2+=guessesReq[icount1];
        }
        return sum2/guessesReq.length;
    }
    public void solver(String[] possible, String[] guessable, boolean rule){
        String word;
        Scanner reader= new Scanner(System.in);
        String digits;

        word="salet";
        //word="tares";

        System.out.println("The best guess is "+word);
        System.out.println("Enter the response to the best guess");
        digits=reader.next();
        possible=updater(possible, word, converter(digits));
        if(rule){
            guessable=updater(guessable, word, converter(digits));
        }

        while(possible.length>1){
            if(possible.length<4){
                guessable=possible;
            }
            word=BestWord(possible, guessable);
            System.out.println("The best guess is "+word);
            System.out.println("Enter the response to the best guess");
            digits=reader.next();
            possible=updater(possible, word, converter(digits));
            if(rule){
                guessable=updater(guessable, word, converter(digits));
            }
        }
        if(possible.length==1){
            System.out.println("The word is "+possible[0]);
        }
        else{
            System.out.println("Something unexpected happened");
        }
    }

    public int[] converter(String digits){
        int[] output= new int[digits.length()];
        for(int count1=0; count1<digits.length(); count1++){
            if(digits.charAt(count1)=='G'||digits.charAt(count1)=='g'){
                output[count1]=2;
            }
            if(digits.charAt(count1)=='Y'||digits.charAt(count1)=='y'){
                output[count1]=1;
            }
            if(digits.charAt(count1)=='R'||digits.charAt(count1)=='r'||digits.charAt(count1)=='B'||digits.charAt(count1)=='b'){
                output[count1]=0;
            }
        }
        return output;
    }
    public String[] updater(String[] possible, String guess, int[] result){
        ArrayList<String> newList= new ArrayList<String>();
        for(int count1=0; count1<possible.length; count1++){
            int[] results=outputMaker(guess, possible[count1]);
            if(results[0]==result[0] && (results[1]==result[1] && (results[2]==result[2] && (results[3]==result[3] && (results[4]==result[4]))))){
                newList.add(possible[count1]);
            }
        }
        String[] newListFinal= new String[newList.size()];
        for(int count2=0;count2<newList.size();count2++){
            newListFinal[count2]=newList.get(count2);
        }
        return newListFinal;
    }

    public String BestWord(String[] possible, String[] guessable){
        double max=0;
        String bestWord="";
        for(int count1=0; count1<guessable.length;count1++){
            if(entropy(guessable[count1],possible)>max){
                max=entropy(guessable[count1],possible);
                bestWord=guessable[count1];
            }
        }
        return bestWord;
    }

        public int[] outputMaker(String Guess, String Real){
            int[] output= new int[5];
            for(int count1=0; count1<Guess.length(); count1++){
                char letter=Guess.charAt(count1);
                for(int count2=0; count2<Real.length(); count2++) {
                    if(letter==Real.charAt(count2)){
                        if(count1==count2){
                            output[count1]=2;
                            count2=Real.length();
                        }else{
                            if(Guess.charAt(count1)==Real.charAt(count1)){
                                output[count1]=2;
                            }else {
                                output[count1] = 4;
                            }
                            count2=Real.length();
                        }
                    }else{
                        output[count1]=0;
                    }
                }
            }
            for(int count3=0; count3<Guess.length(); count3++){
                if(output[count3]==4){
                    int sum=0;
                    for(int count1=0; count1<Real.length(); count1++){
                        if(Real.charAt(count1)==Guess.charAt(count3)){
                            sum++;
                        }
                    }
                    for(int count2=0; count2<Real.length(); count2++){
                        if(Guess.charAt(count2)==Guess.charAt(count3) &&(output[count2]==2 ||output[count2]==1)){
                            sum--;
                        }
                    }
                    if(sum>0) {
                        output[count3] = 1;
                    }else{
                        output[count3]=0;
                    }
                    }
                }
            return output;
        }
        public double entropy(String guess, String[] possible){
        double[] distro=new double[243];
        for(int count1=0; count1<possible.length; count1++){
            int[] pattern=outputMaker(guess,possible[count1]);
            distro[pattern[0]+3*pattern[1]+9*pattern[2]+27*pattern[3]+81*pattern[4]]++;
        }
        double sum=0;
        for(int count2=0; count2<distro.length; count2++){

            if(Math.abs(distro[count2])>0.00000000001) {
                sum = (distro[count2] / possible.length) * Math.log(possible.length / distro[count2]) / Math.log(2) + sum;
            }
        }
        for(int count1=0; count1>possible.length;count1++){
            if(guess==possible[count1]) {
                sum = sum + 1 / possible.length;
                break;
            }
        }
        return sum;
        }
    }
