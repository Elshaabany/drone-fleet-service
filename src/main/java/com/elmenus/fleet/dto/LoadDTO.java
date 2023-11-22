package com.elmenus.fleet.dto;

import java.util.List;

public class LoadDTO {

    private List<String> medicationCodes;

    public List<String> getMedicationCodes() {
        return medicationCodes;
    }

    public void setMedicationCodes(List<String> medicationCodes) {
        this.medicationCodes = medicationCodes;
    }
}
