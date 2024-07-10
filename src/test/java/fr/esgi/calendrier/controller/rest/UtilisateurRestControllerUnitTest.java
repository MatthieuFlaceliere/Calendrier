package fr.esgi.calendrier.controller.rest;

import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.dto.UtilisateurDto;
import fr.esgi.calendrier.mapper.UtilisateurMapper;
import fr.esgi.calendrier.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UtilisateurRestControllerUnitTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    UtilisateurService utilisateurService;
    @MockBean
    UtilisateurMapper utilisateurMapper;

    @Test
    void testGetAllUtilisateurs() throws Exception {
        // Crée un utilisateur ficti
        Utilisateur utilisateur = new Utilisateur();
        Page<Utilisateur> utilisateurs = new PageImpl<>(Arrays.asList(utilisateur));

        // Utilisation de ArgumentCaptor pour capturer l'argument Pageable
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        // Configure le mock pour retourner la page d'utilisateurs lorsqu'on appelle findAll
        when(utilisateurService.findAll(pageableCaptor.capture())).thenReturn(utilisateurs);

        // Effectue une requête GET sur l'endpoint /api/utilisateurs avec des paramètres de pagination
        mvc.perform(get("/api/utilisateurs?page=0&size=10"))
                // Vérifie que le statut de la réponse est 200 OK
                .andExpect(status().isOk());

        // Vérifie que la méthode findAll du service a été appelée une fois avec un Pageable
        verify(utilisateurService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetUtilisateurById() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        // Configure le mock pour retourner l'utilisateur fictif lorsqu'on appelle findById
        when(utilisateurService.findById(anyLong())).thenReturn(utilisateur);

        // Effectue une requête GET sur l'endpoint /api/utilisateurs/1
        mvc.perform(get("/api/utilisateurs/1"))
                // Vérifie que le statut de la réponse est 200 OK
                .andExpect(status().isOk());

        // Vérifie que la méthode findById du service a été appelée une fois avec n'importe quel Long
        verify(utilisateurService, times(1)).findById(anyLong());
    }

    @Test
    void testCreateUtilisateur() throws Exception {
        // Crée un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur();

        // Configure le mock pour retourner l'utilisateur fictif lors de la conversion de DTO à entité
        when(utilisateurMapper.toEntity(any(UtilisateurDto.class))).thenReturn(utilisateur);

        // Effectue une requête POST sur l'endpoint /api/utilisateurs avec un contenu JSON
        mvc.perform(post("/api/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                   "nom":"John",
                                   "prenom":"Doe",
                                   "email":"john.doe@esgi.fr",
                                   "theme":"dark",
                                   "password":"password"
                                 }
                                """))
                // Vérifie que le statut de la réponse est 201 Created
                .andExpect(status().isCreated());

        // Vérifie que la méthode save du service a été appelée une fois avec n'importe quel Utilisateur
        verify(utilisateurService, times(1)).save(any(Utilisateur.class));
    }


    @Test
    void testUpdateUtilisateur() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.findById(anyLong())).thenReturn(utilisateur);

        mvc.perform(put("/api/utilisateurs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                    "nom":"John",
                                    "prenom":"Doe",
                                    "email":"john.doe@esgi.fr",
                                    "theme":"dark",
                                    "password":"password"
                                  }
                                """))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).findById(anyLong());
        verify(utilisateurService, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void testDeleteUtilisateur() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.findById(anyLong())).thenReturn(utilisateur);

        mvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isOk());

        verify(utilisateurService, times(1)).findById(anyLong());
        verify(utilisateurService, times(1)).delete(any(Utilisateur.class));
    }
}
