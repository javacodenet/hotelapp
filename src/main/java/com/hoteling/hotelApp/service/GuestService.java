package com.hoteling.hotelApp.service;

import com.hoteling.hotelApp.domain.Guest;
import com.hoteling.hotelApp.repository.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public void saveGuest(Guest guest) {
        guestRepository.save(guest);
    }
}
