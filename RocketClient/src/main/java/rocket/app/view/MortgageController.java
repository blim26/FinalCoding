package rocket.app.view;

import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;

import eNums.eAction;
import exceptions.IncomeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private ComboBox loanterm;
	private ObservableList<String> myComboBoxData = FXCollections.observableArrayList("15 years", "30 years");
	
	@FXML
	private Label lblMortgagePayment;
	
	@FXML
	private void initialize() {
		loanterm.setItems(myComboBoxData);
	}
	
	@FXML
	private Button btnCalculatePayment;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Integer.parseInt(txtHouseCost.getText()));
		if(loanterm.getValue() == "15 years") {
			lq.setiTerm(15);
		}
		else {
			lq.setiTerm(30);
		}
		

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest) throws IncomeException
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		RateBLL rate = new RateBLL();
		
		if(lRequest.getdRate() == 0) {
			lblMortgagePayment.setText("Credit score too low");
		}
		if(rate.AcceptableIncome(lRequest) == false) {
			lblMortgagePayment.setText("Cannot afford the house");
		}
		else {
			lblMortgagePayment.setText(String.format("%.2f", Double.toString(lRequest.getdPayment())));
		}
		
	}
}
