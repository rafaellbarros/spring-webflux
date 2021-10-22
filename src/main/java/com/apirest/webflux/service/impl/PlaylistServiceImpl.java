package com.apirest.webflux.service.impl;


import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import com.apirest.webflux.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl  implements PlaylistService {

    private final PlaylistRepository repository;

    @Override
    public Flux<Playlist> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Playlist> findById(final String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Playlist> save(final Playlist playlist) {
        return repository.save(playlist);
    }
}
