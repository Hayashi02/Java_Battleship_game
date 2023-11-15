package main.java;
import com.Ships.*;
import java.util.*;
public class Game {
    
    String[][] grid ={
            {"A1", "A2", "A3", "A4", "A5", "A6", "A7"},
            {"B1", "B2", "B3", "B4", "B5", "B6", "B7"},
            {"C1", "C2", "C3", "C4", "C5", "C6", "C7"},
            {"D1", "D2", "D3", "D4", "D5", "D6", "D7"},
            {"E1", "E2", "E3", "E4", "E5", "E6", "E7"},
            {"F1", "F2", "F3", "F4", "f5", "F6", "F7"},
            {"G1", "G2", "G3", "G4", "G5", "G6", "G7"}};
    
    ArrayList<String> grind2 = new ArrayList<>();
    char[] chararr = {'A','B','C','D','E','F','G'};
    HashMap<String, Ship> ships = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    int playerScore = 0;
    
    public String StartGame(){
        for(int i = 0; i < grid.length; i++) {
            if(i == 0 ){
                System.out.println("       1      2      3      4      5      6      7");
                System.out.println("    ________________________________________________");
            }
            System.out.println("   |      |      |      |      |      |      |      |");
            System.out.print(chararr[i] + "  |  ");
            for(int j = 0; j < grid[i].length; j++){
                
                System.out.print(grid[i][j]+"  |  ");
            }
            System.out.println();
            System.out.println("   |______|______|______|______|______|______|______|");

        }
        createCopy();
        placeShips();

        System.out.println("You have 15 chances to destroy 3 ships.");
        for (int i = 0; i < 15; i++) {

            String in = getInput();           
            if(ships.get("battleship").containsCell(in)){
                attack("battleship", in);
            } else if(ships.get("cruiser").containsCell(in)){
                attack("cruiser", in);
            } else if(ships.get("destroyer").containsCell(in)){
                attack("destroyer", in);
            }else{
                for (int j = 0; j < grid.length; j++) {
                    for (int j2 = 0; j2 < grid[j].length; j2++) {
                        if(grid[j][j2].equals(in)){
                            altGrid('a', "O ", j, j2, grid);
                            System.out.println("Miss\n");
                        }
                    }
                }
            }

        }
        System.out.println("Total score: "+ playerScore + "\n\n");
        return "";
    }
 
    void altGrid(char toDo, String toAdd, int index1, int index2, String[][] arr){
        if(toDo == 'a'){
            arr[index1][index2] = toAdd;
        }

        for(int i = 0; i < grid.length; i++) {
            if(i == 0){
                System.out.println("      1     2     3     4     5     6     7");
                System.out.println("    _________________________________________");
                System.out.println("   |     |     |     |     |     |     |     |");
            }
            System.out.print(chararr[i] + "  |  ");
            for(int j = 0; j < grid[i].length; j++){
                
                System.out.print(grid[i][j]+" |  ");
            }
            System.out.println();
            if(i == 6){
                System.out.println("   |_____|_____|_____|_____|_____|_____|_____|");
            }else{
                System.out.println("   |-----|-----|-----|-----|-----|-----|-----|");
            }

        }
    }

    private void attack(String key, String in){
        String result = ships.get(key).cellStatus(in);
        if(result.equals("Hit!")){
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if(grid[i][j].equals(in)){
                        altGrid('a', "X ", i, j, grid);
                        System.out.println("\nHit!");
                        if(ships.get(key).isDestroyed()){
                            System.out.println(key + " is destroyed!!!\n");
                            if(key.equals("destroyer")) playerScore += 2;
                            else if(key.equals("cruiser")) playerScore += 3;
                            else if(key.equals("battleship")) playerScore += 5;
                        }
                    }
                }
            }
        } else if(result.equals("Miss")){
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if(grid[i][j].equals(in)){
                        altGrid('a', "O", i, j, grid);
                        System.out.println("\nMiss");
                    }
                }
            }
        }
        
    }
    
    public void placeShips(){
        Random random = new Random();
        boolean[][] tempGrid = new boolean[7][7];

        //horizontal battleship
        String key = "battleship";
        int shipRow = random.nextInt(7);
        int shipCol = random.nextInt(6) + 1;

        if(shipCol < 3) shipCol++;
        else if(shipCol > 5) shipCol--;

        ships.put(key, new Ship());
        addCell(key, shipRow, shipCol - 2);
        tempGrid[shipRow][shipCol - 2] = true;
        addCell(key, shipRow, shipCol - 1);
        tempGrid[shipRow][shipCol - 1] = true;
        addCell(key, shipRow, shipCol);
        tempGrid[shipRow][shipCol] = true;
        addCell(key, shipRow, shipCol + 1);
        tempGrid[shipRow][shipCol + 1] = true;

        //vertical cruiser
        key = "cruiser";
        boolean x = false;
        do{
            shipRow = random.nextInt(6) + 1;
            shipCol = random.nextInt(6);
            if (checkSpots(tempGrid, shipCol, shipRow - 1, shipRow, shipRow + 1)) {
                ships.put(key, new Ship());
                addCell(key, shipRow - 1, shipCol);
                tempGrid[shipRow - 1][shipCol] = true;
                addCell(key, shipRow, shipCol);
                tempGrid[shipRow][shipCol] = true;
                addCell(key, shipRow + 1, shipCol);
                tempGrid[shipRow + 1][shipCol] = true;

                x = false;
            } else {
                x = true;
            }
        } while(x);

        //horizontal destroyer
        key = "destroyer";
        shipRow = random.nextInt(7);
        shipCol = random.nextInt(6);

        do {
            if(!tempGrid[shipRow][shipRow] && !tempGrid[shipRow][shipCol + 1]){
                ships.put(key, new Ship());
                addCell(key, shipRow, shipCol);
                addCell(key, shipRow, shipCol + 1);
                //temp check
                tempGrid[shipRow][shipCol] = true;
                tempGrid[shipRow][shipCol + 1] = true;
                x = false;
            } else{
                shipRow = random.nextInt(7);
                shipCol = random.nextInt(7);
                x = true;
            }
            
        } while (x);
    }

    private void addCell(String key, int row, int col){
        ships.get(key).addCell(grid[row][col]);
    }
    
    private static boolean checkSpots(boolean[][] tempGrid, int col, int... rows) {
        for (int row : rows) {
            if (tempGrid[row][col]) {
                return false;
            }
        }
        return true;
    }

    public boolean inputValidator(String in){
        if(in.length() > 2) return false;
        if(grind2.contains(in)) return true;
        return false;
    }

    private void createCopy(){
        for (int index = 0; index < grid.length; index++) {
            for (int index2 = 0; index2 < grid[index].length; index2++) {
                grind2.add(grid[index][index2]);
            }
        }
    }

    private String getInput(){
        System.out.println("Enter a cell to attack (e.g, A1): ");
        String in = scanner.nextLine().toUpperCase().trim();
        if(inputValidator(in)){
            return in;
        } else{
            System.out.println("Enter a valid index!");
            return getInput();
        }
    }
}