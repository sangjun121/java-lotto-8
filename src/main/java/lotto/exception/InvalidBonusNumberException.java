package lotto.exception;

public class InvalidBonusNumberException extends IllegalArgumentException {
    public InvalidBonusNumberException() {
        super(LottoError.INVALID_BONUS_NUMBER.getMessage());
    }

    public InvalidBonusNumberException(String message) {
        super(message);
    }
}
