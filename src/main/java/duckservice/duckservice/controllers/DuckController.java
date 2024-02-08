package duckservice.duckservice.controllers;

import duckservice.duckservice.model.Duck;
import duckservice.duckservice.repository.DucksRepository;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ducks")
public class DuckController {

    private DucksRepository ducksRepository;

    public DuckController(DucksRepository ducksRepository) {
        this.ducksRepository = ducksRepository;
    }


    @PostMapping
    public boolean addDuck(@RequestBody Duck duck) {
        try {
            return ducksRepository.addDuck(duck);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Duck> getAllDucks() {
        try {
            return ducksRepository.getAllDucks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Duck> getDuck(@PathVariable int id) {
        try {
            Duck duck = ducksRepository.getDuck(id);
            if(duck != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(duck);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/image")
    public boolean uploadImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksRepository.uploadImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> downloadImage(@PathVariable int id) {
        try {
            byte[] image = ducksRepository.downloadImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/{id}/audio")
    public boolean uploadAudio(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksRepository.uploadAudio(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<?> downloadAudio(@PathVariable int id) {
        try {
            byte[] audio = ducksRepository.downloadAudio(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(audio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/search")
    public List<Duck> search(@RequestParam String type){
        try {
             return ducksRepository.search(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}