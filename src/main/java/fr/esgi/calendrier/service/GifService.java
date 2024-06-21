package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Gif;

public interface GifService {
    public void save(Gif gif);

    public Gif findById(Long id);
}
