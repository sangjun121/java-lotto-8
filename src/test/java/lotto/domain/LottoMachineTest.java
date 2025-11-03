package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.exception.InvalidLottoException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    private static final int VALID_LOTTO_COUNT = 8;

    @Test
    void 정확한_로또_목록이_생성된다() {
        NumberGenerator fixedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        LottoMachine lottoMachine = new LottoMachine(fixedNumberGenerator);

        Lottos lottos = lottoMachine.generateLottos(VALID_LOTTO_COUNT);

        assertThat(lottos.getLottos())
                .hasSize(VALID_LOTTO_COUNT)
                .extracting(Lotto::getNumbers)
                .allMatch(numbers -> numbers.equals(List.of(1, 2, 3, 4, 5, 6)));

    }

    @Test
    void 잘못된_로또_번호_범위인_경우_생성시_예외가_발생한다() {
        NumberGenerator invalidNumberGenerator = () -> List.of(0, 2, 3, 4, 5, 46);
        LottoMachine lottoMachine = new LottoMachine(invalidNumberGenerator);

        assertThatThrownBy(() -> lottoMachine.generateLottos(VALID_LOTTO_COUNT))
                .isInstanceOf(InvalidLottoException.class)
                .hasMessage(LottoError.INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    void 잘못된_로또_번호_개수인_경우_생성시_예외가_발생한다() {
        NumberGenerator invalidNumberGenerator = () -> List.of(1, 2, 3, 4, 5);
        LottoMachine lottoMachine = new LottoMachine(invalidNumberGenerator);

        assertThatThrownBy(() -> lottoMachine.generateLottos(VALID_LOTTO_COUNT))
                .isInstanceOf(InvalidLottoException.class)
                .hasMessage(LottoError.INVALID_LOTTO_SIZE.getMessage());
    }

    @Test
    void 중복된_로또_번호_개수인_경우_생성시_예외가_발생한다() {
        NumberGenerator duplicatedNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 5);
        LottoMachine lottoMachine = new LottoMachine(duplicatedNumberGenerator);

        assertThatThrownBy(() -> lottoMachine.generateLottos(VALID_LOTTO_COUNT))
                .isInstanceOf(InvalidLottoException.class)
                .hasMessage(LottoError.LOTTO_NUMBER_DUPLICATED.getMessage());
    }
}
