package sg.edu.nus.iss.app;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App 

{
    public static String dirName = "day08data";
    public static String fileName = "idioms.txt";
    public static String dirFileName = dirName + File.separator + fileName;

    private App(){

    }
    public static void main( String[] args ) throws IOException
    {
        if(args.length>0){
            if(args[0].equalsIgnoreCase("kill")){
                System.out.println("Ending Program....");
                System.exit(0);
            };
        }

        
        /*
        FileService fs = new FileService();
        Boolean directoryCreated = fs.createDir(dirName);

        if (directoryCreated){
            System.out.println("directory Created: " + dirName);
        } else{
            System.out.println("directory already exists: " + dirName);
        }

        try{
            Boolean fileCreated = fs.createFile(dirName, fileName);
            if(fileCreated){
                System.out.println("file created: " + fileName);
            }else{
                System.out.println("file already exists: " + fileName);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(dirFileName);
            for(int i = 0;i<20;i++){
                
                //writing string in byte array
                fos.write(String.valueOf(i).getBytes());
                //fos.write(str[0]);
                fos.write('\n');
            }

            EmployeeService es = new EmployeeService();
            List<Employee> empList = es.generateEmployees();

            //clear output stream
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        Console con = System.console();
        String conInput = "";
        

        IdiomService idiomSvc = new IdiomService();
        List<String> idioms = null;

        ProfileService ps = new ProfileService();

        while (!conInput.equalsIgnoreCase("Q")){
            String randomIdiom = "";
            displayMenu();
            conInput = con.readLine("Enter your selection:");
            switch(conInput){
                case"1":
                    CSVExample();
                    break;
                case "2":
                    idioms = new ArrayList<String>();
                    idioms = idiomSvc.readFile(dirFileName);
                    break;
                case "3":
                    randomIdiom = idiomSvc.randomIdiom(idioms);
                    message(randomIdiom);
                    break;
                case "4":
                    idiomSvc.showAllIdioms(idioms);
                    break;
                case "5":
                    ps.readFile();
                    break;
                case "Q":
                case "q":
                    message("Bye...bye...");
                    break;
                default:
                    break;
            }
        }


    }

    public static void CSVExample() throws IOException{
        EmployeeService eservices = new EmployeeService();
        List<Employee> employees = eservices.generateEmployees();
        CSVService serviceClass = new CSVService();
        serviceClass.writeToCSV(employees, "employeeData");

        System.out.println("\n\n\nPrinting from file read: >>>>");
        List<Employee> employeesCopied = new ArrayList<Employee>();
        employeesCopied = serviceClass.readFromCSV("employeeData");
        employeesCopied.forEach(System.out::println);
    }

    public static void displayMenu(){
        message("Welcome to My App");
        message("====================");
        message("1. CSV Read and Write Test");
        message("2. Read Idioms File");
        message("3. Pick an idiom randomly");
        message("4. List all idioms");
        message("5. Read text file and check for words");
        message("Q. Quit the program");
    }

    public static void message(String line){
        System.out.println(line);
    }
}
