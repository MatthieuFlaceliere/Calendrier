package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Gif;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GifService {
    void save(Gif gif);

    String store(MultipartFile file) throws IOException, IllegalArgumentException;

    void deleteAllUploadingGif() throws IOException;
}
