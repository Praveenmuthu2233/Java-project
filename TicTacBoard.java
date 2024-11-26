import java.util.*;

public class TicTacBoard {

    public static String ticTacBoard[][] = {
        {"00","01","02"},
        {"10","11","12"},
        {"20","21","22"}
    };
    public static int playerCount = 0;

    public static void print() {
        for (int i = 0;i<ticTacBoard.length;i++){
            for (int j=0;j<ticTacBoard[i].length;j++){
                System.out.print(ticTacBoard[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void greetingMsg(Scanner sc) {
        int x = 0;
        int y = 0;
        System.out.println(playerCount%2 == 0?"Player X":"Player O");
        System.out.println("Enter X value : ");
        x = sc.nextInt();
        System.out.println("Enter Y value : ");
        y = sc.nextInt();
        System.out.println(insert(x, y, playerCount%2 == 0?"X":"O"));
    }

    public static String insert(int x, int y, String playerName) {
        if((x < 3 && x >= 0) &&(y < 3 && y >= 0)){
            if(!ticTacBoard[x][y].equals("X") && !ticTacBoard[x][y].equals("O")){
                ticTacBoard[x][y] = playerName;
                print();
                playerCount++;
                if(cheak()){
                    System.out.println("!---------------!");
                    System.out.println(playerName + "Wins!!!!");
                    System.out.println("!---------------!");
                    System.exit(0);
                }else if(playerCount == 9){
                    System.out.println("!---------------!");
                    System.out.println("It's a Draw!");
                    System.out.println("!---------------!");
                    System.exit(0);
                }
                return "Inserted";
            }else{
                return "Already Occupied";
            }
        }
        return "Unexpected Place";
    }

    public static boolean cheak(){
        for(int i=0;i<3;i++){
            if(ticTacBoard[i][0].equals(ticTacBoard[i][1])
            && ticTacBoard[i][1].equals(ticTacBoard[i][2])
            && !ticTacBoard[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(ticTacBoard[0][i].equals(ticTacBoard[1][i])
            && ticTacBoard[1][i].equals(ticTacBoard[2][i])
            && !ticTacBoard[0][1].equals("")){
                return true;
            }
        }
        
        if(ticTacBoard[0][2].equals(ticTacBoard[1][1])
        && ticTacBoard[1][1].equals(ticTacBoard[2][0])
        && !ticTacBoard[0][2].equals("")){
            return true;
        }

        if(ticTacBoard[0][0].equals(ticTacBoard[1][1])
        && ticTacBoard[1][1].equals(ticTacBoard[2][2])
        && !ticTacBoard[0][0].equals("")){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            greetingMsg(sc);
        }
    }
}
