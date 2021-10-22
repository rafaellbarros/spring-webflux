package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RestController
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

    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents(){

        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));

        Flux<Playlist> playlistFlux = service.findAll();
        log.info("Passou aqui events");

        return Flux.zip(interval, playlistFlux);

    }
}

