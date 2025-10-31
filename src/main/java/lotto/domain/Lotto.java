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
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (!Validator.hasSize(numbers, NUMBER_COUNT)) {
            throw new InvalidLottoException(LottoError.INVALID_LOTTO_SIZE.getMessage());
        }
    }
}
