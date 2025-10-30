package lotto.view;

import lotto.exception.InvalidInputException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public class InputParser {
    private static final String NUMBER_SEPARATOR = ",";

    public int parsePurchaseAmount(String input) {
        validatePurchaseAmountInput(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NOT_NUMBER.getMessage());
        }
    }

    // TODO: List<Integer>로 반환 타입 수정
    public void parseWinningNumber(String input) {
        validateWinningNumberInput(input);
    }

    private void validatePurchaseAmountInput(String input) {
        checkPurchaseAmountNonNullOrBlank(input);
    }

    private void validateWinningNumberInput(String input) {
        checkWinningNumberNonNullOrBlank(input);
        checkWinningNumberNoConsecutiveCommas(input);
        checkWinningNumberNoCommaAtEnds(input);
    }

    private void checkPurchaseAmountNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private void checkWinningNumberNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private void checkWinningNumberNoConsecutiveCommas(String input) {
        if (Validator.containsConsecutiveSubstring(input, NUMBER_SEPARATOR)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_FORMAT_WRONG.getMessage());
        }
    }

    private void checkWinningNumberNoCommaAtEnds(String input) {
        if (Validator.startsOrEndsWith(input, NUMBER_SEPARATOR)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_FORMAT_WRONG.getMessage());
        }
    }
}
