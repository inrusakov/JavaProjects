package sample.Utility;

import sample.InterfacesAnnotations.*;

import java.util.List;

@Constrained
public class Point {
    @NotNull
    @Negative
    private Integer X;
    @NotNull
    @Positive
    private Integer Y;
    @NotNull
    @Size(min = 0, max = 20)
    private List<@Negative Integer> numList;
    public Point(int x, int y, List<Integer> num){
        X = x;
        Y = y;
        numList = num;
    }
}
