package jigtor.criteria;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

@EqualsAndHashCode
public class SkillSet {

    private HashMap<String, Knowledge> skills;

    public SkillSet(HashMap<String, Knowledge> skills) {
        this.skills = skills;
    }

    public Set<String> names() {
        return skills.keySet();
    }

    private List<String> knows() {
        return newArrayList(Collections2.filter(names(), knowsSkill()));
    }

    public boolean knows(String skill) {
        return skills.get(skill).equals(Knowledge.Knows);
    }

    private Predicate<? super String> knowsSkill() {
        return new Predicate<String>() {
            @Override
            public boolean apply(String name) {
                return knows(name);
            }
        };
    }

    private List<String> wantsToKnow() {
        return newArrayList(Collections2.filter(names(), wantsToKnowSkill()));
    }

    public boolean wantsToKnow(String skill) {
        return skills.get(skill).equals(Knowledge.WantToKnow);
    }

    private Predicate<? super String> wantsToKnowSkill() {
        return new Predicate<String>() {
            @Override
            public boolean apply(String name) {
                return wantsToKnow(name);
            }
        };
    }

    @Override
    public String toString() {
        return "knows: " + knows() + " and wants to know: " + wantsToKnow();
    }


}
