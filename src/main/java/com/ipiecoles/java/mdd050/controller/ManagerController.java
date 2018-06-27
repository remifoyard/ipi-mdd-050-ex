package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/{id}/equipe/{matricule}/add")
    public void addTechniciens(@PathVariable("id") Long id, @PathVariable("matricule") String matricule) throws EntityNotFoundException {
        managerService.addTechniciens(id, matricule);
    }

    @RequestMapping("/{id}/equipe/{idTech}/remove")
    public void deleteTechniciens(@PathVariable("id") Long id, @PathVariable("idTech") Long idTech) throws EntityNotFoundException {
        managerService.deleteTechniciens(id, idTech);
    }

}
