package lotto.exception;

public enum LottoError {
    INVALID_INPUT("[ERROR] 올바르지 않은 입력 값입니다."),
    PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK("[ERROR] 로또 구입 금액은 null이거나 blank일 수 없습니다.");

    private final String message;

    LottoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
