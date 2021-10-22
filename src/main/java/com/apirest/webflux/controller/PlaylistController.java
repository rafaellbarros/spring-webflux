package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// @RestController
@RequiredArgsConstructor
@RequestMapping("playlists")
public class PlaylistController {

    private final PlaylistService service;

    @GetMapping
    public Flux<Playlist> getPlaystlist() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> getPlaylistId(@PathVariable final String id) {
        return service.findById(id);
    }

    @PostMapping()
    public Mono<Playlist> save(@RequestBody final Playlist playlist) {
        return service.save(playlist);
    }
}

