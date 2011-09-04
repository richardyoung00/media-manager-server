package MediaManager.util;

public class Util {

    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

    public static String jMessage(String msg) {
        StringBuilder s = new StringBuilder();
        s.append("<script type='text/javascript'>alert('");
        s.append(msg);
        s.append("');</script>");
        return s.toString();
    }

    public static boolean stringContains(String input, String... tests) {
        for (String t : tests) {
            if (input.contains(t)) {
                return true;
            }
        }
        return false;

    }
}
