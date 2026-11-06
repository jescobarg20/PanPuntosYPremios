/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Recom;
import com.mycompany.pan.api.repositorios.RecomRepo;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author santi
 */

@Service
public class RecomServ {

    private final RecomRepo recomRepo;

    public RecomServ(RecomRepo recomRepo) {
        this.recomRepo = recomRepo;
    }

    public List<Recom> listarTodos() {
        return recomRepo.findAll();
    }

    public Recom buscarPorId(int id) {
        return recomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "La recompensa no existe"));
    }

    public Recom guardar(Recom recompensa) {
        return recomRepo.save(recompensa);
    }

    public void eliminar(int id) {
        Recom recompensa = buscarPorId(id);
        recomRepo.delete(recompensa);
    }
}