package lotto.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super(LottoError.INVALID_INPUT.getMessage());
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
