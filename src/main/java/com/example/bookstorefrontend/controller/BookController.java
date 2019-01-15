package com.example.bookstorefrontend.controller;

import com.example.bookstorefrontend.model.Book;
import com.example.bookstorefrontend.service.BookApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class BookController {

    private final BookApiClient client;

    @Autowired
    public BookController(BookApiClient client) {
        this.client = client;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/showme")
    @ResponseBody public String showme() {
        return "index";
    }

    @GetMapping("/proba")
    public String proba(Model model) {
        model.addAttribute("text", "Proooooba");
        model.addAttribute("drugi", "jakis wpis");
        return "index";
    }
    @GetMapping("/about")
    public String about (Principal principal, Model model){
        model.addAttribute("username",principal != null ? principal.getName() :"nieznajomy");
        return "books/about";
    }

    @GetMapping("/books")
    public  String listing(Model model){
        model.addAttribute("listing",client.fetchAllBooks());
     return "books/listing";
    }

    @GetMapping("/books/{bookId}")
    public  String description (@PathVariable ("bookId") String bookId, Model model) {
        List<String> idList = client.fetchAllBooks().getBooks().stream().map(book -> book.getId()).collect(Collectors.toList());
        if(idList.contains(bookId)) {
            model.addAttribute("book", client.fetchBookInfo(bookId));
            return "books/details";
        } else {
            return "redirect:/books";
        }
    }
    @GetMapping("/books/add")
    public  String addPage(){
       return "books/add";
    }

    @PostMapping("/books/add")
    public  String add(@ModelAttribute BookForm bookForm, RedirectAttributes redirectAttributes){
    redirectAttributes.addFlashAttribute("result","Książka została dodana");
    return "redirect:/books";
    }
}
