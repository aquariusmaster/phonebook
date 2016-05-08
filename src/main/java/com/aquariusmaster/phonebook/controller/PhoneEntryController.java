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
        return "entries";
    }

    @RequestMapping("/entry")
    public String addEntry(Model model, @RequestParam(value = "action", required = false) String action,
                           @RequestParam(value = "id", required = false) Long id) {

        if(action == null || action.equals("new")){
            PhoneEntry newEntry = new PhoneEntry();
            model.addAttribute("entry", newEntry);
            return "entry";
        }
        if (action != null && action.equals("edit") && id != null){
            PhoneEntry savedEntry = entryService.getPhoneEntry(id);
            model.addAttribute("entry", savedEntry);
            return "entry";
        }
        return "entry";
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String createEntry(Model model, @Valid @ModelAttribute("entry") PhoneEntry entry, BindingResult result) {

        if (result.hasErrors()){
            System.out.println("errors");
            System.out.println(result);
            return "entry";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        entry.setUsername(username);
        entryService.saveOrUpdate(entry);

        return "forward:/?mes=Entry saved.";
    }

    @RequestMapping(value = "/delete")
    public String deleteEntry(Model model, @RequestParam(value = "id", required = true) Long id) {

        String message = null;
        boolean isDeleted = entryService.delete(id);
        if (isDeleted){
            message = "Success deleted entry";
        }else{
            message= "Can't delete";
        }
        return "forward:/?mes=Entry deleted";
    }

    @RequestMapping("/search")
    public String addEntry(Model model, @RequestParam(value = "q", required = false) String q) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<PhoneEntry> entries = entryService.searchPhoneEntry(q, username);
        model.addAttribute("entries", entries);
        model.addAttribute("message", "search results: " + entries.size());
        return "entries";
    }

}
