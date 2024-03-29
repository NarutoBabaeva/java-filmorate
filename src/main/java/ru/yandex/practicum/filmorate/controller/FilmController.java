package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film saveFilm(@RequestBody Film film) throws ValidationException {
        filmService.saveFilm(film);
        return film;
    }

    @PutMapping
    public Film updateExistingFilm(@RequestBody Film film) throws ValidationException {
        filmService.updateExistingFilm(film);
        return film;
    }

    @GetMapping("{id}")
    public Film filmById(@PathVariable int id) {
        return filmService.filmById(id);
    }

    @PutMapping("{id}/like/{userId}")
    public void likeFilm(@PathVariable int id, @PathVariable int userId) {
        filmService.likeFilm(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void removeLike(@PathVariable int id, @PathVariable int userId) {
        filmService.removeLike(id, userId);
    }

    @GetMapping("popular")
    public List<Film> getPopularFilms(@RequestParam(required = false, defaultValue = "10") int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count <= 0");
        }
        return filmService.getPopularFilms(count);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }
}