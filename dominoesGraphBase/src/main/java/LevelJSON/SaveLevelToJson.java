package LevelJSON;

import Level.Level;
import Level.Domino;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveLevelToJson {

    private static void generateLevels(int sizeH, int sizeW, int start, int count) {
        for (int i = start; i < start + count; i++) {
            Level level = new Level(sizeH, sizeW);
            while (!level.isValidLevel()) {
                level = new Level(sizeH, sizeW);
            }
            LevelJSON levelJSON = new LevelJSON(level.getLevel(), level.getAnswer(), level.getDominoes());

            String filePath = "Levels/" + sizeH + "x" + sizeW + "/level" + i + ".json";
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(levelJSON);

            try {
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private static LevelJSON getLevelFromJSON(int sizeH, int sizeW, int levelNumber) {
        String path = "Levels/" + sizeH + "x" + sizeW + "/level" + levelNumber + ".json";
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(jsonString, LevelJSON.class);
    }

    private static void setLevelSolveFalse(int sizeH, int sizeW, int levelNumber) {
        LevelJSON levelJSON = getLevelFromJSON(sizeH, sizeW, levelNumber);
        levelJSON.unSolve();
        levelJSON.rewrite(sizeH, sizeW, levelNumber);
    }

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static void parseLevelFromHTML(String path, int levelNumber, int sizeH, int sizeW) {
        Parser parser = new Parser(sizeH, sizeW, path);
        int[][] table = parser.getTable();
        Domino[] dominoes = Level.generateDominoes(table.length, table[0].length);
        int[][] answer = new int[table.length][table[0].length];

        LevelJSON levelJSON = new LevelJSON(table, answer, dominoes);

        String filePath = "Levels/" + table.length + "x" + table[0].length + "/level" + levelNumber + ".json";
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(levelJSON);

        try {
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        generateLevels(42, 41, 1, 1);
//        setLevelSolveFalse(6, 6, 1);
//        parseLevelFromHTML("LevelsFromDominosa/41x42/2.txt", 2, 41, 42);
    }


}
