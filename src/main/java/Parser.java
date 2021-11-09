import java.util.Scanner;

/**
 * Основной класс
 */
public class Parser {
    //Типы лексем.
    final int NONE = 0;
    final int DELIMITER = 1;
    final int VARIABLE = 2;
    final int NUMBER = 3;

    //There are the types of syntax errors.
    final int SYNTAX = 0;
    final int UNBALPARENS = 1;
    final int NOEXP = 2;
    final int DIVBYZERO = 3;

    //Лексема, отмечающая конец выражения.
    final String EOE = "\0";
    private String exp; //Ссылка на строку с выражением.
    private int expldx; //Текущий индекс.
    private String token; //Содержит текущую лексему.
    private int tokType; //Содержит тип текущей лексемы.


    private ListTemplate list=new ListTemplate();
    Scanner scanner = new Scanner(System.in);
    //Точка входа анализатора.
    public double evalute(String expstr) throws ParserException {
        double result;
        exp = expstr;
        expldx = 0;
        getToken();
        if (token.equals(EOE))
            handleErr(NOEXP);   //Нет выражения
        //Анализ и вычисление выражения.
        result = AdditionOrSubtraction();
        if (!token.equals(EOE)) //Последняя лексема должна быть ЕОЕ
            handleErr(SYNTAX);
        return result;
    }

    //Сложить или вычесть два числа.
    private double AdditionOrSubtraction() throws ParserException {
        char op;
        double result;
        double partialResult;
        result = MultiplicationOrDivision();
        while ((op = token.charAt(0)) == '+' || op == '-') {
            getToken();
            partialResult = MultiplicationOrDivision();
            switch (op) {
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }

    //Умножить или разделить два числа.
    private double MultiplicationOrDivision() throws ParserException {
        char op;
        double result;
        double partialResult;
        result = Power();
        while ((op = token.charAt(0)) == '*' || op == '/' | op == '%') {
            getToken();
            partialResult = Power();
            switch (op) {
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if (partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result /= partialResult;
                    break;
                case '%':
                    if (partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result %= partialResult;
                    break;
            }
        }
        return result;
    }

    //Выполнить возведение в степень.
    private double Power() throws ParserException {
        double result;
        double partialResult;
        double ex;
        int t;
        result = UnaryPlusOrMinus();
        if (token.equals("^")) {
            getToken();
            partialResult = Power();
            ex = result;
            if (partialResult == 0.0) {
                result = 1.0;
            } else
                for (t = (int) partialResult - 1; t > 0; t--)
                    result *= ex;
        }
        return result;
    }

    //Определить унарные + или -.
    private double UnaryPlusOrMinus() throws ParserException {
        double result;
        String op;
        op = "";
        if ((tokType == DELIMITER) && token.equals("+") || token.equals("-")) {
            op = token;
            getToken();
        }
        result = InBracketsOrSomeFunction();
        if (op.equals("-"))
            result = -result;
        return result;
    }

    //Обработать выражение в скобках.
    private double InBracketsOrSomeFunction() throws ParserException {
        double result;
        if (token.equals("(")) {
            getToken();
            result = AdditionOrSubtraction();
            if (!token.equals(")"))
                handleErr(UNBALPARENS);
            getToken();
        }else if  (token.equals("|")) {
            getToken();
            result = Math.abs(AdditionOrSubtraction());
            if (!token.equals("|"))
                handleErr(UNBALPARENS);
            getToken();
        }
        else if (token.equals("sin")) {
            getToken();
            result = Math.sin(AdditionOrSubtraction());
        }
        else if  (token.equals("cos")) {
            getToken();
            result = Math.cos(AdditionOrSubtraction());
        }
        else if  (token.equals("tg")) {
            getToken();
            result = Math.tan(AdditionOrSubtraction());
        }
        else if  (token.equals("ln")) {
            getToken();
            result = Math.log(AdditionOrSubtraction());
        }
        else
            result = atom();
        return result;
    }

    //Получить значение числа.
    private double atom() throws ParserException {
        double result = 0.0;
        switch (tokType) {
            case NUMBER:
                try {
                    result = Double.parseDouble(token);
                } catch (NumberFormatException exc) {
                    handleErr(SYNTAX);
                }
                getToken();
                break;
            case VARIABLE:
                try {
                    if(!list.isEmpty()&&list.Search(token)){
                            result= list.get(token);
                    }
                    else{
                        System.out.println("Введите значение для переменной "+token);
                        result = scanner.nextDouble();
                        Var v=new Var(token,result);
                        list.addToHead(v);
                    }
                } catch (NumberFormatException exc) {
                    handleErr(SYNTAX);
                }
                getToken();
                break;
            default:
                handleErr(SYNTAX);
                break;
        }
        return result;
    }

    //Handle an error.
    private void handleErr(int error) throws ParserException {
        String[] err = {
                "Syntax Error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by Zero"
        };
        throw new ParserException(err[error]);
    }

    //Получить следующую лексему.
    private void getToken() {
        tokType = NONE;
        token = "";
        //Проверить конец выражения.
        if (expldx == exp.length()) {
            token = EOE;
            return;
        }
        //Пропустить пробелы.
        while (expldx < exp.length() && Character.isWhitespace(exp.charAt(expldx)))
            ++expldx;
        //Пробелы в конце выражения.
        if (expldx == exp.length()) {
            token = EOE;
            return;
        }
        if (isDelim(exp.charAt(expldx))) { //если оператор или скобки
            token += exp.charAt(expldx);
            expldx++;
            tokType = DELIMITER;
        } else if (Character.isLetter(exp.charAt(expldx))) { //если переменная или функция
            while (!isDelim(exp.charAt(expldx))) {
                token += exp.charAt(expldx);
                expldx++;
                if (expldx >= exp.length())
                    break;
            }
            tokType = VARIABLE;
        } else if (Character.isDigit(exp.charAt(expldx))) { //Число.
            while (!isDelim(exp.charAt(expldx))) {
                token += exp.charAt(expldx);
                expldx++;
                if (expldx >= exp.length())
                    break;
            }
            tokType = NUMBER;
        } else {    //Неизвестный символ. Выход.
            token = EOE;
            return;
        }
    }

    //Возвращать true если с является оператором или скобкой.
    private boolean isDelim(char c) {
        if ((" ^+-/*%=(){}|".indexOf(c) != -1))
            return true;
        return false;
    }
}
