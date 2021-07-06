package AddressFinder;

import java.sql.Date;
import java.util.Calendar;

/**
 * Класс клиента.
 */
public class Client extends AbstractClient{
    public Client(String name, String surname, Date date, Address address) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.address = address;
    }

    /**
     * @return Имя клиента.
     */
    public String getName(){
        return name;
    }

    /**
     * @return Фамилия клиента.
     */
    public String getSurname(){
        return surname;
    }

    /**
     * @return Дата рождения.
     */
    public Date getDate(){
        return date;
    }

    /**
     * @return Адрес.
     */
    public Address getAddress(){
        return address;
    }

    /**
     * Переопределенный метод toString.
     * @return Полная информация о клиенте.
     */
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", address=" + address +
                '}';
    }
}
