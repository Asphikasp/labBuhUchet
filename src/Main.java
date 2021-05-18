import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("ZA WARUDO");
        System.out.println("OROROROROROROOROROOROROOROROOROROO");

        /*
        createAccount("Vodka", accounts);
        addLineToAccount("Vodka","Purchasing", "44","0", accounts);
        addLineToAccount("Vodka","Selling back", "0","150", accounts);


        createAccount("Tequila",accounts);
        addLineToAccount("Tequila","Purchasing","35","0", accounts);
        addLineToAccount("Tequila","Selling back","0","240", accounts);

        printAccountList(accounts);

        printAccount("Vodka",accounts);
        printAccount("Tequila",accounts);
        */

        // Создание сканера
        Scanner scanner = new Scanner(System.in);

        // Добавление строковых переменных
        String enterText;
        ArrayList<String> myText = new ArrayList<>();
        ArrayList<String> transactionsLog = new ArrayList<>();
        ArrayList<String> transactionLog = new ArrayList<>();


        // Индикатор выхода
        boolean exit = false;
        boolean open_transaction_exit = false;
        boolean clommitExit;

        // Оповещение о старте
        System.out.println("User interface start\n");

        do {
            enterText = scanner.nextLine();
            myText.clear();
            myText.addAll(Arrays.asList(enterText.split(" ")));
            switch (myText.get(0)) {
                case "help":
                    System.out.println(
                            """
                            help - shows this list
                            show - show all accounts
                            show [name] - shows this account
                            createAccount [name] - creates account
                            openTransaction - opens transaction
                            transaction [accountName] [transactionName] [debit] [credit] - adds new transaction line to account
                            showTransactions - show current transactions
                            closeTransaction - closes transaction
                            exit - exit program
                            """
                    );
                    break;
                case "show":
                    try {
                        printAccount(myText.get(1), accounts);
                    } catch (IndexOutOfBoundsException e) {
                        printAccountList(accounts);
                    }
                    break;
                case "createAccount":
                case "crAc":
                    try {
                        createAccount(myText.get(1), accounts);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "openTransaction":
                    if(!open_transaction_exit) {
                        open_transaction_exit = true;
                        System.out.println("Transaction has been opened");
                        transactionsLog.clear();
                    } else {
                        System.out.println("Transaction is already opened");
                    }
                    break;
                case "transaction":
                case "tran":
                    try {
                        if(open_transaction_exit) {
                            transactionsLog.add(myText.get(1) + " " + myText.get(2) + " " +myText.get(3) + " " + myText.get(4));
                        } else {
                            System.out.println("Transaction is closed");
                            break;
                        }
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("Wrong syntax try use: transaction [accountName] [transactionName] [debit] [credit]");
                    }
                    break;
                case "showTransactions":
                    for(String transaction : transactionsLog){
                        transactionLog.clear();
                        transactionLog.addAll(Arrays.asList(transaction.split(" ")));
                        System.out.println(
                                "New line in " + transactionLog.get(0) + "\n"
                                + transactionLog.get(1) + " " + transactionLog.get(2) + " " + transactionLog.get(3) + "\n"
                        );
                    }
                    break;
                case "commit":
                    for(String transaction : transactionsLog) {
                        transactionLog.clear();
                        transactionLog.addAll(Arrays.asList(transaction.split(" ")));
                        addLineToAccount(transactionLog.get(0),transactionLog.get(1),transactionLog.get(2),transactionLog.get(3), accounts);
                    }
                case "closeAndCommit":
                case "clommit":
                    System.out.println("Are you sure? commit " + transactionsLog.size() + " transactions and close\n press(y/n)\n");
                    enterText = scanner.nextLine();
                    clommitExit = false;
                    do {
                        switch (enterText) {
                            case "y":
                                for (String transaction : transactionsLog) {
                                    transactionLog.clear();
                                    transactionLog.addAll(Arrays.asList(transaction.split(" ")));
                                    addLineToAccount(transactionLog.get(0), transactionLog.get(1), transactionLog.get(2), transactionLog.get(3), accounts);
                                }
                                System.out.println("");
                                open_transaction_exit = false;
                                System.out.println("Transaction has been closed\n");
                                clommitExit = true;
                                break;
                            case "n":
                                clommitExit = true;
                            default:
                                System.out.println("press y/n for yes or no option\n");
                                break;
                        }
                    }while (!clommitExit);
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command\n");
                    break;
            }
        } while (!exit);
    }

    public static void createAccount(String name, ArrayList<Account> accounts){
        boolean unic = true;
        for(Account account: accounts) {
            if(account.getName().equalsIgnoreCase(name)){
                unic = false;
                break;
            }
        }
        if(unic) {
            Account account = new Account(name);
            accounts.add(account);
            System.out.println("Account was created: name - " + name + "\n");
        } else {
            System.out.println("Account whit this name already exist\n");
        }
    }

    public static void addLineToAccount(String name,String operation, String debet, String credit, ArrayList<Account> accounts){
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name)) {
                account.newLine(operation, debet, credit);
                return;
            }
        }
        System.out.println("Account name not found error in addLineToAccount function");
    }

    public static void printAccount(String name, ArrayList<Account> accounts){
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name)) {
                System.out.println(account.toString());
                return;
            }
        }
        System.out.println("Account whit this name does not exist\n");
    }

    public static void printAccountList(ArrayList<Account> accounts){
        //System.out.println("\n");
        for (int i = 0;i < accounts.size();i++) {
            System.out.println((i+1) + "  " + accounts.get(i).getName());
        }
        //System.out.println("\n");
    }
}
