package lotto.domain;

import java.util.List;
import lotto.exception.InvalidLottoException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public class Lotto {
    private static final int NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortedNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateRange(numbers);
        validateNumbersNotDuplicated(numbers);
    }

    private List<Integer> sortedNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (!Validator.hasSize(numbers, NUMBER_COUNT)) {
            throw new InvalidLottoException(LottoError.INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (!Validator.isInRange(number, MIN_NUMBER, MAX_NUMBER)) {
                throw new InvalidLottoException(LottoError.INVALID_LOTTO_NUMBER_RANGE.getMessage());
            }
        }
    }

    private void validateNumbersNotDuplicated(List<Integer> numbers) {
        if (Validator.isDuplicated(numbers)) {
            throw new InvalidLottoException(LottoError.LOTTO_NUMBER_DUPLICATED.getMessage());
        }
    }
}
