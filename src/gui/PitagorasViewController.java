package gui;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import exceptions.ValidationException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Pitagoras;

public class PitagorasViewController implements Initializable {

	Pitagoras pitagoras = new Pitagoras();

	// Variaveis que iremos utilizar:
	double resultadoFinal, hipotenusaQuadrado, catetoAQuadrado, catetoBQuadrado;

	// As anotacoes @FXML para associarmos com o front-end

	@FXML
	private TextField txtCatetoA;

	@FXML
	private Label labelErrorCatetoA;

	@FXML
	private TextField txtCatetoB;

	@FXML
	private Label labelErrorCatetoB;

	@FXML
	private TextField txtHipotenusa;

	@FXML
	private Label labelErrorHipotenusa;

	@FXML
	private TextField txtResultado;

	@FXML
	private Button btCalcular;

	@FXML
	private Button btLimpar;

	// Metodo quando o usuario clicar no botao "Calcular"
	public void onBtCalcular() {
		try {
			pitagoras = validacaoCampos();
			Calcular();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}
	}

	// Método quando o usuario clicar no botão "Limpar"
	public void onBtLimpar() {
		txtCatetoA.setText("");
		txtCatetoB.setText("");
		txtHipotenusa.setText("");
		txtResultado.setText("");
		labelErrorCatetoA.setText("");
		labelErrorCatetoB.setText("");
		labelErrorHipotenusa.setText("");
	}

	// Restricoes: nao aceitar letra, apenas números, quando o usuario digitar.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Utils.setTextFieldDouble(txtCatetoA);
		Utils.setTextFieldDouble(txtCatetoB);
		Utils.setTextFieldDouble(txtHipotenusa);
	}

	// Metodo para fazer o calculo do Teorema de Pitagoras
	public void Calcular() {

		if (txtCatetoA.getText() == null || txtCatetoA.getText().trim().equals("")) {

			double doubleCatetoB = Utils.tryParseToDouble(txtCatetoB.getText());
			double doubleHipotenusa = Utils.tryParseToDouble(txtHipotenusa.getText());
			// Condicao regra de pitagoras: valor do cateto sempre precisa ser inferior ao da hipotenusa
			if (doubleCatetoB >= doubleHipotenusa) {
				Alerts.showAlert("Atenção", null, "O valor do cateto precisa ser menor que o valor da hipotenusa!",
						AlertType.INFORMATION);
				onBtLimpar();
			} else { //Se for maior, fazer o calculo do teorema:
				hipotenusaQuadrado = Math.pow(Utils.tryParseToDouble(txtHipotenusa.getText()), 2);
				catetoBQuadrado = Math.pow(Utils.tryParseToDouble(txtCatetoB.getText()), 2);

				resultadoFinal = Math.sqrt(hipotenusaQuadrado - catetoBQuadrado);
				String total = String.valueOf(resultadoFinal);
				txtResultado.setText(total);
			}
		} else if (txtCatetoB.getText() == null || txtCatetoB.getText().trim().equals("")) {

			double doubleCatetoA = Utils.tryParseToDouble(txtCatetoA.getText());
			double doubleHipotenusa = Utils.tryParseToDouble(txtHipotenusa.getText());

			if (doubleCatetoA >= doubleHipotenusa) {
				Alerts.showAlert("Atenção", null, "O valor do cateto precisa ser menor que o valor da hipotenusa!",
						AlertType.INFORMATION);
				onBtLimpar();
			} else {

				hipotenusaQuadrado = Math.pow(Utils.tryParseToDouble(txtHipotenusa.getText()), 2);
				catetoAQuadrado = Math.pow(Utils.tryParseToDouble(txtCatetoA.getText()), 2);

				resultadoFinal = Math.sqrt(hipotenusaQuadrado - catetoAQuadrado);
				String total = String.valueOf(resultadoFinal);
				txtResultado.setText(total);
			}
		} else if (txtHipotenusa.getText() == null || txtHipotenusa.getText().trim().equals("")) {

			catetoAQuadrado = Math.pow(Utils.tryParseToDouble(txtCatetoA.getText()), 2);
			catetoBQuadrado = Math.pow(Utils.tryParseToDouble(txtCatetoB.getText()), 2);

			resultadoFinal = Math.sqrt(catetoAQuadrado + catetoBQuadrado);
			String total = String.valueOf(resultadoFinal);
			txtResultado.setText(total);
		} else {
			Alerts.showAlert("Ops", null, "Você digitou um campo a mais! Digite apenas dois números!", AlertType.ERROR);
			onBtLimpar();
		}
	}

	// Metodo para vincular as mensagens de erros
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("catetoA")) {
			labelErrorCatetoA.setText(errors.get("catetoA"));
		}

		if (fields.contains("catetoB")) {
			labelErrorCatetoB.setText(errors.get("catetoB"));
		}

		if (fields.contains("hipotenusa")) {
			labelErrorHipotenusa.setText(errors.get("hipotenusa"));
		}
	}

	// Metodo para validar os dados:
	private Pitagoras validacaoCampos() {

		ValidationException exception = new ValidationException("Validation error");

		// Método para validar se os campos estão vazios
		if ((txtCatetoA.getText() == null || txtCatetoA.getText().trim().equals(""))
				& (txtCatetoB.getText() == null || txtCatetoB.getText().trim().equals(""))
				& (txtHipotenusa.getText() == null || txtHipotenusa.getText().trim().equals(""))) {
			exception.addError("catetoA", "Não pode estar vazio");
			exception.addError("catetoB", "Não pode estar vazio");
			exception.addError("hipotenusa", "Não pode estar vazio");
		}

		if ((txtCatetoA.getText() == null || txtCatetoA.getText().trim().equals(""))
				& (txtCatetoB.getText() == null || txtCatetoB.getText().trim().equals(""))) {
			exception.addError("catetoA", "Não pode estar vazio");
			exception.addError("catetoB", "Não pode estar vazio");
		}
		labelErrorHipotenusa.setText("");

		if ((txtCatetoB.getText() == null || txtCatetoB.getText().trim().equals(""))
				& (txtHipotenusa.getText() == null || txtHipotenusa.getText().trim().equals(""))) {
			exception.addError("catetoB", "Não pode estar vazio");
			exception.addError("hipotenusa", "Não pode estar vazio");
		}
		labelErrorCatetoA.setText("");

		if ((txtCatetoA.getText() == null || txtCatetoA.getText().trim().equals(""))
				& (txtHipotenusa.getText() == null || txtHipotenusa.getText().trim().equals(""))) {
			exception.addError("catetoA", "Não pode estar vazio");
			exception.addError("hipotenusa", "Não pode estar vazio");
		}
		labelErrorCatetoB.setText("");

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return pitagoras;
	}

}
