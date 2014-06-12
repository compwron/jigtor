package jigtor.matcher;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.PeoplePair;
import jigtor.people.Person;
import jigtor.people.SponsorMatches;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ComparativeSkillMatcherTest {

    private ComparativeSkillMatcher comparativeSkillMatcher;
    private double timeAtCompany = 1.0;
    private Integer employeeNumber = 1;

    private SkillSet noSkills = new SkillSetBuilder().build();
    private Person frank = new Person("frank", timeAtCompany, noSkills, employeeNumber);
    private Person greg = new Person("greg", timeAtCompany, noSkills, employeeNumber);
    private Person henry = new Person("henry", timeAtCompany, scalaSkill(Knowledge.WantToKnow), employeeNumber);
    private Person ian = new Person("ian", timeAtCompany, scalaSkill(Knowledge.Knows), employeeNumber);

    @Before
    public void setUp() {
        comparativeSkillMatcher = new ComparativeSkillMatcher();
    }

    @Test
    public void shouldNotMatchTwoPeopleWithNoDeclaredSkills() {
        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(frank, newArrayList(greg)).allMatches();
        assertThat(peoplePairs, hasSize(0));
    }

    @Test
    public void shouldMatchPeopleWhenSeekerHasAWantToLearnAndPotentialMentorHasKnowledge() {
        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(henry, newArrayList(ian)).allMatches();
        assertThat(peoplePairs, Is.<List<PeoplePair>>is(newArrayList(new PeoplePair(henry, ian))));
    }

    @Test
    public void shouldNotMatchPeopleWhenSeekerHasMoreKnowledgeOfTheOnlyTopicThanPotentialMentor() {
        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(ian, newArrayList(henry)).allMatches();
        assertThat(peoplePairs, hasSize(0));
    }

    @Test
    public void moreCongruencyShouldBeABetterMatch() {
//        if a wants to know x and y, and b knows x and c knows x and y, rank c more highly
        SkillSet wantsTwoSkills = new SkillSetBuilder().withSkill("scala", Knowledge.WantToKnow).withSkill("clojure", Knowledge.WantToKnow).build();
        Person jasmine = new Person("Jasmine", timeAtCompany, wantsTwoSkills, employeeNumber);

        SkillSet hasSkillOne = new SkillSetBuilder().withSkill("scala", Knowledge.Knows).build();
        Person kristen = new Person("Kristen", timeAtCompany, hasSkillOne, employeeNumber);

        SkillSet hasSkillOneAndTwo = new SkillSetBuilder().withSkill("scala", Knowledge.Knows).withSkill("clojure", Knowledge.Knows).build();
        Person lakshi = new Person("Lakshi", timeAtCompany, hasSkillOneAndTwo, employeeNumber);


        ArrayList<Person> potentialSponsors = newArrayList(kristen, lakshi);
        SponsorMatches sponsorMatches = comparativeSkillMatcher.matchWithin(jasmine, potentialSponsors);

        assertThat(sponsorMatches.allMatches(), hasSize(2));
        assertThat(sponsorMatches.topMatch(), is(new PeoplePair(jasmine, lakshi)));

    }

    @Ignore("Not implemented yet")
    @Test
    public void shouldNotRecommendASponsorWhoDoesNotHaveMoreExperienceThanTheSponsee(){
        SkillSet wantsScala = new SkillSetBuilder().withSkill("scala", Knowledge.WantToKnow).build();
        SkillSet hasScala = new SkillSetBuilder().withSkill("scala", Knowledge.Knows).build();

        Person a = new Person("A", timeAtCompany, hasScala, employeeNumber);
        Person b = new Person("B", timeAtCompany + 1, hasScala, employeeNumber);
        Person c = new Person("C", timeAtCompany, wantsScala, employeeNumber);

        List<PeoplePair> sponsorMatches = comparativeSkillMatcher.matchWithin(c, newArrayList(a, b)).allMatches();
        assertThat(sponsorMatches, not(contains(new PeoplePair(c, a))));
        assertThat(sponsorMatches, contains(new PeoplePair(c, b)));
    }


    private SkillSet scalaSkill(Knowledge knowledge) {
        return getSkillSet(knowledge, "scala");
    }

    private SkillSet getSkillSet(Knowledge knowledge, String skillName) {
        return new SkillSetBuilder().withSkill(skillName, knowledge).build();
    }
}
