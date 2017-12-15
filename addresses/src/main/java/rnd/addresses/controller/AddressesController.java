package rnd.addresses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import rnd.addresses.model.Addresses;
import rnd.addresses.repository.AddressesRepository;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api")
@RestController
public class AddressesController {

    @Autowired
    private AddressesRepository addressesRepository;

    @Async
    @GetMapping("/addresses")
    public List<Addresses> getAllAddresses() {
        return addressesRepository.findAll();
    }

    @Async
    @PostMapping("/address")
    public Addresses createAddress(@Valid @RequestBody Addresses addresses) {
        return addressesRepository.save(addresses);
    }

    @Async
    @GetMapping("/address/{id}")
    public ResponseEntity<Addresses> getAddressById(@PathVariable(value = "id") Long addressId) {
        Addresses address = addressesRepository.findOne(addressId);
        if(address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(address);
    }

    @Async
    @PutMapping("/address/{id}")
    public ResponseEntity<Addresses> updateAddress(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Addresses addressDetails) {
        Addresses addresses = addressesRepository.findOne(noteId);
        if(addresses == null) {
            return ResponseEntity.notFound().build();
        }
        addresses.setEmail(addressDetails.getEmail());
        addresses.setSkype(addressDetails.getSkype());
        addresses.setPhone(addressDetails.getPhone());
        addresses.setRoom((int) addressDetails.getRoom());

        Addresses updatedUser = addressesRepository.save(addresses);
        return ResponseEntity.ok(updatedUser);
    }

    @Async
    @DeleteMapping("/address/{id}")
    public ResponseEntity<Addresses> deleteAddress(@PathVariable(value = "id") Long noteId) {
        Addresses addresses = addressesRepository.findOne(noteId);
        if(addresses == null) {
            return ResponseEntity.notFound().build();
        }

        addressesRepository.delete(addresses);
        return ResponseEntity.ok().build();
    }
}
