package jigtor.criteria;

import java.util.HashMap;

public class SkillSetBuilder {
    private HashMap<String, Knowledge> skills = new HashMap<String, Knowledge>();

    public SkillSetBuilder withSkill(String skill, Knowledge knowledge) {
        skills.put(skill, knowledge);
        return this;
    }

    public SkillSet build() {
        return new SkillSet(skills);
    }
}
