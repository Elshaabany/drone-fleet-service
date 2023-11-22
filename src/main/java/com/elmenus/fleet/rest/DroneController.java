package com.elmenus.fleet.rest;

import com.elmenus.fleet.dto.DroneDTO;
import com.elmenus.fleet.entity.Drone;
import com.elmenus.fleet.entity.Load;
import com.elmenus.fleet.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public Drone registerDrone(@RequestBody DroneDTO droneDTO) {
        return droneService.registerDrone(droneDTO);
    }

    @PostMapping("/{id}/loads")
    public Drone loadDrone(@PathVariable Long id, @RequestBody Load load) {
        return droneService.loadDrone(id, load);
    }

    @GetMapping("/{id}/loads")
    public List<Load> getLoadedMedications(@PathVariable Long id) {
        return droneService.getLoadedMedications(id);
    }

    @GetMapping
    public List<Drone> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{id}/battery")
    public Integer checkBatteryLevel(@PathVariable Long id) {
        return droneService.checkBatteryLevel(id);
    }
}