package com.dio_class.devweek.Controller;


import com.dio_class.devweek.Entity.FaixaEtaria;
import com.dio_class.devweek.Entity.Incidencia;
import com.dio_class.devweek.Repo.FaixaEtariaRepo;
import com.dio_class.devweek.Repo.IncidenciaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ControllerIncidencia {
    private final IncidenciaRepo ocRepository;

    public ControllerIncidencia(IncidenciaRepo ocRepository) {
        this.ocRepository = ocRepository;
    }

    @GetMapping("/incidencia")
    public ResponseEntity<List<Incidencia>> findIncidencias(){
            List<Incidencia> listaIncidencia = ocRepository.findAll();
            if (listaIncidencia.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(listaIncidencia, HttpStatus.OK);
        }

    @GetMapping("/incidencia/{id}")
    public ResponseEntity<Incidencia> findByIdFaixaEtaria(@PathVariable long id){
            Optional<Incidencia> IncidenciaOptional = ocRepository.findById(id);
            if (IncidenciaOptional.isPresent()){
                Incidencia IncidenciaUnid = IncidenciaOptional.get();
                return new ResponseEntity<>(IncidenciaUnid, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/incidencia/{id}")
    public Incidencia newIncidencia(@RequestBody Incidencia newIncidencia){
        return ocRepository.save(newIncidencia);
    }

}
