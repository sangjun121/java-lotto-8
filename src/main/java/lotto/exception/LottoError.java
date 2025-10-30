package lotto.exception;

public enum LottoError {
    INVALID_INPUT("[ERROR] 올바르지 않은 입력 값입니다."),
    PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK("[ERROR] 로또 구입 금액은 null이거나 blank일 수 없습니다."),
    PURCHASE_AMOUNT_INPUT_NOT_NUMBER("[ERROR] 로또 구입 금액은 숫자를 입력해야 합니다."),
    WINNING_NUMBER_INPUT_NULL_OR_BLANK("[ERROR] 당첨 번호 입력 값은 null이거나 blank일 수 없습니다."),
    WINNING_NUMBER_INPUT_FORMAT_WRONG("[ERROR] 당첨 번호 입력 값은 숫자 6개와 쉼표 5개가 번갈아 나오는 형식이어야 합니다."),

    INVALID_PURCHASE_AMOUNT("[ERROR] 올바르지 않은 로또 구입 금액입니다."),
    PURCHASE_AMOUNT_LESS_THAN_MINIMUM("[ERROR] 로또 구입 금액은 1000원 이상이어야 합니다."),
    PURCHASE_AMOUNT_NOT_MULTIPLE_OF_THOUSAND("[ERROR] 로또 구입 금액은 1000원 단위여야 합니다.");

    private final String message;

    LottoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
