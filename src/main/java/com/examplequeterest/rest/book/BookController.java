package com.examplequeterest.rest.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository repository;

    @GetMapping()
    public List<Book> getAll() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String,String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm,searchTerm);
    }


    @PostMapping()
    public Book post(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){

        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

    }