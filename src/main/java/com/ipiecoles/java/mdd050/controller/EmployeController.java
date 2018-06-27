package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.exception.EmployeException;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @RequestMapping(value = "",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE , method = RequestMethod.GET, params = "matricule")
    public Employe findMyMatricule(@RequestParam("matricule") String matricule) {
        if (employeService.findMyMatricule(matricule) != null){
            return employeService.findMyMatricule(matricule);
        } else {
            throw new EntityNotFoundException("L'employé matricule : " +
                    matricule + " n'a pas été trouvé.");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Employe> findAllEmployes(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortDirection") String sortDirection, @RequestParam("sortProperty") String sortProperty) {
            return employeService.findAllEmployes( page,  size,  sortProperty,  sortDirection);

    }

    @RequestMapping(
            method = RequestMethod.POST, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = "application/json", //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces = "application/json", //Type MIME des données fournies dans la réponse
            value = "" //Chemin du mapping (concaténé avec l'éventuel chemin présent au niveau du contrôleur)
    )

    public @ResponseBody <T extends Employe> T  creerEmploye(@RequestBody  T employe){
        return employeService.creerEmploye(employe);
    }

    @RequestMapping(
            method = RequestMethod.PUT, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = "application/json", //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces = "application/json", //Type MIME des données fournies dans la réponse
            value = "/{id}" //Chemin du mapping (concaténé avec l'éventuel chemin présent au niveau du contrôleur)
    )
    public @ResponseBody <T extends Employe> T  creerEmploye(@RequestBody  T employe, @PathVariable("id") Long id)  throws EmployeException {
        return employeService.updateEmploye(id, employe);
    }

    @RequestMapping(
            method = RequestMethod.DELETE, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE,
            value = "/{id}" //Chemin du mapping (concaténé avec l'éventuel chemin présent au niveau du contrôleur)
    )
    public void deleteEmploye(@PathVariable("id") Long id) throws EmployeException{
        employeService.deleteEmploye(id);
    }
}
