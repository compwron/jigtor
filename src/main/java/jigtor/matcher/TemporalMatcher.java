package jigtor.matcher;

import jigtor.people.PeoplePair;
import jigtor.people.Person;

import java.util.ArrayList;
import java.util.List;

public class TemporalMatcher  {
    public List<PeoplePair> matchByTimeAtCompany(Person seeker, List<Person> people, Double lowestValidOffset) {
//        Could use employee Id instead of time, depending on what the API gives

        List<PeoplePair> peoplePairs = new ArrayList<PeoplePair>();
        for (Person person : people) {
            if (person.hasMoreExperienceThan(lowestValidOffset)) {
                peoplePairs.add(new PeoplePair(seeker, person));
            }
        }
        return peoplePairs; // use a guava transform
    }

    public List<PeoplePair> matchByEmployeeId(Person seeker, List<Person> people, double lowestValidOffset) {
        List<PeoplePair> peoplePairs = new ArrayList<PeoplePair>();
        for (Person person : people) {
            if (person.hasEmployeeIdMoreThan(lowestValidOffset, seeker)) {
                peoplePairs.add(new PeoplePair(seeker, person));
            }
        }
        return peoplePairs; // use a guava transform
    }
}
