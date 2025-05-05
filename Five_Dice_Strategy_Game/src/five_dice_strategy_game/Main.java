// Alam Rincon
package five_dice_strategy_game;
// Importing relevant libraries
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Initialising the game
        gameInitialisation();
    }
    
    public static void gameInitialisation() {
        //Starting or leaving the game
        //Creating the board if started
        Scanner input = new Scanner(System.in);
        System.out.println("Enter (1) to start the game, (0) to leave: ");
        String startGame = input.nextLine();
        if ("0".equals(startGame)) {
            System.out.println("Terminating the game");
        } else if ("1".equals(startGame)) {
            Player ONE = new Player();
            Player TWO = new Player();
            String pressedSelection = "e";
            int initSelection = 0;
            int initDiceArraySum = 0;
            int initPlayer = 0;
            int initComboSelected = 0;
            System.out.println("Game Initialised");
            fiveDiceStrategyBoard(initPlayer, ONE, TWO, initDiceArraySum, initSelection);
            gameRoundAndTurn(initPlayer, ONE, TWO, pressedSelection, initComboSelected);
        } else {
            System.out.println("Enter the right input");
            gameInitialisation();
        }
    }
    
    public static void fiveDiceStrategyBoard(int player, Player ONE, Player TWO, int selectedCombo, int diceSumTransfer) {
        //Initialise players board
        System.out.printf("--------------------------------------%n");
        System.out.printf("||||%-26s||||\n", "\t  Five Dice Strategy");
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%-10s||%-10s|\n", "Combinations", "Player One", "Player Two");
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Ones", ONE.boardValues[0], TWO.boardValues[0]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Twos", ONE.boardValues[1], TWO.boardValues[1]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Threes", ONE.boardValues[2], TWO.boardValues[2]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Fours", ONE.boardValues[3], TWO.boardValues[3]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Fives", ONE.boardValues[4], TWO.boardValues[4]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Sixes", ONE.boardValues[5], TWO.boardValues[5]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Sequence", ONE.boardValues[6], TWO.boardValues[6]);
        System.out.printf("--------------------------------------%n");
        System.out.printf("|%-12s||%10d||%10d|\n", "Total", ONE.boardValues[7], TWO.boardValues[7]);
        System.out.printf("--------------------------------------%n");
        //If any player reaches the maximum score, it will announce the winner and end the game.
        if (ONE.boardValues[7] == 125) {
            System.out.println("PLAYER ONE WINS. YOU'RE LUCKY, YOU GOT THE HIGHEST SCORE.");
            System.exit(1);
        } else if (TWO.boardValues[7] == 125) {
            System.out.println("PLAYER WINS. YOU'RE LUCKY, YOU GOT THE HIGHEST SCORE.");
            System.exit(1);
        }
    }
    
    public static void gameRoundAndTurn(int player, Player ONE, Player TWO, String pressedSelection, int intComboSelected) {
        //Loop through the rounds and the player's turns. The player value will be always passed.
        for (int i = 0; i < 7; i++) {
            System.out.printf("---------------%n");
            System.out.printf("|||%-8s%s|||\n", "Round", i + 1);
            System.out.printf("---------------%n");
            for (int j = 0; j < 2; j++) {
                switch (j) {
                    case 0:
                        player = 1;
                        System.out.printf("||%-10s||\n", "Player One");
                        turnThrow(player, ONE, TWO, pressedSelection, intComboSelected);
                        break;
                    case 1:
                        player = 2;
                        System.out.printf("||%-10s||\n", "Player Two");
                        turnThrow(player, ONE, TWO, pressedSelection, intComboSelected);
                        break;
                }
            }
        }
        //At the end of the loop, it will check for the winner according to the scores achieved by each player.
        if (ONE.boardValues[7] == TWO.boardValues[7]) {
            System.out.println("TIE");
        } else if (ONE.boardValues[7] > TWO.boardValues[7]) {
            System.out.println("PLAYER ONE WINS.");
        } else if (ONE.boardValues[7] < TWO.boardValues[7]) {
            System.out.println("PLAYER TWO WINS.");
        }
    }
    
    public static void turnThrow(int player, Player ONE, Player TWO, String pressedSelection, int intComboSelected) {
        //Give an initial option to throw the dices or forfeit the turn of the player
        Scanner input = new Scanner(System.in);
        System.out.println("Enter (t) to throw the dice or (f) to forfeit: ");
        String diceThrow = input.nextLine();
        if ("t".equals(diceThrow)) {
            //Create a throw of five dices with random values
            int diceArray[] = {(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};
            throwDice(player, ONE, TWO, diceArray, 1, pressedSelection, intComboSelected);
        } else if ("f".equals(diceThrow)) {
            //After each turn the game board will be printed
            int noSum = 0;
            int noSelect = 0;
            int voidPlayer = 0;
            System.out.println("You forfeit your turn");
            fiveDiceStrategyBoard(voidPlayer, ONE, TWO, noSelect, noSum);
        } else {
            System.out.println("Enter the right input.");
            turnThrow(player, ONE, TWO, pressedSelection, intComboSelected);
        }
    }

    public static void throwDice(int player, Player ONE, Player TWO, int[] dice, int throwCount, String pressedSelection, int intComboSelected) {
        //Function will be initialised with 0 values to after be replaced with the values of new dices
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            //If the throw has dices passed with 0 values it will assign random values to those
            if (dice[i] == 0) {
                dice[i] = (int) (Math.random() * 6 + 1);
            }
        }
        if (throwCount < 3) {
            System.out.println("Throw " + throwCount);
            for (int i = 0; i < 5; i++) {
                System.out.print("[" + dice[i] + "]");
            }
            //Checks for any previous selection made in previous turns unless is the first one
            if ("e".equals(pressedSelection)) {
                //Displays options available if the user hasn't selected any combo. It also gives the opportunity to defer the dices
                System.out.println("\tPress (s) to select a combo or (d) to defer");
                String selection = input.nextLine();
                if ("s".equals(selection)) {
                    diceCombo(player, ONE, TWO, dice, throwCount++, pressedSelection);
                } else if ("d".equals(selection)) {
                    deferDice(player, ONE, TWO, throwCount, pressedSelection, intComboSelected);
                } else {
                    System.out.println("Please enter the right input");
                    throwDice(player, ONE, TWO, dice, throwCount, pressedSelection, intComboSelected);
                }
            } else if ("s".equals(pressedSelection)) {
                //If a combo was selected, the option to continue with your throw or to defer the dices, will be available
                System.out.println("\tPress (c) to continue or (d) to defer");
                String selection2 = input.nextLine();
                if ("c".equals(selection2)) {
                    System.out.println("You maintained your dice combo");
                    int[] diceSaved;
                    diceSaved = new int[5];
                    throwCount++;
                    newDiceCheck(player, ONE, TWO, dice, diceSaved, throwCount, intComboSelected, pressedSelection);
                } else if ("d".equals(selection2)) {
                    clearDeferredCombo(player, ONE, TWO, intComboSelected);
                    deferDice(player, ONE, TWO, throwCount, pressedSelection, intComboSelected);
                } else {
                    System.out.println("Please enter the right input");
                    throwDice(player, ONE, TWO, dice, throwCount, pressedSelection, intComboSelected);
                }
            }
        } else if (throwCount == 3) {
            //For the last throw it will check if a combo was selected before and continue to save score achieved. If not, it will force the user to choose a combo to continue.
            System.out.println("Throw " + throwCount);
            for (int i = 0; i < 5; i++) {
                System.out.print("[" + dice[i] + "]");
            }
            if ("e".equals(pressedSelection)) {
                System.out.println();
                diceCombo(player, ONE, TWO, dice, throwCount++, pressedSelection);
            } else if ("s".equals(pressedSelection)) {
                System.out.println();
                int[] diceSaved;
                diceSaved = new int[5];
                lastDiceCheck(player, ONE, TWO, dice, diceSaved, intComboSelected);
            }
        }
    }

    public static void clearDeferredCombo(int player, Player ONE, Player TWO, int intComboSelected) {
        /*This function will make sure that if the dices were deferred and a combo was selected, the combo will be eliminated from the players records,
        allowing the user to use the combo later on*/
        if (player == 1) {
            for (int i = 0; i < 7; i++) {
                if (ONE.comboSelection[i] == intComboSelected) {
                    ONE.comboSelection[i] = 0;
                }
            }
        } else if (player == 2) {
            for (int i = 0; i < 7; i++) {
                if (TWO.comboSelection[i] == intComboSelected) {
                    TWO.comboSelection[i] = 0;
                }
            }
        }
    }
    
    public static void deferDice(int player, Player ONE, Player TWO, int throwCount, String pressedSelection, int intComboSelected) {
        //If the dices are deferred, it will bring a new throw, continue the count and let the program know that no selection has been made.
        System.out.println("You deferred your throw");
        pressedSelection = "e";
        int newDiceArray[] = {(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};
        throwCount++;
        throwDice(player, ONE, TWO, newDiceArray, throwCount, pressedSelection, intComboSelected);
    }

    public static void diceCombo(int player, Player ONE, Player TWO, int[] sameDice, int continueCount, String pressedSelection) {
        //Shows the combo combinations that the user could select in each turn or throw
        Scanner input = new Scanner(System.in);
        System.out.println(" Combos:\t ONES(1)\t TWOS(2)\t THREES(3)\t FOURS(4)\t FIVES(5)\t SIXES(6)\t SEQUENCES(7)");
        String comboSelected = input.nextLine();
        pressedSelection = "s";
        //Starts a new empty array to after store the new values of the dices
        int diceSaved[];
        diceSaved = new int[5];
        if (continueCount < 3) {
            //Checks the input of the user and if it matches with an combo that hasn't been used, it will allow the user to continue
            if ("1".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 1, pressedSelection);
            } else if ("2".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 2, pressedSelection);
            } else if ("3".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 3, pressedSelection);
            } else if ("4".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 4, pressedSelection);
            } else if ("5".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 5, pressedSelection);
            } else if ("6".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 6, pressedSelection);
            } else if ("7".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                continueCount++;
                newDiceCheck(player, ONE, TWO, sameDice, diceSaved, continueCount, 7, pressedSelection);
            } else {
                System.out.println("Enter the right option");
                diceCombo(player, ONE, TWO, sameDice, continueCount, pressedSelection);
            }
        } else if (continueCount == 3) {
            //If it is the last round, it will execute a function to calculate the last dice array that will be multiplied and added to the board
            if ("1".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 1);
            } else if ("2".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 2);
            } else if ("3".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 3);
            } else if ("4".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 4);
            } else if ("5".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 5);
            } else if ("6".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 6);
            } else if ("7".equals(comboSelected) && comboAllowanceCheck(player, ONE, TWO, sameDice, continueCount, pressedSelection, comboSelected)) {
                lastDiceCheck(player, ONE, TWO, sameDice, diceSaved, 7);
            } else {
                System.out.println("Enter the right option");
                diceCombo(player, ONE, TWO, sameDice, continueCount, pressedSelection);
            }
        }
    }

    public static boolean comboAllowanceCheck(int player, Player ONE, Player TWO, int[] sameDice, int continueCount, String pressedSelection, String comboSelected) {
        /*Checks for the combo selections made in every turn by each user, if a selection hasn't been used, it will allow the user to select that combo and store 
        it in the player's records*/
        boolean isCorrect = true;
        int intComboSelected = Integer.parseInt(comboSelected);
        if (player == 1) {
            for (int i = 0; i < 7; i++) {
                if (intComboSelected == ONE.comboSelection[i]) {
                    System.out.println("This combo has been selected already, try another one.");
                    isCorrect = false;
                }
            }
            if (isCorrect) {
                ONE.comboSelection[intComboSelected - 1] = intComboSelected;
            }
        } else if (player == 2) {
            for (int i = 0; i < 7; i++) {
                if (intComboSelected == TWO.comboSelection[i]) {
                    System.out.println("This combo has been selected already, try another one.");
                    isCorrect = false;
                }
            }
            if (isCorrect) {
                TWO.comboSelection[intComboSelected - 1] = intComboSelected;
            }
        }
        return isCorrect;
    }

    public static void newDiceCheck(int player, Player ONE, Player TWO, int[] oldDice, int[] diceSaved, int continueCount, int intComboSelected, String pressedSelection) {
        /*Check the dice result for round 1 and round 2, starts another throw function and passes the count to go throw the next turn of the player.
        For the sequence, implements all possible checks and if no sequence has been achieved, the user will be allowed to throw selected dices*/
        if (intComboSelected == 7) {
            System.arraycopy(oldDice, 0, diceSaved, 0, 5);
            if (sequenceCheck(oldDice)) {
                System.out.println("You have a sequence, I'll recommend you to keep it");
                throwDice(player, ONE, TWO, oldDice, continueCount, pressedSelection, intComboSelected);
            } else {
                System.out.println("No Sequence.\nPlease select the dices you would like to hold. e.g.'1 2 4 5'"
                        + "\nYou will have to hold at least one dice to continue.");
                System.arraycopy(modifyDice(diceSaved), 0, diceSaved, 0, 5);
                throwDice(player, ONE, TWO, diceSaved, continueCount, pressedSelection, intComboSelected);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (oldDice[i] == intComboSelected) {
                    diceSaved[i] = oldDice[i];
                }
            }
            throwDice(player, ONE, TWO, diceSaved, continueCount, pressedSelection, intComboSelected);
        }
    }

    public static boolean sequenceCheck(int[] sameDice) {
        //Checks that no possible sequence is achieved, if a sequence is achived it will return true
        boolean isSequence1 = true;
        boolean isSequence2 = true;
        Arrays.sort(sameDice);
        if (!seqOneToFive(sameDice)) {
            System.out.println("No 1 to 5 Sequence");
            isSequence1 = false;
        }
        if (!seqTwoToSix(sameDice)) {
            System.out.println("No 2 to 6 Sequence");
            isSequence2 = false;
        }
        return isSequence1 || isSequence2;
    }

    public static boolean seqOneToFive(int[] sameDice) {
        //Checks the throw for a 1-5 dice sequence
        boolean checkSeq1 = true;
        int oneToFive[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < 5; i++) {
            if (oneToFive[i] != sameDice[i]) {
                checkSeq1 = false;
            }
        }
        if (checkSeq1) {
            System.out.println("You have a 1 to 5 Sequence");
        }
        return checkSeq1;
    }

    public static boolean seqTwoToSix(int[] sameDice) {
        //Checks the throw for a 2-6 dice sequence
        boolean checkSeq2 = true;
        int twoToSix[] = {2, 3, 4, 5, 6};
        for (int i = 0; i < 5; i++) {
            if (twoToSix[i] != sameDice[i]) {
                checkSeq2 = false;
            }
        }
        if (checkSeq2) {
            System.out.println("You have a 2 to 6 Sequence");
        }
        return checkSeq2;
    }

    public static int[] modifyDice(int[] diceToModify) {
        //Allows the user when the sequence is selected, to modify the throw by selecting the dices that will be replaced afterwards by a random dice;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + ". [" + diceToModify[i] + "]");
        }
        int[] diceToReturn;
        diceToReturn = new int[5];
        boolean correctInput = false;
        while (!correctInput) {
            String[] valuesForChange = input.nextLine().trim().split(" ");
            if (checkAllInt(valuesForChange)) {
                for (int i = 0; i < valuesForChange.length; i++) {
                    switch (Integer.parseInt(valuesForChange[i])) {
                        case 1:
                            diceToReturn[0] = diceToModify[0];
                            break;
                        case 2:
                            diceToReturn[1] = diceToModify[1];
                            break;
                        case 3:
                            diceToReturn[2] = diceToModify[2];
                            break;
                        case 4:
                            diceToReturn[3] = diceToModify[3];
                            break;
                        case 5:
                            diceToReturn[4] = diceToModify[4];
                            break;
                        default:
                            break;
                    }
                }
                correctInput = true;
            } else {
                System.out.println("Wrong input, do it again.");
            }
        }
        return diceToReturn;
    }

    public static boolean checkAllInt(String[] valuesForChange) {
        //Checks for any undesire irregularities in the input for the sequence modification and limits the user's inputs. 
        boolean isChecked = true;
        
        for (int i = 0; i < valuesForChange.length; i++) {
            if (!(valuesForChange.length > 0 && valuesForChange.length < 6)) {
                isChecked = false;
                break;
            }
            if(valuesForChange[i].length() <= 0){
                isChecked = false;
                break;
            }
            if (!Character.isDigit(valuesForChange[i].charAt(0))) {
                isChecked = false;
                break;
            }
            if (!(Integer.parseInt(valuesForChange[i]) > 0 && Integer.parseInt(valuesForChange[i]) < 6)) {
                isChecked = false;
                break;
            }
        }
        return isChecked;
    }

    public static void lastDiceCheck(int player, Player ONE, Player TWO, int[] oldDice, int[] lastDiceSaved, int x) {
        //Checks for the last dice and places the values selected into a new value to be calculated 
        if (x == 7) {
            System.arraycopy(oldDice, 0, lastDiceSaved, 0, 5);
            calculateSumOfAll(player, ONE, TWO, lastDiceSaved, x);
        } else {
            for (int i = 0; i < 5; i++) {
                if (oldDice[i] == x) {
                    lastDiceSaved[i] = oldDice[i];
                }
            }
            calculateSumOfAll(player, ONE, TWO, lastDiceSaved, x);
        }
    }

    public static void calculateSumOfAll(int player, Player ONE, Player TWO, int[] lastDice, int x) {
        //Calculates all the values for the combo selected by the player and brings the results to the strategy board
        int value = 0;
        //Different approach to the calculation if is a sequence
        if (x == 7) {
            if (sequenceCheck(lastDice)) {
                System.out.println("You have a sequence, well done.");
                value = 20;
                updateBoard(player, ONE, TWO, x, value);
            } else {
                System.out.println("No Sequence at all. Good luck next game.");
                updateBoard(player, ONE, TWO, x, value);
            }
        } else {
            for (int j = 0; j < 5; j++) {
                if (lastDice[j] != 0) {
                    value = value + lastDice[j];
                }
            }
            updateBoard(player, ONE, TWO, x, value);
        }
    }

    public static void updateBoard(int player, Player ONE, Player TWO, int x, int value) {
        System.out.println("Turn is over");
        //The following code will be to allocate the sum of all the array achieved by the user into the board and calculate the total of the player
        if (player == 1) {
            ONE.boardValues[x - 1] = value;
            ONE.boardValues[7] = ONE.boardValues[0] + ONE.boardValues[1] + ONE.boardValues[2] + ONE.boardValues[3] + ONE.boardValues[4] + ONE.boardValues[5] + ONE.boardValues[6];
        } else if (player == 2) {
            TWO.boardValues[x - 1] = value;
            TWO.boardValues[7] = TWO.boardValues[0] + TWO.boardValues[1] + TWO.boardValues[2] + TWO.boardValues[3] + TWO.boardValues[4] + TWO.boardValues[5] + TWO.boardValues[6];
        }
        //Calls back the board with the updated value
        fiveDiceStrategyBoard(player, ONE, TWO, x, value);
    }
}

