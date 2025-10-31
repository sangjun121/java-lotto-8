package lotto.domain;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prizeMoney;

    Rank(int matchCount, boolean isBonusMatch, int prizeMoney) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank valueOf(int matchCount, boolean isBonusMatch) {
        for (Rank rank : values()) {
            if (rank.matchCount == SECOND.matchCount && rank.matchCount == matchCount
                    && rank.isBonusMatch == isBonusMatch) {
                return rank;
            }

            if (rank.matchCount == matchCount) {
                return rank;
            }
        }

        return NONE;
    }
}

