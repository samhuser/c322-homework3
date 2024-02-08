package duckservice.duckservice.repository;

import duckservice.duckservice.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DucksRepositoryTest {

    @Test
    void addDuck() {
        Duck duck = new RedheadDuck(3, DuckType.REDHEAD);
        DucksRepository ducksRepository = new DucksRepository();
        try {
            assertTrue(ducksRepository.addDuck(duck));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Duck duck1 = new DecoyDuck(2, DuckType.DECOY_DUCK);
        try {
            assertTrue(ducksRepository.addDuck(duck1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getAllDucks() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        assertEquals(3,ducksRepository.getAllDucks().size());
    }

    @Test
    void getDuck() {
        DucksRepository ducksRepository = new DucksRepository();
        Duck duck = new RedheadDuck(1, DuckType.REDHEAD);
        try {
            ducksRepository.addDuck(duck);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Duck duckR = ducksRepository.getDuck(1);
            assertTrue(duck.getId() == duckR.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void search() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        Duck duck = new MallardDuck(5, DuckType.REDHEAD);
        assertTrue(ducksRepository.addDuck(duck));
        assertEquals(2, ducksRepository.search("redhead").size());
    }
    @AfterAll
    static void cleanUp() throws IOException {
        File file = new File("ducks/db.txt");
        file.delete();
    }
}