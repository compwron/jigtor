package jigtor.matcher;

import jigtor.people.PeoplePair;
import jigtor.people.Person;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ComparativeSkillMatcher {
    public List<PeoplePair> matchWithin(Person seeker, List<Person> people) {
        List<PeoplePair> peoplePairs = newArrayList();
        for (Person person : people) {
            if (person.knowsWhatSeekerWantsToKnow(seeker)) {
                peoplePairs.add(new PeoplePair(seeker, person));
            }
        }
        return peoplePairs;
    }
}
