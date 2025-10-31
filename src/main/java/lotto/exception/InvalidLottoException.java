package lotto.exception;

public class InvalidLottoException extends IllegalArgumentException {
    public InvalidLottoException() {
        super(LottoError.INVALID_LOTTO.getMessage());
    }

    public InvalidLottoException(String message) {
        super(message);
    }
}
