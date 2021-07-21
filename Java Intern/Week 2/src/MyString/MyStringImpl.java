package MyString;

public class MyStringImpl implements MyString {
    private String info = "";

    @Override
    public void addLetter() {
        if (Math.random() > 0.5)
            info += 'a';
        else
            info += 'b';
    }

    @Override
    public void show() {
        System.out.println(info);
    }
}
