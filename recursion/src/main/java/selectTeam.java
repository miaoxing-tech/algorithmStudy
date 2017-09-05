import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: miaoxing
 * DATE:    2017/6/16
 */
public class SelectTeam {

    private final Joiner COMMA_JOINER = Joiner.on(",").skipNulls();

    public void selectTeam(List<String> persons, int teamSize) {
        List<String> result = Lists.newArrayListWithExpectedSize(teamSize);
        selectTeam(persons, teamSize, result);
    }

    private void selectTeam(List<String> persons, int teamSize, List<String> result) {
        if (teamSize == 0) {
            System.out.println("新队伍: " + COMMA_JOINER.join(result));
            return;
        }

        String tmp = persons.get(0);
        result.add(tmp);
        selectTeam(persons.subList(1, persons.size()), teamSize - 1, result);
        result.remove(tmp);

        if (persons.size() > teamSize) {
            selectTeam(persons.subList(1, persons.size()), teamSize, result);
        }
    }

    public static void main(String[] args) {
        SelectTeam selectTeam = new SelectTeam();
        List<String> persons = Lists.newArrayList("A", "B", "C", "D", "E");
        selectTeam.selectTeam(persons, 3);
    }

}
