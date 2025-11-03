package lotto.view;

import java.util.Arrays;
import java.util.List;
import lotto.exception.InvalidInputException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public class InputParser {
    private static final String NUMBER_SEPARATOR = ",";
    private static final int NUMBER_SEPARATOR_COUNT = 5;

    public int parsePurchaseAmount(String input) {
        validatePurchaseAmountInput(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NOT_NUMBER.getMessage());
        }
    }

    public List<Integer> parseWinningNumber(String input) {
        validateWinningNumberInput(input);
        List<String> numbers = splitByNumberSeparator(input);

        try {
            return numbers.stream().map(Integer::parseInt).sorted().toList();
        } catch (NumberFormatException e) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_NOT_NUMERIC.getMessage());
        }
    }

    public int parseBonusNumber(String input) {
        validateBonusNumberInput(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(LottoError.BONUS_NUMBER_INPUT_NOT_NUMBER.getMessage());
        }
    }

    private List<String> splitByNumberSeparator(String input) {
        return Arrays.stream(input.split(NUMBER_SEPARATOR)).toList();
    }

    private void validatePurchaseAmountInput(String input) {
        validatePurchaseAmountNonNullOrBlank(input);
    }

    private void validateWinningNumberInput(String input) {
        validateWinningNumberNonNullOrBlank(input);
        validateWinningNumberNoConsecutiveCommas(input);
        validateWinningNumberNoCommaAtEnds(input);
        validateWinningNumberCommaCount(input);
    }

    private void validateBonusNumberInput(String input) {
        validateBonusNumberNonNullOrBlank(input);
    }

    private void validatePurchaseAmountNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private void validateWinningNumberNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private void validateWinningNumberNoConsecutiveCommas(String input) {
        if (Validator.containsConsecutiveSubstring(input, NUMBER_SEPARATOR)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_FORMAT_WRONG.getMessage());
        }
    }

    private void validateWinningNumberNoCommaAtEnds(String input) {
        if (Validator.startsOrEndsWith(input, NUMBER_SEPARATOR)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_FORMAT_WRONG.getMessage());
        }
    }

    private void validateWinningNumberCommaCount(String input) {
        if (!Validator.containsCharExactCount(input, NUMBER_SEPARATOR, NUMBER_SEPARATOR_COUNT)) {
            throw new InvalidInputException(LottoError.WINNING_NUMBER_INPUT_FORMAT_WRONG.getMessage());
        }
    }

    private void validateBonusNumberNonNullOrBlank(String input) {
        if (Validator.isNullOrBlank(input)) {
            throw new InvalidInputException(LottoError.BONUS_NUMBER_INPUT_NULL_OR_BLANK.getMessage());
        }
    }
}
