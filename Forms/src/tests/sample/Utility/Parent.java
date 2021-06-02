package sample.Utility;

import sample.InterfacesAnnotations.*;

@Constrained
public class Parent {
    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;

    @NotNull
    @Positive
    @InRange(min = 18, max = 99)
    private Integer age;

    @NotNull
    @NotBlank
    @NotEmpty
    @AnyOf({"Russia", "USA", "Canada", "Netherlands", "Germany", "Poland"})
    private String nationality;

    public Parent(String firstName, String lastName, Integer age, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nationality = nationality;
    }
}
