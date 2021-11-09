import java.io.*;

/**
 * Демонстрационная версия.
 **/
public class pars {
    public static void main(String[] args) throws IOException {
        String expr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Parser p = new Parser();
        System.out.println("Для выхода из программы введите пустое выражение.");
        for (; ; ) {
            System.out.println("Введите выражение: ");
            expr = br.readLine();
            if (expr.equals(""))
                break;
            try {
                System.out.println("Результат: " + p.evalute(expr));
                System.out.println();
            } catch (ParserException exc) {
                System.out.println(exc);
            }
        }
    }
}
