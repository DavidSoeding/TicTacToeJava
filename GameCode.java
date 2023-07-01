import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class GameCode {
  public static void main(String[] args) {
    
    //benötigte Variablen werden erstellt
    char[] [] tutorial = {
      {' ', '1', ' ', '│', ' ', '2', ' ', '│', ' ', '3', ' '},
      {'─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─'},
      {' ', '4', ' ', '│', ' ', '5', ' ', '│', ' ', '6', ' '},
      {'─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─'},
      {' ', '7', ' ', '│', ' ', '8', ' ', '│', ' ', '9', ' '}
      };
    char[] [] spielfeld = {
      {' ', ' ', ' ', '│', ' ', ' ', ' ', '│', ' ', ' ', ' '},
      {'─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─'},
      {' ', ' ', ' ', '│', ' ', ' ', ' ', '│', ' ', ' ', ' '},
      {'─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─'},
      {' ', ' ', ' ', '│', ' ', ' ', ' ', '│', ' ', ' ', ' '}
      };
    
    ArrayList<Integer> spielerWinCheck = new ArrayList<Integer>();
    ArrayList<Integer> computerWinCheck = new ArrayList<Integer>();
    
    //Spiel wird gestartet, Anweisung wird geprintet
    System.out.println("Das Tic Tac Toe Spiel hat begonnen. Der Computer spielt O, Sie spielen X. Geben Sie Ihren Zug ein.");
    printSpielfeld(tutorial);
    
    //Endlosschleife wird gestartet
    while(true) {
      //Input von Spieler wird angefragt
      Scanner scan = new Scanner(System.in);
      int zug = scan.nextInt();
      
      //überprüfen, ob gewählter zug gültig ist
      if (spielerWinCheck.contains(zug) || computerWinCheck.contains(zug) || zug > 9) {
        System.out.println("Eingabe ungültig. Bitte versuchen sie es erneut.");
      }
      else {
        //abspeichern des zuges
        spielerWinCheck.add(zug);
        //zug machen
        zugMachen(spielfeld, zug, "spieler");
        //neues spielfend printen
        printSpielfeld(spielfeld);
        
        //überprüfen, ob das spiel gewonnen ist oder unentschieden. Entsprechende Nachricht wird ausgegeben
        if (checkForWin(spielerWinCheck)) {
          System.out.println("Sie haben gewonnen.");
        } else if (computerWinCheck.size() + spielerWinCheck.size() == 9) {
          System.out.println("Es ist unendschieden.");
        } else {
          //Spieler hat jetzt einen Zug gemacht. Jetzt macht der Computer zufällig einen Zug
          Random rand = new Random();
          int computerZug = rand.nextInt(9) + 1;
          
          //überprüfen, ob Zug des Computers gültig ist, wenn nicht dann wird erneut "gewürfelt"
          while (spielerWinCheck.contains(computerZug) || computerWinCheck.contains(computerZug)) {
            computerZug = rand.nextInt(9) + 1;
          }
          //zug wird abgespeichert
          computerWinCheck.add(computerZug);
          //zug wird gemacht
          zugMachen(spielfeld, computerZug, "computer");
          //neues spielfeld wird geprintet
          printSpielfeld(spielfeld);
          //überprüfen, ob das spiel gewonnen wurde oder unentschieden ist. Entsprechende Nachricht wird ausgegeben
          if (checkForWin(computerWinCheck)) {
            System.out.println("Der Computer hat gewonnen");
          } else if (computerWinCheck.size() + spielerWinCheck.size() == 9) {
            System.out.println("Es ist unendschieden.");
          }
        }
      }
    }
  }
  
  //methode, die als Parameter einen 2D char array nimmt und printet
  public static void printSpielfeld(char [][] spielfeld) {
    //2d char array wird in mehrere char arrays umgewandelt (pro zeile)
    for(char[] reihe : spielfeld) {
      for(char c : reihe) {
        //chars von array werden einzeln geprintet
        System.out.print(c);
      }
      //nach erster zeile Zeilenumbruch
      System.out.println();
    }
    //zeilenumbruch für abstand hinter dem spielfeld
    System.out.println();
  }
  
  //methoode die als Parameter das Spielfend, einen Int als Zug und einen String als spieler (also computer oder spieler) nimmt
  public static void zugMachen(char [][] spielfeldTemp, int zug, String spieler) {
    //neue variable, die Symbol speichert
    char spielSymbol = ' ';
    //symbol wird entprechend des spielers festgelegt
    if (spieler == "spieler") {
      spielSymbol = 'X';
    } else {
      spielSymbol = 'O';
    }
    
//mit mathematik (schwierig): 
    //ausrechnen, welcher Zug(Zahl von 1-9) mit welchen Coordinaten des 2d arrays übereinstimmt
    //x koordinate ausrechnen
    int x = 4 * ((zug - 1) % 3) + 1;
    //y koordinate ausrechnen
    int y = 2 * (int) Math.floor((zug - 1) / 3);
    
    spielfeldTemp[y][x] = spielSymbol;

    
//oder mit switch case (leicht):    
    
//    switch(zug) {
//      case 1:
//        spielfeldTemp[0][1] = spielSymbol;
//        break;
//      case 2:
//        spielfeldTemp[0][5] = spielSymbol;
//        break;
//      case 3:
//        spielfeldTemp[0][9] = spielSymbol;
//        break;
//      case 4:
//        spielfeldTemp[2][1] = spielSymbol;
//        break;
//      case 5:
//        spielfeldTemp[2][5] = spielSymbol;
//        break;
//      case 6:
//        spielfeldTemp[2][9] = spielSymbol;
//        break;
//      case 7:
//        spielfeldTemp[4][1] = spielSymbol;
//        break;
//      case 8:
//        spielfeldTemp[4][5] = spielSymbol;
//        break;
//      case 9:
//        spielfeldTemp[4][9] = spielSymbol;
//        break;
//      default:
//        break;
//    }
  }
  //hier wird ein boolean ausgerechnet, der als Parameter eine Array List benutzt)
  public static boolean checkForWin(ArrayList<Integer> winCheck) {
    
    //hier wird mit einer einfachen else if folge geschaut ob eine der 8 Gewinn-Möglichkeiten erfüllt ist
    if (winCheck.contains(1) && winCheck.contains(2) && winCheck.contains(3)) {
      return true;
    } else if (winCheck.contains(4) && winCheck.contains(5) && winCheck.contains(6)) {
      return true;
    } else if (winCheck.contains(7) && winCheck.contains(8) && winCheck.contains(9)) {
      return true;
    } else if (winCheck.contains(1) && winCheck.contains(4) && winCheck.contains(7)) {
      return true;
    } else if (winCheck.contains(2) && winCheck.contains(5) && winCheck.contains(8)) {
      return true;
    } else if (winCheck.contains(3) && winCheck.contains(6) && winCheck.contains(9)) {
      return true;
    } else if (winCheck.contains(1) && winCheck.contains(5) && winCheck.contains(9)) {
      return true;
    } else if (winCheck.contains(3) && winCheck.contains(5) && winCheck.contains(7)) {
      return true;
    }
    return false;
  }
}