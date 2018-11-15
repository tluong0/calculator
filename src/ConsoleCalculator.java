
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 *
 * @author trangluong
 */
public class ConsoleCalculator {

    public static void main(String[] args) throws IOException {
        String fileToRead = "fileToRead.txt";
        if (fileToRead == null) {
            System.out.println("File name is empty");
        } else {
            FileWriter writer = null;
            try {
                Scanner in = new Scanner(new FileReader(fileToRead));

                while (in.hasNextLine()) {
                    
                    String str = in.nextLine();
                    ExpressionTools mytool = new ExpressionTools(str);
//            int mytoolResult;
                    try {
                        int mytoolResult = mytool.InfixtoPostFix();
                        String fileToWrite = "fileToWrite";
                        if (fileToWrite == null) {
                            System.out.println("File name is empty");
                        } else {

                            try {
                                writer = new FileWriter(fileToWrite, true);

                                BufferedWriter bw = new BufferedWriter(writer);
                                writer.write("" + mytoolResult);
                                writer.write("\n");
                                writer.flush();
                                
                            } catch (Exception e) {
                                System.out.println("File can not be found.");
                            }
                        }
                        System.out.println(mytoolResult);

                    } catch (Exception e) {
                        System.out.println("Invalid expression");

                    }
                          
                    //Closing BufferedWriter Stream
                }

            } catch (Exception e) {
                System.out.println("File can not be found here.");
            }
         writer.close(); 
        }
    }
}
