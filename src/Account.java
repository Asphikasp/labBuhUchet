import java.util.ArrayList;

public class Account {

    private String name;
    private int operationNum;
    private ArrayList<String[]> operationList = new ArrayList<>();

    public Account(String name) {
        this.name = name;
        operationNum = 0;
        String[] args = {"№","name","debit","credit"};
        operationList.add(0,args);
    }

    public String getName() {
        return name;
    }

    public int getOperationNum() { return operationNum; }

    public void newLine(String operation, String debet, String kredit) {
        String[] line = new String[4];
        operationNum++;
        line[0] = Integer.toString(operationNum);
        line[1] = operation;
        line[2] = debet;
        line[3] = kredit;
        operationList.add(line);
    }


    @Override
    public String toString() {
        StringBuilder bodyReturn = new StringBuilder();
        //for (String[] strings: accountList)
        for (String[] strings: operationList) {
            for (String string: strings) {
                bodyReturn.append(string).append("\t\t\t");
            }
            bodyReturn.append("\n");
        }
        return name + "\n"
                + "-------------------------------------------------------------\n"
                + bodyReturn
                + "-------------------------------------------------------------";
    }
}
