package datamodel;

import database.repository.UserRepository;

import java.util.Objects;
import java.util.regex.*;

public class SignupInfo extends UserInfo {

    private final String confirmPass;

    public SignupInfo(String name, String email, String countryOfResidence, String passportNo, String password, String confirmPass) {
        super(name, email, countryOfResidence, passportNo, password);
        this.confirmPass = confirmPass;
    }

    private boolean checkName() {
        if (getName().length() == 0 || getName().length() > 128) {
            return false;
        }

        String nameRegex = "^[A-Za-z\\s]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(getName());
        return matcher.find();
    }

    private boolean checkEmail() {
        if (getEmail().length() == 0) return false;
        String emailRegex = "^([\\w-]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(getEmail());
        return matcher.find();
    }

    private boolean checkPass() {
        if (getPassword().length() == 0) return false;
        int alphabet = 0, number = 0;
        for (int i = 0; i < getPassword().length(); i++) {
            char z = getPassword().charAt(i);
            if ((z >= 'a' && z <= 'z') || (z >= 'A' && z <= 'Z')) alphabet++;
            if (z >= '1' && z <= '9') number++;
        }
        return number > 0 && alphabet > 0 && getPassword().length() >= 8;
    }

    public String validateInfo() {
        String warningString = "";

        if (!checkName()) {
            warningString = "Invalid name.(Name must be less than 128 characters)";
        } else if (!checkEmail()) {
            warningString = "Enter a valid email";
        } else if (getCountry().length() == 0) {
            warningString = "Enter Your Country Of Residence";
        } else if (getPassportNo().length() == 0) {
            warningString = "Passport Field is Empty";
        } else if (!checkPass()) {
            warningString = "Password must contain at least 8 letters, an alphabet and a number";
        } else if (!Objects.equals(getPassword(), confirmPass)) {
            warningString = "Confirmation Mismatched";
        }

        if (warningString.length() == 0) {
            new UserRepository().insert(toArray());
        }

        return warningString;
    }
}
