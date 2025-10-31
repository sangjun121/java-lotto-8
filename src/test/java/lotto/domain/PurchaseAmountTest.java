package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Test
    void 구입_금액을_기반으로_올바른_로또_발행_개수를_반환한다(){
        PurchaseAmount purchaseAmount = new PurchaseAmount(8000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 999})
    void 구입_금액이_천원_이상이_아닌_경우_예외가_발생한다(int purchaseAmount) {
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmount))
                .isInstanceOf(InvalidPurchaseAmountException.class)
                .hasMessage(LottoError.PURCHASE_AMOUNT_LESS_THAN_MINIMUM.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1999})
    void 구입_금액이_천원_단위가_아닌_경우_예외가_발생한다(int purchaseAmount) {
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmount))
                .isInstanceOf(InvalidPurchaseAmountException.class)
                .hasMessage(LottoError.PURCHASE_AMOUNT_NOT_MULTIPLE_OF_THOUSAND.getMessage());
    }
}
