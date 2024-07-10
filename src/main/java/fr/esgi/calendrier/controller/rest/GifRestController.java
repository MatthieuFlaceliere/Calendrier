package fr.esgi.calendrier.controller.rest;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.dto.GifDto;
import fr.esgi.calendrier.mapper.GifMapper;
import fr.esgi.calendrier.service.GifService;
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
@RequestMapping("api/gifs")
@AllArgsConstructor
public class GifRestController {

    private final GifService gifService;
    private final GifMapper gifMapper;

    @GetMapping("")
    @Operation(summary = "Get all gifs")
    public Page<Gif> getAllGifs(@PageableDefault() Pageable pageable) {
        return gifService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get gif by id")
    public Gif getGifById(@PathVariable Long id) {
        return gifService.findById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create a new gif", responses = {@ApiResponse(responseCode = "201", description = "Ajout ok")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createGif(@Valid @RequestBody GifDto gifDto) {
        gifService.save(gifMapper.toEntity(gifDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update gif by id")
    public void updateGif(@PathVariable Long id, @RequestBody GifDto gifDto) {
        Gif gifToUpdate = gifService.findById(id);
        gifToUpdate.setUrl(gifDto.getUrl());
        gifToUpdate.setLegende(gifDto.getLegende());
        gifService.save(gifToUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete gif by id")
    public void deleteGif(@PathVariable Long id) {
        Gif gif = gifService.findById(id);
        gifService.delete(gif);
    }
}

