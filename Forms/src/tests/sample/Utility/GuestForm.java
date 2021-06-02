package sample.Utility;

import sample.InterfacesAnnotations.*;

import java.util.List;

@Constrained
public class GuestForm {

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

    @NotNull
    private Parent parent;

    @NotNull
    @Size(min = 0, max = 5)
    private List<@AnyOf({"TV", "Kitchen", "B"}) @NotEmpty @NotNull String> amenities;

    public GuestForm(String firstName, String lastName, Integer age, List<String> amenities, String nationality, Parent parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.amenities = amenities;
        this.nationality = nationality;
        this.parent = parent;
    }
}
