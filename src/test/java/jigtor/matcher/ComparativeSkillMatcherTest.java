package jigtor.matcher;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.PeoplePair;
import jigtor.people.Person;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ComparativeSkillMatcherTest {

    private ComparativeSkillMatcher comparativeSkillMatcher;
    private SkillSet noSkills = new SkillSetBuilder().build();

    @Before
    public void setUp() {
        comparativeSkillMatcher = new ComparativeSkillMatcher();
    }

    @Test
    public void shouldNotMatchTwoPeopleWithNoDeclaredSkills() {
        Person frank = new Person("frank", 1.0, noSkills, 500);
        Person greg = new Person("greg", 1.0, noSkills, 501);

        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(frank, newArrayList(greg));
        assertThat(peoplePairs, hasSize(0));
    }

    private Person henry() {
        return new Person("henry", 1.0, scalaSkill(Knowledge.WantToKnow), 502);
    }

    private Person ian() {
//        Names should be unique in the entire test suite. (Trying human-name-tracking  / BA "profiles")
        return new Person("ian", 1.0, scalaSkill(Knowledge.Knows), 502);
    }

    @Test
    public void shouldMatchPeopleWhenSeekerHasAWantToLearnAndPotentialMentorHasKnowledge() {
        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(henry(), newArrayList(ian()));
        assertThat(peoplePairs, Is.<List<PeoplePair>>is(newArrayList(new PeoplePair(henry(), ian()))));
    }

    @Test
    public void shouldNotMatchPeopleWhenSeekerhasMoreKnowledgeOfTheOnlytopicThanPotentialMentor() {
        List<PeoplePair> peoplePairs = comparativeSkillMatcher.matchWithin(ian(), newArrayList(henry()));
        assertThat(peoplePairs, hasSize(0));
    }


    private SkillSet scalaSkill(Knowledge knowledge) {
        return new SkillSetBuilder().withSkill("scala", knowledge).build();
    }
}
