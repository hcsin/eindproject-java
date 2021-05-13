package com.avans.eindproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avans.eindproject.model.Book;
import com.avans.eindproject.repository.BookMongoRepository;
import com.avans.eindproject.repository.BookSearchRepository;

import java.util.List;

@Controller
public class BookController {

	@Autowired
	BookMongoRepository bookRepository;

	@Autowired
	BookSearchRepository bookSearchRepository;

	/* Home page, loading of data and show in table
	* */
	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("bookList", bookRepository.findAll());
		return "home";
	}

	/* Home page, loading of data and show in table
	 * */
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("bookList", bookRepository.findAll());
		return "home";
	}

	/* Open addbook page, ID counting is saved in ID:1-ISBN
	 * */
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		Book book1 = bookRepository.findById(1).get();
//		System.out.println(book1.getISBN());
		int id = book1.getISBN();
		model.addAttribute("id", id);
//		int newID = bookRepository.findById();
		return "addbook";
	}

	/* Add a new book, when a new book is saved, id counting "ID:1-ISBN" will plus 1
	 * */
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addCar(@ModelAttribute Book book) {
		bookRepository.save(book);
		Book book1 = bookRepository.findById(1).get();
		/* id counting "ID:1-ISBN" will plus 1
		 * */
		int id = book1.getISBN()+1;
		book1.setISBN(id);
		bookRepository.save(book1);
		return "redirect:home";
	}


	/* Saving a new book
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:home";
	}

	/* Search by anything
	 * */
	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) {
		model.addAttribute("bookList", bookSearchRepository.searchBooks(search));
		model.addAttribute("search", search);
		return "home";
	}

	/* Open edit page with value
	 * */
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable int id, Model model){
		model.addAttribute("book", bookRepository.findById(id).get());
		return "/edit";
	}

	/* Delete book by id
	 * */
	@RequestMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable int id){
		bookRepository.deleteById(id);
		return "redirect:/home";
	}
}
