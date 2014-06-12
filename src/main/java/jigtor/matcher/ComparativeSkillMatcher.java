package jigtor.matcher;

import jigtor.people.PeoplePair;
import jigtor.people.Person;
import jigtor.people.SponsorMatches;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparativeSkillMatcher {
    public SponsorMatches matchWithin(Person sponsee, List<Person> people) {
        Map<PeoplePair, Integer> pairs = new HashMap<PeoplePair, Integer>();
        for (Person sponsor : people) {
            Integer amountLearnable = sponsee.canLearnFrom(sponsor);
            if (amountLearnable > 0) {
                pairs.put(new PeoplePair(sponsee, sponsor), amountLearnable);
            }
        }
        return new SponsorMatches(pairs);
    }
}
