import java.util.Arrays;
import java.util.regex.*;

public class Main {

    /*
    * Пример кода проверки соответствия строки шаблону
    */
    private static void example(){
        Pattern pattern = Pattern.compile("ITIS");
        Matcher matcher = pattern.matcher("This is ITIS!");

        System.out.print("Find: ");
        // Поиск соответствия шаблону в строку (поиск подстроки)
        if(matcher.find()){
            System.out.println(matcher.group()); // Вывести всю строку
        }else{
            System.out.println("Not matched");
        }

        System.out.print("Matches: ");
        // Строгая проверка соответствия строки шаблону
        if(matcher.matches()){
            System.out.println(matcher.group()); // Вывести всю строку
        }else{
            System.out.println("Not matched");
        }

    }

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
            while (matcher.find()){
                result = true;
                int count;

                // Количество соответствий
                if((count  = matcher.groupCount()) > 0)
                    // group() - показать соответствие
                    // group(0) - вся строка соответсвтия
                    // group(int N) - найденное соответствие (то что в скобках)
                    System.out.println("Count:" + count + "\nMatched String:" + matcher.group(count));
            }

            return result;
        } catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }


    /*
    * Поиск соответствия и замена в строке
     */
    private static boolean replacer (String patternString, String text, String replaceTo){
        try{
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(text);

            boolean result = false;
            System.out.println("String: " + text + "\nPattern:" + patternString);

            while (matcher.find()){
                System.out.println("New String: " + matcher.replaceAll(replaceTo));
                result = true;
            }

            return result;
        } catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }

    /*
    * Поиск соответствия и замена в строке
     */
    private static boolean splitter (String splitterString, String text){
        try{
            Pattern pattern = Pattern.compile(splitterString);

            String[] words = pattern.split(text);
            System.out.println(Arrays.toString(words));
            return true;

        } catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }

    private static boolean findHexColor(String hexColor){
        Pattern pattern = Pattern.compile("^#[A-Fa-f0-9]{6}$");
        Matcher matcher = pattern.matcher(hexColor);

        return matcher.matches();
    }

    public static void main(String[] args) {
       //   example();
       //   System.out.println(validator("I(.*)S", "++ITIS++"));
       //   System.out.println(validator("I(.*)S", "IT IS ITIS!"));
       //   System.out.println(replacer("I(.*)S", "What is it? IT IS ITIS!", "-"));
       //   System.out.println(splitter(" ", "What is it? IT IS ITIS!"));
       //   System.out.println(splitter("I(.*)S", "What is it? IT IS ITIS!"));
       //   System.out.println(findHexColor("#FFEE00"));
       //   System.out.println(findHexColor("#FE0"));
       //   System.out.println(findHexColor("12345F"));

    }
}
