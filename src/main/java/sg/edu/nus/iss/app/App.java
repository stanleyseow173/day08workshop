package sg.edu.nus.iss.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App 
{
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

        EmployeeService eservices = new EmployeeService();
        List<Employee> employees = eservices.generateEmployees();
        CSVService serviceClass = new CSVService();
        serviceClass.writeToCSV(employees, "employeeData");

        System.out.println("\n\n\nPrinting from file read: >>>>");
        List<Employee> employeesCopied = new ArrayList<Employee>();
        employeesCopied = serviceClass.readFromCSV("employeeData");
        employeesCopied.forEach(System.out::println);

        String dirName = "day08data";
        String fileName = "data.txt";
        String dirFileName = dirName + File.separator + fileName;

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
        
    }
}
