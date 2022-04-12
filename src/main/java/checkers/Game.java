package checkers;

public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        Menu menu = new Menu("", "");
        Cell[][] cells;
        Checker[][] checkers;
        Checker[][] actualCheckers;

        String[][] table = new String[8][8];
        Board board = new Board(table, false);

        menu.welcomeMenu();
        menu.nameMenu();
        menu.chooseOpponentMenu();
        menu.rulesMenu();

        cells = game.setCells();
        checkers = game.setCheckersOnCells(cells);
        board.setReady(true);

        actualCheckers = game.move(cells, checkers, 6, 2, 4, 4);
        System.out.println(actualCheckers[4][4].getId());

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (actualCheckers[i][n]!=null)
                    System.out.println(actualCheckers[i][n]);
            }
        }
        System.out.println();

        actualCheckers = game.move(cells, actualCheckers, 3, 5, 5, 3);
//        System.out.println(actualCheckers[5][3].getId());

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (actualCheckers[i][n]!=null)
                    System.out.println(actualCheckers[i][n]);
            }
        }

        menu.quitMenu();

    }

    public Cell[][] setCells() {
        Cell[][] cells = new Cell[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, "black", "");
            }
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 1; x < 8; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, "white", "");
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition("white");
            }
        }
        for (int y = 5; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition("black");
            }
        }
        return cells;
    }

    public Checker[][] setCheckersOnCells(Cell[][] cells) {
        Checker[][] checkers = new Checker[8][8];
        int counter = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (cells[x][y].getStartPosition().equals("white")) {
                    counter++;
                    checkers[x][y] = new Checker(counter, "white", false, false, x, y);
                    cells[x][y].setEmpty(false);
                }
            }
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (cells[x][y].getStartPosition().equals("black")) {
                    counter++;
                    checkers[x][y] = new Checker(counter, "black", false, false, x, y);
                    cells[x][y].setEmpty(false);
                }
            }
        }
        return checkers;
    }

    public Checker[][] move(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate, int newXCoordinate, int newYCoordinate) {

        Game game = new Game();
        Checker[][] actualCheckers = new Checker[8][8];

        Checker[][] temporaryCheckers = new Checker[8][8];
        int enemyCheckerCounter = 0;
        int enemyXPosition = 0;
        int enemyYPosition = 0;

        if (cells[newXCoordinate][newYCoordinate].isEmpty()) {
            if (newXCoordinate > xCoordinate && newYCoordinate > yCoordinate) {
                for (int i = xCoordinate+1; i < newXCoordinate; i++) {
                    for (int n = yCoordinate+1; n < newYCoordinate; n++) {
                        if(cells[i][n].isEmpty()) {
                        } else if (cells[i][n].isEmpty() == false){
                            if(checkers[i][n] != null){
                                if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true){
                                    System.out.println("Cannot move over your checker.");
                                } else if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == false){
                                    enemyCheckerCounter++;
                                    enemyXPosition = i;
                                    enemyYPosition = n;
                                }
                            }
                        }
                    }
                }
                if(enemyCheckerCounter == 0){
                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, checkers);
                } else if (enemyCheckerCounter == 1){
                    actualCheckers = game.elimination(enemyXPosition, enemyYPosition, cells, checkers);
//                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, temporaryCheckers);
                }
            } else if (newXCoordinate > xCoordinate && newYCoordinate < yCoordinate) {
                for (int i = xCoordinate + 1; i < newXCoordinate; i++) {
                    for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                        if(cells[i][n].isEmpty()) {
                        } else if (cells[i][n].isEmpty() == false){
                            if(checkers[i][n] != null){
                                if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true){
                                    System.out.println("Cannot move over your checker.");
                                } else if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == false){
                                    enemyCheckerCounter++;
                                    enemyXPosition = i;
                                    enemyYPosition = n;
                                }
                            }
                        }
                    }
                }
                if(enemyCheckerCounter == 0){
                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, checkers);
                } else if (enemyCheckerCounter == 1){
                    actualCheckers = game.elimination(enemyXPosition, enemyYPosition, cells, checkers);
//                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, temporaryCheckers);
                }
            }else if (newXCoordinate < xCoordinate && newYCoordinate > yCoordinate) {
                for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
                    for (int n = yCoordinate + 1; n < newYCoordinate; n++) {
                        if(cells[i][n].isEmpty()) {
                        } else if (cells[i][n].isEmpty() == false){
                            if(checkers[i][n] != null){
                                if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true){
                                    System.out.println("Cannot move over your checker.");
                                } else if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == false){
                                    enemyCheckerCounter++;
                                    enemyXPosition = i;
                                    enemyYPosition = n;
                                }
                            }
                        }
                    }
                }
                if(enemyCheckerCounter == 0){
                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, checkers);
                } else if (enemyCheckerCounter == 1){
                    checkers[enemyXPosition][yCoordinate].setEliminated(true);
                    actualCheckers = game.elimination(enemyXPosition, enemyYPosition, cells, checkers);
//                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, temporaryCheckers);
                }
            } else if (newXCoordinate < xCoordinate && newYCoordinate < yCoordinate) {
                for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
                    for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                        if(cells[i][n].isEmpty()) {
                        } else if (cells[i][n].isEmpty() == false){
                            if(checkers[i][n] != null){
                                if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true){
                                    System.out.println("Cannot move over your checker.");
                                } else if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == false){
                                    enemyCheckerCounter++;
                                    enemyXPosition = i;
                                    enemyYPosition = n;
                                }
                            }
                        }
                    }
                }
                if(enemyCheckerCounter == 0){
                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, checkers);
                } else if (enemyCheckerCounter == 1){
                    actualCheckers = game.elimination(enemyXPosition, enemyYPosition, cells, checkers);
//                    actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate, temporaryCheckers);
                }
            }
        }
        return actualCheckers;
    }

    public Checker[][] newPosition(Cell cells[][], int newXCoordinate, int newYCoordinate, int xCoordinate, int yCoordinate, Checker[][] checkers){
        Checker[][] actualCheckers = new Checker[8][8];

        int temporaryId = checkers[xCoordinate][yCoordinate].getId();
        String temporaryColor = checkers[xCoordinate][yCoordinate].getColor();
        boolean temporaryEliminated = checkers[xCoordinate][yCoordinate].isEliminated();
        boolean temporaryQueen = checkers[xCoordinate][yCoordinate].isQueen();
        Checker temporaryChecker = new Checker(temporaryId, temporaryColor, temporaryEliminated, temporaryQueen, newXCoordinate, newYCoordinate);

        cells[newXCoordinate][newYCoordinate].setEmpty(false);
        cells[xCoordinate][yCoordinate].setEmpty(true);

        actualCheckers[newXCoordinate][newYCoordinate] = temporaryChecker;

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if (checkers[x][y] != null) {
                    temporaryId = checkers[x][y].getId();
                    temporaryColor = checkers[x][y].getColor();
                    temporaryEliminated = checkers[x][y].isEliminated();
                    temporaryQueen = checkers[x][y].isQueen();
                    temporaryChecker = new Checker(temporaryId, temporaryColor, temporaryEliminated, temporaryQueen, x, y);
                    actualCheckers[x][y] = temporaryChecker;
                }
            }
        }
        return actualCheckers;
    }

    public Checker[][] elimination(int xCoordinate, int yCoordinate, Cell cells[][], Checker[][] checkers) {
        Checker[][] actualCheckers = new Checker[8][8];
        System.out.println(xCoordinate + " " + yCoordinate);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                    cells[xCoordinate][yCoordinate].setEmpty(true);
                    cells[x][y] = cells[x][y];
                    if (checkers[x][y] != null) {
                        checkers[xCoordinate][yCoordinate].setEliminated(true);
                        actualCheckers[x][y] = checkers[x][y];
                }
            }
        }
        return actualCheckers;
    }

    public void checkerScanner (Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate, int enemyCheckerCounter, int i, int n, int enemyXPosition, int enemyYPosition) {

        if(cells[i][n].isEmpty()) {
        } else if (cells[i][n].isEmpty() == false){
            if(checkers[i][n] != null){
                if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true){
                    System.out.println("Cannot move over your checker.");
                } else if(checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == false){
                    enemyCheckerCounter++;
                    enemyXPosition = i;
                    enemyYPosition = n;
                }
            }
        }
    }
}