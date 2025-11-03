package lotto.domain.vo;

import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.LottoError;
import lotto.util.Validator;

public final class PurchaseAmount {
    private static final int PURCHASE_AMOUNT_THRESHOLD = 1000;
    private static final int PURCHASE_AMOUNT_UNIT = 1000;

    private final int value;

    private PurchaseAmount(int value) {
        validate(value);
        this.value = value;
    }

    public static PurchaseAmount from(int value) {
        return new PurchaseAmount(value);
    }

    public int calculateLottoCount() {
        return value / PURCHASE_AMOUNT_UNIT;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        validateMinimumAmount(value);
        validateMultipleOfThousand(value);
    }

    private void validateMinimumAmount(int value) {
        if (Validator.isLessThan(value, PURCHASE_AMOUNT_THRESHOLD)) {
            throw new InvalidPurchaseAmountException(LottoError.PURCHASE_AMOUNT_LESS_THAN_MINIMUM.getMessage());
        }
    }

    private void validateMultipleOfThousand(int value) {
        if (!Validator.isDivisibleBy(value, PURCHASE_AMOUNT_UNIT)) {
            throw new InvalidPurchaseAmountException(LottoError.PURCHASE_AMOUNT_NOT_MULTIPLE_OF_THOUSAND.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PurchaseAmount)) {
            return false;
        }
        PurchaseAmount that = (PurchaseAmount) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
