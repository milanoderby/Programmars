import java.io.*;
import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> phoneNumberSet = new HashSet<>();
        for (String phoneNumber : phone_book) {
            phoneNumberSet.add(phoneNumber);
        }

        for (String phoneNumber : phone_book) {
            StringBuilder prefix = new StringBuilder("");
            for (int i = 0; i < phoneNumber.length() - 1; i++) {
                prefix.append(phoneNumber.charAt(i));
                if (phoneNumberSet.contains(prefix.toString())) {
                    return false;
                }
            }
        }
        return true;
    }
}