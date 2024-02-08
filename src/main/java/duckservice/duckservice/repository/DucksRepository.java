package duckservice.duckservice.repository;
import duckservice.duckservice.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class DucksRepository {

    private String IMAGES_FOLDER_PATH = "ducks/images/";
    private String SOUNDS_FOLDER_PATH = "ducks/sounds/";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME = "ducks/db.txt";
    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
    public boolean addDuck(Duck duck) throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        String data = duck.toString();
        appendToFile(path, data + NEW_LINE);
        return true;
    }


    public List<Duck> getAllDucks() throws IOException {
        List<Duck> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line : data) {
            // Do some shit here to extract it
                String[] words = line.split(",");
                Duck duck = null;
                switch (words[1]){
                    case "Mallard":
                        duck = new MallardDuck(Integer.parseInt(words[0]), DuckType.MALLARD);
                        break;
                    case "Redhead":
                        duck = new RedheadDuck(Integer.parseInt(words[0]), DuckType.REDHEAD);
                        break;
                    case "Rubber":
                        duck = new RubberDuck(Integer.parseInt(words[0]), DuckType.RUBBER_DUCK);
                        break;
                    default: duck = new DecoyDuck(Integer.parseInt(words[0]), DuckType.DECOY_DUCK);
                }

            result.add(duck);
        }
        return result;
    }

    public Duck getDuck(int id) throws IOException {
        List<Duck> ducks = getAllDucks();
        for(Duck duck : ducks) {
            if (duck.getId() == id) {
                return duck;
            }
        }
        return null;
    }
    public List<Duck> search(String type) throws IOException {
        DuckType duckType = DuckType.toEnum(type);
        List<Duck> ducks = getAllDucks();
        List<Duck> result = new ArrayList<>();
        for(Duck duck : ducks) {
            if (duck.getType().equals(duckType)) {
                result.add(duck);
            }
        }
        return result;
    }
    public boolean uploadImage(int id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        System.out.println("The file " + path + " was saved successfully.");
        file.transferTo(path);
        return true;
    }

    public byte[] downloadImage(int id) throws IOException {
        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        byte[] image = Files.readAllBytes(path);
        return image;
    }
    public boolean uploadAudio(int id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        String fileExtension = ".mp3";
        Path path = Paths.get(SOUNDS_FOLDER_PATH
                + id + fileExtension);
        System.out.println("The file " + path + " was saved successfully.");
        file.transferTo(path);
        return true;
    }

    public byte[] downloadAudio(int id) throws IOException {
        String fileExtension = ".mp3";
        Path path = Paths.get(SOUNDS_FOLDER_PATH
                + id + fileExtension);
        byte[] image = Files.readAllBytes(path);
        return image;
    }

}
