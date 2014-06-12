package jigtor.matcher;

import jigtor.criteria.SkillSet;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.PeoplePair;
import jigtor.people.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TemporalMatcherTest {

    private TemporalMatcher temporalMatcher;
    private SkillSet skills = new SkillSetBuilder().build();
    private Person alice = new Person("alice", 0.2, skills, 401);
    private Person bob = new Person("bob", 0.2, skills, 400);
    private Person charlie = new Person("charlie", 10, skills, 1);
    private Person dave = new Person("dave", 0.3, skills, 399);
    private Person edward = new Person("edward", 1.2, skills, 200);

    private Double lowestValidTimeOffset = 1.0;
    private Integer lowestValidEmployeeIdOffset = 20;

    @Before
    public void setUpByTimeAtCompanyByTimeAtCompany() {
        temporalMatcher = new TemporalMatcher();
    }

    @Test
    public void shouldNotSuggestLessExperiencedPersonAsMatchByTimeAtCompanyByTimeAtCompany() {
        List<Person> people = Arrays.asList(bob);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByTimeAtCompany(alice, people, lowestValidTimeOffset);
        assertThat(peoplePairs, hasSize(0));
    }

    @Test
    public void shouldSuggestMoreExperiencedPersonAsMatchByTimeAtCompanyByTimeAtCompany() {
        List<Person> people = Arrays.asList(charlie);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByTimeAtCompany(alice, people, lowestValidTimeOffset);
        assertThat(peoplePairs, hasSize(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldNotSuggestMoreExperiencedPersonWithNotEnoughMoreExperienceAsMatchByTimeAtCompany() {
        List<Person> people = Arrays.asList(charlie, bob, dave);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByTimeAtCompany(alice, people, lowestValidTimeOffset);
        assertThat(peoplePairs, hasSize(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldSuggestAllSuitablePeopleAsMatchesByTimeAtCompany() {
        List<Person> people = Arrays.asList(charlie, edward);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByTimeAtCompany(alice, people, lowestValidTimeOffset);
        assertThat(peoplePairs, hasSize(2));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
        assertThat(peoplePairs.get(1), is(new PeoplePair(alice, edward)));
    }

    @Test
    public void shouldNotSuggestLessExperiencedPersonAsMatchByEmployeeId() {
        List<Person> people = Arrays.asList(bob);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByEmployeeId(alice, people, lowestValidEmployeeIdOffset);
        assertThat(peoplePairs, hasSize(0));
    }

    @Test
    public void shouldSuggestMoreExperiencedPersonAsMatchByEmployeeId() {
        List<Person> people = Arrays.asList(charlie);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByEmployeeId(alice, people, lowestValidEmployeeIdOffset);
        assertThat(peoplePairs, hasSize(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldNotSuggestMoreExperiencedPersonWithNotEnoughMoreExperienceAsMatchByEmployeeId() {
        List<Person> people = Arrays.asList(charlie, bob, dave);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByEmployeeId(alice, people, lowestValidEmployeeIdOffset);
        assertThat(peoplePairs, hasSize(1));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
    }

    @Test
    public void shouldSuggestAllSuitablePeopleAsMatchesByEmployeeId() {
        List<Person> people = Arrays.asList(charlie, edward);

        List<PeoplePair> peoplePairs = temporalMatcher.matchByEmployeeId(alice, people, lowestValidEmployeeIdOffset);
        assertThat(peoplePairs, hasSize(2));
        assertThat(peoplePairs.get(0), is(new PeoplePair(alice, charlie)));
        assertThat(peoplePairs.get(1), is(new PeoplePair(alice, edward)));
    }


}
