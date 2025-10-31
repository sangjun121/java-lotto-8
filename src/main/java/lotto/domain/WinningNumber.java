package lotto.domain;

import java.util.List;
import lotto.exception.InvalidWinningNumberException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public class WinningNumber {
    private static final int NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public WinningNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        checkWinningNumberCount(numbers);
        checkWinningNumberInRange(numbers);
        checkWinningNumberDuplicated(numbers);
    }

    private void checkWinningNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new InvalidWinningNumberException(LottoError.INVALID_WINNGING_NUMBER_COUNT.getMessage());
        }
    }

    private void checkWinningNumberInRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (!Validator.isInRange(number, MIN_NUMBER, MAX_NUMBER)) {
                throw new InvalidWinningNumberException(LottoError.INVALID_WINNING_NUMBER_RANGE.getMessage());
            }
        }
    }

    private void checkWinningNumberDuplicated(List<Integer> numbers) {
        if (Validator.isDuplicated(numbers)) {
            throw new InvalidWinningNumberException(LottoError.WINNING_NUMBER_DUPLICATED.getMessage());
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}
