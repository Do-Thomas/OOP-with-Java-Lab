package data;

import java.util.Scanner;

public class AllEnum {

    enum Role {
//            R001("R001"), R002("R002"), ADMIN("ADMIN");

        R001, R002, ADMIN;
    }
//        public final String role;

    public static class inRole {

        Role role;

        public inRole(Role role) {
            this.role = role;
        }

        public void pickedRole() {
            switch (role) {
                case R001:
                    System.out.println(role);
                    break;
                case R002:
                    System.out.println(role);
                    break;
                case ADMIN:
                    System.out.println(role);
                    break;
            }
        }
    }

    enum Status {
        ENABLED, DISABLED;
//        public final String status;
    }

    public static class inStatus {

        Status status;

        public inStatus(Status status) {
            this.status = status;
        }

        public void pickStatus() {
            switch (status) {
                case ENABLED:
                    System.out.println(status);
                    break;
                case DISABLED:
                    System.out.println(status);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Test inRole
        System.out.print("Enter Role: ");
        String s = sc.nextLine();
        inRole Role1 = new inRole(Role.valueOf(s));
        Role1.pickedRole();

        // Test inStatus
        System.out.print("Enter Status: ");
        String sta = sc.nextLine();
        inStatus Sta1 = new inStatus(Status.valueOf(sta));
        Sta1.pickStatus();
    }
}
