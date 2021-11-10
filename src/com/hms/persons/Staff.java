package com.hms.persons;

import java.util.*;
import java.io.*;
import java.nio.charset.MalformedInputException;

public class Staff extends Person {
    protected String type;
    protected String salary;
    protected int workingDays;
    protected String loginId;
    protected String password;

    public static void main(String[] args) {
        Staff staff = new Staff();
        staff.addPerson(10, 200);
        staff.addPerson(10, 200);
        staff.getDetails();
    }

    public Staff() {
        id = -1;
        cat = "Staff";
    }

    public Staff(Staff s) {
        this.assign(s);
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(18, 60);
        if (this.id == -2)
            return;
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter type of staff:\n");
        inp = cin.nextLine();
        type = inp;
        System.out.print("\nEnter salary of staff:\n");
        inp = cin.nextLine();
        salary = inp;
        workingDays = 0;
        cat = "Staff";
        if (com.hms.Hotel.staffList.entrySet().size() > 0) {
            this.id = com.hms.Hotel.staffList.lastEntry().getKey() + 1;
            com.hms.Hotel.staffList.put(this.id, new Staff(this));
        } else
            com.hms.Hotel.staffList.put(1, new Staff(this));
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;

        System.out.println("\nStaff Details:");
        super.printDetails();
        System.out.print("Type            :" + type + "\n");
        System.out.print("Salary          :" + salary + "\n");
        System.out.print("Working Days    :" + workingDays + "\n");
        return;
    }

    public void assign(Staff s) {
        super.assign(s);
        this.type = s.type;
        this.salary = s.salary;
        this.workingDays = s.workingDays;
        this.loginId = s.loginId;
        this.password = s.password;
        return;
    }

