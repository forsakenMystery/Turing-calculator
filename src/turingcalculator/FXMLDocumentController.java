/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingcalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author hossein
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField resultTextField;
    @FXML
    private TextArea resultTuring;
    private Turing engine;
    private char operator;
    private boolean isResult = false;

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        engine = null;
        resultTextField.setText("");
    }

    @FXML
    private void handleOperandButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (!isResult) {
            resultTextField.appendText(clickedButton.getText());
        } else {
            resultTextField.setText(clickedButton.getText());
            isResult = false;
        }

    }

    @FXML
    private void handleOperatorButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        isResult = true;
        String text = resultTextField.getText();
        Integer number;

        number = Integer.valueOf(text);

        if (engine == null) {
            engine = new Turing(number);
        } else {
            if (engine.toInt() >= 0) {
                switch (operator) {
                    case '+':
                        engine.add(number);
                        break;
                    case '-':

                        engine.subtract(number);

                        break;
                    case '*':
                        engine.multiply(number);
                        break;
                    case '/':
                        engine.division(number);
                        break;
                    case '=':
                        break;
                }
            }
        }
        operator = clickedButton.getText().charAt(0);
        resultTuring.setText(engine.toString());
        if (operator != '=') {
            resultTextField.setText(String.valueOf(engine.toInt()) + operator);
        } else {
            resultTextField.setText(String.valueOf(engine.toInt()));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
