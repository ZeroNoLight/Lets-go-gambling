import java.util.Scanner;
import java.util.Random;

public class Gambling{
    private static Scanner scanner = new Scanner(System.in);
    static void gamble(){

        // Slot machine
        int balance = 100;
        int bet;
        int payout;
        String[] row;
        String playAgain;

        System.out.println("***********************");
        System.out.println("Welcome to java slots");
        System.out.println("Symbols: 🍒🍉🍋🔔⭐");
        System.out.println("***********************");

        while(balance>0){
            System.out.println("Current balance: $"+balance);
            System.out.println("Place your bet amount: ");
            bet = scanner.nextInt();
            scanner.nextLine();

            if(bet>balance){
                System.out.println("INSUFFICIENT FUNDS");
                continue;
            }
            else if(bet<=0){
                System.out.println("Bet must be greater than 0");
                continue;
            } else{
                balance-=bet;
            }

            System.out.println("Spinning...");
            row = spinRow();
            printRow(row);
            payout = getPayout(row,bet);

            if(payout>0){
                System.out.println("*********************");
                System.out.println("🔥🔥🔥"+"You won $"+payout+"🔥🔥🔥");
                System.out.println("*********************");
                balance+=payout;
            }
            else{
                System.out.println("Sorry you lost this round");
            }

            
            
        }
        
        loseMessage();
        

    }

    static String[] spinRow(){


        String[] symbols = {"🍒","🍉","🍋","🔔","⭐"};
        String[] row = new String[3];
        Random random = new Random();


        for(int i=0;i<3;i++){
            row[i]=symbols[random.nextInt(symbols.length)];
        }
        return row;
    }
    static void printRow(String[] row){
        System.out.println("****************");
        System.out.println(" "+String.join(" | ",row));
        System.out.println("****************");
    }
    static int getPayout(String[]row, int bet){

        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
                case "🍒" -> bet*3;
                case "🍉" -> bet*4;
                case "🍋" -> bet*5;
                case "🔔" -> bet*10;
                case "⭐" -> bet*15;
                default->0;
            };
        
        }

        return 0;
    }
    static void loseMessage(){
        String playAgain;
        System.out.print("🔥🔥🔥Do you want to play again and pay all your debts?🔥🔥🔥 (Y/N)");
        playAgain = scanner.nextLine().toUpperCase();
        if(playAgain.equalsIgnoreCase("N")){
            System.out.print("🔥🔥🔥Do you want to pay your mortgage?🔥🔥🔥 (Y/N)");
            playAgain = scanner.nextLine().toUpperCase();
        }
        

        if(playAgain.equals("Y")){
            gamble();
        }
    }
}