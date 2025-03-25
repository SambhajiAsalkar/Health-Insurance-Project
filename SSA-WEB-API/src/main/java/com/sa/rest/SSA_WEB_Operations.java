package com.sa.rest;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssa-web-api")
public class SSA_WEB_Operations {

	private static final TreeMap<Integer, String> ssnStateMap = new TreeMap<>();

    static {
        ssnStateMap.put(1, "New Hampshire");
        ssnStateMap.put(4, "Maine");
        ssnStateMap.put(8, "Vermont");
        ssnStateMap.put(10, "Massachusetts");
        ssnStateMap.put(35, "Rhode Island");
        ssnStateMap.put(40, "Connecticut");
        ssnStateMap.put(50, "New York");
        ssnStateMap.put(135, "New Jersey");
        ssnStateMap.put(159, "Pennsylvania");
        ssnStateMap.put(212, "Maryland");
        ssnStateMap.put(221, "Delaware");
        ssnStateMap.put(223, "Virginia");
        ssnStateMap.put(232, "North Carolina");
        ssnStateMap.put(236, "West Virginia");
        ssnStateMap.put(247, "South Carolina");
        ssnStateMap.put(252, "Georgia");
        ssnStateMap.put(261, "Florida");
        ssnStateMap.put(268, "Ohio");
        ssnStateMap.put(303, "Indiana");
        ssnStateMap.put(318, "Illinois");
        ssnStateMap.put(362, "Michigan");
        ssnStateMap.put(387, "Wisconsin");
        ssnStateMap.put(400, "Kentucky");
        ssnStateMap.put(408, "Tennessee");
        ssnStateMap.put(416, "Alabama");
        ssnStateMap.put(425, "Mississippi");
        ssnStateMap.put(429, "Arkansas");
        ssnStateMap.put(433, "Louisiana");
        ssnStateMap.put(440, "Oklahoma");
        ssnStateMap.put(449, "Texas");
        ssnStateMap.put(468, "Minnesota");
        ssnStateMap.put(478, "Iowa");
        ssnStateMap.put(486, "Missouri");
        ssnStateMap.put(501, "North Dakota");
        ssnStateMap.put(505, "Nebraska");
        ssnStateMap.put(509, "Kansas");
        ssnStateMap.put(516, "Montana");
        ssnStateMap.put(518, "Idaho");
        ssnStateMap.put(520, "Wyoming");
        ssnStateMap.put(521, "Colorado");
        ssnStateMap.put(528, "Utah");
        ssnStateMap.put(530, "Nevada");
        ssnStateMap.put(531, "Washington");
        ssnStateMap.put(540, "Oregon");
        ssnStateMap.put(545, "California");
        ssnStateMap.put(574, "Alaska");
        ssnStateMap.put(576, "Hawaii");
        ssnStateMap.put(580, "Virgin Islands");
        ssnStateMap.put(584, "Puerto Rico");
        ssnStateMap.put(586, "Guam, Amer. Samoa, Philippine");
        ssnStateMap.put(625, "New Mexico");
        ssnStateMap.put(680, "Nevada");
        ssnStateMap.put(700, "Railroad workers through 1963");
    }

    @GetMapping("/find/{ssn}")
    public ResponseEntity<String> getStatesBySSN(@PathVariable Integer ssn) {
        if (String.valueOf(ssn).length() != 9) {
            return new ResponseEntity<>("Invalid SSN", HttpStatus.BAD_REQUEST);
        }

        int prefix = ssn / 1000000; // Extract the first three digits
        NavigableMap.Entry<Integer, String> entry = ssnStateMap.floorEntry(prefix);
        String stateName = (entry != null) ? entry.getValue() : "Invalid SSN";
        
        return new ResponseEntity<>(stateName, HttpStatus.OK);	}
}
