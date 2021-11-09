/**
 * Класс исключений для ошибок анализатора.
 */
public class ParserException extends Exception {
    String errStr; //describes the error

    public ParserException(String str) {
        errStr = str;
    }

    public String toString() {
        return errStr;
    }
}
