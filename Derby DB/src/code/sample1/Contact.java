package sample1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    public static Integer id = 0;
    public String surname;
    public String name;
    public String middleName;
    public String cellPhone;
    public String homePhone;
    public String address;
    public String birthDay;
    public String notes;

    public Contact(String surname,
                   String name,
                   String middleName,
                   String cellPhone,
                   String homePhone,
                   String address,
                   String birthDay,
                   String notes){
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;

        if (dataChecker("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", cellPhone)
                || cellPhone.equals("-"))
            this.cellPhone = cellPhone;
        else {
            throw new IllegalArgumentException("Wrong cell phone argument!");
        }

        if (dataChecker("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", homePhone)
                || homePhone.equals("-"))
            this.homePhone = homePhone;
        else{
            throw new IllegalArgumentException("Wrong home phone argument!");
        }

        this.address = address;
        if (dataChecker("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)", birthDay)
                || homePhone.equals("-"))
            this.birthDay = birthDay;
        else{
            throw new IllegalArgumentException("Wrong birth day argument!");
        }

        if(homePhone.equals("-") && cellPhone.equals("-"))
            throw new IllegalArgumentException("You cannot have both number saved as -, print cell or home phone!");

        this.notes = notes;
    }

    public Contact(){

    }

    public Contact(String surname,
                   String name,
                   String middleName,
                   String cellPhone,
                   String homePhone,
                   String address,
                   String birthDay,
                   String notes,
                   String test){
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.address = address;
        this.birthDay = birthDay;
        this.notes = notes;
    }

    public Contact(Integer id){
        this.id = id;
    }

    public static void increment(){
        id++;
    }

    public static void clear(){
        id = 0;
    }

    public static boolean dataChecker(String pattern, String data){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(data);
        if (m.find()){
            return true;
        }
        return false;
    }
}
