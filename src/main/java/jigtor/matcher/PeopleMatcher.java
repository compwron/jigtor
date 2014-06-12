package jigtor.matcher;


import jigtor.people.PeoplePair;
import jigtor.people.Person;

import java.util.List;

public interface PeopleMatcher {
    public List<PeoplePair> matchWithin(List<Person> people);

    public List<PeoplePair> matchBetween(List<Person> juniorPeople, List<Person> seniorPeople);
}
