package lotto.domain;

import lotto.exception.InvalidBonusNumberException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public final class BonusNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber of(int value) {
        validate(value);
        return new BonusNumber(value);
    }

    private static void validate(int value) {
        validateRange(value);
    }

    private static void validateRange(int value) {
        if (!Validator.isInRange(value, MIN_NUMBER, MAX_NUMBER)) {
            throw new InvalidBonusNumberException(LottoError.INVALID_BONUS_NUMBER_RANGE.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BonusNumber)) {
            return false;
        }
        BonusNumber that = (BonusNumber) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
