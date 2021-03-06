package Library.service;

import Library.entity.Author;
import Library.repository.author.AuthorRepository;

import java.util.List;


public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveNewAuthor(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }



    public List<Author> findAuthorsByKeyWord(String keyWord) {
        return authorRepository.searchByKeyWord(keyWord);
    }




}
