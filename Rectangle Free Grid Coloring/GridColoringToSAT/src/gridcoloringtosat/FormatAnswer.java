/**
 * Formats a walksat output (variables on many lines) to a file that contains
 * "Instance Satisfiable" on the first line, then all variable values printed
 * on the second line to be easily parsed by other classes in this package.
 *
 * @author Mike Groh
 */
package gridcoloringtosat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FormatAnswer {

    private final String INPUT_FILE = "src/gridcoloringtosat/unformatted.txt";
    private final String OUTPUT_FILE = "src/gridcoloringtosat/satsolution.txt";

    public FormatAnswer() {
        try {
            FileReader gridSolution;

            gridSolution = new FileReader(INPUT_FILE);

            //scanner used to read the file
            Scanner sc = new Scanner(gridSolution);

            ////temp string for when reading in lines from file
            String tmpLine = "";

            //stores false if a solution is not found in the file, true otherwise
            boolean solutionFound = false;

            //this will loop through each line of the solver's output file
            //until it reaches the line that starts with Instance Satisfiable
            //if there is not a solution
            while (sc.hasNextLine()) {
                tmpLine = sc.nextLine();

                if (tmpLine.startsWith("Instance Satisfiable")) {

                    //this file contains a solution
                    solutionFound = true;

                    //we got what we need from the file, just break out of the loop
                    break;
                }
            }

            if (solutionFound) {
                FileWriter file = new FileWriter(new File(OUTPUT_FILE));
                file.write("Instance Satisfiable" + "\n");
                while (sc.hasNextLine()) {
                    file.write(sc.nextLine().trim() + " ");
                }
                file.close();
            }
        } catch (Exception e) {
            System.err.println(e.getCause());
        }
    }
}
