package datamodel;

import database.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.*;

public class SignupInfo {
    private final String name;
    private final String email;
    private final String countryOfResidence;
    private final String passportNo;
    private final String password;
    private final String confirmPass;

//    private final ArrayList<String> user_info = new ArrayList<>();

    public SignupInfo(String name, String email, String countryOfResidence, String passportNo, String password, String confirmPass) {

        this.name = name;
        this.email = email;
        this.countryOfResidence = countryOfResidence;
        this.passportNo = passportNo;
        this.password = password;
        this.confirmPass = confirmPass;
    }

    private boolean checkEmail() {
        String emailRegex = "^([\\w-]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
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
        return number > 0 && alphabet > 0 && password.length() >= 8;
    }

    public String validateInfo() {
        String warningString = "";

        if (name.length() == 0) {
            warningString = "Name field is empty";
        } else if (name.length() > 128) {
            warningString = "Name must be less than 128 characters";
        } else if (countryOfResidence.length() == 0) {
            warningString = "Enter Your Country Of Residence";
        } else if (!checkEmail()) {
            warningString = "Enter a valid email";
        } else if (!checkPass()) {
            warningString = "Password must contain at least 8 letters, an alphabet and a number";
        } else if (!Objects.equals(password, confirmPass)) {
            warningString = "Confirmation Mismatched";
        } else if (passportNo.length() == 0) {
            warningString = "Enter your passport number";
        }

        if (warningString.length() == 0) {
//            user_info.addAll(List.of(new String[]{
//                    name,email,countryOfResidence,passportNo,password,confirmPass
//            }));
            new UserRepository().insert(new String[]{
                    name, email, countryOfResidence, passportNo, password
            });
        }

        return warningString;
    }

//    public ArrayList<String> getUser_info() {
//        return user_info;
//    }
}
