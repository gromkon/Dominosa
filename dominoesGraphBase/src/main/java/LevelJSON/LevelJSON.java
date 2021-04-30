package LevelJSON;

import Level.Domino;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class LevelJSON {

    private int[][] table;
    private int[][] answer;
    private Domino[] dominoes;
    private boolean isSolve;
    private ElementJSON[][] elements;
    private int sec;

    public LevelJSON(int[][] table, int[][] answer, Domino[] dominoes) {
        this.table = table;
        this.answer = answer;
        this.dominoes = dominoes;
        this.elements = new ElementJSON[table.length][table[0].length];
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                elements[i][j] = new ElementJSON();
            }
        }
        isSolve = false;
        sec = 0;
    }

    public void rewrite(int sizeH, int sizeW, int levelNumber) {
        String filePath = "Levels/" + sizeH + "x" + sizeW + "/level" + levelNumber + ".json";
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(this);

        try {
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unSolve() {
        isSolve = false;
        sec = 0;
    }

    public void setSolve(int sec) {
        isSolve = true;
        this.sec = sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
        isSolve = true;
    }

    public void setElements(ElementJSON[][] elements) {
        this.elements = elements;
    }

    public ElementJSON[][] getElements() {
        return elements;
    }

    public int[][] getTable() {
        return table;
    }

    public Domino[] getDominoes() {
        return dominoes;
    }

    public boolean isSolve() {
        return isSolve;
    }

    public int getSec() {
        return sec;
    }
}
