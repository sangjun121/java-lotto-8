package lotto.exception;

public class InvalidWinningNumberException extends IllegalArgumentException {
    public InvalidWinningNumberException() {
        super(LottoError.INVALID_WINNGING_NUMBER.getMessage());
    }

    public InvalidWinningNumberException(String message) {
        super(message);
    }
}
