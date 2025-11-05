package com.unincor.projetoChamados.controller;
import com.unincor.projetoChamados.model.domain.Usuario;
import com.unincor.projetoChamados.model.service.UsuarioService;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
public String autenticar(@RequestParam String email, @RequestParam String senha, Model model) {
    Usuario usuario = usuarioService.buscarPorEmail(email).orElse(null);

    if (usuario != null && usuario.getSenha().equals(senha)) {
        return "redirect:/dashboard";
    }

    model.addAttribute("erro", "Credenciais inválidas");
    return "login";
}

}