    public void getDetails() {
        Scanner cin = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            int opt = 0;
            String inp;
            System.out.print(
                    "\nSearch using: (select one of the following options)\n1. ID\n2. Name\n3. Mobile Number\n4. Type\n");
            inp = cin.next();
            inp += cin.nextLine();
            opt = Integer.parseInt(inp);
            while (opt < 1 || opt > 4) {
                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                inp = cin.next();
                inp += cin.nextLine();
                opt = Integer.parseInt(inp);
            }
            switch (opt) {

            case 1:
                int reqId = 0;
                System.out.print("\nEnter ID:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqId = Integer.parseInt(inp);
                if (com.hms.Hotel.staffList.containsKey(reqId)) {
                    this.assign(com.hms.Hotel.staffList.get(reqId));
                    done = true;
                    break;
                } else {
                    System.out.print("\nNo matching record found!\n");
                    System.out.print("\nTry again? (Y = Yes | N = No)\n");
                    inp = cin.next();
                    inp += cin.nextLine();
                    while (!inp.equals("Y") && !inp.equals("N")) {
                        System.out.print("\nInvalid Choice!\nEnter again:\n ");
                        inp = cin.next();
                        inp += cin.nextLine();
                    }
                    if (inp.equals("N")) {
                        done = true;
                        break;
                    }
                }
                break;
            case 2:
                String reqName = "";
                System.out.print("\nEnter Name:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqName = inp;
                int found = 0;
                TreeMap<Integer, Staff> MatchingRecords = new TreeMap<Integer, Staff>();
                for (Map.Entry<Integer, Staff> entry : com.hms.Hotel.staffList.entrySet())
                    if (entry.getValue().name.equals(reqName)) {
                        MatchingRecords.put(entry.getKey(), entry.getValue());
                        found++;
                    }
                if (found == 0) {
                    System.out.print("\nNo matching record found!\n");
                    System.out.print("\nTry again? (Y = Yes | N = No)\n");
                    inp = cin.next();
                    inp += cin.nextLine();
                    while (!inp.equals("Y") && !inp.equals("N")) {
                        System.out.print("\nInvalid Choice!\nEnter again:\n ");
                        inp = cin.next();
                        inp += cin.nextLine();
                    }
                    if (inp.equals("N")) {
                        done = true;
                        break;
                    }
                } else {
                    System.out.print("\nMatching records:\n");
                    for (Map.Entry<Integer, Staff> entry : MatchingRecords.entrySet()) {
                        entry.getValue().printDetails();
                        System.out.print("\n");
                    }
                    Boolean done1 = false;
                    while (!done1) {
                        System.out.print("\nEnter the ID of required staff from the list above:\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        id = Integer.parseInt(inp);
                        if (!MatchingRecords.containsKey(id)) {
                            System.out.print("\nEntered ID doesn't match ID of any staff from the list above!\n");
                            System.out.print("\nTry again? (Y = Yes | N = No)\n");
                            inp = cin.next();
                            inp += cin.nextLine();
                            while (!inp.equals("Y") && !inp.equals("N")) {
                                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                                inp = cin.next();
                                inp += cin.nextLine();
                            }
                            if (inp.equals("N")) {
                                done1 = true;
                                break;
                            }
                        } else {
                            this.assign(MatchingRecords.get(id));
                            done1 = done = true;
                            break;
                        }
                    }
                    if (done)
                        break;
                    else {
                        System.out.print("\nNo matching record found!\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }
                }
                break;
            case 3:
                String reqMobNumber = "";
                System.out.print("\nEnter Mobile Number:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqMobNumber = inp;
                int found1 = 0;
                TreeMap<Integer, Staff> MatchingRecords1 = new TreeMap<Integer, Staff>();
                for (Map.Entry<Integer, Staff> entry : com.hms.Hotel.staffList.entrySet())
                    if (entry.getValue().mobNumber.equals(reqMobNumber)) {
                        MatchingRecords1.put(entry.getKey(), entry.getValue());
                        found1++;
                    }
                if (found1 == 0) {
                    System.out.print("\nNo matching record found!\n");
                    System.out.print("\nTry again? (Y = Yes | N = No)\n");
                    inp = cin.next();
                    inp += cin.nextLine();
                    while (!inp.equals("Y") && !inp.equals("N")) {
                        System.out.print("\nInvalid Choice!\nEnter again:\n ");
                        inp = cin.next();
                        inp += cin.nextLine();
                    }
                    if (inp.equals("N")) {
                        done = true;
                        break;
                    }
                } else {
                    System.out.print("\nMatching records:\n");
                    for (Map.Entry<Integer, Staff> entry : MatchingRecords1.entrySet()) {
                        entry.getValue().printDetails();
                        System.out.print("\n");
                    }
                    Boolean done2 = false;
                    while (!done2) {
                        System.out.print("\nEnter the ID of required staff from the list above:\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        id = Integer.parseInt(inp);
                        if (!MatchingRecords1.containsKey(id)) {
                            System.out.print("\nEntered ID doesn't match ID of any staff from the list above!\n");
                            System.out.print("\nTry again? (Y = Yes | N = No)\n");
                            inp = cin.next();
                            inp += cin.nextLine();
                            while (!inp.equals("Y") && !inp.equals("N")) {
                                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                                inp = cin.next();
                                inp += cin.nextLine();
                            }
                            if (inp.equals("N")) {
                                done2 = true;
                                break;
                            }
                        } else {
                            this.assign(MatchingRecords1.get(id));
                            done2 = done = true;
                            break;
                        }
                    }
                    if (done)
                        break;
                    else {
                        System.out.print("\nNo matching record found!\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }

                }
                break;
            case 4:
                String reqType = "";
                System.out.print("\nEnter Type:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqType = inp;
                int found2 = 0;
                TreeMap<Integer, Staff> MatchingRecords2 = new TreeMap<Integer, Staff>();
                for (Map.Entry<Integer, Staff> entry : com.hms.Hotel.staffList.entrySet())
                    if (entry.getValue().type.equals(reqType)) {
                        MatchingRecords2.put(entry.getKey(), entry.getValue());
                        found2++;
                    }
                if (found2 == 0) {
                    System.out.print("\nNo matching record found!\n");
                    System.out.print("\nTry again? (Y = Yes | N = No)\n");
                    inp = cin.next();
                    inp += cin.nextLine();
                    while (!inp.equals("Y") && !inp.equals("N")) {
                        System.out.print("\nInvalid Choice!\nEnter again:\n ");
                        inp = cin.next();
                        inp += cin.nextLine();
                    }
                    if (inp.equals("N")) {
                        done = true;
                        break;
                    }
                } else {
                    System.out.print("\nMatching records:\n");
                    for (Map.Entry<Integer, Staff> entry : MatchingRecords2.entrySet()) {
                        entry.getValue().printDetails();
                        System.out.print("\n");
                    }
                    Boolean done2 = false;
                    while (!done2) {
                        System.out.print("\nEnter the ID of required staff from the list above:\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        id = Integer.parseInt(inp);
                        if (!MatchingRecords2.containsKey(id)) {
                            System.out.print("\nEntered ID doesn't match ID of any staff from the list above!\n");
                            System.out.print("\nTry again? (Y = Yes | N = No)\n");
                            inp = cin.next();
                            inp += cin.nextLine();
                            while (!inp.equals("Y") && !inp.equals("N")) {
                                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                                inp = cin.next();
                                inp += cin.nextLine();
                            }
                            if (inp.equals("N")) {
                                done2 = true;
                                break;
                            }
                        } else {
                            this.assign(MatchingRecords2.get(id));
                            done2 = done = true;
                            break;
                        }
                    }
                    if (done)
                        break;
                    else {
                        System.out.print("\nNo matching record found!\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }
                }
            }
            return;
        }
    }
}
