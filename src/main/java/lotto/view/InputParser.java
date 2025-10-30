package lotto.view;

import lotto.exception.InvalidInputException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public class InputParser {
    //TODO: 반환 값 int로 수정 예정
    public int parsePurchaseAmount(String input) {
        validatePurchaseAmountInput(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NOT_NUMBER.getMessage());
        }
    }

    private void validatePurchaseAmountInput(String input) {
        checkPurchaseAmountNonNullOrBlank(input);
    }

    private void checkPurchaseAmountNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK.getMessage());
        }
    }
}
