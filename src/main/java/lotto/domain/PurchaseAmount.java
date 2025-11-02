package lotto.domain;

import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public final class PurchaseAmount {
    private static final int PURCHASE_AMOUNT_THRESHOLD = 1000;
    private static final int PURCHASE_AMOUNT_UNIT = 1000;

    private final int value;

    public PurchaseAmount(int value) {
        validatePurchaseAmount(value);
        this.value = value;
    }

    public int calculateLottoCount() {
        return value / PURCHASE_AMOUNT_UNIT;
    }

    public int getValue() {
        return value;
    }

    private void validatePurchaseAmount(int value) {
        checkMinimumPurchaseAmount(value);
        checkMultipleOfThousand(value);
    }

    private void checkMinimumPurchaseAmount(int value) {
        if (Validator.isLessThan(value, PURCHASE_AMOUNT_THRESHOLD)) {
            throw new InvalidPurchaseAmountException(LottoError.PURCHASE_AMOUNT_LESS_THAN_MINIMUM.getMessage());
        }
    }

    private void checkMultipleOfThousand(int value) {
        if (!Validator.isDivisibleBy(value, PURCHASE_AMOUNT_UNIT)) {
            throw new InvalidPurchaseAmountException(LottoError.PURCHASE_AMOUNT_NOT_MULTIPLE_OF_THOUSAND.getMessage());
        }
    }
}
