package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.repository.GifRepository;
import fr.esgi.calendrier.service.GifService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {
    private final GifRepository gifRepository;

    @Value("${application.upload-dir}")
    private String uploadDir;

    @Value("${application.url}")
    private String url;

    @Value("${application.upload-url}")
    private String uploadUrl;

    @Override
    public Page<Gif> findAll(Pageable pageable) {
        return this.gifRepository.findAll(pageable);
    }

    @Override
    public Gif findById(Long id) {
        return this.gifRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Gif not found"));
    }

    @Override
    public void save(Gif gif) {
        this.gifRepository.save(gif);
    }

    @Override
    public void delete(Gif gif) {
        this.gifRepository.delete(gif);
    }

    @Override
    public String store(MultipartFile file) throws IOException, IllegalArgumentException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("image/gif")) {
            throw new IllegalArgumentException("File is not a gif");
        }

        Path gifStorageLocation = Paths.get(uploadDir);
        String fileName = UUID.randomUUID() + ".gif";

        InputStream inputStream = file.getInputStream();
        Path gifPath = gifStorageLocation.resolve(fileName);
        java.nio.file.Files.copy(inputStream, gifPath, StandardCopyOption.REPLACE_EXISTING);

        return url + uploadUrl + fileName;
    }

    @Override
    public void deleteAllUploadingGif() throws IOException {
        FileSystemUtils.deleteRecursively(Paths.get(uploadDir));
        Files.createDirectories(Paths.get(uploadDir));
    }
}
