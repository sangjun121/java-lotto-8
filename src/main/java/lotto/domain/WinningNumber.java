package lotto.domain;

import java.util.List;
import lotto.exception.InvalidWinningNumberException;
import lotto.exception.LottoError;

public class WinningNumber {
    private static final int NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public WinningNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        checkWinningNumberCount(numbers);
    }

    private void checkWinningNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new InvalidWinningNumberException(LottoError.INVALID_WINNGING_NUMBER_COUNT.getMessage());
        }
    }

}
