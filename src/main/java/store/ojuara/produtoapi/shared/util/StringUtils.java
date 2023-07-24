package store.ojuara.produtoapi.shared.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static String removerAcentos(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
