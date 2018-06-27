package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.exception.EmployeException;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/employes")
public class EmployeController {
    @Autowired
    private EmployeService employeService;

    @RequestMapping("/count")
    public Long CountAllEmploye() {
        return employeService.countAllEmploye();
    }

    @RequestMapping("/{id}")
    public Employe findById(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (employeService.findById(id) != null){
            return employeService.findById(id);
        } else {
            throw new EntityNotFoundException("L'employé d'identifiant : " +
                    id + " n'a pas été trouvé.");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employe findMyMatricule(@RequestParam("matricule") String matricule) throws EntityNotFoundException {
        if (employeService.findMyMatricule(matricule) != null){
            return employeService.findMyMatricule(matricule);
        } else {
            throw new EntityNotFoundException("L'employé matricule : " +
                    matricule + " n'a pas été trouvé.");
        }
    }
}
