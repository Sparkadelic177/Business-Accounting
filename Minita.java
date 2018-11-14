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


public class Minita extends Application{

    double sum = 0;
    LocalDate date = LocalDate.now();
    final String file = date + ".txt"; //file name is the date that it was made in
    PrintWriter outputStream = null;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){

        //the hbox is the pane / root where it adds all the children parts 
        HBox hbox = new HBox(30);
 
        Label expensesLbl = new Label("Expenses");
        TextField expensesFeild = new TextField();

        Label cashLbl = new Label("Cash");
        TextField cashFeild = new TextField();

        Label creditLbl = new Label("Credit");
        TextField creditFeild = new TextField();

        VBox inputs = new VBox(10);
        inputs.setAlignment(Pos.TOP_LEFT);

        inputs.getChildren().addAll(expensesLbl, 
                                    expensesFeild, 
                                    cashLbl, 
                                    cashFeild, 
                                    creditLbl, 
                                    creditFeild);

       

        Button add = new Button("Add");
        Button print = new Button("Print");

        //making a vbox to set the buttons positing
        VBox buttons = new VBox(10);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        buttons.getChildren().addAll(add, print);


        //need a event handler to take in all the numbers and calculate it 
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //The text need to be parsed into int 
                String expensesString = expensesFeild.getText();
                String cashString = cashFeild.getText();
                String creditString = creditFeild.getText();

                double expenses = Double.parseDouble(expensesString);
                double cash = Double.parseDouble(cashString);
                double credit = Double.parseDouble(creditString);

                try{
                    outputStream = new PrintWriter(new FileOutputStream(file, true));
                    //print what was added before it is cleared
                    outputStream.println("Cash: " + cashString + " | " +
                                        "Credit: " + creditString + " | " +
                                        "Expenses: " + expensesString);
                      //then the feilds needs to be cleared 
                    expensesFeild.setText("");
                    cashFeild.setText("");
                    creditFeild.setText("");

                    outputStream.close();
                }
                catch(FileNotFoundException err){
                    System.out.println("The file is not found or can't be made with the path given " + err);
                    System.exit(0);
                }

            
              
                //add up the 
                sum = sum + ((cash + credit) - expenses);
            }
        });

           

        //this action prints to a file in the root folder
        //implement a try catch function 
        print.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){

                try{ 
                    outputStream = new PrintWriter(new FileOutputStream(file, true));
                    outputStream.println("Total: " + sum);
                    outputStream.close();

                }
                catch(FileNotFoundException err){
                    System.out.println("The file is not found or can't be made with the path given " + err);
                    System.exit(0);
                }
            }
        });
        


        hbox.getChildren().addAll(inputs,buttons);

    
        Scene scene = new Scene(hbox , 500, 500);

        stage.setScene(scene);
        stage.setTitle("La Minita / Mina");
        stage.show();

    }
}