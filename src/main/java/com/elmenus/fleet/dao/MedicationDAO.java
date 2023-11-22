package com.elmenus.fleet.dao;

import com.elmenus.fleet.entity.Medication;

public interface MedicationDAO {

    Medication findMedicationByCode(String code);

}
