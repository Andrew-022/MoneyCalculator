package software.swing;

import software.Currency;
import software.Command;
import software.ExchangeMoneyCommand;
import software.fixerwebservice.FixerCurrencyLoader;
import software.fixerwebservice.FixerExchangeRateLoader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SwingMainFrame extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<Currency> currencies;

    private SwingMoneyDisplay moneyDisplay;
    private SwingCurrencyDialog currencyDialog;
    private SwingMoneyDialog moneyDialog;

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setSize(800, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createMoneyDialog(), BorderLayout.WEST);
        this.add(createCurrencyDialog(), BorderLayout.EAST);
        this.add(createMoneyDisplay(), BorderLayout.SOUTH);

        this.currencies = new FixerCurrencyLoader().load();
        Command command = createCommand();
        add("exchange money", command);
    }

    private Component createToolbar() {
        JPanel toolbarPanel = new JPanel();
        JButton calculate = new JButton("Calculate");
        calculate.addActionListener(e -> commands.get("exchange money").execute());
        toolbarPanel.add(calculate);
        return toolbarPanel;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog();
        this.moneyDialog = swingMoneyDialog;
        JPanel moneyDialogPanel = new JPanel();
        moneyDialogPanel.setLayout(new BorderLayout());
        moneyDialogPanel.add(moneyDialog, BorderLayout.CENTER);
        moneyDialogPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return moneyDialogPanel;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        JPanel moneyDialogPanel = new JPanel();
        moneyDialogPanel.setLayout(new BorderLayout());
        moneyDialogPanel.add(currencyDialog, BorderLayout.CENTER);
        moneyDialogPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 20)); // Ajusta el margen según sea necesario

        return moneyDialogPanel;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay swingMoneyDisplay = new SwingMoneyDisplay();
        this.moneyDisplay = swingMoneyDisplay;
        JPanel moneyDialogPanel = new JPanel();
        moneyDialogPanel.setLayout(new BorderLayout());
        moneyDialogPanel.add(moneyDisplay, BorderLayout.CENTER);
        moneyDialogPanel.setBorder(BorderFactory.createEmptyBorder(5, 300, 20, 5)); // Ajusta el margen según sea necesario

        return moneyDialogPanel;
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    private Command createCommand() {
        return new ExchangeMoneyCommand(
                this.moneyDialog.define(currencies),
                this.currencyDialog.define(currencies),
                new FixerExchangeRateLoader(),
                this.moneyDisplay);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingMainFrame frame = new SwingMainFrame();
            frame.setVisible(true);
        });
    }
}
