package jigtor.matcher;

import jigtor.people.PeoplePair;
import jigtor.people.Person;

import java.util.ArrayList;
import java.util.List;

public class ByIdMatcher implements PeopleMatcher {
    public List<PeoplePair> matchByClosestOffset(Person seeker, List<Person> people, Double lowestValidOffset) {
        List<PeoplePair> peoplePairs = new ArrayList<PeoplePair>();
        for (Person person : people) {
            if (person.hasMoreExperienceThan(seeker.getTimeAtCompany() + lowestValidOffset)) {
                peoplePairs.add(new PeoplePair(seeker, person));
                System.out.println("adding");
            }
        }
        return peoplePairs;
    }

    @Override
    public List<PeoplePair> matchWithin(List<Person> people) {
        return null;
    }

    @Override
    public List<PeoplePair> matchBetween(List<Person> juniorPeople, List<Person> seniorPeople) {
        return null;
    }
}
