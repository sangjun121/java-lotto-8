package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.InvalidBonusNumberException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @Test
    void 보너스_숫자가_음수인_경우_예외가_발생한다() {
        assertThatThrownBy(() -> BonusNumber.of(-1))
                .isInstanceOf(InvalidBonusNumberException.class)
                .hasMessage(LottoError.INVALID_BONUS_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 보너스_숫자가_1부터_45사이가_아닌_경우_예외가_발생한다(int bonusNumber) {
        assertThatThrownBy(() -> BonusNumber.of(bonusNumber))
                .isInstanceOf(InvalidBonusNumberException.class)
                .hasMessage(LottoError.INVALID_BONUS_NUMBER_RANGE.getMessage());
    }
}
