/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.extension;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ExtensionUtils {

    public static String format(String str, String... params) {
        return String.format(str, (Object[]) params);
    }

    public static String md5(String str) {
        try {
            return Hex.encodeHexString(MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException nae) {
            throw new IllegalStateException(nae);
        }
    }

    public static String computeHMAC(String secret, String... parts) {
        if(parts == null || parts.length == 0) {
            return "";
        }
        var text = Arrays.stream(parts).map(StringUtils::trimToEmpty).collect(Collectors.joining(""));
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secret).hmacHex(text);
    }
}
