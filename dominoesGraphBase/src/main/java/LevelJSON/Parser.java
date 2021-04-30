package LevelJSON;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    int[][] table;

    public Parser(int sizeH, int sizeW, String path) {
        parseString(sizeH, sizeW, getStringFromFile(path));
    }

    public String getStringFromFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void parseString(int sizeH, int sizeW, String html) {
        table = new int[sizeH][sizeW];
        int iterLines = 0;
        int iterColumns = 0;
        Document doc = Jsoup.parse(html);
        Elements cellElements = doc.getElementsByClass("cell");
        for (int i = 0; i < cellElements.size(); i++) {
            System.out.println(iterLines + "; " + iterColumns);
            Element element = cellElements.get(i);
            Elements status0 = element.getElementsByClass("status0");
            try {
                table[iterLines][iterColumns] = Integer.parseInt(status0.get(0).text());
                iterColumns++;
                if (iterColumns == sizeW) {
                    iterColumns = 0;
                    iterLines++;
                }
            } catch (Exception e) {
                Elements status5 = element.getElementsByClass("status5");
                table[iterLines][iterColumns] = Integer.parseInt(status5.get(0).text());
                iterColumns++;
                if (iterColumns == sizeW) {
                    iterColumns = 0;
                    iterLines++;
                }
            }

        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j]++;
            }
        }
    }

    public int[][] getTable() {
        return table;
    }
}
