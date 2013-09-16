import java.util.regex.*;

public class Main {

    /*
    * Поиск соответствия в строке
     */
    private static boolean validator (String patternString, String text){
        try{
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(text);

            boolean result = false;
            System.out.println("String: " + text + "\nPattern:" + patternString);

            // Найти следующее соответствие
            while (matcher.find()) {
                result = true;
                int count;

                // Количество соответствий
                if((count  = matcher.groupCount()) > 0)
                    System.out.println("Count:" + count);

                    for(int i=1; i<=count; i++)
                        System.out.println("Matched String " + i + ":" + matcher.group(i));
            }

            return result;
        } catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }

    public static void main(String[] args) {
        // Множественная выборка
        // System.out.println(validator("(\\w*)=(\\d*);", "x=15; y=45; z=75;"));

        // Начало строки
        // System.out.println(validator("^This", "This is ITIS!"));

        // Конец строки
        // System.out.println(validator("[!\\?\\.]$", "This is ITIS!"));

        // Ограничение по слову
        // System.out.println(validator("\\b(.*[s])\\b", "This is ITIS!"));

        // Жадный квантификатор
        // System.out.println(validator("I(.*)S\\b", "IT IS ITIS!"));

        // Ленивый квантификатор
        // System.out.println(validator("I(.*?)S", "IT IS ITIS!"));

        // Условия
        // System.out.println(validator("#([A-Fa-f\\d]{3}|[A-Fa-f\\d]{6})\\b", "#FE0011"));

    }
}
