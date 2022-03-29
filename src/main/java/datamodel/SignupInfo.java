package datamodel;

import java.util.Objects;
import java.util.regex.*;

public class SignupInfo {
    private String name;
    private String email;
    private String countryOfResidence;
    private String passportNo;
    private String password;
    private String confirmPass;

    public SignupInfo(String name, String email, String countryOfResidence, String passportNo, String password, String confirmPass) {
        this.name = name;
        this.email = email;
        this.countryOfResidence = countryOfResidence;
        this.passportNo = passportNo;
        this.password = password;
        this.confirmPass = confirmPass;
    }

    private boolean checkEmail() {
        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private boolean checkPass() {
        int alphabet = 0, number = 0;
        for (int i = 0; i < password.length(); i++) {
            char z = password.charAt(i);
            if ((z >= 'a' && z <= 'z') || (z >= 'A' && z <= 'Z')) alphabet++;
            if (z >= '1' && z <= '9') number++;
        }
        return number > 0 && alphabet > 0 && password.length() > 8;
    }

    public String validateInfo() {
        String ret = "";

        if (name.length() == 0) {
            ret = "Name field is empty";
        } else if (name.length() > 128) {
            ret = "Name must be less than 128 characters";
        } else if (!checkEmail()) {
            ret = "Enter a valid email";
        } else if (!checkPass()) {
            ret = "Password must contain at least 8 letters, an alphabet and a number";
        } else if (!Objects.equals(password, confirmPass)) {
            ret = "Confirmation Mismatched";
        } else if (passportNo.length() == 0) {
            ret = "Enter your passport number";
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public String getPassword() {
        return password;
    }
}
