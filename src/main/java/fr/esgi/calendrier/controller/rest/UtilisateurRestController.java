package fr.esgi.calendrier.controller.rest;

import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.dto.UtilisateurDto;
import fr.esgi.calendrier.mapper.UtilisateurMapper;
import fr.esgi.calendrier.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/utilisateurs")
@AllArgsConstructor
public class UtilisateurRestController {

    private UtilisateurService utilisateurService;
    private UtilisateurMapper utilisateurMapper;

    @GetMapping("")
    @Operation(summary = "Get all utilisateurs")
    public Page<Utilisateur> getAllUtilisateurs(@PageableDefault() Pageable pageable) {
        return utilisateurService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get utilisateur by id")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.findById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create a new utilisateur", responses = {@ApiResponse(responseCode = "201", description = "Ajout ok")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUtilisateur(
            @Valid @RequestBody UtilisateurDto utilisateur
    ) {
        utilisateurService.save(utilisateurMapper.toEntity(utilisateur));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update utilisateur by id")
    public void updateUtilisateur(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurDto utilisateur
    ) {
        Utilisateur utilisateurToUpdate = utilisateurService.findById(id);
        utilisateurToUpdate.setNom(utilisateur.getNom());
        utilisateurToUpdate.setPrenom(utilisateur.getPrenom());
        utilisateurToUpdate.setEmail(utilisateur.getEmail());
        utilisateurToUpdate.setTheme(utilisateur.getTheme());
        utilisateurService.save(utilisateurToUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete utilisateur by id")
    public void deleteUtilisateur(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        utilisateurService.delete(utilisateur);
    }
}
