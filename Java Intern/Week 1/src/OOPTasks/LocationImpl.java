package OOPTasks;

public class LocationImpl implements Location{
    private String locationName;
    private Type type;
    private Location parent;

    public LocationImpl() {
        super();
    }

    @Override
    public String getName() {
        return locationName;
    }

    @Override
    public void setName(String name) {
        locationName = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;

    }

    @Override
    public String toString() {
        return locationName + " " + "(" + type + ")";
    }

    @Override
    public String getParentName() {
        if (parent != null) {
            return parent.getName();
        } else
            return "--";
    }

    @Override
    public Location getTopLocation() {
        if (parent != null)
            return parent.getTopLocation();
        return this;
    }

    @Override
    public boolean isCorrect() {
        if (type.ordinal() > parent.getType().ordinal()) {
            return true;
        } else
            return false;
    }

    @Override
    public String getAddress() {
        String result = locationName + " ";
        String str = "";
        if (parent == null) {
            return result;
        } else {
            int temp1 = 0;
            int temp2 = 0;
            char[] array = locationName.toCharArray();
            for (int i = 0; i < array.length; i++) {
                if (array[i] == '.') {
                    temp1 = i;
                }
                if (array[i] == ' ') {
                    temp2 = i;
                }
            }
            if (array[array.length - 1] == '.' || temp1 == temp2 - 1) {
                str += locationName + ", " + parent.getAddress() + " ";

            } else {
                str += type.getNameForAddress() + locationName + ", " + parent.getAddress();
            }
        }

        return str;
    }
}
