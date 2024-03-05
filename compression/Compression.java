package compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Compression {
    

    /**
     * compression fichier
     */
    public static void CompressFile(String fileToCompress,String repertoireDestination) {
        String myFileCompressed = repertoireDestination+"/fichier.sfc";
    
        try {
            FileInputStream IstreamFile = new FileInputStream(fileToCompress);

            FileOutputStream OstreamFile = new FileOutputStream(myFileCompressed);

            Deflater deflater = new Deflater();
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(OstreamFile,deflater);


            byte[] buffer = new byte[1024];
            int length;
            while ((length = IstreamFile.read(buffer)) != -1) {
                deflaterOutputStream.write(buffer, 0, length);
            }

            IstreamFile.close();
            deflaterOutputStream.finish();
            deflaterOutputStream.close();

            System.out.println("Compression réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la compression : " + e.getMessage());
        }
    }



    /**
     * Decompression fichier
     */
    public static void DecompressFile(String fileCompressed) {
        String myFileDecompressed = "greateFile2.temp";
    
        try {
            FileInputStream fis = new FileInputStream(fileCompressed);
            FileOutputStream fos = new FileOutputStream(myFileDecompressed);
            Inflater inflater = new Inflater();
            InflaterInputStream iis = new InflaterInputStream(fis, inflater);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = iis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }

            fis.close();
            fos.close();

            System.out.println("Décompression réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la décompression : " + e.getMessage());
        }
    }
}
