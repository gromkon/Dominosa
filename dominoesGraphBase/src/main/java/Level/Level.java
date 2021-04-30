package Level;

import java.util.ArrayList;
import java.util.Random;

public class Level {

    private int sizeW;
    private int sizeH;
    private int[][] level;
    private int[][] answer;
    private Domino[] dominoes;
    private ArrayList<Integer> usedDominoes;
    private double maxDominoValue;
    private int countDominoes;

    private boolean validLevel;

    public Level(int size) {
        this(size, size);
    }

    public Level(int sizeH, int sizeW) {
        validLevel = true;

        this.sizeH = sizeH;
        this.sizeW = sizeW;

        if (sizeW * sizeH % 2 != 0) {
            throw new RuntimeException("Должно быть четное количество ячеек");
        }
        countDominoes = sizeH * sizeW / 2;

        calcMaxDominoValue();

        level = new int[sizeH][sizeW];
        answer = new int[sizeH][sizeW];
        dominoes = new Domino[countDominoes];
        usedDominoes = new ArrayList<>();
        for (int i = 0; i < countDominoes; i++) {
            usedDominoes.add(i);
        }
        generateDominoes();
        generateLevel();
    }

    private void calcMaxDominoValue() {
        int sum = 0;
        int i = 1;
        while (sum < countDominoes) {
            sum += i;
            i++;
        }
        maxDominoValue = i - 1;
    }

    private void generateDominoes() {
        int iter = 0;
        for (int i = 1; i <= maxDominoValue; i++) {
            if (iter >= countDominoes) {
                break;
            }
            for (int j = i; j <= maxDominoValue; j++) {
                if (iter >= countDominoes) {
                    break;
                }
                dominoes[iter++] = new Domino(i, j);
            }
        }
    }

    private static int calcMaxDominoValue(int sizeH, int sizeW ) {
        int countDominoes = sizeH * sizeW / 2;
        int sum = 0;
        int i = 1;
        while (sum < countDominoes) {
            sum += i;
            i++;
        }
        return i - 1;
    }

    public static Domino[] generateDominoes(int sizeH, int sizeW) {
        int maxDominoValue = calcMaxDominoValue(sizeW, sizeH);
        int countDominoes = sizeH * sizeW / 2;
        Domino[] dominoes = new Domino[countDominoes];
        int iter = 0;
        for (int i = 1; i <= maxDominoValue; i++) {
            if (iter >= countDominoes) {
                break;
            }
            for (int j = i; j <= maxDominoValue; j++) {
                if (iter >= countDominoes) {
                    break;
                }
                dominoes[iter++] = new Domino(i, j);
            }
        }
        return dominoes;
    }

    private void generateLevel() {
        Random random = new Random();
        int iter = 0;
        for (int y = 0; y < level.length; y++) {
            if (iter == countDominoes || !validLevel) {
                break;
            }
            for (int x = 0; x < level[y].length; x++) {
                if (iter == countDominoes || !validLevel) {
                    break;
                }
                if (level[y][x] == 0) {
                    boolean horizontally = canHorizontally(x, y);
                    boolean vertically = canVertically(x, y);
                    if (horizontally && vertically) {
                        boolean isHorizontally = random.nextBoolean();
                        if (isHorizontally) {
                            Domino domino = getRandomDomino();
                            domino.setPos(x, y, x + 1, y);
                            level[y][x] = domino.getFirst();
                            level[y][x + 1] = domino.getSecond();
                            answer[y][x] = iter;
                            answer[y][x + 1] = iter;
                            iter++;
                        } else {
                            Domino domino = getRandomDomino();
                            domino.setPos(x, y, x, y + 1);
                            level[y][x] = domino.getFirst();
                            level[y + 1][x] = domino.getSecond();
                            answer[y][x] = iter;
                            answer[y + 1][x] = iter;
                            iter++;
                        }
                    } else if (horizontally) {
                        Domino domino = getRandomDomino();
                        domino.setPos(x, y, x + 1, y);
                        level[y][x] = domino.getFirst();
                        level[y][x + 1] = domino.getSecond();
                        answer[y][x] = iter;
                        answer[y][x + 1] = iter;
                        iter++;
                    } else if (vertically) {
                        Domino domino = getRandomDomino();
                        domino.setPos(x, y, x, y + 1);
                        level[y][x] = domino.getFirst();
                        level[y + 1][x] = domino.getSecond();
                        answer[y][x] = iter;
                        answer[y + 1][x] = iter;
                        iter++;
                    } else {
                        validLevel = false;
                        break;
                    }
                }
            }
        }
    }

    private Domino getRandomDomino() {
        Random random = new Random();
        int min = 0;
        int max = usedDominoes.size() - 1;
        int randomPos = random.nextInt((max - min) + 1) + min;
        Domino domino = dominoes[usedDominoes.get(randomPos)];
        usedDominoes.remove(randomPos);
        return domino;
    }

    private boolean canHorizontally(int x, int y) {
        if (x + 1 < sizeW) {
            return level[y][x + 1] == 0;
        } else {
            return false;
        }
    }

    private boolean canVertically(int x, int y) {
        if (y + 1 < sizeH) {
            return level[y + 1][x] == 0;
        } else {
            return false;
        }
    }

    public boolean isValidLevel() {
        return validLevel;
    }

    public int[][] getLevel() {
        return level;
    }

    public int[][] getAnswer() {
        return answer;
    }

    public Domino[] getDominoes() {
        return dominoes;
    }
}
