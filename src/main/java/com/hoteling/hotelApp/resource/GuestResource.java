package com.hoteling.hotelApp.resource;

import com.hoteling.hotelApp.domain.Guest;
import com.hoteling.hotelApp.repository.GuestRepository;
import com.hoteling.hotelApp.service.GuestService;
import com.hoteling.hotelApp.service.UserSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    private GuestService guestService;

    public GuestResource(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping("/guest/add")
    public String addGuest(Guest guest) {
        guestService.saveGuest(guest);
        return String.valueOf(guest.getId());
    }
}
