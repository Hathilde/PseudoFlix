
//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class ReadData {
    String filePath;
    //String imageFilePath;
    private ArrayList<Medier> sortedMediaObjects;

    public ReadData(String filePath) {
        this.filePath = filePath;
        //this.filePath = imageFilePath;

    }

    public void reader() {

        File file = new File(filePath);
        String[] singleMediaMetaData;

        sortedMediaObjects = new ArrayList<>();

        Scanner s = null;

        try {
           s = new Scanner(file, "iso-8859-1");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()) {

            singleMediaMetaData = s.nextLine().trim().split(";");
            String title = singleMediaMetaData[0];

            String years = singleMediaMetaData[1];

            String genreListe = singleMediaMetaData[2];
            String[] genreArray = genreListe.split(",");
            List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

            String rating = singleMediaMetaData[3];

            if (singleMediaMetaData.length > 4) {

                String seasonsAndEpisodes = singleMediaMetaData[4];

                String imgPath = "serieforsider2/" + title + ".jpg";

                Serier serie = new Serier(title, years, genre, rating, imgPath, seasonsAndEpisodes);
                sortedMediaObjects.add(serie);

            } else {
                String imgPath = "filmplakater/" + title + ".jpg";

                Film film = new Film(title, years, genre, rating, imgPath);
                sortedMediaObjects.add(film);

            }
        }
    }
    /*public void images (){

        //Files
        File directory = new File(imageFilePath);
        File [] files = directory.listFiles();
        Image[] fileName = new Image [files.length ];
        //ImageView[ ] images = new ImageView[files.length];

        for (int i = 0; i < files.length ; i++) {
            System.out.println(files[i].getName());
            fileName[i] = new Image(files[i].toURI().toString());
            images[i] = new ImageView(fileName[i]);
            String titleName = files[i].getAbsoluteFile().getName();

        }
        }*/
    public ArrayList<Medier> getSortedMediaObjects() {
        return sortedMediaObjects;
    }
}

