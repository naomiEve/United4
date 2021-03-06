package us.dangeru.la_u_ncher413.API;

import android.util.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.dangeru.la_u_ncher413.utils.P;

/**
 * Utilities file for detecting what type of page the user is on from the URL
 */

public final class URLUtils {
    /**
     * Detects whether the url is in the form /:board/thread/:thread, and if so, extracts the relevant
     * components and returns them
     * @param url the url to check
     * @return <:board, :thread_id> if the url is a thread, otherwise null
     */
    public static Pair<String, Integer> isThread(String url) {
        url = url.replace(P.get("awoo_endpoint"), "");
        if (BoardsList.boards == null) return null;
        for (BoardsList.Board board : BoardsList.boards) {
            Pattern pattern = Pattern.compile("^/" + board.name + "/thread/(\\d+)/?");
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return new Pair<>(board.name, Integer.valueOf(matcher.group(1)));
            }
        }
        return null;
    }
    /**
     * Detects whether the url is in the form /:board, and if so, extracts the board name and
     * returns it
     * @param url the url to check
     * @return the board name if the url is a board, otherwise null
     */
    public static String isBoard(String url) {
        url = url.replace(P.get("awoo_endpoint"), "");
        if (BoardsList.boards == null) return null;
        for (BoardsList.Board board : BoardsList.boards) {
            if (url.matches("^/" + board.name + "/?")) {
                return board.name;
            }
        }
        return null;
    }
    /**
     * Detects whether the url is in the form /ip/:addr, and if so, extracts the viewed address and
     * returns it
     * @param url the url to check
     * @return the ip address if the url is an ip page, otherwise null
     */
    public static String isIpList(String url) {
        url = url.replace(P.get("awoo_endpoint"), "");
        Pattern pattern = Pattern.compile("^/ip/(.*)/?");
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) return matcher.group(1);
        return null;
    }
    private URLUtils() {}
}
