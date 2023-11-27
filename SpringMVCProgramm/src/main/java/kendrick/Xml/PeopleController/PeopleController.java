package kendrick.Xml.PeopleController;

import jakarta.validation.Valid;
import kendrick.Xml.DAO.PersonDAO;
import kendrick.Xml.Person.Person;
import kendrick.Xml.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/people")
@Controller
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    //    private final PeopleService peopleService;
//
//    @Autowired
//    public PeopleController(PeopleService peopleService) {
//        this.peopleService = peopleService;
//    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
//        model.addAttribute("people",peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.show(id));
//        model.addAttribute("person",peopleService.findById(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")  Person person){
//        model.addAttribute("person",new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        return "people/new";
    }
        personDAO.save(person);
//        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model , @PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));
        //model.addAttribute("person",peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult ,@PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        //peopleService.update(id,person);
        personDAO.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        //peopleService.delete(id);
        return "redirect:/people";
    }
}
