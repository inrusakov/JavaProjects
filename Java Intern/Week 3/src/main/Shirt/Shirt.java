package Shirt;

public class Shirt {
    private String id;
    private String description;
    private String color;
    private String size;

    /**
     * Конструктор со всеми параметрами.
     * @param id ID
     * @param description Описание
     * @param color Цвет
     * @param size Размер
     */
    public Shirt(String id, String description, String color, String size) {
        this.id = id;
        this.description = description;
        this.size = size;
    }

    /**
     * Конструктор со строковом параметром.
     * @param shirt Строковое представление Футболки.
     */
    public Shirt(String shirt) {
        String[] params = shirt.split(",");
        id = params[0];
        description = params[1];
        color = params[2];
        size = params[3];
    }

    /**
     * @return Строковое преобразование футболки.
     */
    public String toString() {
        return String.format("ID: %s Description: %s Color: %s Size: %s ", id, description, color, size);
    }
}
