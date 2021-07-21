package Address;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Address {
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String corp;
    private String apartment;

    /**
     * Конструктор со всеми параметрами.
     * @param country Страна
     * @param region Регион
     * @param city Город
     * @param street Улица
     * @param house Дом
     * @param corp Корпус
     * @param apartment Квартира
     */
    public Address(String country, String region, String city, String street, String house, String corp, String apartment) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.corp = corp;
        this.apartment = apartment;
    }

    /**
     * Конструктор с параметром строкой.
     * @param address Строка с адресом.
     */
    public Address(String address) {
        if (address.contains(",") && !address.contains(".") && !address.contains(";") && !address.contains("-"))
            addressSplit(address);
        else
            addressTokenizerSplit(address);
    }

    /**
     * Преобразование строки в адрес.
     * @param address Строка с адресом.
     */
    private void addressSplit(String address){
        String[] splitAddress = address.split(",");
        country = splitAddress[0].replaceFirst(" ","");
        region = splitAddress[1].replaceFirst(" ","");
        city = splitAddress[2].replaceFirst(" ","");
        street = splitAddress[3].replaceFirst(" ","");
        house = splitAddress[4].replaceFirst(" ","");
        corp = splitAddress[5].replaceFirst(" ","");
        apartment = splitAddress[6].replaceFirst(" ","");
    }

    /**
     * Преобразование строки адреса с разными разделителями.
     * @param address Строка адреса с разными разделителями
     */
    private void addressTokenizerSplit(String address){
        StringTokenizer stringTokenizer = new StringTokenizer(address, ",.;-");
        List<String> list = new ArrayList<>();
        while(stringTokenizer.hasMoreTokens()){
            list.add(stringTokenizer.nextToken());
            if(list.get(list.size()-1).startsWith(" "))
                list.set(list.size()-1, list.get(list.size()-1).replaceFirst(" ", ""));
        }
        country = list.get(0);
        region = list.get(1);
        city = list.get(2);
        street = list.get(3);
        house = list.get(4);
        corp = list.get(5);
        apartment = list.get(6);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", corp='" + corp + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
