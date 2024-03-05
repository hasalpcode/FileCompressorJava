package archives;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Archives
 */
public class Archives {

  
    public static void createGreatFile(String[]files , String repertoireDestination) {
        File archiveFile = new File(repertoireDestination+"/"+"greateFile.temp");

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(archiveFile))) {
            for (int i = 0; i < files.length; i++) {
                File sourceFile = new File(files[i]);
                if (!sourceFile.exists() || !sourceFile.isFile()) {
                    System.out.println("Le fichier " + files[i] + " n'existe pas ou n'est pas un fichier.");
                    continue;
                }
                bWriter.write("file: " + sourceFile.getName());  // balise de debut(nom du fichier) pour l'ecriture
                bWriter.newLine();

                try (BufferedReader bReader = new BufferedReader(new FileReader(sourceFile))) {
                    String line;
                    while ((line = bReader.readLine()) != null) { // tant que la ligne n'est pas vide
                        bWriter.write(line);
                        bWriter.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Erreur de lecture du fichier " + sourceFile.getName() + ": " + e.getMessage());
                }

                bWriter.write("fin file"); // balise de fin
                bWriter.newLine();
            }
            System.out.println("Archivage réussi.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'archivage : " + e.getMessage());
        }
        


    }


    
    public static void desarchive(String archiveFile, String repertoireDestination) {
        try (BufferedReader bReader = new BufferedReader(new FileReader(archiveFile))) {
            String line;
            File currentFile = null;
            BufferedWriter currentWriter = null;

            while ((line = bReader.readLine()) != null) {
                if (line.startsWith("file: ")) {
                    String fileName = line.substring("file: ".length());
                    currentFile = new File(repertoireDestination+"/"+fileName);
                    currentWriter = new BufferedWriter(new FileWriter(currentFile));
                } else if (line.startsWith("end file")) {
                    if (currentWriter != null) {
                        currentWriter.close();
                        currentFile = null;
                    }
                } else {
                    if (currentWriter != null) {
                        currentWriter.write(line);
                        currentWriter.newLine();
                    }
                }
            }
            System.out.println("Désarchivage réussi.");
        } catch (IOException e) {
            System.out.println("Erreur lors du désarchivage : " + e.getMessage());
        }
    }
    

    // public static void desarchive(String archiveFile) {
    //     try (BufferedReader bReader = new BufferedReader(new FileReader(archiveFile))) {
    //         String line;
    //         int fileCount = 0;
    //         BufferedWriter bWriter = null;

    //         while ((line = bReader.readLine()) != null) {
    //             if (line.startsWith("file")) {
    //                 fileCount++;
    //                 bWriter = new BufferedWriter(new FileWriter("org_fichier" + fileCount + ".txt"));
    //             } else if (line.startsWith("end file")) {
    //                 if (bWriter != null) {
    //                     bWriter.close();
    //                 }
    //             } else {
    //                 if (bWriter != null) {
    //                     bWriter.write(line);
    //                     bWriter.newLine();
    //                 }
    //             }
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Erreur lors de la désarchivage : " + e.getMessage());
    //     }
    // }
}