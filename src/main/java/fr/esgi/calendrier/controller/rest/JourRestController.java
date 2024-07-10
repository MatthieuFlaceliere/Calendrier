package fr.esgi.calendrier.controller.rest;

import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.dto.JourDto;
import fr.esgi.calendrier.dto.JourUpdateDto;
import fr.esgi.calendrier.mapper.JourMapper;
import fr.esgi.calendrier.service.JourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jours")
@AllArgsConstructor
public class JourRestController {

    private final JourService jourService;
    private final JourMapper jourMapper;

    @GetMapping("")
    @Operation(summary = "Get all jours")
    public Page<Jour> getAllJours(@PageableDefault() Pageable pageable) {
        return jourService.findAll(pageable);
    }

    @GetMapping("/{jour}/{mois}")
    @Operation(summary = "Get jour by id")
    public Jour getJourById(@PathVariable String jour, @PathVariable String mois) {
        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        return jourService.findById(jourId);
    }

    @PostMapping("")
    @Operation(summary = "Create a new jour", responses = {@ApiResponse(responseCode = "201", description = "Ajout ok")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createJour(@RequestBody JourDto jourDto) {
        jourService.save(jourMapper.toEntity(jourDto));
    }

    @PutMapping("/{jour}/{mois}")
    @Operation(summary = "Update jour by id")
    public void updateJour(@PathVariable String jour, @PathVariable String mois, @RequestBody JourUpdateDto jourUpdateDto) {
        JourId id = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        Jour jourToUpdate = jourService.findById(id);
        jourToUpdate.setGif(jourUpdateDto.getGif());
        jourToUpdate.setPoints(jourUpdateDto.getPoints());
        jourToUpdate.setUtilisateur(jourUpdateDto.getUtilisateur());
        jourService.save(jourToUpdate);
    }

    @DeleteMapping("/{jour}/{mois}")
    @Operation(summary = "Delete jour by id")
    public void deleteJour(@PathVariable String jour, @PathVariable String mois) {
        JourId id = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        Jour jourToDelete = jourService.findById(id);
        jourService.delete(jourToDelete);
    }
}

