
import java.lang.reflect.Array;

import archives.Archives ;
import compression.Compression;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SenFileCompressor {
       //     Archives archives = new Archives();
    //     // Archives.createGreatFile(new String[]{"/Users/hachimdiallo/Downloads/ar-web.txt","/Users/hachimdiallo/Downloads/certificat.pdf"});
    //     // Archives.desarchive("greateFile.temp");


        // Compression compression = new Compression();
        // // Compression.CompressFile("/Users/hachimdiallo/Documents/dsjava2/greateFile.temp");

    //     // Compression.DecompressFile("/Users/hachimdiallo/Documents/dsjava2/fichier.sfc");

    static String cheminRepertoire = "";
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Aucune option spécifiée.");
            afficherAide();
            return;
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    if (i + 1 < args.length) {
                        String[] fichiers = args[i + 1].split(",");
                        compresserFichiers(fichiers);
                    } else {
                        System.out.println("Erreur : spécifiez une liste de fichiers à compresser après l'option -c.");
                    }
                    break;

                 case "-r":
                    if (i + 1 < args.length) {
                        cheminRepertoire = args[i + 1];
                        System.out.println("le fichiers de stock "+ cheminRepertoire);
                        i++;
                    } else {
                        System.out.println("Erreur : spécifiez le chemin du répertoire après l'option -r.");
                    }
                    break;
                case "-d":
                    if (i + 1 < args.length) {
                        desarchiverFichier(args[i + 1]);
                    } else {
                        System.out.println("Erreur : spécifiez le fichier à désarchiver après l'option -d.");
                    }
                    break;
                case "-h":
                   
                    afficherAide();
                    break;
                default:
                    System.out.println("Option non reconnue : " + args[i]);
            }
        }
    }

    private static void compresserFichiers(String[] fichiers) {
        Archives.createGreatFile(fichiers,"./destination");
       
        Compression.CompressFile("./destination/greateFile.temp","./destination");
        System.out.println("Compression des fichiers : " + String.join(", ", fichiers));
    }

    private static void desarchiverFichier(String fichier) {
        Compression.DecompressFile("./destination/fichier.sfc");
        Archives.desarchive("greateFile2.temp","./destination");
        System.out.println("Désarchivage du fichier : " + fichier);
    }

    private static void afficherAide() {
        System.out.println("Utilisation :");
        System.out.println("java SenFileCompressor -c <liste fichiers à compresser>");
        System.out.println("java SenFileCompressor -c <liste fichiers à compresser> -r <chemin du répertoire>");

        System.out.println("java SenFileCompressor -d <fichier à désarchiver>");
    }
}
