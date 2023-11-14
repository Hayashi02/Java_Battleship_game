package main.java;

public class App {
    public static void main(String[] args) {
        String[][] grid ={
            {"A1", "A2", "A3", "A4", "A5", "A6", "A7"},
            {"B1", "B2", "B3", "B4", "B5", "B6", "B7"},
            {"C1", "C2", "C3", "C4", "C5", "C6", "C7"},
            {"D1", "D2", "D3", "D4", "D5", "D6", "D7"},
            {"E1", "E2", "E3", "E4", "E5", "E6", "E7"},
            {"F1", "F2", "F3", "F4", "f5", "F6", "F7"},
            {"G1", "G2", "G3", "G4", "G5", "G6", "G7"}};

            char[] chararr = {'A','B','C','D','E','F','G'};
            for(int i = 0; i < grid.length; i++) {
                if(grid[i][0].equals("A1")){
                    System.out.println("      1     2     3     4     5     6     7");
                    System.out.println("    _________________________________________");
                    System.out.println("   |     |     |     |     |     |     |     |");
                }
                System.out.print(chararr[i] + "  |  ");
                for(int j = 0; j < grid[i].length; j++){
                    
                    System.out.print("O"+"  |  ");
                }
                System.out.println();
                if(i == 6){
                    System.out.println("   |_____|_____|_____|_____|_____|_____|_____|");
                }else{
                    System.out.println("   |-----|-----|-----|-----|-----|-----|-----|");
                }
    
            }
    }
}
