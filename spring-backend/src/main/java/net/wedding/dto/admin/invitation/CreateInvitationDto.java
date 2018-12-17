package net.wedding.dto.admin.invitation;

import java.util.ArrayList;
import java.util.List;

public class CreateInvitationDto {

    private int maxGuestCount;
    private List<CreateGuestDto> guestList;

    public int getMaxGuestCount() {
        return maxGuestCount;
    }

    public void setMaxGuestCount(int maxGuestCount) {
        this.maxGuestCount = maxGuestCount;
    }

    public List<CreateGuestDto> getGuestList() {
        if (this.guestList == null) {
            this.guestList = new ArrayList<>();
        }
        return guestList;
    }

    public void setGuestList(List<CreateGuestDto> guestList) {
        this.guestList = guestList;
    }
}
