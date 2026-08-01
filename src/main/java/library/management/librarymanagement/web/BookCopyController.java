package library.management.librarymanagement.web;

import jakarta.validation.Valid;
import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.dtos.BookCopyDTO;
import library.management.librarymanagement.model.enums.BookStatus;
import library.management.librarymanagement.service.BookCopyService;
import library.management.librarymanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookService bookService;

    @GetMapping("/books/{id}/copies")
    public String bookCopiesView(@PathVariable Long id, Model model){

        Book book = bookService.findBookById(id);

        model.addAttribute("book", book);
        model.addAttribute("copies", bookCopyService.getCopiesByBook(id));

        return "book-copies";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/books/{id}/copies/new")
    public String bookCopyAddForm(@PathVariable Long id, Model model){

        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("copy", new BookCopyDTO());
        model.addAttribute("statuses", BookStatus.values());

        return "book-copy-form";
    }

    @PreAuthorize(value = "hasRole('ADMINISTRATOR')")
    @PostMapping("/books/{id}/copies/new")
    public String addBookCopy(@PathVariable Long id, @Valid @ModelAttribute("copy") BookCopyDTO copyDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookService.findBookById(id));
            model.addAttribute("statuses", BookStatus.values());
            return "book-copy-form";
        }

        bookCopyService.addCopy(id, copyDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book copy added successfully.");

        return "redirect:/books/" + id + "/copies";
    }

    @PreAuthorize(value = "hasRole('ADMINISTRATOR')")
    @GetMapping("/book-copies/{id}/edit")
    public String bookCopyEditForm(@PathVariable Long id, Model model){

        BookCopy copy = bookCopyService.getById(id).get();

        BookCopyDTO copyDTO = new BookCopyDTO();
        copyDTO.setInventoryNumber(copy.getInventoryNumber());
        copyDTO.setCurrentStatus(copy.getCurrentStatus());
        copyDTO.setShelfLocation(copy.getShelfLocation());
        copyDTO.setAcquisitionDate(copy.getAcquisitionDate());
        copyDTO.setConditionNotes(copy.getConditionNotes());

        model.addAttribute("book", copy.getParentBook());
        model.addAttribute("copy", copyDTO);
        model.addAttribute("copyId", id);
        model.addAttribute("statuses", BookStatus.values());

        return "book-copy-form";
    }

    @PreAuthorize(value = "hasRole('ADMINISTRATOR')")
    @PostMapping("/book-copies/{id}/edit")
    public String editBookCopy(@PathVariable Long id, @Valid @ModelAttribute("copy") BookCopyDTO copyDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookCopyService.getById(id).get().getParentBook());
            model.addAttribute("copyId", id);
            model.addAttribute("statuses", BookStatus.values());
            return "book-copy-form";
        }

        BookCopy copy = bookCopyService.editCopy(id, copyDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Book copy updated successfully.");

        return "redirect:/books/" + copy.getParentBook().getId() + "/copies";
    }
}
