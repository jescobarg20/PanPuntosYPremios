
package com.mycompany.pan.api.vistas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioWEB {
    
    @GetMapping("/operaciones")
        public String operaciones() {
        return "operaciones";
}

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
}
