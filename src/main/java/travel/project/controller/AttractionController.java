package travel.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import travel.project.domain.Attraction;
import travel.project.domain.Destination;
import travel.project.service.Detination.DestinationService;
import travel.project.service.PackService;

import java.util.List;

@Slf4j
@RequestMapping("/attraction")
@Controller
@RequiredArgsConstructor
public class AttractionController {

    private final PackService packService;
    private final DestinationService destinationService;

    @GetMapping
    public String attractions(Model model){
        List<Destination> allDestination = packService.findAllDestination();
        model.addAttribute("destination", allDestination);
        return "admin/attractions";
    }
    @GetMapping("/{attractionId}")
    public String attractionForm(@PathVariable long attractionId, Model model){
       Attraction attraction = packService.findAttractionById(attractionId);
       model.addAttribute("attraction",attraction);
        return "pack/attractionForm";
    }

    @PutMapping("/{attractionId}")
    public String updateAttraction(@ModelAttribute Attraction attraction, @PathVariable long attractionId){
        packService.updateAttraction(attraction);
        log.info("타긴 하잖아");
        return "redirect:/";
    }
    @GetMapping("/destination/{selectedDestination}")
    public String attractionList(@PathVariable("selectedDestination") long destinationId, Model model){
        List<Attraction> attractionList = destinationService.findAttractionById(destinationId);
        model.addAttribute("attractionList", attractionList);
        return "pack/attractionList";
    }
    @DeleteMapping("/{attractionId}")
    public String deleteAttraction(@PathVariable long attractionId){
        System.out.println("타냐? ");
        packService.deleteAttractionById(attractionId);
        return "redirect:/";
    }
}
