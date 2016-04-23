package com.aquariusmaster.phonebook.controller;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import com.aquariusmaster.phonebook.service.PhoneEntryService;
import com.aquariusmaster.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by harkonnen on 20.04.16.
 */

@Controller
public class PhoneEntryController {

    @Autowired
    private PhoneEntryService entryService;

    @Autowired
    private UserService usersService;

    @ModelAttribute("entry")
    public PhoneEntry constructUser() {
        return new PhoneEntry();
    }

    @RequestMapping("/")
    public String showBooks(Model model, @RequestParam(value = "mes", required = false) String message) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("entries", entryService.getPhoneEntries(username));
        model.addAttribute("message", message);
        System.out.println(message);
        return "entries";
    }

    @RequestMapping("/entry")
    public String addEntry(Model model) {

        PhoneEntry entry = new PhoneEntry();
        model.addAttribute("entry", entry);
        return "entry";
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String createEntry(Model model, @Valid @ModelAttribute("entry") PhoneEntry entry, BindingResult result) {

        System.out.println("Username: " + entry.getUsername());
        if (result.hasErrors()){
            System.out.println("errors");
            System.out.println(result);
            return "entry";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        entry.setUsername(username);
        entryService.save(entry);

        return "entry-success";
    }

    @RequestMapping(value = "/edit")
    public String updateEntry(Model model, @RequestParam(value = "id", required = true) Long id) {

        System.out.println("Id=" + id);

        PhoneEntry entry = entryService.getPhoneEntry(id);
        model.addAttribute("entry", entry);
        return "entry-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEntry(Model model, @Valid @ModelAttribute("entry") PhoneEntry entry, BindingResult result) {

        System.out.println("Edit Username: " + entry.getUsername());
        if (result.hasErrors()){
            System.out.println("Edit errors");
            System.out.println(result);
            return "entry-edit";
        }
        System.out.println("Edit id=" + entry.getId());
        System.out.println("Edit username=" + entry.getUsername());
        entryService.update(entry);

        return "forward:/?mes=Edited successfull!";
    }

    @RequestMapping(value = "/delete")
    public String deleteEntry(Model model, @RequestParam(value = "id", required = true) Long id) {

        System.out.println("Delete Id=" + id);
        String message = null;
        boolean isDeleted = entryService.delete(id);
        System.out.println("Is deleted " + isDeleted);
        if (isDeleted){
            message = "Success deleted entry";
        }else{
            message= "Can't delete";
        }
        return "forward:/?mes=" + message;
    }


}
