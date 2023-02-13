package sg.edu.nus.iss.app;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProfileService {
    
    public String dirName = "day08data";
    public String fileName = "profile.txt";
    public String dirFileName = dirName + File.separator + fileName;
    
    public void readFile() throws IOException{
        File file = new File(dirFileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        //Console conInput = System.console();
        

        String line  = "";
        String [] buffer;
        int occurence = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to search: ");
        String scannerInput = scanner.nextLine();

        while((line = br.readLine())!=null){
            line = line.replaceAll("\\,", " ");
            line = line.replaceAll("\\."," ");
            //System.out.println(line);
            buffer = line.split(" ");

            for(String s : buffer){
                //System.out.println("buffer read is: " + s);
                //System.out.println("Scanner Input is :" + scannerInput);
                if(s.equalsIgnoreCase(scannerInput)){
                    occurence++;
                }
            }
        }

        if (occurence == 0){
            System.out.printf("Word %s is not found\n",scannerInput);
        }
        else{
            System.out.printf("Word %s occurs %d number of times", scannerInput, occurence);
        }

        br.close();
        fr.close();
    }
}
