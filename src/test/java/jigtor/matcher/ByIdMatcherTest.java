package jigtor.matcher;


import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import jigtor.people.PeoplePair;
import jigtor.people.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ByIdMatcherTest {

    private ByIdMatcher byIdMatcher;
    private SkillSet skills = new SkillSet(new HashMap<String, Knowledge>());
    private Person alice = new Person("Alice", 0.2, skills, 5);
    private Person bob = new Person("Bob", 0.2, skills, 4);
    private Person charlie = new Person("Charlie", 10, skills, 1);
    private Person dave = new Person("Dave", 0.3, skills, 3);
    private Person edward = new Person("Edward", 1.2, skills, 2);

    private double lowestValidOffset = 1.0;

    @Before
    public void setUp() {
        byIdMatcher = new ByIdMatcher();
    }

    @Test
    public void shouldNotSuggestLessExperiencedPersonAsMatch() {
        List<Person> people = Arrays.asList(bob);

        List<PeoplePair> peoplePairs = byIdMatcher.matchByClosestOffset(alice, people, lowestValidOffset);
        assertThat(peoplePairs, hasSize(0));
    }

    @Test
    public void shouldSuggestMoreExperiencedPersonAsMatch() {
        List<Person> people = Arrays.asList(charlie);

        List<PeoplePair> peoplePairs = byIdMatcher.matchByClosestOffset(alice, people, lowestValidOffset);
        assertThat(peoplePairs, hasSize(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldNotSuggestMoreExperiencedPersonWithNotEnoughMoreExperienceAsMatch(){
        List<Person> people = Arrays.asList(charlie, bob, dave);

        List<PeoplePair> peoplePairs = byIdMatcher.matchByClosestOffset(alice, people, lowestValidOffset);
        assertThat(peoplePairs.size(), is(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldSuggestAllSuitablePeopleAsMatches(){
        List<Person> people = Arrays.asList(charlie, edward);

        List<PeoplePair> peoplePairs = byIdMatcher.matchByClosestOffset(alice, people, lowestValidOffset);
        assertThat(peoplePairs, hasSize(2));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
        assertThat(peoplePairs.get(1), is(new PeoplePair(alice, edward)));
    }
}
