import java.util.ArrayList;
import java.util.List;

public class optimization {

    private static String OptimizationChallenge(String str) {
        List<Character> variables = new ArrayList<>();
        for (char c : str.toCharArray()) if (Character.isLetter(c) && !variables.contains(c)) variables.add(c);

        int numVariables = variables.size();

        for (int i = 0; i < (1 << numVariables); i++) {
            boolean[] assignment = new boolean[numVariables];
            for (int j = 0; j < numVariables; j++) assignment[j] = (i & (1 << j)) != 0;

            if (evaluateExpression(str, variables, assignment)) return "yes";
        }

        return "no";
    }

    private static boolean evaluateExpression(String str, List<Character> variables, boolean[] assignment) {
        StringBuilder expression = new StringBuilder(str);
        for (int i = 0; i < variables.size(); i++) {
            char variable = variables.get(i);
            char value = assignment[i] ? 'T' : 'F';
            expression = new StringBuilder(expression.toString().replace(variable, value));
        }

        while (expression.toString().contains("(")) {
            int startIndex = expression.lastIndexOf("(");
            int endIndex = expression.indexOf(")", startIndex);
            String subExpression = expression.substring(startIndex + 1, endIndex);
            boolean subResult = evaluateSubExpression(subExpression);
            expression.replace(startIndex, endIndex + 1, subResult ? "T" : "F");
        }

        return evaluateSubExpression(expression.toString());
    }

    private static boolean evaluateSubExpression(String subExpression) {
        subExpression = subExpression.replace("~T", "F").replace("~F", "T");
        for (int i = 0; i < subExpression.length(); i++)
            if (subExpression.charAt(i) == '&' || subExpression.charAt(i) == '|') {
                char operator = subExpression.charAt(i);
                boolean left = subExpression.charAt(i - 1) == 'T';
                boolean right = subExpression.charAt(i + 1) == 'T';

                if (operator == '&')
                    subExpression = subExpression.replace(subExpression.substring(i - 1, i + 2), left && right ? "T" : "F");
                else
                    subExpression = subExpression.replace(subExpression.substring(i - 1, i + 2), left || right ? "T" : "F");

                i = 0;  // Restart the loop after each replacement
            }

        return subExpression.equals("T");
    }

    public static void main(String[] args) {
        System.out.println(OptimizationChallenge("(a&b)|c")); // Output: yes
        System.out.println(OptimizationChallenge("((a&c)&~a)")); // Output: no
    }
}
