import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

public class PasswordChecker {
    private static int dig = 1;
    private static int up = 1;
    private static int low = 1;
    private static int spec = 1;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PasswordChecker.class.getName());

    public static void main(String[] args) {
        
        out.print("Hi, Some few rules of the house\n" +
                "password should exist\n" +
                "password should have at least one lowercase letter\n" +
                "password should have at least one uppercase letter\n" +
                "password should at least have one digit\n" +
                "password should have at least one special character \n" +
                "password should be longer than 8 characters\n");
        out.println("-------------------------------------------------------");

        out.print("Please enter a Password: \n");
        Scanner input = new Scanner(in);
        String password;
        password = input.nextLine();
        out.println((PasswordIsValid(password)));
        out.println((PasswordIsOk(password)));

    }
    public static boolean PasswordIsOk(String password) {
        final Logger logger = LogManager.getLogger(PasswordChecker.class.getName());
        if (dig > 1 && low > 1 && password.length() == 8) {
            out.println("Password is Ok!");
            logger.debug ("User Password Ok");
        } else if (!(dig > 1) && !(low > 1) && !(password.length() == 8)) {
            out.println("password is not Ok !");
            logger.debug ("User Password is not Ok");
        }
        return true;
    }

    public static String PasswordIsValid(String password) {
        final Logger logger = LogManager.getLogger(PasswordChecker.class.getName());
        try {
            if (password.length() > 0 && password.length() >= 8) {
                char character;
                int dig = 1, up = 1, low = 1, spec = 1;
                for (int i = 0; i < password.length(); i++) {
                    character = password.charAt(i);
                    if (Character.isDigit(character)) {
                        dig++;
                    } else if (Character.isUpperCase(character)) {
                        up++;
                    } else if (Character.isLowerCase(character)) {
                        low++;
                    } else if (!Character.isLowerCase(character) && !Character.isUpperCase(character) && !Character.isDigit(character)) {
                        spec++;
                    }
                }
                if (dig > 1 && up > 1 && low > 1 && spec > 1) {
                    logger.info ("User Password Valid");
                    throw new Exception("The password is Valid");
                } else if (dig == 1) {
                    logger.error ("User Password does not have a number");
                    throw new Exception("The password must have at least one digit");
                } else if (up == 1) {
                    logger.error ("User Password does not have upper case");
                    throw new Exception("The password must have at least one uppercase");
                } else if (low == 1) {
                    logger.error ("User Password does not have lower case");
                    throw new Exception("The password must have at least one lowercase");
                } else if (spec == 1) {
                    Pattern sPattern = Pattern.compile("[a-zA-Z0-9]*");
                    Matcher sMatcher = sPattern.matcher(password);
                    if (sMatcher.matches()) {
                        logger.error ("User Password does not have a special character");
                        throw new Exception ("The password must have at least one special character");
                    }
                }
            } else {
                logger.error ("User entered Password that is less than Minimum");
                throw new Exception("Password is less than Minimum");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "Always follow the rules";
    }
}
