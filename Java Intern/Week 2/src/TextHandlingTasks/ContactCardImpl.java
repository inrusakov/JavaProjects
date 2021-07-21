package TextHandlingTasks;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactCardImpl implements ContactCard{
    public String FN;
    private String ORG;
    private String GENDER;
    private Calendar BDAY;
    private String [] TEL = new String[20];
    private int numOfTel = 0;
    @Override
    public ContactCard getInstance(Scanner scanner) {
        ContactCardImpl card = new ContactCardImpl();
        String temp;
        // Begin.
        temp = scanner.nextLine();
        if(!temp.equals("BEGIN:VCARD"))
            throw new InputMismatchException();

        // Full name parsing.
        temp = scanner.nextLine();
        if(!temp.startsWith("FN:")){
            throw new InputMismatchException();
        }
        String []FNX;
        FNX = temp.split("FN:");
        card.FN = FNX[1];

        // Organisation.
        temp = scanner.nextLine();
        if(!temp.startsWith("ORG:")){
            throw new InputMismatchException();
        }
        String []ORGX;
        ORGX = temp.split("ORG:");
        card.ORG = ORGX[1];

        // While info in stream is not END.
        temp = scanner.nextLine();
        while(!temp.equals("END:VCARD")){
            // Gender.
            if(temp.startsWith("GENDER:")){
                if(card.GENDER != null || card.BDAY != null || card.TEL[0] != null){
                    throw new InputMismatchException();
                }
                String []GenderTemp;
                GenderTemp = temp.split("GENDER:");
                if(!GenderTemp[1].equals("M") && !GenderTemp[1].equals("F"))
                    throw new InputMismatchException();
                card.GENDER = GenderTemp[1];
            }
            // Birthday.
            else if(temp.startsWith("BDAY:")){
                if(card.TEL[0] != null) {
                    throw new InputMismatchException();
                }
                card.BDAY = Calendar.getInstance();
                String []bDayTemp;
                String []bDayTemp2;

                bDayTemp = temp.split("BDAY:");
                bDayTemp2 = bDayTemp[1].split("-");
                if(bDayTemp2.length != 3)
                    throw new InputMismatchException();

                int day = Integer.parseInt(bDayTemp2[0]);
                int month = Integer.parseInt((bDayTemp2[1]));
                int year = Integer.parseInt((bDayTemp2[2]));
                if(day > 31 || day < 1 || month > 12 || month < 1 || year > 9999 || year < 1000){
                    throw new InputMismatchException();
                }
                card.BDAY.set(year, month, day);
            }
            // Tel.
            else if(temp.startsWith("TEL;TYPE=")){
                String []tel1Temp;
                tel1Temp = temp.split("TEL;TYPE=");
                String []tel2Temp;
                tel2Temp = tel1Temp[1].split(":");
                if(tel2Temp.length != 2 || tel2Temp[0].isEmpty()){
                    throw new InputMismatchException();
                }

                card.TEL[numOfTel] =  tel2Temp[0];
                card.TEL[numOfTel + 1] = tel2Temp[1];
                char[] data = new char[11];
                data = card.TEL[numOfTel + 1].toCharArray();
                if(card.TEL[numOfTel + 1].length() != 10)
                    throw new InputMismatchException();
                for (int i = 0; i < 10; i++) {
                    if(data[i] < '0' || data[i] > '9'){
                        throw new InputMismatchException();
                    }
                }
                numOfTel+=2;
            }
            else{
                throw new InputMismatchException();
            }
            temp = scanner.next();
        }
        // End.
        if(!temp.equals("END:VCARD"))
            throw new InputMismatchException();
        return card;
    }

    @Override
    public ContactCard getInstance(String data) {
        Scanner s = new Scanner(data);
        ContactCard d = getInstance(s);
        return d;
    }

    @Override
    public String getFullName() {
        return FN;
    }

    @Override
    public String getOrganization() {
        return ORG;
    }

    @Override
    public boolean isWoman() {
        if(GENDER == null)
            return false;
        return GENDER.equals("F");
    }

    @Override
    public Calendar getBirthday() {
        if(BDAY == null)
            throw new NoSuchElementException();
        return BDAY;
    }

    @Override
    public Period getAge() {
        if(BDAY == null)
            throw new NoSuchElementException();
        LocalDate before = LocalDate.of(BDAY.get(Calendar.YEAR), BDAY.get(Calendar.MONTH), BDAY.get(Calendar.DAY_OF_MONTH));
        Calendar today = Calendar.getInstance();
        LocalDate newDate = LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        Period period = Period.between(before, newDate);
        if(today.get(Calendar.MONTH) < BDAY.get(Calendar.MONTH) || (today.get(Calendar.MONTH) == BDAY.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) <= BDAY.get(Calendar.DAY_OF_MONTH)))
            period = period.minusDays(1);
        period = period.plusMonths(1);
        return period;
    }

    @Override
    public int getAgeYears() {
        if(BDAY == null)
            throw new NoSuchElementException();
        int age;
        Calendar today = Calendar.getInstance();
        age = today.get(Calendar.YEAR) - BDAY.get(Calendar.YEAR);
        if(today.get(Calendar.MONTH) < BDAY.get(Calendar.MONTH) || (today.get(Calendar.MONTH) == BDAY.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) <= BDAY.get(Calendar.DAY_OF_MONTH)))
            age-= 1;
        return age;
    }

    @Override
    public String getPhone(String type) {
        int i = 0;
        while(TEL[i] != null && !TEL[i].equals(type)){
            i += 2;
            if(TEL[i + 1] == null)
                throw new NoSuchElementException();
        }

        String tel;
        tel = "(" + TEL[i + 1].substring(0, 3) + ") "
                + TEL[i + 1].substring(3, 6) + "-"
                + TEL[i + 1].substring(6, 10);
        return tel;
    }
}
