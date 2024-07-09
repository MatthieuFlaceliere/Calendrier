package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Gif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GifService {
    void save(Gif gif);

    String store(MultipartFile file) throws IOException, IllegalArgumentException;

    void deleteAllUploadingGif() throws IOException;

    Gif findById(Long id);

    void delete(Gif gif);

    Page<Gif> findAll(Pageable pageable);
}
