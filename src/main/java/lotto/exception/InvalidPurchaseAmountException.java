package lotto.exception;

public class InvalidPurchaseAmountException extends IllegalArgumentException {
    public InvalidPurchaseAmountException() {
        super(LottoError.INVALID_INPUT.getMessage());
    }

    public InvalidPurchaseAmountException(String message) {
        super(message);
    }
}
