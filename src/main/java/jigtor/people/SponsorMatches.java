package jigtor.people;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@AllArgsConstructor
public class SponsorMatches {
    private Map<PeoplePair, Integer> peoplePairs;

    public PeoplePair topMatch() {
        Integer topMatchAmount = 0;
        PeoplePair topPeoplePair = null;

        for (PeoplePair peoplePair : peoplePairs.keySet()) {
            Integer currentMatchAmount = peoplePairs.get(peoplePair);
            if (currentMatchAmount > topMatchAmount) {
                topMatchAmount = currentMatchAmount;
                topPeoplePair = peoplePair;
            }
        }

        return topPeoplePair;
    }


    public List<PeoplePair> allMatches() {
        return newArrayList(peoplePairs.keySet());
    }
}
