import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;

//I need to find a way to add the weeks total and the increment for the weeks
//remove the tax from each entry which is 8.87 percent.
//when she prints the text file should pop up

public class Minita extends Application{

    double sum = 0;
    LocalDate date = LocalDate.now();
    final String file = date + ".txt"; //file name is the date that it was made in
    PrintWriter outputStream = null;
    private GridPane root;
    public double weeksTotal;;
    public int increment = 0;
    public double tax = 8.87 ;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){

        String[] days = {"Sunday", "Monday", "Tuesday", "Wenesday","Thursday","Friday","Saturday"};
        double[] lunchSlots = new double[7];
        double[] dinnerSlots = new double[7];
        double[] totalSlots = new double[7];
        double[] lunchCreditSlots = new double[7];
        double[] lunchCashSlots = new double[7];
        double[] lunchExpensesSlots = new double[7];
        double[] dinnerCreditSlots = new double[7];
        double[] dinnerCashSlots = new double[7];
        double[] dinnerExpensesSlots = new double[7];
        double cashTotal = 0;




        root = new GridPane();
        root.setVgap(10); //vertical gap
        root.setHgap(5); //horizontal gap
        root.setAlignment(Pos.CENTER); //aligment in the box


        //days of the week should change with add click event need to be added
        Label dayLbl = new Label(days[increment]);

        Label creditLunchLbl = new Label("Lunch Credit");
        TextField creditLunchFeild = new TextField();
        creditLunchLbl.setLabelFor(creditLunchFeild);

        Label creditDinnerLbl = new Label("Dinner Credit");
        TextField creditDinnerFeild = new TextField();
        creditDinnerLbl.setLabelFor(creditDinnerFeild);

        Label expensesLunchLbl = new Label("Lunch Expenses");
        TextField expensesLunchFeild = new TextField();
        expensesLunchLbl.setLabelFor(expensesLunchFeild);

        Label expensesDinnerLbl = new Label("Dinner Expenses");
        TextField expensesDinnerFeild = new TextField();
        expensesDinnerLbl.setLabelFor(expensesDinnerFeild);

        Label cashLunchLbl = new Label("Lunch Cash");
        TextField cashLunchFeild = new TextField();
        cashLunchLbl.setLabelFor(cashLunchFeild);

        Label cashDinnerLbl = new Label("Dinner Cash");
        TextField cashDinnerFeild = new TextField();
        cashDinnerLbl.setLabelFor(cashDinnerFeild);

        Button add = new Button("Add");
        Button print = new Button("Print");


        //need a event handler to take in all the numbers and calculate it
        add.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                try{

                    //The text need to be parsed into double and place them inside the apporiate array
                    String dinnerExpensesString = expensesDinnerFeild.getText(),
                            dinnerCashString = cashDinnerFeild.getText(),
                            dinnerCreditString = creditDinnerFeild.getText(),
                            lunchExpensesString = expensesLunchFeild.getText(),
                            lunchCashString = cashLunchFeild.getText(),
                            lunchCreditString = creditLunchFeild.getText();

                    double dinnerExpenses = Double.parseDouble(dinnerExpensesString),
                            dinnerCash = Double.parseDouble(dinnerCashString),
                            dinnerCredit = Double.parseDouble(dinnerCreditString),
                            lunchExpenses = Double.parseDouble(lunchExpensesString),
                            lunchCash = Double.parseDouble(lunchCashString),
                            lunchCredit = Double.parseDouble(lunchCreditString);

                    for (int i  = increment; i <= increment ; i++) {
                        dinnerExpensesSlots[increment] = dinnerExpenses ;
                        dinnerCreditSlots[increment] = dinnerCredit ;
                        dinnerCashSlots[increment] = dinnerCash ;
                        lunchExpensesSlots[increment] = lunchExpenses ;
                        lunchCreditSlots[increment] = lunchCredit;
                        lunchCashSlots[increment] = lunchCash;
                        dinnerSlots[increment] = dinnerExpenses + dinnerCredit + dinnerCash;
                        lunchSlots[increment] = lunchExpenses + lunchCredit + lunchCash;
                        totalSlots[increment] = lunchSlots[increment] + + dinnerSlots[increment];
                        weeksTotal = weeksTotal + totalSlots[increment];
                        cashTotal = cashtotal + dinnerCashSlots[increment] + lunchCashSlots[increment];
                    }


                    //the feilds needs to be cleared
                    expensesDinnerFeild.setText("");
                    cashDinnerFeild.setText("");
                    creditDinnerFeild.setText("");
                    expensesLunchFeild.setText("");
                    cashLunchFeild.setText("");
                    creditLunchFeild.setText("");

                }
                catch(Exception err){
                    System.out.println("There was an error in the calculations: " + err);
                }

                //adding up the increments for the days of the week
                if(increment == 6){
                    System.out.println("This is the end of the week cycle");
                }
                else{
                    increment = increment + 1;
                    System.out.print(increment);
                }
            }
        });



        //THE PRINT FUNCTION SHOULD PUT UP THE FILE
        print.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try{
                    outputStream = new PrintWriter(new FileOutputStream(file, true));
                    outputStream.println(
                        "\nSUNDAY" + "\t\t\t\t\t" + " CC " + " EXP " + " CASH " + 
                        "\n L = " + lunchSlots[0] + "\t\t\t\t|" + lunchCreditSlots[0] + "|" + lunchExpensesSlots[0] + "|" + + lunchCashSlots[0] +
                        "\n D = " + dinnerSlots[0]+ " => Total:" + totalSlots[0]+"w  " + "|" + dinnerCreditSlots[0] + "|" + dinnerExpensesSlots[0] + "|" + dinnerCashSlots[0] +
                        "\n\nMONDAY" +
                        "\n L = " + lunchSlots[1] + "\t\t\t\t|" + lunchCreditSlots[1] + "|" + lunchExpensesSlots[1] + "|" + + lunchCashSlots[1] +
                        "\n D = " + dinnerSlots[1]+ " => Total:" + totalSlots[1]+"w  " + "|" + dinnerCreditSlots[1] + "|" + dinnerExpensesSlots[1] + "|" + dinnerCashSlots[1] +
                        "\n\nTUESDAY" +
                        "\n L = " + lunchSlots[2] + "\t\t\t\t|" + lunchCreditSlots[2] + "|" + lunchExpensesSlots[2] + "|" + + lunchCashSlots[2] +
                        "\n D = " + dinnerSlots[2]+ " => Total:" + totalSlots[2]+"w  " + "|" + dinnerCreditSlots[2] + "|" + dinnerExpensesSlots[2] + "|" + dinnerCashSlots[2] +
                        "\n\nWENDESDAY" +
                        "\n L = " + lunchSlots[3] + "\t\t\t\t|" + lunchCreditSlots[3] + "|" + lunchExpensesSlots[3] + "|" + + lunchCashSlots[3] +
                        "\n D = " + dinnerSlots[3]+ " => Total:" + totalSlots[3]+"w  " + "|" + dinnerCreditSlots[3] + "|" + dinnerExpensesSlots[3] + "|" + dinnerCashSlots[3] +
                        "\n\nTHURSDAY" +
                        "\n L = " + lunchSlots[4] + "\t\t\t\t|" + lunchCreditSlots[4] + "|" + lunchExpensesSlots[4] + "|" + + lunchCashSlots[4] +
                        "\n D = " + dinnerSlots[4]+ " => Total:" + totalSlots[4]+"w  " + "|" + dinnerCreditSlots[4] + "|" + dinnerExpensesSlots[4] + "|" + dinnerCashSlots[4] +
                        "\n\nFRIDAY" +
                        "\n L = " + lunchSlots[5] + "\t\t\t\t|" + lunchCreditSlots[5] + "|" + lunchExpensesSlots[5] + "|" + + lunchCashSlots[5] +
                        "\n D = " + dinnerSlots[5]+ " => Total:" + totalSlots[5]+"w  " + "|" + dinnerCreditSlots[5] + "|" + dinnerExpensesSlots[5] + "|" + dinnerCashSlots[5] +
                        "\n\nSATURDAY" +
                        "\n L = " + lunchSlots[6] + "\t\t\t\t|" + lunchCreditSlots[6] + "|" + lunchExpensesSlots[6] + "|" + + lunchCashSlots[6] +
                        "\n D = " + dinnerSlots[6]+ " => Total:" + totalSlots[6]+"w  " + "|" + dinnerCreditSlots[6] + "|" + dinnerExpensesSlots[6] + "|" + dinnerCashSlots[6] +
                        "\n\n\t\tWeeks total = " + weeksTotal + "\t\t\t cash total = " + cashTotal;
                        );

                    outputStream.close();
                }
                catch(FileNotFoundException err){
                    System.out.println("The file is not found or can't be made with the path given " + err);
                    System.exit(0);
                }
                catch(Exception err){
                    System.out.println("There was an error " + err);
                }

            }
        });


        root.addRow(0);
        root.addRow(1, creditLunchLbl, creditLunchFeild);
        root.addRow(2, expensesLunchLbl, expensesLunchFeild);
        root.addRow(3, cashLunchLbl, cashLunchFeild);
        root.addRow(4, creditDinnerLbl, creditDinnerFeild);
        root.addRow(5, expensesDinnerLbl, expensesDinnerFeild);
        root.addRow(6, cashDinnerLbl, cashDinnerFeild);
        root.addRow(7, add, print);


        Scene scene = new Scene(root , 500, 500);

        stage.setScene(scene);
        stage.setTitle("La Minita / Mina");
        stage.show();

    }
}
