package jigtor.criteria;

import lombok.Getter;

public enum Knowledge {
    Knows(4), Moderate(3), ALittle(2), WantToKnow(1);

    @Getter
    private final Integer knowledgeAmount;

    private Knowledge(Integer knowledgeAmount) {
        this.knowledgeAmount = knowledgeAmount;
    }
}
