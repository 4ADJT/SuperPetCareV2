package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.user.DataAuthentication;
import br.com.superpetcare.superpetcare.domain.user.UserEntity;
import br.com.superpetcare.superpetcare.infra.security.DataTokenJWT;
import br.com.superpetcare.superpetcare.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@Tag(name = "Security Super Pet Care API")
public class SecurityController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Autenticação", description = "Método responsável pela autenticação da aplicação")
    public ResponseEntity login(@RequestBody @Valid DataAuthentication dataAuthentication) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthentication.login(), dataAuthentication.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
