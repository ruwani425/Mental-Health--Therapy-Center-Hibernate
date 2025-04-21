package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.test;

import org.mindrot.jbcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {
//        String password = "12345";
//        String hashByCriptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(hashByCriptedPassword);
        String p = "$2a$10$79BXk5odiojKDfiHB68rp.Hu1fRNp.iVovr98okPbz.DsaA9/sWIa";
        String password = "12345";

        boolean isTrue=BCrypt.checkpw(password,p);
        System.out.println(isTrue);
    }
}
