package inventory.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Tools {

    public String centerString(int width, String str) {
        return String.format("%-" + width + "s", String.format("%" + (str.length() + (width - str.length()) / 2) + "s", str));
    }

    public boolean regexChecker(String regex, String string){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

}
