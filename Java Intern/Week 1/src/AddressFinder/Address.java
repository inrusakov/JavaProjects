package AddressFinder;

/**
 * Класс адреса.
 */
public class Address {
    private final String country;
    private final String city;
    private final String street;
    private final String house;

    /**
     * Конструктор.
     * @param country Страна
     * @param city Город
     * @param street Улица
     * @param house Номер дома
     */
    public Address(String country, String city, String street, String house) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    /**
     * @return Страна в адресе.
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return Город в адресе.
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Улица в адресе.
     */
    public String getStreet() {
        return street;
    }

    /**
     * @return Номер дома в адресе.
     */
    public String getHouse() {
        return house;
    }

    /**
     * Переопределенный метод toString.
     * @return Полный адрес.
     */
    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
