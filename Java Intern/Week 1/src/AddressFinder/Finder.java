package AddressFinder;

import java.sql.Date;
import java.util.*;

/**
 * Класс поисковика.
 */
public class Finder {
    /**
     * Метод для поиска людей по имени.
     *
     * @param name       Имя
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> nameSearcher(String name, ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getName().equals(name))
                list.add(collection.get(i));
        }
        return list;
    }

    /**
     * Методя для поиска клиентов по атрибуту адреса.
     *
     * @param info       Информация из адреса.
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> addressAttributeSearcher(String info, ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getAddress().toString().contains(info))
                list.add(collection.get(i));
        }
        return list;
    }

    /**
     * Метод для поиска людей, родившихся между датами.
     *
     * @param from       Дата для поиска от.
     * @param to         Дата для поиска до.
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> dateSearcher(Date from, Date to, ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getDate().after(from) && collection.get(i).getDate().before(to))
                list.add(collection.get(i));
        }
        return list;
    }

    /**
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> findYoungest(ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        Client res = null;
        Date date = null;

        if (collection.size() > 0)
            date = collection.get(0).getDate();
        if (date != null) {
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getDate().after(date))
                    date = collection.get(i).getDate();
            }
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getDate().equals(date)) {
                    res = collection.get(i);
                    list.add(res);
                }
            }
        }

        return list;
    }

    /**
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> findOldest(ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        Client res = null;
        Date date = null;

        if (collection.size() > 0)
            date = collection.get(0).getDate();
        if (date != null) {
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getDate().before(date))
                    date = collection.get(i).getDate();
            }
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getDate().equals(date)) {
                    res = collection.get(i);
                    list.add(res);
                }
            }
        }
        return list;
    }

    /**
     * @param street Улица для поиска.
     * @param collection Коллекция для поиска.
     * @return Лист с клиентами, подходящими по критериям.
     */
    public static List<Client> findSameStreetClients(String street, ArrayList<Client> collection) {
        ArrayList<Client> list = new ArrayList<>();
        for (Client client : collection) {
            if (client.getAddress().getStreet().equals(street))
                list.add(client);
        }
        return list;
    }
}
