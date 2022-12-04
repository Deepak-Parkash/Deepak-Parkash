package com.calc.deepak;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.Stack;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalcController extends Application {
    private CalcModel model;
    private CalcViewCalc view;
    private Boolean isinfix = false;


    @FXML
    private TextField textCalc;
    @FXML
    private Label resultCalc;
    @FXML
    private RadioButton radRev;
    @FXML
    private RadioButton radInf;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcController.class.getResource("calc-view_calc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Calculator!");
        stage.setScene(scene);
        // create radiobuttons
        RadioButton r1 = new RadioButton("male");
        RadioButton r2 = new RadioButton("female");
        RadioButton r3 = new RadioButton("others");

        // add label
        TilePane r = new TilePane();
        r.getChildren().add(r1);
        r.getChildren().add(r2);
        r.getChildren().add(r3);

        stage.show();


    }

    @FXML
    public void calculate(ActionEvent event) {
        SolutionRevPolishCalc solutionRevPolish = new SolutionRevPolishCalc();
        char[] a = textCalc.getText().toString().toCharArray();
        String[] str = new String[textCalc.getText().toString().length()];
        for (int i = 0; i < str.length; i++) {
            str[i] = a[i] + "";
        }

        try {
            model = new CalcModel();
            String result2 = "" + model.evaluate(textCalc.getText().toString(), isinfix);
            resultCalc.setText(result2);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setTitle("Error Message");

            if (textCalc.getText().toString().length() == 0) {
                error.setHeaderText("please enter the number");
            } else {
                error.setHeaderText("Error Number Exception");
            }

            error.setAlertType(Alert.AlertType.ERROR);
            error.show();
        }

    }

    public void radRevAction() {
        radInf.setSelected(false);
        radRev.setSelected(true);
        expressionType();
    }

    public void radInfAction() {
        radInf.setSelected(true);
        radRev.setSelected(false);
        expressionType();
    }

    public Boolean expressionType() {
        return isinfix = !isinfix;

    }

    public void calculate2(Calculator2 cal, List<String> postFix) {

        Stack<BigDecimal> stack = new Stack<>();

        for (int i = 0; i < postFix.size(); i++) {

            String next = postFix.get(i);

            if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
                ArithmaticCalculatorCommand cmd = new ArithmaticCalculatorCommand(next.charAt(0), stack.pop(), stack.pop(), cal);
                Invoker invoker = new Invoker();
                invoker.compute(cmd);
                stack.push(cal.getCurrent());
            } else if (false) {

            } else {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }


    public static List<String> infixToPostfixConvert(String input) {

        int priority = 0;
        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                }
                postfixBuffer = "";
                // check the precedence
                if (stack.size() <= 0) stack.push(ch);
                else {
                    Character chTop = (Character) stack.peek();
                    if (chTop == '*' || chTop == '/') priority = 1;
                    else priority = 0;
                    if (priority == 1) {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        } else { // Same
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        }
                    } else {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            stack.push(ch);
                        } else stack.push(ch);
                    }
                }
            } else {
                postfixBuffer += ch;
            }
        }
        postfixArray.add(postfixBuffer);
        int len = stack.size();
        for (int j = 0; j < len; j++)
            postfixArray.add(stack.pop().toString());

        return postfixArray;
    }


}

class ArithmaticCalculatorCommand implements Command {

    private char operator;
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private Calculator2 calculator;

    public ArithmaticCalculatorCommand(char operator, BigDecimal leftOperand, BigDecimal rightOperand, Calculator2 calculator) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
        this.calculator = calculator;
    }


    @Override
    public void calculate() {
        calculator.operation(operator, leftOperand, rightOperand);
    }
}

interface Command {

    public void calculate();
}

class Invoker {

    public void compute(Command command) {
        command.calculate();
    }
}

class Calculator2 {

    private Calculator2 calculator;
    private BigDecimal current = new BigDecimal(0);

    public Calculator2() {

    }

    public Calculator2 getInstance() {
        if (calculator == null) {
            calculator = new Calculator2();
        }
        return calculator;
    }

    /*
     * This method calculate current value for any number of calculation operations.
     * Currently following operations are supported
     * +,-,*,/
     *
     * @param operator
     * @param leftOperand
     * @param rightOperand
     *
     */
    public void operation(char operator, BigDecimal leftOperand, BigDecimal rightOperand) {
        switch (operator) {
            case '+':
                current = leftOperand.add(rightOperand);
                break;
            case '-':
                current = rightOperand.subtract(leftOperand);
                break;
            case '/':
                current = rightOperand.divide(leftOperand);
                break;
            case '*':
                current = leftOperand.multiply(rightOperand);
                break;
            default:
                break;
        }
    }

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

}

class CalculatorParser {

    static boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') return true;

        return false;
    }

    static float evaluatePostfix(String exp) {
        Stack<Integer> postFix = new Stack<>();    // Create postfix stack
        int n = exp.length();

        for (int i = 0; i < n; i++) {
            if (isOperator(exp.charAt(i))) {
                // pop top 2 operands.
                int op1 = postFix.pop();
                int op2 = postFix.pop();

                // evaluate in reverse order i.e. op2 operator op1.
                switch (exp.charAt(i)) {
                    case '+':
                        postFix.push(op2 + op1);
                        break;

                    case '-':
                        postFix.push(op2 - op1);
                        break;

                    case '*':
                        postFix.push(op2 * op1);
                        break;

                    case '/':
                        postFix.push(op2 / op1);
                        break;

                }

            }
            // Current Char is Operand simple push into stack
            else {
                // convert to integer
                int operand = exp.charAt(i) - '0';
                postFix.push(operand);
            }
        }

        // Stack at End will contain result.
//        System.out.println(postFix.pop());
        return Float.valueOf("" + postFix.pop());
    }

    /*
     * This is the starting point of the program. It receives input from the command line
     * and process them further and sends to calculate function. At the end this method
     * displays the calculated result.
     */
    public BigDecimal start(String line) {

        List<String> postfixString = infixToPostfixConvert(line);
        Calculator2 calculator = new Calculator2().getInstance();
        calculator.setCurrent(new BigDecimal(0));

        calculate(calculator, postfixString);

        System.out.println(calculator.getCurrent() + ":TT:");
        return calculator.getCurrent();
    }


    public void calculate(Calculator2 cal, List<String> postFix) {

        Stack<BigDecimal> stack = new Stack<BigDecimal>();

        for (int i = 0; i < postFix.size(); i++) {

            String next = postFix.get(i);

            if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
                ArithmaticCalculatorCommand cmd = new ArithmaticCalculatorCommand(next.charAt(0), stack.pop(), stack.pop(), cal);
                Invoker invoker = new Invoker();
                invoker.compute(cmd);
                stack.push(cal.getCurrent());
            } else if (false) {

            } else {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }

    /*
     * This method convert the infix into postfix in order to proceed in the calculation.
     * @param input
     */
    public List<String> infixToPostfixConvert(String input) {

        int priority = 0;
        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                }
                postfixBuffer = "";
                // check the precedence
                if (stack.size() <= 0) stack.push(ch);
                else {
                    Character chTop = (Character) stack.peek();
                    if (chTop == '*' || chTop == '/') priority = 1;
                    else priority = 0;
                    if (priority == 1) {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        } else { // Same
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        }
                    } else {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            stack.push(ch);
                        } else stack.push(ch);
                    }
                }
            } else {
                postfixBuffer += ch;
            }
        }
        postfixArray.add(postfixBuffer);
        int len = stack.size();
        for (int j = 0; j < len; j++)
            postfixArray.add(stack.pop().toString());

        return postfixArray;
    }
}