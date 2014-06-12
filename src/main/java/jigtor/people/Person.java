package jigtor.people;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@EqualsAndHashCode
@Getter
public class Person {
    private String name;
    private final Double timeAtCompany;
    private final SkillSet skills;
    private Integer employeeId;

    public Person(String name, double timeAtCompany, SkillSet skills, Integer employeeId) {
        this.name = name;
        this.timeAtCompany = timeAtCompany;
        this.skills = skills;
        this.employeeId = employeeId;
    }

    public boolean hasMoreExperienceThan(Double requiredExperience) {
        return timeAtCompany >= requiredExperience;
    }

    public boolean hasEmployeeIdMoreThan(double lowestValidOffset, Person seeker) {
        return this.employeeId + lowestValidOffset <= seeker.getEmployeeId();
    }

    public boolean knowsWhatSeekerWantsToKnow(Person seeker) {
        List<String> seekerWantsToKnow = seeker.wantsToKnow();

        for (String knowledge : knows()) {
            if (seekerWantsToKnow.contains(knowledge)) {
                return true;
            }
        }
        return false;
    }

    private List<String> knows() {
        List<String> knows = newArrayList();
        for (String skill : skills.names()) {
            if (skills.knows(skill)) {
                knows.add(skill);
            }
        }
        return knows;
    }

    public List<String> wantsToKnow() {
        List<String> wantsToKnow = newArrayList();
        for (String skill : skills.names()) {
            if (skills.wantsToKnow(skill)) {
                wantsToKnow.add(skill);
            }
        }
        return wantsToKnow;
    }

    @Override
    public String toString() {
        return this.name + " (" + timeAtCompany + " years) " + this.skills;
    }

    public Integer canLearnFrom(Person mentor) {
        List<String> seekerWantsToKnow = mentor.knows();
        Integer knowledgeMatch = 0;
        List<String> wantsToKnow = wantsToKnow();
        for (String knowledge : wantsToKnow) {
            if (seekerWantsToKnow.contains(knowledge)) {
                knowledgeMatch += (Knowledge.Knows.getKnowledgeAmount() - Knowledge.WantToKnow.getKnowledgeAmount());
            }
        }
        return knowledgeMatch;
    }
}
