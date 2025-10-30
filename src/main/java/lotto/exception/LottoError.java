package lotto.exception;

public enum LottoError {
    INVALID_INPUT("[ERROR] 올바르지 않은 입력 값입니다."),
    PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK("[ERROR] 로또 구입 금액은 null이거나 blank일 수 없습니다."),
    PURCHASE_AMOUNT_INPUT_NOT_NUMBER("[ERROR] 로또 구입 금액은 숫자를 입력해야 합니다."),

    INVALID_PURCHASE_AMOUNT("[ERROR] 올바르지 않은 로또 구입 금액입니다."),
    PURCHASE_AMOUNT_LESS_THAN_MINIMUM("[ERROR] 로또 구입 금액은 1000원 이상이어야 합니다.");

    private final String message;

    LottoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
