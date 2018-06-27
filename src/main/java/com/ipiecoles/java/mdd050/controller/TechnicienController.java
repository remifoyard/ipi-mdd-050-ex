package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/techniciens")
public class TechnicienController {
    @Autowired
    private TechnicienService technicienService;

    @RequestMapping("/techniciens/{id}/manager/{matricule}/add")
    public void addManager(@PathVariable("id") Long id, @PathVariable("matricule") String matricule) throws EntityNotFoundException {
        technicienService.addManager(id, matricule);
    }

}
