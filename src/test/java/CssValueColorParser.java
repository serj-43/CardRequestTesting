import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssValueColorParser {

    public static Color parse(String input)
    {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = c.matcher(input);

        if (m.matches())
        {
            return new Color(Integer.valueOf(m.group(1)),  // r
                    Integer.valueOf(m.group(2)),  // g
                    Integer.valueOf(m.group(3))); // b
        }

        return null;
    }
}
