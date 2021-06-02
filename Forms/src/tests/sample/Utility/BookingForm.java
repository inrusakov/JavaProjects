package sample.Utility;

import sample.InterfacesAnnotations.AnyOf;
import sample.InterfacesAnnotations.Constrained;
import sample.InterfacesAnnotations.NotNull;
import sample.InterfacesAnnotations.Size;

import java.util.List;

@Constrained
public class BookingForm {
    @NotNull
    @Size(min = 1, max = 5)
    private List<@NotNull GuestForm> guests;
    @NotNull
    private List<@AnyOf({"TV", "Kitchen"}) String> amenities;
    @NotNull
    @AnyOf({"House", "Hostel"})
    private String propertyType;
    @NotNull
    public BookingForm(List<GuestForm> guests, List<String> amenities, String
            propertyType) {
        this.guests = guests;
        this.amenities = amenities;
        this.propertyType = propertyType;
    }
}
