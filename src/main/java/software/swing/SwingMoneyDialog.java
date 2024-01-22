package software.swing;

import software.Currency;
import software.Money;
import software.MoneyDialog;
import software.fixerwebservice.FixerExchangeRateLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private JTextField amountFile;
    private SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountFile());
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountFile() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        this.amountFile = textField;
        return textField;
    }

    @Override
    public Money get() {
        try {
            return new Money(selectAmount(),currencyDialog.get());
        }catch (NumberFormatException e){
            return new Money(0,currencyDialog.get());
        }

    }

    private double selectAmount() {
        return round(Double.parseDouble(this.amountFile.getText()));
    }

    private double round(double value) {
        return Math.round(value *100.0)/100.0;
    }
}
