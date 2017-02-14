package demo;

import scannerUtil.ScanManager;

/**
 * Created by Anastasiya on 12.02.2017.
 */
public class Implementation {
    private static String templateString;
    private static String variablesString;
    private static final String TEMPLATE_START = "${";
    private static final String TEMPLATE_END = "}";

    public static void initialization() {
        System.out.println("Введите шаблон: ");
        templateString = ScanManager.consoleReadString();

        System.out.println("Введите переменные: ");
        variablesString = ScanManager.consoleReadString();
        implementation();
    }

    public static void implementation() {
        int indexStart;
        int indexEnd = 0;
        long countIndex;
        String stringForReplace;
        String result = templateString;

        countIndex = templateString.chars().filter(ch -> ch == '$').count();
        String[] array = new String[((int) countIndex)];

        for (int i = 0; i < countIndex; i++) {
            indexStart = templateString.indexOf(TEMPLATE_START, indexEnd);
            indexEnd = templateString.indexOf(TEMPLATE_END, indexStart);
            array[i] = templateString.substring(indexStart, indexEnd + TEMPLATE_END.length());

            stringForReplace = array[i].substring(TEMPLATE_START.length(), array[i].length() - 1);

            if (variablesString.contains(stringForReplace)) {
                result = result.replace(array[i], getStringForReplace(stringForReplace));
            } else result = result.replace(array[i], "Error");
        }
        System.out.println("Результат: " + result);
    }

    private static String getStringForReplace(String string) {
        char[] charArray = variablesString.toCharArray();
        int indexStart = 0;
        int indexEnd = charArray.length;

        for (int i = (variablesString.indexOf(string) + string.length()); i < charArray.length; i++) {
            if (!((charArray[i] == ' ') || (charArray[i] == ',') || (charArray[i] == '”') || (charArray[i] == '"') || (charArray[i] == '='))) {
                if (indexStart == 0) {
                    indexStart = i;
                }
            } else if (indexStart != 0) {
                indexEnd = i;
                break;
            }
        }
        return variablesString.substring(indexStart, indexEnd);
    }
}
